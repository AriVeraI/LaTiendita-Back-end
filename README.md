# 🛍️ La Tiendita de la Yeya — Backend

Backend de la plataforma e-commerce **La Tiendita de la Yeya**, desarrollado como parte de un proyecto colaborativo de desarrollo Full Stack.

Este repositorio contiene la implementación del Backend, la API REST y la estructura de persistencia necesaria para gestionar la información de la tienda.

El proyecto fue desarrollado utilizando **Java y Spring Boot**, con una base de datos relacional diseñada en **SQL** y preparada para trabajar con **MariaDB** durante el despliegue.

---

## 📌 Descripción

El Backend de La Tiendita de la Yeya proporciona los servicios necesarios para gestionar la información y operaciones principales de la plataforma e-commerce.

La aplicación permite establecer la comunicación entre el Frontend y la base de datos mediante una **API REST** desarrollada con Spring Boot.

Entre las principales operaciones contempladas se encuentran:

- Gestión de productos.
- Gestión de categorías.
- Gestión de variantes.
- Gestión de imágenes de productos.
- Gestión de usuarios.
- Gestión de roles.
- Gestión de pedidos.
- Gestión de detalles de pedidos.
- Gestión de pagos.
- Gestión de envíos.

---

# 🏗️ Arquitectura

El Backend utiliza una arquitectura por capas para separar responsabilidades y mantener una estructura organizada y mantenible.

Client / Frontend
       ↓
   REST API
       ↓
  Controller
       ↓
    Service
       ↓
   Repository
       ↓
     Model
       ↓
    MariaDB

### Controller

Contiene los controladores responsables de recibir las solicitudes HTTP y exponer los endpoints de la API REST.

### Service

Contiene la lógica de negocio y coordina las operaciones realizadas por la aplicación.

### Repository

Gestiona el acceso a los datos mediante Spring Data JPA.

### Model

Representa las entidades utilizadas por la aplicación y sus relaciones con las tablas de la base de datos.

### DTO

Se utilizan Data Transfer Objects para controlar la información que entra y sale de determinados endpoints.

---

# 🛠️ Tecnologías utilizadas

## Backend

* **Java**
* **Spring Boot**
* **Spring Data JPA**
* **Hibernate**
* **API REST**
* **DTO**
* **Maven/Gradle**

## Base de datos

* **SQL**
* **MariaDB**

La base de datos fue diseñada utilizando SQL y se utilizará **MariaDB para el despliegue** de la aplicación.

## Herramientas

* **IntelliJ IDEA**
* **Git**
* **GitHub**
* **Jira**
* **Postman**

---

# 🗄️ Base de datos

La aplicación utiliza una base de datos relacional diseñada específicamente para las necesidades del e-commerce.

Entre las principales entidades contempladas se encuentran:

Usuarios
Roles
Productos
Categorías
Variantes
Imágenes de Productos
Pedidos
Detalles de Pedido
Pagos
Envíos

Las tablas se encuentran relacionadas mediante claves primarias y foráneas para mantener la integridad de la información.

---

# 🔗 Relación entre productos y variantes

Una de las relaciones implementadas en el Backend corresponde a la asociación entre productos y sus variantes.

La relación se maneja mediante la tabla:

variantes_has_productos

Esta tabla permite asociar diferentes variantes con un producto.

En Java esta relación se representa mediante la entidad:

VariantesHP

La relación utiliza una llave compuesta formada por:

variantes_id_variantes
productos_id_productos

Esto permite que un producto pueda tener diferentes variantes y que cada variante mantenga información propia, como:

* Atributos.
* SKU.
* Stock.

---

# 🌐 API REST

El Backend expone diferentes endpoints para permitir la comunicación con el Frontend.

Por ejemplo, para consultar los productos:

GET /productos

Para consultar un producto específico:

GET /productos/{id}

Para consultar las variantes asociadas a un producto:

GET /api/variantes-productos/producto/{id}

Estos endpoints permiten que el Frontend obtenga información directamente desde el Backend.

---

# 🔄 Flujo de información

La comunicación entre las diferentes partes del sistema sigue el siguiente flujo:

Frontend
    ↓
HTTP Request
    ↓
Controller
    ↓
Service
    ↓
Repository
    ↓
JPA / Hibernate
    ↓
MariaDB


La respuesta realiza el recorrido inverso:

MariaDB
    ↓
JPA / Hibernate
    ↓
Repository
    ↓
Service
    ↓
Controller
    ↓
JSON Response
    ↓
Frontend


De esta manera, el Frontend puede consumir la información proporcionada por la API REST sin acceder directamente a la base de datos.

---

# 📦 Principales módulos

El Backend está organizado en diferentes componentes para facilitar el mantenimiento y evolución del proyecto.

controller/
dto/
model/
repository/
service/

### Controller

Contiene los endpoints de la API.

### DTO

Contiene los objetos utilizados para transportar información.

### Model

Contiene las entidades de la aplicación.

### Repository

Contiene las interfaces utilizadas para acceder a la base de datos.

### Service

Contiene la lógica de negocio.

---

# 🧪 Pruebas de API

Durante el desarrollo se utilizó **Postman** para probar los endpoints del Backend.

Las pruebas permitieron verificar operaciones como:

* Consulta de productos.
* Creación de productos.
* Consulta de información.
* Comunicación con la base de datos.
* Consulta de variantes.
* Validación de respuestas HTTP.

---

# 🗃️ Persistencia de datos

La persistencia se implementó utilizando **Spring Data JPA** y **Hibernate**.

Esto permite trabajar con las entidades Java y mapearlas con las tablas correspondientes de la base de datos.

La estructura permite realizar operaciones CRUD sobre las diferentes entidades mediante repositories y services.

---

# 🚀 Despliegue

Para el despliegue del proyecto se utilizará **MariaDB** como sistema gestor de base de datos.

La configuración de conexión se encuentra en:

src/main/resources/application.properties

Ejemplo de configuración:

spring.datasource.url=jdbc:mariadb://localhost:3306/nombre_base_datos
spring.datasource.username=usuario
spring.datasource.password=contraseña

spring.jpa.hibernate.ddl-auto=update


> Los datos reales de conexión no deben almacenarse directamente en el repositorio.

---

# 💻 Ejecución local

## Requisitos

Para ejecutar el Backend localmente se necesita:

* Java JDK
* IntelliJ IDEA
* MariaDB
* Git

---

## 1. Clonar el repositorio

git clone URL_DEL_REPOSITORIO

## 2. Abrir el proyecto

Abrir el proyecto desde **IntelliJ IDEA**.

## 3. Configurar la base de datos

Crear la base de datos en MariaDB y configurar las credenciales correspondientes en:

application.properties

## 4. Ejecutar la aplicación

Ejecutar la clase principal de Spring Boot desde IntelliJ IDEA.

Una vez iniciada la aplicación, la API estará disponible en:

http://localhost:8080
---

# 🔐 Configuración

Las credenciales y configuraciones sensibles deben manejarse mediante variables de entorno o mecanismos seguros de configuración.

No se deben subir al repositorio:

Contraseñas
Tokens
Credenciales de producción
Claves privadas

---

# 👥 Desarrollo colaborativo

El Backend fue desarrollado por un equipo de 9 desarrolladores bajo una metodología ágil basada en **Scrum**.

Para la organización y colaboración utilizamos:

* Git
* GitHub
* Jira
* IntelliJ IDEA
* Postman

El trabajo se realizó mediante ramas independientes para desarrollar funcionalidades y posteriormente integrar los cambios al proyecto principal.

---

# 📚 Aprendizajes

El desarrollo del Backend permitió aplicar conocimientos relacionados con:

* Programación orientada a objetos con Java.
* Desarrollo de APIs REST.
* Spring Boot.
* Arquitectura por capas.
* Spring Data JPA.
* Hibernate.
* DTOs.
* Relaciones entre entidades.
* Operaciones CRUD.
* Diseño de bases de datos relacionales.
* SQL.
* Integración con MariaDB.
* Pruebas de endpoints con Postman.
* Control de versiones con Git.
* Trabajo colaborativo mediante GitHub.
* Resolución de conflictos entre ramas.
* Metodología Scrum.

---

# 📈 Resultado

El Backend constituye la capa de servicios y persistencia de **La Tiendita de la Yeya**, proporcionando una API REST que permite al Frontend consultar y gestionar la información necesaria para el funcionamiento del e-commerce.

La estructura basada en **Spring Boot, arquitectura por capas, JPA/Hibernate y MariaDB** permite mantener una separación clara de responsabilidades y facilita la evolución del proyecto.

---

## 👨‍💻 Equipo

**Proyecto desarrollado por el equipo Los Java DavaDu.**

* Ariadna Jazmín Vera Iglesias
* Brandon Essaw Cortez Beltrán
* Cristian Giovany Rodriguez Rosales
* Ernesto Nava Hernandez
* Irán Daniela Gutiérrez Salazar
* Israel Josue Martinez Ruiz
* Joyce Martinez Zubieta
* Noe Santiago Lopez Damian
* Yazmin Aurora Silva Olivares

---

## 💎 La Tiendita de la Yeya

**Backend — Proyecto Full Stack Java**

> Java · Spring Boot · REST API · JPA · Hibernate · SQL · MariaDB

