# Backend - REST API for Notes Web Application

This is the backend for the notes web application, developed with **Java 23**, **Spring Boot**, **PostgreSQL**, and **Docker** for containerization and deployment on **Render**. The application follows a layered architecture, separating business logic in the Service Layer.

## Frontend -> [Repository](https://github.com/AlenSaavedra/fronend-challenge-ensolvers)

## Description

The API provides the necessary endpoints to manage the creation, editing, deletion, and filtering of notes and tags. The architecture is organized into several layers:

- **Controller Layer**: Handles HTTP requests and directs them to the appropriate services.
- **Service Layer**: Contains business logic and processes requests.
- **Repository Layer**: Accesses the database (PostgreSQL) through JPA (Java Persistence API).

## Endpoints

The API provides the following endpoints:

### Notes

- **GET /api/notes**  
  Returns all stored notes.

- **GET /api/notes/{id}**  
  Returns the note with the specified ID.

- **POST /api/notes**  
  Creates a new note.

- **PUT /api/notes/{id}**  
  Updates the note with the specified ID.

- **DELETE /api/notes/{id}**  
  Deletes the note with the specified ID.

### Tags

- **GET /api/tags**  
  Returns all available tags.

- **GET /api/notes/filter?tag={tag}**  
  Returns notes filtered by the specified tag.

## Architecture

The application follows a layered architecture to separate responsibilities and keep the code organized and maintainable.

1. **Controller Layer**  
   Manages HTTP requests and directs them to the Service Layer. Controllers receive requests and return responses.

   Example:
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

       // Other endpoints...
   }
   ```

2. **Service Layer**  
   Contains business logic. Services process requests and handle interactions with the repository. 

   Example:
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

       // Other methods for updating, deleting, and filtering notes
   }
   ```

3. **Repository Layer**  
   Uses Spring Data JPA to interact with the PostgreSQL database. This layer defines methods to access data.

   Example:
   ```java
   @Repository
   public interface NoteRepository extends JpaRepository<Note, Long> {
       List<Note> findByTag(String tag);
   }
   ```

## Technologies Used

- **Java 23**: Programming language used for the backend.
- **Spring Boot**: Framework to build the REST API.
- **PostgreSQL**: Relational database to store notes and tags.
- **Docker**: Containerization of the backend to simplify deployment.
- **Render**: Platform for application deployment.
- **Maven**: Dependency manager and project builder.

## Requirements

- **Java 23** (JDK)
- **Maven**
- **PostgreSQL** (must be running to store data)
- **Docker** (to run the application in a containerized environment)

## Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/AlenSaavedra/backend-notes-app.git
   ```

2. Navigate to the project folder:

   ```bash
   cd your-backend-repository
   ```

3. Install dependencies:

   ```bash
   mvn install
   ```

4. If you want to run the application locally with Docker, use the following command to build and run the container:

   ```bash
   docker-compose up --build
   ```

   This will start the application in a container along with the PostgreSQL database.

## Usage

1. Ensure PostgreSQL is running (if not using Docker for the database).
2. Start the application:

   ```bash
   mvn spring-boot:run
   ```

3. The API will be available at `http://localhost:8080`.

4. The application will be deployed on Render once the repository is configured and linked to the platform.

## Project Structure

```
/src
  /controller        # Controllers to handle HTTP requests
  /service           # Business logic
  /repository        # Repositories to interact with the database
  /model             # Data models (Note, Tag)
  /application.properties # Application configuration
  /Dockerfile        # Dockerfile to create the backend image
```

## Contributions

If you want to contribute to this project, please follow these steps:

1. Fork the repository.
2. Create a branch for your feature (`git checkout -b feature/my-feature`).
3. Make your changes and commit them (`git commit -am 'Add new feature'`).
4. Push your changes to your fork (`git push origin feature/my-feature`).
5. Open a Pull Request in the original repository.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Author

Alen Saavedra  
[GitHub](https://github.com/AlenSaavedra) | [LinkedIn](https://www.linkedin.com/in/alensaavedra/)
```
