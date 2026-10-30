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
- [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
- [API](#api)
- [MongoDB](#mongodb)
- [Testing](#testing)
- [Docker](#docker)
- [Relación con DemoMicroservices](#relación-con-demomicroservices)
- [License](#license)

## Qué es

DemoService no forma parte de la arquitectura de microservicios. Se levanta aparte y se comporta como
un servicio de terceros: expone una API pequeña y estable que la aplicación `DemoMicroservices` consume
por HTTP. Además, la parte de animales muestra persistencia real con MongoDB.

La estructura sigue el mismo estilo hexagonal del dominio: `domain` contiene el modelo, los puertos y
los casos de uso; `infrastructure` contiene los controladores y el adaptador de MongoDB.

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
{"type": "Perro", "name": "Luis"}
```

Responde `201 Created`.

### `GET /animals`

Devuelve los animales guardados.

```bash
curl "http://localhost:9000/animals"
```

```json
[
  {"type": "Perro", "name": "Luis"},
  {"type": "Gato", "name": "Lola"}
]
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

Son **29 tests**: generador, dominio, los tres endpoints con `@WebMvcTest` y un test de integración
del repositorio con **Testcontainers** y MongoDB. El test de integración necesita Docker.

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
