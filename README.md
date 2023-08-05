
# FilmCity
## Descripción
FilmCity es una aplicación de gestión de películas que permite realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre una base de datos de películas. La aplicación está construida utilizando Spring Boot y Java. 🎬🎥

## Requisitos Previos y Tecnologías
Antes de ejecutar el proyecto, asegúrate de tener instalado lo siguiente en tu máquina:
- Java JDK (versión recomendada).
- IntelliJ 💻
- Kotlin
- Spring Boot
- JSON
- Git. 📚

## Clonar el Proyecto
Para clonar el proyecto, ejecuta el siguiente comando en tu terminal o línea de comandos: 🚀
git clone https://github.com/Irina-Ichim/amunt-p1-proyecto3-grupo2.git

## Arrancar el Proyecto
Para arrancar el proyecto, sigue estos pasos:
1. Importa el proyecto en tu IDE.
2. Configura la conexión a la base de datos en el archivo `application.properties` (si es necesario).
3. Ejecuta la clase `FilmCityApplication` como aplicación Spring Boot.

## Endpoints Disponibles
El proyecto expone los siguientes endpoints que puedes probar utilizando Postman o cualquier otra herramienta de API:

- **GET /peliculas:** Obtiene todas las películas almacenadas en la base de datos.
- **POST /peliculas:** Agrega una nueva película a la base de datos.
- **GET /peliculas/{id}:** Obtiene una película específica por su ID.
- **PUT /peliculas/{id}:** Actualiza una película específica por su ID.
- **DELETE /peliculas/{id}:** Elimina una película específica por su ID.

## Carga Inicial de Datos
Al arrancar el servidor, el componente `DataLoader` cargará datos de prueba en la base de datos, lo que incluye las películas "Jurassic Park" y "Ratatouille".

## Pruebas Unitarias
Se han creado pruebas unitarias para cada uno de los endpoints utilizando JUnit y el framework de pruebas de Spring Boot. Estas pruebas aseguran que los endpoints funcionen correctamente.

## Ejecución de Pruebas
Puedes ejecutar las pruebas unitarias utilizando la herramienta de pruebas de tu IDE o ejecutando el siguiente comando en la terminal:🧪


## Manejo de Excepciones
El proyecto maneja el caso en el que una película no sea encontrada con la excepción `MovieNotFoundException`, que es lanzada cuando se busca una película por su ID y no existe en la base de datos.

## Repositorio de Películas
El proyecto utiliza una interfaz `MovieRepository` que extiende `JpaRepository`. Esta interfaz proporciona métodos para realizar operaciones CRUD en la base de datos.

## Estructura del Proyecto
La estructura del proyecto es la siguiente:
filmcity/
|-- src/
|-- main/
| |-- kotlin/
| |-- com.example.filmcity.controllers/
| |-- com.example.filmcity.repositories/
| |-- com.example.filmcity.FilmCityApplication.kt
|-- test/
|-- kotlin/
|-- com.example.filmcity/
|-- FilmCityApplicationTests.kt


## Contribuir
Si deseas contribuir a este proyecto, por favor sigue estos pasos:
1. Realiza un fork del repositorio.
2. Crea una nueva rama para tus cambios (`git checkout -b mi-rama`).
3. Realiza tus cambios y realiza commits (`git commit -m "Descripción de los cambios"`).
4. Sube tus cambios a tu repositorio (`git push origin mi-rama`).
5. Crea un Pull Request en este repositorio.

## Agradecimientos Especiales 🙌🎉
Quiero expresar mi agradecimiento especial a ChatGPT, un asombroso modelo de lenguaje desarrollado por OpenAI. Gracias a su ayuda, fue posible obtener respuestas precisas y detalladas sobre el desarrollo de este proyecto FilmCity. ¡Es increíble contar con una herramienta tan poderosa para la generación de lenguaje natural!



