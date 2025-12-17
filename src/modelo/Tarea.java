package modelo;

import java.time.LocalDate;

public class Tarea {
    private static int contadorId = 1;
    private int id;
    private String nombre;
    private String descripcion;
    private Prioridad prioridad;
    private Estado estado;
    private LocalDate fechaLimite;
    private int recursoId;

    public enum Prioridad {
        BAJA, MEDIA, ALTA, URGENTE
    }

    public enum Estado {
        PENDIENTE, EN_PROGRESO, COMPLETADA, CANCELADA
    }

    public Tarea(String nombre, String descripcion, Prioridad prioridad, LocalDate fechaLimite) {
        this.id = contadorId++;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.estado = Estado.PENDIENTE;
        this.fechaLimite = fechaLimite;
        this.recursoId = -1;
    }
      // Getters y Setters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Prioridad getPrioridad() { return prioridad; }
    public void setPrioridad(Prioridad prioridad) { this.prioridad = prioridad; }
    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }
    public LocalDate getFechaLimite() { return fechaLimite; }
    public void setFechaLimite(LocalDate fechaLimite) { this.fechaLimite = fechaLimite; }
    public int getRecursoId() { return recursoId; }
    public void setRecursoId(int recursoId) { this.recursoId = recursoId; }

    @Override
    public String toString() {
        return String.format("ID: %d | %s | Prioridad: %s | Estado: %s | Fecha: %s | Recurso: %s",
                id, nombre, prioridad, estado, fechaLimite, 
                recursoId == -1 ? "Sin asignar" : "ID " + recursoId);
    }

    public String toStringDetallado() {
        return String.format("""
                ============================================
                ID: %d
                Nombre: %s
                Descripción: %s
                Prioridad: %s
                Estado: %s
                Fecha Límite: %s
                Recurso Asignado: %s
                ============================================
                """, id, nombre, descripcion, prioridad, estado, fechaLimite,
                recursoId == -1 ? "Sin asignar" : "ID " + recursoId);
    }
}
