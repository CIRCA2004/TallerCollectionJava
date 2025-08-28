package Cuervo;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class IndiceInvertido {
    // Estructura: palabra -> conjunto de páginas (ordenado y sin duplicados)
    private Map<String, TreeSet<Integer>> indice;

    public IndiceInvertido() {
        indice = new HashMap<>();
    }

    // Agregar ocurrencia de una palabra en una página
    public void agregar(String palabra, int pagina) {
        indice.computeIfAbsent(palabra, k -> new TreeSet<>()).add(pagina);
    }

    // Consultar páginas de una palabra
    public TreeSet<Integer> consultar(String palabra) {
        return indice.getOrDefault(palabra, new TreeSet<>());
    }

    // Mostrar índice completo (opcional)
    public void mostrarIndice() {
        for (Map.Entry<String, TreeSet<Integer>> entrada : indice.entrySet()) {
            System.out.println(entrada.getKey() + " → " + entrada.getValue());
        }
    }

    // Ejemplo de uso
    public static void main(String[] args) {
        IndiceInvertido indice = new IndiceInvertido();

        // --- Agregar ocurrencias ---
        indice.agregar("java", 3);
        indice.agregar("java", 10);
        indice.agregar("estructura", 5);
        indice.agregar("java", 7);
        indice.agregar("estructura", 1);
        indice.agregar("indice", 2);
        indice.agregar("java", 3); // Repetido, no se duplica

        // --- Consultar ---
        System.out.println("Consulta 'java': " + indice.consultar("java"));
        System.out.println("Consulta 'estructura': " + indice.consultar("estructura"));
        System.out.println("Consulta 'indice': " + indice.consultar("indice"));
        System.out.println("Consulta 'algoritmo' (no existe): " + indice.consultar("algoritmo"));

        // --- Mostrar índice completo ---
        System.out.println("\nÍndice invertido completo:");
        indice.mostrarIndice();
    }
}
