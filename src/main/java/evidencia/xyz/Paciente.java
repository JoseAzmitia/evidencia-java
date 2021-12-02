package evidencia.xyz;

public class Paciente{
    private int idPaciente, telefono, edad;
    private String nombre, apPaterno,apMaterno;
    private char sexo;

    public Paciente(int idPaciente, int telefono, int edad, String nombre, String apPaterno, String apMaterno, char sexo) {

        this.idPaciente = idPaciente;
        this.telefono = telefono;
        this.edad = edad;
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.sexo = sexo;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public void consultaPaciente(Paciente paciente){
        System.out.println(paciente);
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "idPaciente=" + idPaciente +
                ", telefono=" + telefono +
                ", edad=" + edad +
                ", nombre='" + nombre + '\'' +
                ", apPaterno='" + apPaterno + '\'' +
                ", apMaterno='" + apMaterno + '\'' +
                ", sexo=" + sexo +
                '}';
    }
}
