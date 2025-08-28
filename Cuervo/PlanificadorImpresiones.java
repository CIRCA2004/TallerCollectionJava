package Cuervo;
import java.util.PriorityQueue;
import java.util.Comparator;

public class PlanificadorImpresiones {

    // Clase que representa un trabajo de impresión
    public static class Trabajo {
        private final String nombre;
        private final int tamañoPaginas;
        private final int ordenLlegada; // usado para desempatar

        public Trabajo(String nombre, int tamañoPaginas, int ordenLlegada) {
            this.nombre = nombre;
            this.tamañoPaginas = tamañoPaginas;
            this.ordenLlegada = ordenLlegada;
        }

        public String getNombre() {
            return nombre;
        }

        public int getTamañoPaginas() {
            return tamañoPaginas;
        }

        public int getOrdenLlegada() {
            return ordenLlegada;
        }

        @Override
        public String toString() {
            return String.format("%s (páginas: %d, llegada #%d)",
                    nombre, tamañoPaginas, ordenLlegada);
        }
    }

    public static void main(String[] args) {
        // Comparador: primero por tamaño (menor), luego por orden de llegada (menor)
        Comparator<Trabajo> comparador = Comparator
                .comparingInt(Trabajo::getTamañoPaginas)
                .thenComparingInt(Trabajo::getOrdenLlegada);

        PriorityQueue<Trabajo> cola = new PriorityQueue<>(comparador);

        // Simulación: se envían trabajos de impresión
        int llegada = 0;
        encolar(cola, new Trabajo("Informe Finanzas", 10, llegada++));
        encolar(cola, new Trabajo("Contrato", 3, llegada++));
        encolar(cola, new Trabajo("Presentación", 15, llegada++));
        encolar(cola, new Trabajo("Factura", 3, llegada++));
        encolar(cola, new Trabajo("Manual Técnico", 25, llegada++));

        System.out.println("\n=== Orden de impresión ===");
        while (!cola.isEmpty()) {
            Trabajo t = cola.poll(); // atiende el siguiente en orden óptimo
            System.out.println("Imprimiendo -> " + t);
        }
    }

    // Método auxiliar para registrar trabajos
    private static void encolar(PriorityQueue<Trabajo> cola, Trabajo t) {
        cola.add(t);
        System.out.println("Encolado: " + t);
    }
}

