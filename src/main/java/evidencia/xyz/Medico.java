package evidencia.xyz;

public class Medico{
    private int idMedico, NoCedula, edad;
    private String especialidad, nombre, apPaterno,apMaterno;
    private char sexo;

    public Medico(int idMedico, int noCedula, int edad, String especialidad, String nombre, String apPaterno, String apMaterno, char sexo) {
        this.idMedico = idMedico;
        this.NoCedula = noCedula;
        this.edad = edad;
        this.especialidad = especialidad;
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.sexo = sexo;
    }

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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "Medico{" +
                "idMedico=" + idMedico +
                ", NoCedula=" + NoCedula +
                ", edad=" + edad +
                ", especialidad='" + especialidad + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apPaterno='" + apPaterno + '\'' +
                ", apMaterno='" + apMaterno + '\'' +
                ", sexo=" + sexo +
                '}';
    }

    public void consultaMedico(Medico medico){
        System.out.println(medico);
    }
}
