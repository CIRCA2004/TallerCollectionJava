package Cuervo;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DepuracionCorreos {

    public static void main(String[] args) {
        // Conjunto inicial de correos (únicos, sin orden)
        Set<String> correos = new HashSet<>();
        correos.add("juan@gmail.com");
        correos.add("ana@yahoo.com");
        correos.add("pedro@empresa.com");
        correos.add("sofia@spam.com");
        correos.add("luis@hotmail.com");
        correos.add("marta@empresa.com");
        correos.add("diego@banned.org");

        // Lista de dominios vetados
        Set<String> dominiosVetados = Set.of("spam.com", "banned.org");

        System.out.println("Tamaño inicial: " + correos.size());
        System.out.println("Correos antes de depuración:");
        for (String correo : correos) {
            System.out.println(" - " + correo);
        }

        // Recorrer con Iterator y eliminar los vetados
        Iterator<String> it = correos.iterator();
        while (it.hasNext()) {
            String correo = it.next();
            String dominio = correo.substring(correo.indexOf('@') + 1);
            if (dominiosVetados.contains(dominio)) {
                System.out.println("Eliminando -> " + correo);
                it.remove(); // eliminación segura
            }
        }

        System.out.println("\nTamaño final: " + correos.size());
        System.out.println("Correos después de depuración:");
        for (String correo : correos) {
            System.out.println(" - " + correo);
        }
    }
}
