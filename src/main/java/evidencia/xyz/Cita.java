package evidencia.xyz;

public class Cita {
    private int idCita, fechaCita;
    private Paciente paciente;
    private Medico medico;
    private Receta receta;

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public int getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(int fechaCita) {
        this.fechaCita = fechaCita;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }
}
