package evidencia.xyz;

public class Receta {
    int idReceta;
    String descripcion;
    Medicamento medicina;

    public Receta(int idReceta, String descripcion, Medicamento medicina) {
        this.idReceta = idReceta;
        this.descripcion = descripcion;
        this.medicina = medicina;
    }

    public int getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(int idReceta) {
        this.idReceta = idReceta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Medicamento getMedicina() {
        return medicina;
    }

    public void setMedicina(Medicamento medicina) {
        this.medicina = medicina;
    }
}
