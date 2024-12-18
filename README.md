# Backend - API REST para Aplicación Web de Notas

Este es el backend de la aplicación web de notas, desarrollado con **Java 23**, **Spring Boot**, **PostgreSQL**, y **Docker** para la contenedorización y despliegue en **Render**. La aplicación sigue la arquitectura de capas, separando la lógica de negocio en la capa de servicio (Service Layer).

## Descripción

La API proporciona los endpoints necesarios para gestionar la creación, edición, eliminación y filtrado de notas y etiquetas. La arquitectura está organizada en varias capas:

- **Controller Layer**: Maneja las peticiones HTTP y dirige las solicitudes a los servicios adecuados.
- **Service Layer**: Contiene la lógica de negocio y procesa las solicitudes.
- **Repository Layer**: Accede a la base de datos (PostgreSQL) a través de JPA (Java Persistence API).

## Endpoints

La API tiene los siguientes endpoints:

### Notas

- **GET /api/notes**  
  Devuelve todas las notas almacenadas.

- **GET /api/notes/{id}**  
  Devuelve la nota con el ID especificado.

- **POST /api/notes**  
  Crea una nueva nota.

- **PUT /api/notes/{id}**  
  Actualiza la nota con el ID especificado.

- **DELETE /api/notes/{id}**  
  Elimina la nota con el ID especificado.

### Etiquetas

- **GET /api/tags**  
  Devuelve todas las etiquetas disponibles.

- **GET /api/notes/filter?tag={tag}**  
  Devuelve las notas filtradas por la etiqueta especificada.

## Arquitectura

La aplicación sigue una arquitectura de capas para separar las responsabilidades y mantener el código organizado y fácil de mantener.

1. **Controller Layer**  
   Gestiona las solicitudes HTTP y las direcciona a la capa de servicio. Los controladores reciben las peticiones y devuelven las respuestas.

   Ejemplo:
   ```java
   @RestController
   @RequestMapping("/api/notes")
   public class NoteController {

       @Autowired
       private NoteService noteService;

       @GetMapping
       public List<Note> getAllNotes() {
           return noteService.getAllNotes();
       }

       @PostMapping
       public Note createNote(@RequestBody Note note) {
           return noteService.createNote(note);
       }

       // Otros endpoints...
   }
   ```

2. **Service Layer**  
   Contiene la lógica de negocio. Los servicios procesan las solicitudes y gestionan las interacciones con el repositorio. 

   Ejemplo:
   ```java
   @Service
   public class NoteService {

       @Autowired
       private NoteRepository noteRepository;

       public List<Note> getAllNotes() {
           return noteRepository.findAll();
       }

       public Note createNote(Note note) {
           return noteRepository.save(note);
       }

       // Otros métodos para actualizar, eliminar y filtrar notas
   }
   ```

3. **Repository Layer**  
   Utiliza Spring Data JPA para interactuar con la base de datos PostgreSQL. Aquí se definen los métodos para acceder a los datos.

   Ejemplo:
   ```java
   @Repository
   public interface NoteRepository extends JpaRepository<Note, Long> {
       List<Note> findByTag(String tag);
   }
   ```

## Tecnologías utilizadas

- **Java 23**: Lenguaje de programación utilizado para el backend.
- **Spring Boot**: Framework para construir la API REST.
- **PostgreSQL**: Base de datos relacional para almacenar las notas y etiquetas.
- **Docker**: Contenerización del backend para facilitar el despliegue.
- **Render**: Plataforma para el despliegue de la aplicación.
- **Maven**: Manejador de dependencias y construcción del proyecto.

## Requisitos

- **Java 23** (JDK)
- **Maven**
- **PostgreSQL** (debe estar en funcionamiento para almacenar los datos)
- **Docker** (para ejecutar la aplicación de manera contenerizada)

## Instalación

1. Clona el repositorio:

   ```bash
   git clone https://github.com/AlenSaavedra/backend-notes-app.git
   ```

2. Navega a la carpeta del proyecto:

   ```bash
   cd tu-repositorio-backend
   ```

3. Instala las dependencias:

   ```bash
   mvn install
   ```

4. Si deseas ejecutar la aplicación localmente con Docker, puedes usar el siguiente comando para construir y ejecutar el contenedor:

   ```bash
   docker-compose up --build
   ```

   Esto arrancará la aplicación en un contenedor junto con la base de datos PostgreSQL.

## Uso

1. Asegúrate de tener PostgreSQL en funcionamiento (si no usas Docker para la base de datos).
2. Inicia la aplicación:

   ```bash
   mvn spring-boot:run
   ```

3. La API estará disponible en `http://localhost:8080`.

4. La aplicación estará desplegada en Render una vez que se haya configurado el repositorio y se haya vinculado con la plataforma.

## Estructura del Proyecto

```
/src
  /controller        # Controladores para manejar las solicitudes HTTP
  /service           # Lógica de negocio
  /repository        # Repositorios para interactuar con la base de datos
  /model             # Modelos de datos (Nota, Etiqueta)
  /application.properties # Configuración de la aplicación
  /Dockerfile        # Dockerfile para crear la imagen del backend
```

## Contribuciones

Si deseas contribuir a este proyecto, por favor sigue estos pasos:

1. Haz un fork del repositorio.
2. Crea una rama para tu feature (`git checkout -b feature/mi-feature`).
3. Haz tus cambios y haz commit de ellos (`git commit -am 'Añadir nueva feature'`).
4. Sube tus cambios a tu fork (`git push origin feature/mi-feature`).
5. Abre un Pull Request en el repositorio original.

## Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más detalles.

## Autor

Alen Saavedra  
[GitHub](https://github.com/AlenSaavedra) | [LinkedIn](https://www.linkedin.com/in/alensaavedra/)
```

