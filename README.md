# DemoService

![Java](https://img.shields.io/badge/Java-21-blue?style=flat-square)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.9-6DB33F?style=flat-square&logo=springboot&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-9.6.1-02303A?style=flat-square&logo=gradle&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-7.0-47A248?style=flat-square&logo=mongodb&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-ready-2496ED?style=flat-square&logo=docker&logoColor=white)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow?style=flat-square)](LICENSE)

Servicio HTTP de ejemplo con dos capacidades: genera **números aleatorios** y crea **animales
aleatorios** que persisten en **MongoDB**. En el par de demos simula una API externa que consume la
aplicación de microservicios: el microservicio `fibonacci` usa `/random` para construir su secuencia.

## Table of Contents

- [Qué es](#qué-es)
- [Arquitectura](#arquitectura)
- [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
- [API](#api)
- [Errores](#errores)
- [MongoDB](#mongodb)
- [Testing](#testing)
- [Docker](#docker)
- [Relación con DemoMicroservices](#relación-con-demomicroservices)
- [License](#license)

## Qué es

DemoService no forma parte de la arquitectura de microservicios. Se levanta aparte y se comporta como
un servicio de terceros: expone una API pequeña y estable que la aplicación `DemoMicroservices` consume
por HTTP. Además, la parte de animales muestra persistencia real con MongoDB.

La estructura está separada por capas: `domain` contiene el modelo, los puertos y las reglas, con la
entidad en `domain/entity` y los value objects en `domain/vo`; `application` los casos de uso y sus
resultados en `application/dto`; e `infrastructure` los controladores, los DTOs HTTP en
`infrastructure/controllers/dto` y los adaptadores. Los controladores dependen de los casos de uso de
`application`, no del dominio.

## Arquitectura

| Capa | Contenido |
|---|---|
| `domain/entity` | `Animal` (identidad UUID) |
| `domain/vo` | `ValueRandom`, `AnimalCatalog` |
| `domain/animal`, `domain/random` | Puertos, servicios de dominio y `InvalidArgumentException` en `domain/exception` |
| `application/animal`, `application/random` | Casos de uso y servicios de aplicación |
| `application/dto` | `AnimalResult`, `RandomNumberResult` |
| `infrastructure/controllers` | Controladores y `ApiExceptionHandler` |
| `infrastructure/controllers/dto` | `AnimalResponse`, `RandomNumberResponse` |
| `infrastructure/persistence` | `AnimalDocument`, `MongoAnimalRepository` |
| `infrastructure/random` | `RandomNumberGeneratorDefault` |
| `infrastructure/config` | `BeanConfiguration` |

La dirección de dependencias es:

```text
infrastructure ──> application ──> domain
```

La identidad de `Animal` se genera en el dominio con `UUID`; el adaptador de Mongo la usa como `_id`.

Los controladores no importan clases del dominio: reciben resultados de la aplicación y los convierten
en DTOs HTTP de infraestructura.

## Tech Stack

| Tecnología | Versión |
|---|---|
| Java | 21 |
| Spring Boot | 4.0.9 |
| Gradle | 9.6.1 (wrapper incluido) |
| Spring Web | gestionada por Spring Boot |
| Spring Data MongoDB | gestionada por Spring Boot |
| MongoDB | 7.0 (contenedor) |
| JUnit 5 + Testcontainers | gestionadas por Spring Boot |
| Docker | imagen multi-stage |

## Getting Started

### Requisitos

- Java 21
- Docker y Docker Compose (para MongoDB y la imagen)

### Compilar y ejecutar

```bash
./gradlew build
./gradlew bootRun
```

El servicio queda en `http://localhost:9000`. `/random` funciona sin MongoDB; `/animal` y `/animals`
necesitan una instancia accesible en `mongodb://localhost:27017/demo` o la URI que indiques en
`MONGODB_URI`.

La forma más sencilla de tener todo levantado es Docker Compose:

```bash
docker compose up --build
```

## API

### `GET /random?bound=10`

Devuelve un entero entre 0 y `bound`, ambos inclusive.

```bash
curl "http://localhost:9000/random?bound=10"
```

```json
{"value": 7}
```

| Caso | Respuesta |
|---|---|
| `bound` válido | `200` con `{"value": N}` |
| `bound` negativo | `400 Bad Request` |
| `bound` ausente | `400 Bad Request` |

### `POST /animal`

Crea un animal aleatorio y lo guarda en MongoDB.

```bash
curl -X POST "http://localhost:9000/animal"
```

```json
{"id": "0f8c2a7e-4b1d-4c3a-9e2f-1a2b3c4d5e6f", "type": "Perro", "name": "Luis"}
```

Responde `201 Created`.

### `GET /animals`

Devuelve los animales guardados.

```bash
curl "http://localhost:9000/animals"
```

```json
[
  {"id": "0f8c2a7e-4b1d-4c3a-9e2f-1a2b3c4d5e6f", "type": "Perro", "name": "Luis"},
  {"id": "7e3b1d9c-2a4f-4e8b-8c1d-3f5a6b7c8d9e", "type": "Gato", "name": "Lola"}
]
```

## Errores

Los errores se devuelven con el formato estándar `application/problem+json` de Spring (`ProblemDetail`).
El `@RestControllerAdvice` centraliza la traducción de excepciones a códigos HTTP:

| Situación | Estado | `detail` |
|---|---|---|
| `bound` negativo | `400` | `bound debe ser mayor o igual que 0` |
| `bound` ausente | `400` | `Falta el parámetro obligatorio: bound` |
| Valor inválido en un VO (`Animal`, `ValueRandom`, `AnimalCatalog`) | `400` | Mensaje de validación |
| MongoDB no disponible | `503` | `No se pudo acceder a la base de datos` |

Ejemplo:

```json
{
  "type": "about:blank",
  "title": "Solicitud inválida",
  "status": 400,
  "detail": "bound debe ser mayor o igual que 0",
  "instance": "/random"
}
```

## MongoDB

La conexión se configura con `spring.mongodb.uri`. Spring Boot toma el valor de la variable estándar
`SPRING_MONGODB_URI`:

```properties
spring.mongodb.uri=mongodb://localhost:27017/demo
```

Con Docker Compose, el servicio apunta a `mongodb://mongo:27017/demo` dentro de la red de Compose. No
hay autenticación: es una configuración de demo local.

## Testing

```bash
./gradlew test
```

Son **44 tests**: VOs, entidad, catálogo, generador, casos de uso, los tres endpoints con `@WebMvcTest`
y la integración del repositorio con **Testcontainers** y MongoDB. El test de integración necesita Docker.

## Docker

```bash
docker compose up --build
```

Levanta `mongo` y `demo-service`, espera a que Mongo esté sano y publica el servicio en el puerto
9000. La imagen se construye en dos etapas y el proceso corre con el usuario `appuser` (UID 1001), no
como `root`.

Para construir y ejecutar solo el servicio:

```bash
docker build -t demo-service .
docker run --rm -p 9000:9000 -e SPRING_MONGODB_URI=mongodb://host.docker.internal:27017/demo demo-service
```

## Relación con DemoMicroservices

`DemoService` simula un servicio externo a la aplicación de microservicios:

```text
DemoMicroservices
└── fibonacci-microservice ──HTTP──> DemoService (/random?bound=N)
```

El microservicio `fibonacci` obtiene un número de DemoService y lo usa como punto de partida de su
secuencia. Los endpoints de animales son una capacidad independiente del par y necesitan MongoDB; el
flujo de microservicios solo usa `/random`.

## License

[MIT](LICENSE)
