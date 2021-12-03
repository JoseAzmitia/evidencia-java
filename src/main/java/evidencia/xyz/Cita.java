package evidencia.xyz;

public class Cita {
    private int idCita;
    private String fechaCita;
    private Paciente paciente;
    private Medico medico;

    public Cita(int idCita, String fechaCita, Paciente paciente, Medico medico) {
        this.idCita = idCita;
        this.fechaCita = fechaCita;
        this.paciente = paciente;
        this.medico = medico;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public String getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(String fechaCita) {
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

    @Override
    public String toString() {
        return "Cita{" +
                "idCita=" + idCita +
                ", fechaCita='" + fechaCita + '\'' +
                ", paciente=" + paciente +
                ", medico=" + medico +
                '}';
    }
}
