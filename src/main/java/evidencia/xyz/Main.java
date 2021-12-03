package evidencia.xyz;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Main {

    public static List<Usuario> usuarios;
    public static List<Paciente> pacientes = new ArrayList<>();
    public static List<Medico> medicos = new ArrayList<>();
    public static List<Cita> citas = new ArrayList<>();

    public static void main(String[] args) {
        cargarUsuarios();
        login();
    }

    public static void login(){
        boolean existeUsuario;
        String usuario = "";
        String contrasena = "";
        usuario = (JOptionPane.showInputDialog("Ingresa el usuario"));
        contrasena = (JOptionPane.showInputDialog("Ingresa el contraseña"));
        existeUsuario = validarCredenciales(usuario, contrasena);
        if (existeUsuario) {
            JOptionPane.showMessageDialog(null, "Existe el usuario");
            menu();

        } else {
            JOptionPane.showMessageDialog(null, "El usuario no existe");
            login();
        }
    }

    public static void menu(){
        System.out.println("Mostrando menú");
        byte option = 0;
        do{
            try {
                option = Byte.parseByte(JOptionPane.showInputDialog("""
                        Aplicación citas medicas
                        Selecciona una opción
                        1. Crear una cita
                        2. Dar de alta un paciente
                        3. Dar de alta un medico
                        4. Ver la lista de citas
                        0. Salir"""));
            }catch (Throwable e){
                JOptionPane.showMessageDialog(null, "Opción inválida");
                menu();
            }
            switch (option) {
                case 1 -> {
                    /*int idCita = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el id"));*/
                    int idCita = citas.size() + 1;
                    String fechaCita = (JOptionPane.showInputDialog("Ingresa la fecha"));
                    Cita cita = new Cita(idCita,fechaCita, pacientes.get(0), medicos.get(0));
                    System.out.println(cita);
                    save(cita);
                }
                case 2 -> {
                    System.out.println("alta paciente");
                    /*int idPaciente = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el id"));*/
                    int idPaciente = pacientes.size() + 1;
                    int telefono = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el telefono"));
                    int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingresa la edad"));
                    String nombre = (JOptionPane.showInputDialog("Ingresa el nombre"));
                    String apPaterno = (JOptionPane.showInputDialog("Ingresa el apellido paterno"));
                    String apMaterno = (JOptionPane.showInputDialog("Ingresa el apellido materno"));
                    char sexo = (JOptionPane.showInputDialog("Ingresa el sexo (M / F)")).charAt(0);
                    Paciente paciente = new Paciente(idPaciente,telefono,edad,nombre,apPaterno,apMaterno,sexo);
                    pacientes.add(paciente);
                    System.out.println(paciente);
                }
                case 3 -> {
                    System.out.println("alta medico");
                    /*int idMedico = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el id"));*/
                    int idMedico = medicos.size() + 1;
                    int noCedula = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el Número de cédula"));
                    int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingresa la edad"));
                    String especialidad = (JOptionPane.showInputDialog("Ingresa la especialidad"));
                    String nombre = (JOptionPane.showInputDialog("Ingresa el nombre"));
                    String apPaterno = (JOptionPane.showInputDialog("Ingresa el apellido paterno"));
                    String apMaterno = (JOptionPane.showInputDialog("Ingresa el apellido materno"));
                    char sexo = (JOptionPane.showInputDialog("Ingresa el sexo (M / F)")).charAt(0);
                    Medico medico = new Medico(idMedico,noCedula,edad,especialidad,nombre,apPaterno,apMaterno,sexo);
                    medicos.add(medico);
                    System.out.println(medico);
                }
                case 4 -> {
                    System.out.println("lista de citas");
                    ListIterator it = citas.listIterator();
                    System.out.println("Lista de citas:");
                    while(it.hasNext()){
                        System.out.println(it.next());
                    }
                }
                case 0 -> option = 0;
                default -> JOptionPane.showMessageDialog(null, "Opción inválida");
            }
        }while (option != 0);
    }

    public static void cargarUsuarios() {

        if (usuarios == null) {
            usuarios = new ArrayList<>();
        }
        usuarios.add(new Usuario(1, "Edison", "123"));
        usuarios.add(new Usuario(2, "Newton", "456"));
        System.out.println("Los usuarios han sido cargados: " + usuarios.size());

    }

    public static boolean validarCredenciales(String usuario, String contrasena){
        return usuarios.stream().anyMatch(x -> x.getNombre().equals(usuario) && x.getContrasena().equals(contrasena));
    }

    public static void save(Cita cita) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(cita);
            System.out.println(json);
        } catch (Exception e) {
            System.out.println("Error->" + e.getMessage());
        }

        /*Guardar variable*/
    }

    public static void load() {
        String json = "{\"idCita\":1,\"fechaCita\":\"17-02-21\",\"paciente\":{\"idPaciente\":1,\"telefono\":9933,\"edad\":3,\"nombre\":\"Edison\",\"apPaterno\":\"prz\",\"apMaterno\":\"azm\",\"sexo\":\"M\"},\"medico\":{\"idMedico\":1,\"noCedula\":938,\"edad\":34,\"especialidad\":\"general\",\"nombre\":\"Newton\",\"apPaterno\":\"prz\",\"apMaterno\":\"azm\",\"sexo\":\"M\"}}";
        System.out.println("load " + json);
        Gson gson = new Gson();
        Cita cita = gson.fromJson(json, Cita.class);

        System.out.println("nombre del paciente: " + cita.getPaciente().getNombre());
    }
}
