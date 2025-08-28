package Cuervo;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;

public class RankingDeportistas {

    // Clase Deportista
    public static class Deportista {
        private final String nombre;
        private final String apellido;
        private final int puntaje;

        public Deportista(String nombre, String apellido, int puntaje) {
            this.nombre = nombre;
            this.apellido = apellido;
            this.puntaje = puntaje;
        }

        public String getNombre() {
            return nombre;
        }

        public String getApellido() {
            return apellido;
        }

        public int getPuntaje() {
            return puntaje;
        }

        @Override
        public String toString() {
            return String.format("%s %s (puntaje: %d)", nombre, apellido, puntaje);
        }
    }

    public static void main(String[] args) {
        // Comparator compuesto: puntaje DESC, apellido ASC, nombre ASC
        Comparator<Deportista> comparador = Comparator
                .comparingInt(Deportista::getPuntaje).reversed()
                .thenComparing(Deportista::getApellido)
                .thenComparing(Deportista::getNombre);

        TreeSet<Deportista> ranking = new TreeSet<>(comparador);

        // Insertar deportistas (con empates en puntaje)
        ranking.add(new Deportista("Juan", "Martínez", 95));
        ranking.add(new Deportista("Ana", "García", 100));
        ranking.add(new Deportista("Pedro", "López", 95));
        ranking.add(new Deportista("Luis", "Martínez", 80));
        ranking.add(new Deportista("Marta", "García", 100));
        ranking.add(new Deportista("Sofía", "Hernández", 90));
        ranking.add(new Deportista("Carlos", "Alvarez", 95));
        ranking.add(new Deportista("Diego", "Ramírez", 75));
        ranking.add(new Deportista("Elena", "Zapata", 88));
        ranking.add(new Deportista("Fernando", "Alvarez", 95));
        ranking.add(new Deportista("Gabriela", "López", 70));
        ranking.add(new Deportista("Hugo", "Pérez", 100));

        // Mostrar el top 10
        System.out.println("=== Ranking Top 10 ===");
        List<Deportista> lista = new ArrayList<>(ranking);
        for (int i = 0; i < lista.size() && i < 10; i++) {
            System.out.printf("%2d. %s%n", i + 1, lista.get(i));
        }
    }
}

