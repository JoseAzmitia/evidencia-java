package evidencia.xyz;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<Usuario> usuarios;
    public static List<Usuario> pacientes;
    public static List<Usuario> medicos;

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
                    Cita cita = new Cita();

                }
                case 2 -> {
                    System.out.println("alta paciente");
                    int idPaciente = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el id"));
                    int telefono = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el telefono"));
                    int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingresa la edad"));
                    String nombre = (JOptionPane.showInputDialog("Ingresa el nombre"));
                    String apPaterno = (JOptionPane.showInputDialog("Ingresa el apellido paterno"));
                    String apMaterno = (JOptionPane.showInputDialog("Ingresa el apellido materno"));
                    char sexo = (JOptionPane.showInputDialog("Ingresa el sexo (M / F)")).charAt(0);
                    Paciente paciente = new Paciente(idPaciente,telefono,edad,nombre,apPaterno,apMaterno,sexo);
                    System.out.println(paciente);
                }
                case 3 -> {
                    System.out.println("alta medico");
                    int idMedico = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el id"));
                    int noCedula = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el Número de cédula"));
                    int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingresa la edad"));
                    String especialidad = (JOptionPane.showInputDialog("Ingresa la especialidad"));
                    String nombre = (JOptionPane.showInputDialog("Ingresa el nombre"));
                    String apPaterno = (JOptionPane.showInputDialog("Ingresa el apellido paterno"));
                    String apMaterno = (JOptionPane.showInputDialog("Ingresa el apellido materno"));
                    char sexo = (JOptionPane.showInputDialog("Ingresa el sexo (M / F)")).charAt(0);
                    Medico medico = new Medico(idMedico,noCedula,edad,especialidad,nombre,apPaterno,apMaterno,sexo);
                    System.out.println(medico);
                }
                case 4 -> {
                    System.out.println("lista de citas");
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
}
