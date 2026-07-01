# Refactoring Plan

## ✅ What Was Working

The first version of VidaLink successfully implemented the application's core features.

The following functionalities are currently working:

* User authentication with email and password.
* Password hashing using SHA-256.
* Authentication and authorization using JWT.
* Administrator authentication using institutional accounts.
* Reward management (CRUD operations).
* Reward redemption.
* Blood donation proof submission.
* Profile editing.

Most of the business rules developed in Version 1 are functional and will serve as the foundation for Version 2.

---

## ⚠️ What Needed Improvement

Although the application is functional, several technical issues were identified during the analysis of Version 1.

Some of the main problems include:

* High coupling between components.
* Low maintainability in some modules.
* Legacy code that is no longer used.
* Unused database tables.
* Inconsistent project structure.
* Docker configuration that requires improvements.
* Media management (profile pictures and uploaded images) requires refactoring.
* Limited documentation and architectural decisions.

---

## 🚀 What Will Be Improved

Version 2 focuses on applying Software Engineering best practices instead of simply rewriting the code.

The main objectives are:

* Improve the project architecture.
* Apply SOLID principles.
* Improve code organization and readability.
* Increase maintainability.
* Introduce better documentation.
* Add UML diagrams.
* Adopt ADRs (Architectural Decision Records).
* Improve Docker configuration.
* Prepare the project for future scalability.

---

## 🗑️ What Will Be Removed

The following elements will be removed during the refactoring process:

* Unused code.
* Deprecated business logic.
* Unused database tables.
* Redundant methods.
* Unnecessary dependencies.
* Legacy implementations that no longer fit the new architecture.

## 📊 Refactoring Progress

- [x] Recover Version 1
- [x] Analyze the existing architecture
- [ ] Redesign the domain model
- [ ] Create UML diagrams
- [ ] Implement the new backend
- [ ] Implement the new frontend
- [ ] Add automated tests
- [ ] Dockerize the application