# Detector de Mutantes - Gianfranco Canciani
- Legajo: 52611
- DNI: 46866563

Este proyecto es una aplicación Spring Boot que provee una API REST para detectar si un humano es mutante basándose en su secuencia de ADN.

## 🎯 El Problema a Resolver

El objetivo principal es ofrecer un servicio para identificar mutantes. Se considera que un humano es mutante si tiene **más de una secuencia de cuatro letras idénticas** (A, T, C, G) en su ADN. Las secuencias pueden encontrarse de forma horizontal, vertical u oblicua.

## 📦 Prerequisitos

Asegúrate de tener instalado el siguiente software:

| Software   | Versión Mínima | ¿Para qué se usa?        | 
| ---------- | -------------- | ------------------------ | 
| **Java JDK** | 17+            | Lenguaje de programación | 
| **Git**      | Cualquiera     | Control de versiones     | 
| **Docker**   | Cualquiera     | Contenerización          | 

## 🚀 Cómo ejecutar el proyecto

### Opción 1: Usando Gradle (Localmente)

1.  Clona el repositorio:
    ```bash
    git clone https://github.com/tu-usuario/mutant-detector.git
    ```
2.  Navega al directorio del proyecto:
    ```bash
    cd mutant-detector
    ```
3.  Ejecuta la aplicación:
    ```bash
    # Windows
    gradlew.bat bootRun

    # Mac/Linux
    ./gradlew bootRun
    ```

La aplicación estará disponible en `http://localhost:8080`.

### Opción 2: Usando Docker

1.  **Construir la imagen de Docker:**
    ```bash
    docker build -t mutant-detector .
    ```
2.  **Ejecutar el contenedor:**
    ```bash
    docker run -p 8080:8080 mutant-detector
    ```
La aplicación estará disponible en `http://localhost:8080`.

## 📡 Endpoints de la API

Puedes acceder a la documentación interactiva de OpenAPI en [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).

### `POST /mutant`

Este endpoint recibe una secuencia de ADN y determina si pertenece a un mutante.

-   **Cuerpo de la Petición (Request Body):**

    ```json
    {
        "dna": ["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
    }
    ```

-   **Respuestas:**
    -   `200 OK`: Si la secuencia de ADN pertenece a un mutante.
    -   `403 Forbidden`: Si la secuencia de ADN No pertenece a un mutante.
    -   `400 Bad Request`: Si la secuencia de ADN es inválida (no es NxN, contiene caracteres inválidos, etc.).

### `GET /stats`

Este endpoint devuelve estadísticas sobre las verificaciones de ADN realizadas.

-   **Cuerpo de la Respuesta (Response Body):**

    ```json
    {
        "count_mutant_dna": 40,
        "count_human_dna": 100,
        "ratio": 0.4
    }
    ```

## 🏗️ Arquitectura del Proyecto

El proyecto sigue una arquitectura en capas para separar responsabilidades:

-   **Controller:** Recibe las peticiones HTTP, valida la entrada y las delega al servicio.
-   **Service:** Contiene la lógica de negocio principal. Orquesta la detección de mutantes y el cálculo de estadísticas.
-   **Repository:** Provee una interfaz para interactuar con la base de datos usando Spring Data JPA.
-   **Entity:** Define el modelo de datos que se mapea a la tabla de la base de datos.
-   **DTO (Data Transfer Object):** Define la estructura de los datos que se envían y reciben a través de la API.

## 🧮 El Algoritmo Explicado

El algoritmo en `MutantDetector.java` busca secuencias de 4 letras idénticas. Para optimizar, implementa "Early Termination": en cuanto encuentra **más de una** secuencia, retorna `true` inmediatamente sin necesidad de recorrer toda la matriz.

La búsqueda se realiza en 4 direcciones:
1.  **Horizontal (→)**
2.  **Vertical (↓)**
3.  **Diagonal Descendente (↘)**
4.  **Diagonal Ascendente (↗)**

## 💾 Base de Datos

Para optimizar el rendimiento y el uso de espacio, en lugar de guardar la matriz de ADN completa, se calcula un hash **SHA-256** de la secuencia. Este hash se utiliza como identificador único para cachear los resultados.

La entidad `DnaRecord` tiene índices en las columnas `dna_hash` y `is_mutant` para acelerar las consultas de búsqueda y conteo.

Durante el desarrollo, se utiliza una base de datos en memoria **H2**, accesible en `http://localhost:8080/h2-console`.

## 🧪 Cómo ejecutar las pruebas

Para ejecutar las pruebas unitarias y de integración, ejecuta el siguiente comando:

```bash
# Windows
gradlew.bat test

# Mac/Linux
./gradlew test
```

Gianfranco Canciani
