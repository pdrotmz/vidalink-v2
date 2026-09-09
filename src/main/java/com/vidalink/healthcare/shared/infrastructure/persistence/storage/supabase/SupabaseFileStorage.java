package com.vidalink.healthcare.shared.infrastructure.persistence.storage.supabase;

import com.vidalink.healthcare.shared.application.port.out.FileStorage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.io.ByteArrayInputStream;
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

            String[] pathSegments = path.split("/");

            supabaseRestClient.post()
                    .uri(uriBuilder -> {
                        uriBuilder
                                .path("/storage/v1/object")
                                .pathSegment(bucket);

                        for (String segment : pathSegments) {
                            uriBuilder.pathSegment(segment);
                        }

                        return uriBuilder.build();
                    })
                    .header("Authorization", "Bearer " + serviceRoleKey)
                    .header("apikey", serviceRoleKey)
                    .header("x-upsert", "true")
                    .contentType(
                            contentType != null
                                    ? MediaType.parseMediaType(contentType)
                                    : MediaType.APPLICATION_OCTET_STREAM
                    )
                    .body(content)
                    .retrieve()
                    .toBodilessEntity();

            return path;

        } catch (Exception exception) {
            throw new RuntimeException(
                    "Error uploading file to Supabase",
                    exception
            );
        }
    }

    @Override
    public InputStream download(String path) {
        try {
            byte[] content = supabaseRestClient.get()
                    .uri(uriBuilder -> {
                        uriBuilder
                                .path("/storage/v1/object/authenticated")
                                .pathSegment(bucket);

                        for (String segment : path.split("/")) {
                            uriBuilder.pathSegment(segment);
                        }

                        return uriBuilder.build();
                    })
                    .header("Authorization", "Bearer " + serviceRoleKey)
                    .header("apikey", serviceRoleKey)
                    .retrieve()
                    .body(byte[].class);

            if (content == null) {
                throw new RuntimeException(
                        "Empty response while downloading file from Supabase"
                );
            }

            return new ByteArrayInputStream(content);

        } catch (Exception exception) {
            throw new RuntimeException(
                    "Error downloading file from Supabase",
                    exception
            );
        }
    }

    @Override
    public void delete(String path) {
        // todo: later implements
    }
}