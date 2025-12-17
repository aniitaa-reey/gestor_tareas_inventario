package modelo;

public class Recurso {
    private static int contadorId = 1;
    private int id;
    private String nombre;
    private int cantidadDisponible;
    private String unidad;

    public Recurso(String nombre, int cantidadDisponible, String unidad) {
        this.id = contadorId++;
        this.nombre = nombre;
        this.cantidadDisponible = cantidadDisponible;
        this.unidad = unidad;
    }
    // Getters y Setters
     public int getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getCantidadDisponible() { return cantidadDisponible; }
    public void setCantidadDisponible(int cantidadDisponible) { 
        this.cantidadDisponible = cantidadDisponible; 
    }
    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }

    public boolean consumir(int cantidad) {
        if (cantidadDisponible >= cantidad) {
            cantidadDisponible -= cantidad;
            return true;
        }
        return false;
    }

    public void reponer(int cantidad) {
        cantidadDisponible += cantidad;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | %s | Disponible: %d %s", 
                id, nombre, cantidadDisponible, unidad);
    }
}
