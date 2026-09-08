package com.vidalink.healthcare.shared.infrastructure.persistence.storage.supabase;

import com.vidalink.healthcare.shared.application.port.out.FileStorage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.io.InputStream;

@Component
@ConditionalOnProperty(
        name = "storage.provider",
        havingValue = "supabase"
)
public class SupabaseFileStorage implements FileStorage {

    private final RestClient supabaseRestClient;
    private final String serviceRoleKey;
    private final String bucket;
    private final String supabaseUrl;

    public SupabaseFileStorage(
            RestClient supabaseRestClient,
            @Value("${supabase.service-role-key}") String serviceRoleKey,
            @Value("${supabase.bucket}") String bucket,
            @Value("${supabase.url}") String supabaseUrl
    ) {
        this.supabaseRestClient = supabaseRestClient;
        this.serviceRoleKey = serviceRoleKey;
        this.bucket = bucket;
        this.supabaseUrl = supabaseUrl;
    }

    @Override
    public String upload(
            InputStream inputStream,
            String path,
            String contentType
    ) {
        try {
            byte[] content = inputStream.readAllBytes();

            supabaseRestClient.post()
                    .uri("/storage/v1/object/{bucket}/{path}", bucket, path)
                    .header("Authorization", "Bearer " + serviceRoleKey)
                    .header("apikey", serviceRoleKey)
                    .header("x-upsert", "true")
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(content)
                    .retrieve()
                    .toBodilessEntity();

            return supabaseUrl
                    + "/storage/v1/object/public/"
                    + bucket
                    + "/"
                    + path;

        } catch (Exception exception) {
            throw new RuntimeException(
                    "Error uploading file to Supabase",
                    exception
            );
        }
    }

    @Override
    public InputStream download(String path) {
        throw new UnsupportedOperationException(
                "Supabase images are served through their public URL"
        );
    }

    @Override
    public void delete(String path) {
        // todo: later implements
    }
}