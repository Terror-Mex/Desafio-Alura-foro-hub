# ForoHub - API REST con Spring Boot

Este es un proyecto de una API REST para gestión de tópicos en un foro, desarrollado como práctica usando **Spring Boot**, **Spring Security** y **JWT**.

## 🛠️ Tecnologías usadas

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Security
- JWT (JSON Web Token)
- PostgreSQL
- Swagger (Springdoc OpenAPI 2.8.9)

## 📦 Funcionalidades implementadas

- CRUD de tópicos (`/topics`)
- Autenticación con JWT (`/login`)
- Seguridad con filtros personalizados
- Documentación interactiva con Swagger UI (`/swagger-ui.html`)
- Acceso protegido con token Bearer

## 🔐 Seguridad

- Solo el login y la documentación Swagger son públicos.
- El resto de los endpoints requieren token JWT válido en el header:
  
