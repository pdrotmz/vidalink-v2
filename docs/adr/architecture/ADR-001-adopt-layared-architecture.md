# ADR-0001 - Adopt Layered Architecture with Package by Feature

**Date:** 2026-07-04  
**Status:** Accepted  
**Author:** Pedro Tomáz

---

## Context

The first version of VidaLink was built using a traditional MVC architecture. Although it was sufficient for the project's initial goals, the codebase became harder to maintain as new features were added.

For Version 2, the objective is to improve maintainability, organization, and scalability while keeping the architecture simple enough for the project's current complexity.

---

## Decision

The project will adopt a **Layered Architecture** combined with a **Package by Feature** organization.

This approach separates responsibilities into logical layers while grouping related components by business feature instead of technical type.

---

## Rationale

This architectural style was chosen because it:

- Improves code organization.
- Encourages high cohesion.
- Reduces coupling between features.
- Makes navigation through the project easier.
- Keeps the project simple without introducing unnecessary complexity.
- Provides a solid foundation for applying SOLID principles and design patterns.

---

## Consequences

### Positive

- Better project organization.
- Higher maintainability.
- Easier to add new features.
- Clear separation of responsibilities.
- Improved readability.
- Better scalability for the current project scope.

### Negative

- Provides less isolation between layers than Clean Architecture.
- If the domain becomes significantly more complex, a future migration to another architectural style may be considered.