# Product Hexagonal WebFlux

Proyecto para la asignatura de Arquitecutra de Aplicaciones Web del Politécnico Grancolombiano, construida en **Spring Boot 3**, **Java 17**, **WebFlux** y **MongoDB reactivo** siguiendo una arquitectura hexagonal simple.

## Integrantes

1. Luis Alexander Moreno Saavedra
2. Jerson Reicardo Simbaqueva Buitrago
3. Néstor Vélez Vargas


## Requisitos

- Java 17
- Maven 3.x
- MongoDB en local (por defecto `mongodb://localhost:27017/productdb`)

## Ejecutar

```bash
mvn spring-boot:run
```

o desde IntelliJ:

1. Importar el proyecto como *Maven project*.
2. Esperar a que descargue dependencias.
3. Ejecutar la clase `Application`.

## Endpoints REST (reactivos)

- `GET  /api/productos` – Lista todos los productos.
- `GET  /api/productos/{id}` – Obtiene un producto por id.
- `POST /api/productos` – Crea un nuevo producto.
- `PUT  /api/productos/{id}` – Actualiza un producto existente.
- `DELETE /api/productos/{id}` – Elimina un producto.

Ejemplo de cuerpo JSON para los endpoints de crear / actualizar:

```json
{
  "nombre": "Café molido premium",
  "descripcion": "Paquete de 500g",
  "precio": 25000.0
}
```

La colección de Postman se encuentra en el siguiente archivo [Postman Collection](Poli-ArqAppsWebs.postman_collection.json)


