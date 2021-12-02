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
