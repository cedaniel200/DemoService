# DemoService

![Java](https://img.shields.io/badge/Java-21-blue?style=flat-square)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.9-6DB33F?style=flat-square&logo=springboot&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-9.6.1-02303A?style=flat-square&logo=gradle&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-ready-2496ED?style=flat-square&logo=docker&logoColor=white)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow?style=flat-square)](LICENSE)

Servicio HTTP mínimo que devuelve un número aleatorio. En el par de demos **simula una API externa**
que consume la aplicación de microservicios: el microservicio `fibonacci` lo llama para obtener el
número con el que construye su secuencia.

## Table of Contents

- [Qué es](#qué-es)
- [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
- [API](#api)
- [Testing](#testing)
- [Docker](#docker)
- [Relación con DemoMicroservices](#relación-con-demomicroservices)
- [License](#license)

## Qué es

DemoService no forma parte de la arquitectura de microservicios. Se levanta aparte y se comporta como
un servicio de terceros: expone un endpoint pequeño y estable que la aplicación `DemoMicroservices`
consume por HTTP. Así el ejemplo muestra cómo un microservicio depende de una API que no controla.

## Tech Stack

| Tecnología | Versión |
|---|---|
| Java | 21 |
| Spring Boot | 4.0.9 |
| Gradle | 9.6.1 (wrapper incluido) |
| Spring Web | gestionada por Spring Boot |
| JUnit 5 | gestionada por Spring Boot |
| Docker | imagen multi-stage |

## Getting Started

### Requisitos

- Java 21
- Docker (opcional, para la imagen)

### Compilar y ejecutar

```bash
./gradlew build
./gradlew bootRun
```

El servicio queda en `http://localhost:9000`.

También puedes empaquetarlo y ejecutar el jar:

```bash
./gradlew bootJar
java -jar build/libs/service-1.0-SNAPSHOT.jar
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

## Testing

```bash
./gradlew test
```

La suite cubre el generador, el controlador con `@WebMvcTest` y la carga del contexto. Son 24 tests
en total.

## Docker

```bash
docker build -t demo-service .
docker run --rm -p 9000:9000 demo-service
```

La imagen se construye en dos etapas y el proceso corre con el usuario `appuser` (UID 1001), no como
`root`.

## Relación con DemoMicroservices

`DemoService` simula un servicio externo a la aplicación de microservicios. El flujo es:

```text
DemoMicroservices
└── fibonacci-microservice ──HTTP──> DemoService (/random?bound=N)
```

El microservicio `fibonacci` obtiene un número de DemoService y lo usa como punto de partida de su
secuencia. Los demás servicios del ejemplo no dependen de DemoService.

## License

[MIT](LICENSE)
