package Cuervo;
import java.util.ArrayDeque;

public class ProcesamientoPorLotes {

    // Clase simple para representar un Lote de tareas
    public static class Lote {
        private final String nombre;
        private final boolean urgente;

        public Lote(String nombre, boolean urgente) {
            this.nombre = nombre;
            this.urgente = urgente;
        }

        public String getNombre() {
            return nombre;
        }

        public boolean esUrgente() {
            return urgente;
        }

        @Override
        public String toString() {
            return (urgente ? "[URGENTE] " : "[NORMAL] ") + nombre;
        }
    }

    // ---- Ejemplo principal ----
    public static void main(String[] args) {
        ArrayDeque<Lote> cola = new ArrayDeque<>();

        // Agregar lotes normales
        encolarNormal(cola, new Lote("Lote A", false));
        encolarNormal(cola, new Lote("Lote B", false));
        encolarNormal(cola, new Lote("Lote C", false));

        // Inyectar un lote urgente
        encolarUrgente(cola, new Lote("Lote X", true));

        // Agregar más normales
        encolarNormal(cola, new Lote("Lote D", false));

        // Inyectar otro urgente
        encolarUrgente(cola, new Lote("Lote Y", true));

        System.out.println("=== Orden de la cola antes de ejecutar ===");
        imprimirCola(cola);

        System.out.println("\n=== Procesando lotes ===");
        while (!cola.isEmpty()) {
            Lote actual = cola.pollFirst(); // siempre procesamos desde el frente
            System.out.println("Procesando -> " + actual);
        }

        System.out.println("\nCola vacía. Todos los lotes fueron procesados.");
    }

    // Método para encolar un lote normal (al final)
    private static void encolarNormal(ArrayDeque<Lote> cola, Lote lote) {
        cola.addLast(lote);
        System.out.println("Encolado al final: " + lote);
    }

    // Método para encolar un lote urgente (al inicio)
    private static void encolarUrgente(ArrayDeque<Lote> cola, Lote lote) {
        cola.addFirst(lote);
        System.out.println("Inyectado al inicio: " + lote);
    }

    // Mostrar estado actual de la cola
    private static void imprimirCola(ArrayDeque<Lote> cola) {
        int i = 1;
        for (Lote lote : cola) {
            System.out.println(i + ". " + lote);
            i++;
        }
    }
}

