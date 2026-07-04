# ADR-0004 - Adopt Angular

**Date:** 2026-07-04  
**Status:** Accepted  
**Author:** Pedro Tomáz

---

## Context

The first version of VidaLink was developed using React.

For Version 2, the project aims to improve maintainability, code organization, and long-term scalability while providing a more structured frontend architecture.

---

## Decision

The project will adopt **Angular** as the frontend framework.

---

## Rationale

Angular was chosen because it:

- Is built with TypeScript by default.
- Encourages object-oriented programming principles.
- Provides built-in Dependency Injection.
- Offers a modular architecture suitable for medium and large applications.
- Includes routing, forms, HTTP client, and state management support out of the box.
- Integrates well with enterprise application development.

---

## Consequences

### Positive

- Better code organization.
- Strong typing with TypeScript.
- Built-in Dependency Injection.
- Excellent tooling provided by Angular CLI.
- Easier maintenance as the application grows.

### Negative

- Steeper learning curve compared to React.
- Larger initial bundle size.
- More opinionated framework, offering less flexibility in project structure.