# StubService
Servicio que devuelve valores predefinidos

## API 
**URL Base**: http://localhost:9000

### Paths

#### Random Numbers

* [GET] **/random**

Devuelve un animal aleatoreo entre 0 y el numero pasado en el parametro **bound**

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

#### Random Animals

* [POST] **/animal**

Crea un animal de forma aleatorea y lo devuelve. El animal creado es almacenado en la base de datos mongo previamente configurada

**Request**

    /animal

Ejemplo

    http://localhost:9000/animal

**Response**

    {
        "type": "aType",
        "name": "aName"
    }

Ejemplo

    {
        "type": "Grillo",
        "name": "Pepe"
    }

* [GET] **/animals**

Devuelve los animales almacenados en la base de datos mongo previamente configurada

**Request**

    /animals

Ejemplo

    http://localhost:9000/animals

**Response**

    [{
        "type": "aType",
        "name": "aName"
    }]

Ejemplo

    [{
        "type": "Grillo",
        "name": "Pepe"
    }]


## Requerimientos

* Se debe tener instalado docker.

* Java JDK 1.8 
* Gradle versión 7.1.

## Para correr el proyecto
Ejecutar el comando:

    docker compose up

o
    
    docker-compose up

Si no quiere usar docker debe tener corriendo y configurado mongo en su maquina local en el puerto **27017**
y debe cambiar en la línea 30 la palabra **mongo-db** por **localhost** en la clase **MongoDbConfiguration** del paquete **config**

### Si tiene alguna pregunta, puede escribirme a cdanielmg200@gmail.com

