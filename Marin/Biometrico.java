import java.util.HashSet;

public class Biometrico {
    public static void main(String[] args) {
        HashSet<String> huellas = new HashSet<>();

        // Intentos de registro
        System.out.println("Registrando ID: H001 -> " + huellas.add("H001"));
        System.out.println("Registrando ID: H002 -> " + huellas.add("H002"));
        System.out.println("Registrando ID: H003 -> " + huellas.add("H003"));

        // Intentos repetidos
        System.out.println("Registrando ID: H002 -> " + huellas.add("H002"));
        System.out.println("Registrando ID: H001 -> " + huellas.add("H001"));

        // Mostrar resultados
        System.out.println("\nHuellas registradas: " + huellas);
        System.out.println("Total de registros: " + huellas.size());
    }
}
