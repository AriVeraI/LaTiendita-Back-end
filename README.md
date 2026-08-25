# 🛍️ La tiendita de la yeya - Backend API

> Sistema de comercio electrónico (E-commerce) desarrollado como API REST robusta y escalable, utilizando **Java** y **Spring Boot**.

## 🚀 Sobre el Proyecto
**La tiendita de la yeya** es una plataforma backend diseñada para gestionar las operaciones esenciales de una tienda online, incluyendo el control de usuarios, 
catálogo de productos con variantes y categorías, carritos de compras y procesamiento de pedidos transaccionales. 
El sistema implementa una arquitectura limpia basada en capas (Model, Repository, Service, Controller, DTO) para garantizar mantenibilidad y separación de responsabilidades.

---

## 🛠️ Stack Tecnológico y Herramientas

* **Lenguaje:** Java 17
* **Framework:** Spring Boot (Spring Data JPA, Spring Validation)
* **Base de Datos:** MySQL / MySQL Workbench (Diseño EER normalizado)
* **Control de Versiones:** Git & GitHub
* **Gestor de Dependencias:** Gradle
* **Pruebas de API:** Postman

---

## 📁 Arquitectura del Proyecto

El código fuente se encuentra organizado bajo el paquete principal `com.tienditayeya.tyback_end`:

```text
com.tienditayeya.tyback_end/
│
├── 📂 config/          # Configuraciones globales de la aplicación
├── 📂 controller/      # Controladores REST (Endpoints de la API)
├── 📂 dto/             # Objetos de Transferencia de Datos (Data Transfer Objects)
├── 📂 model/           # Entidades JPA mapeadas a la Base de Datos (@Entity)
├── 📂 repository/      # Interfaces de persistencia de Spring Data JPA
└── 📂 service/         # Lógica de negocio de la aplicación
