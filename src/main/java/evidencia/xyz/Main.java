package evidencia.xyz;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<Usuario> usuarios;

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
                        2. Dar de alta un pacienta
                        3. Dar de alta un medico
                        4. Ver la lista de citas
                        0. Salir"""));
            }catch (Throwable e){
                JOptionPane.showMessageDialog(null, "Opción inválida");
                menu();
            }
            switch (option) {
                case 1 -> System.out.println("prueba");
                case 2 -> System.out.println("hola");
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
