package evidencia.xyz;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static List<Usuario> usuarios;
    public static List<Paciente> pacientes = new ArrayList<>();
    public static List<Medico> medicos = new ArrayList<>();
    public static List<Cita> citas = new ArrayList<>();

    public static void main(String[] args) {
        cargarUsuarios();
        loadPacientes();
        loadMedicos();
        loadCitas();
        login();
    }

    public static void login(){
        boolean existeUsuario;
        String usuario, contrasena;
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
                    int idCita = citas.size() + 1;
                    String fechaCita = (JOptionPane.showInputDialog("Ingresa la fecha"));
                    Cita cita = new Cita(idCita,fechaCita, pacientes.get(0), medicos.get(0));
                    System.out.println(cita);
                    citas.add(cita);
                    saveCitas(citas);
                }
                case 2 -> {
                    System.out.println("alta paciente");
                    int idPaciente = pacientes.size() + 1;
                    int telefono = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el telefono"));
                    int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingresa la edad"));
                    String nombre = (JOptionPane.showInputDialog("Ingresa el nombre"));
                    String apPaterno = (JOptionPane.showInputDialog("Ingresa el apellido paterno"));
                    String apMaterno = (JOptionPane.showInputDialog("Ingresa el apellido materno"));
                    char sexo = (JOptionPane.showInputDialog("Ingresa el sexo (M / F)")).charAt(0);
                    Paciente paciente = new Paciente(idPaciente,telefono,edad,nombre,apPaterno,apMaterno,sexo);
                    System.out.println(paciente);
                    pacientes.add(paciente);
                    savePacientes(pacientes);
                }
                case 3 -> {
                    System.out.println("alta medico");
                    int idMedico = medicos.size() + 1;
                    int noCedula = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el Número de cédula"));
                    int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingresa la edad"));
                    String especialidad = (JOptionPane.showInputDialog("Ingresa la especialidad"));
                    String nombre = (JOptionPane.showInputDialog("Ingresa el nombre"));
                    String apPaterno = (JOptionPane.showInputDialog("Ingresa el apellido paterno"));
                    String apMaterno = (JOptionPane.showInputDialog("Ingresa el apellido materno"));
                    char sexo = (JOptionPane.showInputDialog("Ingresa el sexo (M / F)")).charAt(0);
                    Medico medico = new Medico(idMedico,noCedula,edad,especialidad,nombre,apPaterno,apMaterno,sexo);
                    System.out.println(medico);
                    medicos.add(medico);
                    saveMedicos(medicos);
                }
                case 4 -> {
                    System.out.println("lista de citas");
                    for (int i = 0; i < citas.size(); i++) {
                        Cita x = citas.get(i);
                        System.out.println(x.toString());
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

    public static void saveCitas(List<Cita> citas) {
        String rutaCita = "json\\citas.json";
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(citas);
            System.out.println(json);
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(rutaCita));
                writer.write(json);
                FileWriter fileWriter = new FileWriter(rutaCita);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.print(json);
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("Error->" + e.getMessage());
        }
    }

    public static void loadCitas() {
        String json = "";
        String rutaCita = "json\\citas.json";

        try (BufferedReader br = new BufferedReader(new FileReader(rutaCita))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                json += linea;
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        if (json.isEmpty()){
            System.out.println("no hay citas registradas");
        }else{
            // Convertir JSON en objeto de java
            Type type = new TypeToken<List<Cita>>() {
            }.getType();
            citas = new Gson().fromJson(json, type);
            for (int i = 0; i < citas.size(); i++) {
                Cita x = citas.get(i);
                System.out.println(x.toString());
            }
        }
    }

    public static void savePacientes(List<Paciente> pacientes) {
        String rutaCita = "json\\pacientes.json";
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(pacientes);
            System.out.println(json);
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(rutaCita));
                writer.write(json);
                FileWriter fileWriter = new FileWriter(rutaCita);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.print(json);
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("Error->" + e.getMessage());
        }
    }

    public static void loadPacientes() {
        String json = "";
        String rutaCita = "json\\pacientes.json";

        try (BufferedReader br = new BufferedReader(new FileReader(rutaCita))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                json += linea;
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        if (json.isEmpty()){
            System.out.println("no hay pacientes registrados");
        }else{
            // Convertir JSON en objeto de java
            Type type = new TypeToken<List<Paciente>>() {
            }.getType();
            pacientes = new Gson().fromJson(json, type);
            for (int i = 0; i < pacientes.size(); i++) {
                Paciente x = pacientes.get(i);
                System.out.println(x.toString());
            }
        }
    }

    public static void saveMedicos(List<Medico> medicos) {
        String rutaCita = "json\\medicos.json";
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(medicos);
            System.out.println(json);
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(rutaCita));
                writer.write(json);
                FileWriter fileWriter = new FileWriter(rutaCita);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.print(json);
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("Error->" + e.getMessage());
        }
    }

    public static void loadMedicos() {
        String json = "";
        String rutaCita = "json\\medicos.json";

        try (BufferedReader br = new BufferedReader(new FileReader(rutaCita))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                json += linea;
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        if (json.isEmpty()){
            System.out.println("no hay medicos registrados");
        }else{
            // Convertir JSON en objeto de java
            Type type = new TypeToken<List<Medico>>() {
            }.getType();
            medicos = new Gson().fromJson(json, type);
            for (int i = 0; i < medicos.size(); i++) {
                Medico x = medicos.get(i);
                System.out.println(x.toString());
            }
        }
    }
}

            /*System.out.println("load " + json);
            Gson gson = new Gson();
            Cita cita = gson.fromJson(json, Cita.class);
            citas.add(cita);
            Paciente paciente = new Paciente(cita.getPaciente().getIdPaciente(), cita.getPaciente().getTelefono(),
                    cita.getPaciente().getEdad(), cita.getPaciente().getNombre(), cita.getPaciente().getApPaterno(),
                    cita.getPaciente().getApMaterno(), cita.getPaciente().getSexo());
            pacientes.add(paciente);
            Medico medico = new Medico(cita.getMedico().getIdMedico(), cita.getMedico().getNoCedula(),
                    cita.getMedico().getEdad(), cita.getMedico().getEspecialidad(), cita.getMedico().getNombre(),
                    cita.getMedico().getApPaterno(), cita.getMedico().getApPaterno(), cita.getMedico().getSexo());
            medicos.add(medico);
            System.out.println("nombre del paciente: " + cita.getPaciente().getNombre());
             */