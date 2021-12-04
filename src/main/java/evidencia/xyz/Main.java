package evidencia.xyz;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javax.swing.*;
import java.io.*;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static List<Usuario> usuarios;
    public static List<Paciente> pacientes = new ArrayList<>();
    public static List<Medico> medicos = new ArrayList<>();
    public static List<Cita> citas = new ArrayList<>();
    public static List<Receta> recetas = new ArrayList<>();

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
                        4. Crear receta
                        0. Salir"""));
            }catch (Throwable e){
                JOptionPane.showMessageDialog(null, "Opción inválida");
                menu();
            }
            switch (option) {
                case 1 -> {
                   menuCitas();
                }
                case 2 -> {
                    menuPacientes();
                }
                case 3 -> {
                    menuMedicos();
                }
                case 4 -> {
                   generarReceta();
                }
                case 0 -> option = 0;
                default -> JOptionPane.showMessageDialog(null, "Opción inválida");
            }
        }while (option != 0);
    }

    public static void menuCitas(){
        System.out.println("Mostrando menú citas");
        byte option = 0;
        do{
            try {
                option = Byte.parseByte(JOptionPane.showInputDialog("""
                        Aplicación citas medicas
                        Selecciona una opción
                        1. Crear una cita
                        2. Ver la lista de citas
                        0. Salir"""));
            }catch (Throwable e){
                JOptionPane.showMessageDialog(null, "Opción inválida");
                menuCitas();
            }
            switch (option) {
                case 1 -> {
                    crearCita();
                }
                case 2 -> {
                    listaCitas();
                }
                case 0 -> option = 0;
                default -> JOptionPane.showMessageDialog(null, "Opción inválida");
            }
        }while (option != 0);
    }

    public static void menuPacientes(){
        System.out.println("Mostrando menú alta Pacientes");
        byte option = 0;
        do{
            try {
                option = Byte.parseByte(JOptionPane.showInputDialog("""
                        Aplicación citas medicas
                        Selecciona una opción
                        1. Dar de alta un paciente
                        2. Ver la lista de pacientes
                        0. Salir"""));
            }catch (Throwable e){
                JOptionPane.showMessageDialog(null, "Opción inválida");
                menuPacientes();
            }
            switch (option) {
                case 1 -> {
                    altaPaciente();
                }
                case 2 -> {
                    listaPacientes();
                }
                case 0 -> option = 0;
                default -> JOptionPane.showMessageDialog(null, "Opción inválida");
            }
        }while (option != 0);
    }

    public static void menuMedicos(){
        System.out.println("Mostrando menú alta medicos");
        byte option = 0;
        do{
            try {
                option = Byte.parseByte(JOptionPane.showInputDialog("""
                        Aplicación citas medicas
                        Selecciona una opción
                        1. Dar de alta un medico
                        2. Ver la lista de medicos
                        0. Salir"""));
            }catch (Throwable e){
                JOptionPane.showMessageDialog(null, "Opción inválida");
                menuMedicos();
            }
            switch (option) {
                case 1 -> {
                    altaMedico();
                }
                case 2 -> {
                    listaMedico();
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

    public static void crearCita(){
        int idCita = citas.size() + 1;
        int idPaciente, idMedico;
        Boolean disponible = false;
        Boolean formato = false;
        try{
            do{
                String fechaCita = (JOptionPane.showInputDialog("Ingresa la fecha - Formato dd/MM/yyyy"));
                if (isValidDate(fechaCita) == true){
                    if (fechaOcupada(fechaCita) == true){
                        System.out.println("Fecha ocupada, intenta otra fecha");
                        disponible = false;
                    }else{
                        disponible = true;
                        listaPacientes();
                        listaMedico();
                        idPaciente = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el id del Paciente"));
                        if (idPaciente > pacientes.size()){
                            System.out.println("El paciente que eligió no existe, redireccionando al menú de Pacientes..");
                            menuPacientes();
                        }
                        idMedico = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el id del Medico"));
                        if (idPaciente > medicos.size()){
                            System.out.println("El medico que eligió no existe, redireccionando al menú de Medicos..");
                            menuMedicos();
                        }
                        Cita cita = new Cita(idCita,fechaCita, pacientes.get(idPaciente-1), medicos.get(idMedico-1));
                        System.out.println(cita);
                        citas.add(cita);
                        saveCitas(citas);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Formato inválido, intentalo de nuevo");
                }
            }while ((disponible & formato) != false);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error, intenta de nuevo");
            crearCita();
        }
    }

    public static void listaCitas(){
        if (citas.isEmpty()){
            System.out.println("No hay citas en la lista");
        }else{
            System.out.println("lista de citas");
            for (Cita cita : citas) {
                System.out.println("id: " + cita.getIdCita() + " paciente: " + cita.getPaciente().getNombre()
                        + " medico: " + cita.getMedico().getNombre() + " fecha: " + cita.getFechaCita());
            }
        }
    }

    public static void altaPaciente(){
        System.out.println("alta paciente");
        try{
            int idPaciente = pacientes.size() + 1;
            int telefono = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el teléfono"));
            int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingresa la edad"));
            String nombre = (JOptionPane.showInputDialog("Ingresa el nombre"));
            String apPaterno = (JOptionPane.showInputDialog("Ingresa el apellido paterno"));
            String apMaterno = (JOptionPane.showInputDialog("Ingresa el apellido materno"));
            char sexo = (JOptionPane.showInputDialog("Ingresa el sexo (M / F)")).charAt(0);
            Paciente paciente = new Paciente(idPaciente,telefono,edad,nombre,apPaterno,apMaterno,sexo);
            System.out.println(paciente);
            pacientes.add(paciente);
            savePacientes(pacientes);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error, intenta de nuevo");
            menuPacientes();
        }
    }

    public static void listaPacientes(){
        if (medicos.isEmpty()){
            System.out.println("No hay pacientes en la lista");
        }else{
            System.out.println("Lista de pacientes registrados");
            for (Paciente paciente : pacientes){
                System.out.println("id: " + paciente.getIdPaciente() + " nombre: " + paciente.getNombre());
            }
        }
    }

    public static void altaMedico(){
        System.out.println("alta medico");
        try {
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
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error, intenta de nuevo");
            menuMedicos();
        }
    }

    public static void listaMedico(){
        if (medicos.isEmpty()){
            System.out.println("No hay medicos en la lista");
        }else{
            System.out.println("Lista de medicos registrados");
            for (Medico medico : medicos){
                System.out.println("id: " + medico.getIdMedico() + " nombre: " + medico.getNombre());
            }
        }
    }

    public static void generarReceta(){
        System.out.println("Crear receta");
        try{
            int idReceta = recetas.size() + 1;
            String descripcion = (JOptionPane.showInputDialog("Escribe la descripción de la receta"));
            String nombreMed = (JOptionPane.showInputDialog("Escribe el nombre del medicamento"));
            Medicamento medicina = new Medicamento(nombreMed);
            Receta receta = new Receta(idReceta, descripcion, medicina);
            System.out.println(receta);
            recetas.add(receta);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error, intenta de nuevo");
            menu();
        }
    }

    public static boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    public static boolean fechaOcupada(String fecha){
        Boolean existe = false;
        for (Cita cita : citas){
            if (fecha.equals(cita.getFechaCita()) == true){
                existe = true;
                break;
            }else{
                existe = false;
            }
        }
        return existe;
    }
}
