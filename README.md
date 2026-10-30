# DemoService

Servicio HTTP mínimo que devuelve un número aleatorio. Forma parte del par de demos junto a
[DemoMicroservices](https://github.com/cedaniel200/DemoMicroservices), donde el microservicio
`fibonacci` lo consume para construir su secuencia.

## Requisitos

- Java 21
- Gradle wrapper incluido (9.6.1)

## Ejecutar

```bash
./gradlew bootRun
```

El servicio queda en `http://localhost:9000`.

## API

### `GET /random?bound=10`

Devuelve un entero entre 0 y `bound`, ambos inclusive.

```bash
curl "http://localhost:9000/random?bound=10"
```

```json
{"value": 7}
```

Si `bound` falta o es negativo, responde `400 Bad Request`.

## Tests

```bash
./gradlew test
```

Incluye pruebas del generador, del controlador con `@WebMvcTest` y de carga del contexto.

## Docker

```bash
docker build -t demo-service .
docker run --rm -p 9000:9000 demo-service
```

## Stack

- Spring Boot 4.0.9
- Java 21
- Gradle 9.6.1
