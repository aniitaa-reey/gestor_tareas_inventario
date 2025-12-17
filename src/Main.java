import servicio.Gestor;
import modelo.Tarea;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    private static Gestor gestor = new Gestor();
    private static Scanner scanner = new Scanner(System.in);
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        boolean salir = false;

        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║  SISTEMA DE GESTIÓN DE TAREAS E       ║");
        System.out.println("║         INVENTARIO v1.0               ║");
        System.out.println("╚═══════════════════════════════════════╝\n");

        while (!salir) {
            mostrarMenu();
            int opcion = leerEntero("Selecciona una opción: ");

            switch (opcion) {
                case 1 -> menuTareas();
                case 2 -> menuRecursos();
                case 3 -> asignarRecursoATarea();
                case 4 -> menuBusquedasFiltros();
                case 5 -> menuReportes();
                case 0 -> {
                    System.out.println("\n¡Hasta pronto!");
                    salir = true;
                }
                default -> System.out.println(" Opción inválida.");
            }
        }
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n========== MENÚ PRINCIPAL ==========");
        System.out.println("1. Gestión de Tareas");
        System.out.println("2. Gestión de Recursos");
        System.out.println("3. Asignar Recurso a Tarea");
        System.out.println("4. Búsquedas y Filtros");
        System.out.println("5. Reportes y Estadísticas");
        System.out.println("0. Salir");
        System.out.println("====================================");
    }

    private static void menuTareas() {
        System.out.println("\n--- GESTIÓN DE TAREAS ---");
        System.out.println("1. Crear tarea");
        System.out.println("2. Listar tareas");
        System.out.println("3. Ver detalle de tarea");
        System.out.println("4. Actualizar estado");
        System.out.println("5. Eliminar tarea");
        System.out.println("0. Volver");

        int opcion = leerEntero("Opción: ");

        switch (opcion) {
            case 1 -> crearTarea();
            case 2 -> gestor.listarTareas();
            case 3 -> {
                int id = leerEntero("ID de la tarea: ");
                gestor.verDetalleTarea(id);
            }
            case 4 -> actualizarEstadoTarea();
            case 5 -> {
                int id = leerEntero("ID de la tarea a eliminar: ");
                gestor.eliminarTarea(id);
            }
        }
    }

    private static void crearTarea() {
        System.out.println("\n--- CREAR NUEVA TAREA ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();
        
        System.out.println("Prioridad (1=BAJA, 2=MEDIA, 3=ALTA, 4=URGENTE): ");
        int prioridad = leerEntero("") - 1;
        Tarea.Prioridad[] prioridades = Tarea.Prioridad.values();
        
        System.out.print("Fecha límite (dd/MM/yyyy): ");
        LocalDate fecha = leerFecha();

        gestor.agregarTarea(nombre, descripcion, prioridades[prioridad], fecha);
    }

    private static void actualizarEstadoTarea() {
        int id = leerEntero("ID de la tarea: ");
        System.out.println("Estado (1=PENDIENTE, 2=EN_PROGRESO, 3=COMPLETADA, 4=CANCELADA): ");
        int estado = leerEntero("") - 1;
        Tarea.Estado[] estados = Tarea.Estado.values();
        gestor.actualizarEstadoTarea(id, estados[estado]);
    }

    private static void menuRecursos() {
        System.out.println("\n--- GESTIÓN DE RECURSOS ---");
        System.out.println("1. Agregar recurso");
        System.out.println("2. Listar recursos");
        System.out.println("3. Consumir recurso");
        System.out.println("4. Reponer recurso");
        System.out.println("0. Volver");

        int opcion = leerEntero("Opción: ");

        switch (opcion) {
            case 1 -> {
                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();
                int cantidad = leerEntero("Cantidad: ");
                System.out.print("Unidad (ej: kg, unidades, litros): ");
                String unidad = scanner.nextLine();
                gestor.agregarRecurso(nombre, cantidad, unidad);
            }
            case 2 -> gestor.listarRecursos();
            case 3 -> {
                int id = leerEntero("ID del recurso: ");
                int cantidad = leerEntero("Cantidad a consumir: ");
                gestor.consumirRecurso(id, cantidad);
            }
            case 4 -> {
                int id = leerEntero("ID del recurso: ");
                int cantidad = leerEntero("Cantidad a reponer: ");
                gestor.reponerRecurso(id, cantidad);
            }
        }
    }

    private static void asignarRecursoATarea() {
        int tareaId = leerEntero("ID de la tarea: ");
        int recursoId = leerEntero("ID del recurso: ");
        gestor.asignarRecursoATarea(tareaId, recursoId);
    }

    private static void menuBusquedasFiltros() {
        System.out.println("\n--- BÚSQUEDAS Y FILTROS ---");
        System.out.println("1. Buscar tareas por nombre");
        System.out.println("2. Filtrar tareas por estado");
        System.out.println("3. Filtrar tareas por prioridad");
        System.out.println("4. Tareas próximas a vencer");
        System.out.println("5. Tareas vencidas");
        System.out.println("6. Recursos con stock bajo");
        System.out.println("0. Volver");

        int opcion = leerEntero("Opción: ");

        switch (opcion) {
            case 1 -> {
                System.out.print("Buscar por nombre: ");
                String nombre = scanner.nextLine();
                gestor.buscarTareasPorNombre(nombre);
            }
            case 2 -> {
                System.out.println("Estado (1=PENDIENTE, 2=EN_PROGRESO, 3=COMPLETADA, 4=CANCELADA): ");
                int estado = leerEntero("") - 1;
                Tarea.Estado[] estados = Tarea.Estado.values();
                gestor.filtrarTareasPorEstado(estados[estado]);
            }
            case 3 -> {
                System.out.println("Prioridad (1=BAJA, 2=MEDIA, 3=ALTA, 4=URGENTE): ");
                int prioridad = leerEntero("") - 1;
                Tarea.Prioridad[] prioridades = Tarea.Prioridad.values();
                gestor.filtrarTareasPorPrioridad(prioridades[prioridad]);
            }
            case 4 -> {
                int dias = leerEntero("Días (ej: 7 para próximos 7 días): ");
                gestor.listarTareasProximasAVencer(dias);
            }
            case 5 -> gestor.listarTareasVencidas();
            case 6 -> {
                int umbral = leerEntero("Umbral de stock bajo (ej: 10): ");
                gestor.listarRecursosStockBajo(umbral);
            }
        }
    }

    private static void menuReportes() {
        System.out.println("\n--- REPORTES Y ESTADÍSTICAS ---");
        System.out.println("1. Reporte de Productividad");
        System.out.println("0. Volver");

        int opcion = leerEntero("Opción: ");

        if (opcion == 1) {
            gestor.generarReporteProductividad();
        }
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("Por favor ingresa un número válido: ");
        }
        int numero = scanner.nextInt();
        scanner.nextLine();
        return numero;
    }

    private static LocalDate leerFecha() {
        while (true) {
            try {
                String fechaStr = scanner.nextLine();
                return LocalDate.parse(fechaStr, formatter);
            } catch (DateTimeParseException e) {
                System.out.print("Formato inválido. Usa dd/MM/yyyy: ");
            }
        }
    }
}