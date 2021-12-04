package evidencia.xyz;

public class Medicamento {
    private String nombre;

    public Medicamento(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Medicamento{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
