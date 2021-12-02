package evidencia.xyz;

public class Medico extends Persona{
    private int idMedico, NoCedula;
    private String especialidad;

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public int getNoCedula() {
        return NoCedula;
    }

    public void setNoCedula(int noCedula) {
        NoCedula = noCedula;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}
