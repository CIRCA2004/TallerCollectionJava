package Cuervo;
import java.util.Hashtable;
import java.util.Map;
import java.util.stream.Collectors;

public class ConteoConcurrente {

    public static void main(String[] args) {
        // Estructura sincronizada (legado)
        Hashtable<String, Integer> accesos = new Hashtable<>();

        // Simulación de accesos de distintos usuarios
        registrarAcceso(accesos, "juan");
        registrarAcceso(accesos, "ana");
        registrarAcceso(accesos, "juan");
        registrarAcceso(accesos, "marta");
        registrarAcceso(accesos, "pedro");
        registrarAcceso(accesos, "ana");
        registrarAcceso(accesos, "juan");
        registrarAcceso(accesos, "pedro");
        registrarAcceso(accesos, "sofia");
        registrarAcceso(accesos, "ana");

        // Mostrar todos los conteos
        System.out.println("=== Conteo de accesos ===");
        for (Map.Entry<String, Integer> e : accesos.entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }

        // Obtener top 3 usuarios
        System.out.println("\n=== Top 3 usuarios ===");
        accesos.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue())) // descendente por conteo
                .limit(3)
                .forEach(e -> System.out.println(e.getKey() + " -> " + e.getValue()));
    }

    // Método sincronizado (Hashtable ya es seguro, pero lo hacemos claro)
    private static void registrarAcceso(Hashtable<String, Integer> accesos, String usuario) {
        accesos.merge(usuario, 1, Integer::sum);
    }
}

