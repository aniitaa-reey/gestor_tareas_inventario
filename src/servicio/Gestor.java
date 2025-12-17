package servicio;

import modelo.Tarea;
import modelo.Recurso;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Gestor {
    private List<Tarea> tareas;
    private List<Recurso> recursos;

    public Gestor() {
        this.tareas = new ArrayList<>();
        this.recursos = new ArrayList<>();
    }

    public void agregarTarea(String nombre, String descripcion, 
                            Tarea.Prioridad prioridad, LocalDate fechaLimite) {
        Tarea nuevaTarea = new Tarea(nombre, descripcion, prioridad, fechaLimite);
        tareas.add(nuevaTarea);
        System.out.println("Tarea creada con ID: " + nuevaTarea.getId());
    }

    public void listarTareas() {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas registradas.");
            return;
        }
        System.out.println("\n===== LISTA DE TAREAS =====");
        tareas.forEach(System.out::println);
    }

    public void verDetalleTarea(int id) {
        Optional<Tarea> tarea = buscarTarea(id);
        if (tarea.isPresent()) {
            System.out.println(tarea.get().toStringDetallado());
        } else {
            System.out.println("Tarea no encontrada.");
        }
    }

    public void actualizarEstadoTarea(int id, Tarea.Estado nuevoEstado) {
        Optional<Tarea> tarea = buscarTarea(id);
        if (tarea.isPresent()) {
            tarea.get().setEstado(nuevoEstado);
            System.out.println("Estado actualizado a: " + nuevoEstado);
        } else {
            System.out.println("Tarea no encontrada.");
        }
    }

    public void eliminarTarea(int id) {
        if (tareas.removeIf(t -> t.getId() == id)) {
            System.out.println("Tarea eliminada.");
        } else {
            System.out.println("Tarea no encontrada.");
        }
    }
 // ===== GESTIÓN DE RECURSOS =====
    public void agregarRecurso(String nombre, int cantidad, String unidad) {
        Recurso nuevoRecurso = new Recurso(nombre, cantidad, unidad);
        recursos.add(nuevoRecurso);
        System.out.println("Recurso creado con ID: " + nuevoRecurso.getId());
    }

    public void listarRecursos() {
        if (recursos.isEmpty()) {
            System.out.println("No hay recursos registrados.");
            return;
        }
        System.out.println("\n===== INVENTARIO DE RECURSOS =====");
        recursos.forEach(System.out::println);
    }

    public void consumirRecurso(int id, int cantidad) {
        Optional<Recurso> recurso = buscarRecurso(id);
        if (recurso.isPresent()) {
            if (recurso.get().consumir(cantidad)) {
                System.out.println("Recurso consumido. Quedan: " + 
                        recurso.get().getCantidadDisponible() + " " + recurso.get().getUnidad());
            } else {
                System.out.println("Cantidad insuficiente.");
            }
        } else {
            System.out.println("Recurso no encontrado.");
        }
    }

    public void reponerRecurso(int id, int cantidad) {
        Optional<Recurso> recurso = buscarRecurso(id);
        if (recurso.isPresent()) {
            recurso.get().reponer(cantidad);
            System.out.println("Recurso repuesto. Total: " + 
                    recurso.get().getCantidadDisponible() + " " + recurso.get().getUnidad());
        } else {
            System.out.println("Recurso no encontrado.");
        }
    }


        // ===== VINCULAR TAREA CON RECURSO =====

public void asignarRecursoATarea(int tareaId, int recursoId) {
    Optional<Tarea> tarea = buscarTarea(tareaId);
    Optional<Recurso> recurso = buscarRecurso(recursoId);
    
    if (tarea.isPresent() && recurso.isPresent()) {
        tarea.get().setRecursoId(recursoId);
        System.out.println("Recurso asignado a la tarea.");
    } else {
        System.out.println("Tarea o recurso no encontrado.");
    }
}
    // ===== BÚSQUEDAS Y FILTROS AVANZADOS =====
    public void buscarTareasPorNombre(String nombre) {
        List<Tarea> resultados = tareas.stream()
                .filter(t -> t.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .toList();
        
        if (resultados.isEmpty()) {
            System.out.println("No se encontraron tareas con ese nombre.");
        } else {
            System.out.println("\n===== RESULTADOS DE BÚSQUEDA =====");
            resultados.forEach(System.out::println);
        }
    }

    public void filtrarTareasPorEstado(Tarea.Estado estado) {
        List<Tarea> resultados = tareas.stream()
                .filter(t -> t.getEstado() == estado)
                .toList();
        
        if (resultados.isEmpty()) {
            System.out.println("No hay tareas con estado: " + estado);
        } else {
            System.out.println("\n===== TAREAS CON ESTADO: " + estado + " =====");
            resultados.forEach(System.out::println);
        }
    }

    public void filtrarTareasPorPrioridad(Tarea.Prioridad prioridad) {
        List<Tarea> resultados = tareas.stream()
                .filter(t -> t.getPrioridad() == prioridad)
                .toList();
        
        if (resultados.isEmpty()) {
            System.out.println("No hay tareas con prioridad: " + prioridad);
        } else {
            System.out.println("\n===== TAREAS CON PRIORIDAD: " + prioridad + " =====");
            resultados.forEach(System.out::println);
        }
    }

    public void listarTareasProximasAVencer(int dias) {
        LocalDate fechaLimite = LocalDate.now().plusDays(dias);
        List<Tarea> resultados = tareas.stream()
                .filter(t -> t.getEstado() != Tarea.Estado.COMPLETADA && 
                             t.getEstado() != Tarea.Estado.CANCELADA)
                .filter(t -> !t.getFechaLimite().isAfter(fechaLimite))
                .toList();
        
        if (resultados.isEmpty()) {
            System.out.println("No hay tareas próximas a vencer en los próximos " + dias + " días.");
        } else {
            System.out.println("\n===== TAREAS PRÓXIMAS A VENCER (próximos " + dias + " días) =====");
            resultados.forEach(System.out::println);
        }
    }

    public void listarTareasVencidas() {
        LocalDate hoy = LocalDate.now();
        List<Tarea> resultados = tareas.stream()
                .filter(t -> t.getEstado() != Tarea.Estado.COMPLETADA && 
                             t.getEstado() != Tarea.Estado.CANCELADA)
                .filter(t -> t.getFechaLimite().isBefore(hoy))
                .toList();
        
        if (resultados.isEmpty()) {
            System.out.println("No hay tareas vencidas.");
        } else {
            System.out.println("\n  ===== TAREAS VENCIDAS =====");
            resultados.forEach(System.out::println);
        }
    }

    public void listarRecursosStockBajo(int umbral) {
        List<Recurso> resultados = recursos.stream()
                .filter(r -> r.getCantidadDisponible() <= umbral)
                .toList();
        
        if (resultados.isEmpty()) {
            System.out.println("Todos los recursos tienen stock suficiente.");
        } else {
            System.out.println("\n  ===== RECURSOS CON STOCK BAJO (≤ " + umbral + ") =====");
            resultados.forEach(System.out::println);
        }
    }
    // ===== REPORTES Y ESTADÍSTICAS =====
    public void generarReporteProductividad() {
        long pendientes = tareas.stream().filter(t -> t.getEstado() == Tarea.Estado.PENDIENTE).count();
        long enProgreso = tareas.stream().filter(t -> t.getEstado() == Tarea.Estado.EN_PROGRESO).count();
        long completadas = tareas.stream().filter(t -> t.getEstado() == Tarea.Estado.COMPLETADA).count();
        long canceladas = tareas.stream().filter(t -> t.getEstado() == Tarea.Estado.CANCELADA).count();
        
        long tareasActivas = pendientes + enProgreso;
        double porcentajeCompletadas = tareas.isEmpty() ? 0 : (completadas * 100.0) / tareas.size();
        
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║     REPORTE DE PRODUCTIVIDAD             ║");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.println("Total de tareas: " + tareas.size());
        System.out.println("├─ Pendientes: " + pendientes);
        System.out.println("├─ En progreso: " + enProgreso);
        System.out.println("├─ Completadas: " + completadas);
        System.out.println("└─ Canceladas: " + canceladas);
        System.out.println("\nTareas activas: " + tareasActivas);
        System.out.printf("Tasa de completitud: %.1f%%\n", porcentajeCompletadas);
        
        // Mostrar tareas vencidas
        LocalDate hoy = LocalDate.now();
        long vencidas = tareas.stream()
                .filter(t -> t.getEstado() != Tarea.Estado.COMPLETADA && 
                             t.getEstado() != Tarea.Estado.CANCELADA)
                .filter(t -> t.getFechaLimite().isBefore(hoy))
                .count();
        
        if (vencidas > 0) {
            System.out.println(" Tareas vencidas: " + vencidas);
        }
        
        System.out.println("\n--- INVENTARIO DE RECURSOS ---");
        System.out.println("Total de recursos: " + recursos.size());
        
        long stockBajo = recursos.stream().filter(r -> r.getCantidadDisponible() <= 10).count();
        if (stockBajo > 0) {
            System.out.println(" Recursos con stock bajo (≤10): " + stockBajo);
        }
        System.out.println("════════════════════════════════════════════\n");
    }
 // ===== MÉTODOS AUXILIARES =====
    private Optional<Tarea> buscarTarea(int id) {
        return tareas.stream().filter(t -> t.getId() == id).findFirst();
    }

    private Optional<Recurso> buscarRecurso(int id) {
        return recursos.stream().filter(r -> r.getId() == id).findFirst();
    }
}

