# StubService
Servicio que devuelve valores predefinidos

## API 
**URL Base**: http://localhost:9000

### Paths
* **/random**

Devuelve un numero aleatoreo entre 0 y el numero pasado en el parametro **bound**

**Request**

    /random?bound=#

Ejemplo

    http://localhost:9000/random?bound=10

**Response**
    
    {
        "value": #
    }

Ejemplo

    {
        "value": 7
    }

## Requerimientos
Para correr el proyecto se necesita Java JDK 1.8 y Gradle preferiblemente con la versión 7.1.

## Para correr el proyecto
Ejecutar el comando:

    gradlew clean bootRun

