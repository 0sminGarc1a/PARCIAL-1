# API REST con Estructura de Datos Lineal (Cola)

## Descripción del Proyecto

Esta es una API REST funcional implementada con Java y Spring Boot que utiliza una **estructura de datos lineal de tipo cola (queue)** en memoria. La API sigue una arquitectura por capas: Controller → Service → Repository → Model.

## Estructura de Datos: Cola (Queue)

### ¿Qué es una Cola?

Una cola es una estructura de datos lineal que sigue el principio **FIFO (First In, First Out)**, es decir, el primer elemento en entrar es el primero en salir. Funciona como una fila de personas en un supermercado.

### Implementación Manual

La cola está implementada manualmente usando nodos enlazados:

- **QueueNode<T>**: Representa cada nodo en la cola
  - `data`: Almacena el objeto Task
  - `next`: Apunta al siguiente nodo

- **TaskQueue**: Gestiona la estructura completa
  - `front`: Apunta al primer elemento (frente de la cola)
  - `rear`: Apunta al último elemento (final de la cola)
  - `size`: Cantidad de elementos
  - `nextId`: Generador de IDs automáticos

### Operaciones Principales

1. **enqueue(task)**: Agrega una tarea al final de la cola
2. **dequeue()**: Remueve y retorna la tarea del frente de la cola
3. **peek()**: Retorna la tarea del frente sin removerla
4. **isEmpty()**: Verifica si la cola está vacía

## Arquitectura por Capas

### 1. Model Layer
- **Task**: Modelo de datos principal (id, description, priority, createdAt)
- **QueueNode<T>**: Nodo genérico para la estructura enlazada
- **TaskQueue**: Implementación de la cola en memoria

### 2. Repository Layer
- **TaskRepository**: Maneja las operaciones directas sobre la cola
- Encapsula toda la lógica de la estructura de datos

### 3. Service Layer
- **TaskService**: Contiene la lógica de negocio
- Valida datos, maneja excepciones, coordina operaciones

### 4. Controller Layer
- **TaskController**: Expone los endpoints REST
- Maneja peticiones HTTP, respuestas y códigos de estado

## Flujo de una Petición

Cuando un cliente realiza una petición:

1. **Controller**: Recibe la petición HTTP
   - Ej: `POST /api/tasks/add` con JSON body
   - Extrae y valida los parámetros

2. **Service**: Procesa la lógica de negocio
   - Valida que la descripción no sea vacía
   - Crea el objeto Task con los datos

3. **Repository**: Opera la estructura de datos
   - Llama a `taskQueue.enqueue(task)`
   - La estructura enlazada se actualiza:
     - Si está vacía: front y rear apuntan al nuevo nodo
     - Si no: rear.next apunta al nuevo nodo, rear se actualiza

4. **Response**: El resultado viaja de vuelta
   - Repository → Service → Controller → Cliente
   - Se retorna la tarea creada con su ID asignado

## Endpoints Disponibles

### 1. Agregar Tarea
```
POST /api/tasks/add
Content-Type: application/json

{
  "description": "Procesar pedido #123",
  "priority": "alta"
}
```

### 2. Ver Siguiente Tarea
```
GET /api/tasks/next
```

### 3. Procesar Siguiente Tarea
```
POST /api/tasks/process
```

### 4. Estado de la Cola
```
GET /api/tasks/status
```

### 5. Ver Todas las Tareas
```
GET /api/tasks/all
```

### 6. Limpiar Cola
```
DELETE /api/tasks/clear
```

## Ejecución del Proyecto

### Requisitos
- Java 17 o superior
- Maven 3.6 o superior

### Pasos para Ejecutar

1. Clonar o descargar el proyecto
2. Navegar al directorio del proyecto
3. Ejecutar con Maven:
```bash
mvn spring-boot:run
```

4. La API estará disponible en: `http://localhost:8080`

## Ejemplos de Uso

### Agregar tareas:
```bash
curl -X POST http://localhost:8080/api/tasks/add \
  -H "Content-Type: application/json" \
  -d '{"description": "Tarea 1", "priority": "alta"}'

curl -X POST http://localhost:8080/api/tasks/add \
  -H "Content-Type: application/json" \
  -d '{"description": "Tarea 2", "priority": "normal"}'
```

### Ver siguiente tarea:
```bash
curl http://localhost:8080/api/tasks/next
```

### Procesar tarea:
```bash
curl -X POST http://localhost:8080/api/tasks/process
```

## Ventajas de esta Implementación

- **Estructura manual**: Demuestra comprensión profunda de las colas
- **Memoria eficiente**: Solo almacena lo necesario
- **Sin dependencias externas**: No requiere base de datos
- **Arquitectura limpia**: Separación clara de responsabilidades
- **Escalable**: Fácil de extender con nuevas funcionalidades

## Complejidad Algorítmica

- **enqueue**: O(1) - Siempre se agrega al final
- **dequeue**: O(1) - Siempre se remueve del frente
- **peek**: O(1) - Acceso directo al frente
- **isEmpty**: O(1) - Verificación simple

Esta implementación garantiza operaciones constantes, ideales para sistemas de procesamiento por lotes o gestión de tareas.
