# Imagen de la cual se va a basar
FROM openjdk:8-jdk
#FROM gradle:7.1

# RUN nos permite ejecutar comandos dentro de nuestro contenedor
# Creamos dentro del contenedor la carpeta donde estará nuestro código fuente
RUN mkdir -p /home/StubService

# COPY path-host path-container
# Copiamos desde una ubicación del hots a una ubicación dentro del contenedor
#  el . indica que debe copiar lo que este en la carpeta actual
COPY . /home/StubService

#Indicamos la ruta de trabajo
WORKDIR /home/StubService

# Exponemos un puerto para que cualquier otro contenedor o el hots anfitrion
# se pueda conectar al contenedor
EXPOSE 9000

# Indicamos el comando que se debe ejecutar para que el servicio y/o aplicación corra
CMD ["./gradlew", "clean", "bootRun"]



