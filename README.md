# Sistema de Gestión de Tareas e Inventario

Sistema completo desarrollado en Java para gestionar tareas y recursos, con funcionalidades avanzadas de búsqueda, filtros y reportes estadísticos.

![Java](https://img.shields.io/badge/Java-25-orange?style=flat&logo=java)

## Características

### Gestión de Tareas

- Crear, editar y eliminar tareas
- Asignar prioridades (Baja, Media, Alta, Urgente)
- Estados personalizables (Pendiente, En Progreso, Completada, Cancelada)
- Control de fechas límite
- Vinculación con recursos del inventario

### Gestión de Inventario

- Control de recursos con cantidades y unidades
- Sistema de consumo y reposición
- Alertas de stock bajo
- Asignación de recursos a tareas específicas

### Búsquedas y Filtros Avanzados

- Búsqueda de tareas por nombre
- Filtros por estado y prioridad
- Tareas próximas a vencer (configurable)
- Identificación de tareas vencidas
- Detección de recursos con stock crítico

### Reportes y Estadísticas

- Reporte completo de productividad
- Tasa de completitud de tareas
- Análisis de tareas activas vs completadas
- Estado general del inventario

## Tecnologías

- **Java 24** - Lenguaje de programación
- **Collections Framework** - Gestión de datos en memoria
- **Stream API** - Filtros y búsquedas eficientes
- **LocalDate API** - Manejo de fechas
- **POO** - Diseño orientado a objetos

## Cómo ejecutar

### Prerrequisitos

- Java JDK 17 o superior instalado
- Terminal o línea de comandos

### Pasos

1. **Clonar el repositorio**

```bash
git clone https://github.com/TU_USUARIO/gestor-tareas-inventario.git
cd gestor-tareas-inventario
```

2. **Compilar el proyecto**

```bash
javac -d bin src/modelo/*.java src/servicio/*.java src/Main.java
```

3. **Ejecutar la aplicación**

```bash
java -cp bin Main
```

## Uso

Al ejecutar el programa, verás un menú interactivo con las siguientes opciones:

```
========== MENÚ PRINCIPAL ==========
1. Gestión de Tareas
2. Gestión de Recursos
3. Asignar Recurso a Tarea
4. Búsquedas y Filtros
5. Reportes y Estadísticas
0. Salir
====================================
```


**Ana Rey**

- GitHub: [@aniitaa-reey](https://github.com/aniitaa-reey)
- LinkedIn: [Ana Rey](https://linkedin.com/in/ana-rey-05243232b)


Diciembre 2025 - Ana Rey
