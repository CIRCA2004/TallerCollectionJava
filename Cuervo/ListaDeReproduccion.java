package Cuervo;
import java.util.LinkedList;
import java.util.Objects;

public class ListaDeReproduccion {

    // Clase sencilla que representa una canción
    public static class Cancion {
        private final String titulo;
        private final String artista;
        private final int duracionSegundos; // opcional

        public Cancion(String titulo, String artista, int duracionSegundos) {
            this.titulo = titulo;
            this.artista = artista;
            this.duracionSegundos = duracionSegundos;
        }

        public Cancion(String titulo, String artista) {
            this(titulo, artista, 0);
        }

        public String getTitulo() {
            return titulo;
        }

        public String getArtista() {
            return artista;
        }

        @Override
        public String toString() {
            if (duracionSegundos > 0) {
                return String.format("%s - %s (%d:%02d)", artista, titulo, duracionSegundos / 60, duracionSegundos % 60);
            } else {
                return String.format("%s - %s", artista, titulo);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Cancion)) return false;
            Cancion c = (Cancion) o;
            return duracionSegundos == c.duracionSegundos &&
                    Objects.equals(titulo, c.titulo) &&
                    Objects.equals(artista, c.artista);
        }

        @Override
        public int hashCode() {
            return Objects.hash(titulo, artista, duracionSegundos);
        }
    }

    // ---- ListaDeReproduccion ----
    private final LinkedList<Cancion> lista;
    private int cursorIndex; // índice del cursor; -1 si la lista está vacía

    public ListaDeReproduccion() {
        this.lista = new LinkedList<>();
        this.cursorIndex = -1;
    }

    // Añadir canción al final
    public void agregar(Cancion c) {
        if (c == null) return;
        lista.addLast(c);
        if (cursorIndex == -1) cursorIndex = 0; // si estaba vacía, ponemos cursor en la primera canción
    }

    // Insertar en posición (0-based). Si pos > size => agrega al final.
    public void insertarEn(int pos, Cancion c) {
        if (c == null) return;
        if (pos <= 0) lista.addFirst(c);
        else if (pos >= lista.size()) lista.addLast(c);
        else lista.add(pos, c);

        if (cursorIndex == -1) cursorIndex = 0;
    }

    // Eliminar por índice (0-based). Devuelve la canción eliminada o null.
    public Cancion eliminarEn(int pos) {
        if (lista.isEmpty() || pos < 0 || pos >= lista.size()) return null;
        Cancion removed = lista.remove(pos);
        if (lista.isEmpty()) {
            cursorIndex = -1;
        } else {
            // Ajustamos cursor para que apunte a una canción válida:
            if (cursorIndex == pos) {
                // Si eliminamos la canción en cursor, apuntamos a la misma posición (ahora siguiente),
                // pero si era la última, apuntamos a la nueva última (pos-1).
                if (pos >= lista.size()) cursorIndex = lista.size() - 1;
                // else cursorIndex queda en pos (ahora contiene la canción que estaba después)
            } else if (pos < cursorIndex) {
                cursorIndex--; // shift left
            }
        }
        return removed;
    }

    // Mover canción de fromIndex a toIndex (ambos 0-based). Si índices fuera de rango, se acomodan.
    public boolean moverCancion(int fromIndex, int toIndex) {
        if (lista.isEmpty()) return false;
        int size = lista.size();
        if (fromIndex < 0) fromIndex = 0;
        if (fromIndex >= size) fromIndex = size - 1;
        if (toIndex < 0) toIndex = 0;
        if (toIndex >= size) toIndex = size - 1;
        if (fromIndex == toIndex) return true;

        // Guardamos referencia a la canción actual (para recalcular cursor después)
        Cancion current = getCancionActual();

        Cancion moving = lista.remove(fromIndex);
        // después de remover, la lista tiene size-1; el índice de inserción cambia si venía después del removed
        if (toIndex > fromIndex) toIndex = toIndex - 1;
        lista.add(toIndex, moving);

        // Recalcular cursorIndex buscando la canción actual en la lista (si existe)
        if (current == null) {
            cursorIndex = lista.isEmpty() ? -1 : 0;
        } else {
            cursorIndex = lista.indexOf(current);
            if (cursorIndex == -1) {
                // si por alguna razón la canción actual no se encontró (debe ser rara), apuntamos al inicio
                cursorIndex = lista.isEmpty() ? -1 : 0;
            }
        }
        return true;
    }

    // Obtener la canción actual (en cursor)
    public Cancion getCancionActual() {
        if (cursorIndex < 0 || cursorIndex >= lista.size()) return null;
        return lista.get(cursorIndex);
    }

    // Avanzar una canción (si es posible). Devuelve la canción nueva o null si no pudo avanzar.
    public Cancion avanzar() {
        if (lista.isEmpty() || cursorIndex == -1) return null;
        if (cursorIndex < lista.size() - 1) {
            cursorIndex++;
            return lista.get(cursorIndex);
        } else {
            // ya estamos en la última; no avanzamos
            return lista.get(cursorIndex);
        }
    }

    // Retroceder una canción (si es posible). Devuelve la canción nueva o null si no pudo retroceder.
    public Cancion retroceder() {
        if (lista.isEmpty() || cursorIndex == -1) return null;
        if (cursorIndex > 0) {
            cursorIndex--;
            return lista.get(cursorIndex);
        } else {
            // ya en la primera
            return lista.get(cursorIndex);
        }
    }

    // Ir a índice específico (0-based). Devuelve la canción objetivo o null si inválido.
    public Cancion irA(int indice) {
        if (lista.isEmpty() || indice < 0 || indice >= lista.size()) return null;
        cursorIndex = indice;
        return lista.get(cursorIndex);
    }

    // Tamaño
    public int tamaño() {
        return lista.size();
    }

    // Imprimir la lista con indicación del cursor
    public void imprimirLista() {
        if (lista.isEmpty()) {
            System.out.println("[Lista vacía]");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            String marcador = (i == cursorIndex) ? " <-- cursor" : "";
            System.out.printf("%2d: %s%s%n", i, lista.get(i), marcador);
        }
    }

    // Buscar índice de una canción (por equals)
    public int indiceDe(Cancion c) {
        return lista.indexOf(c);
    }

    // ---- Ejemplo de uso en main ----
    public static void main(String[] args) {
        ListaDeReproduccion pl = new ListaDeReproduccion();

        Cancion c1 = new Cancion("Imagine", "John Lennon", 183);
        Cancion c2 = new Cancion("Bohemian Rhapsody", "Queen", 354);
        Cancion c3 = new Cancion("Billie Jean", "Michael Jackson", 294);
        Cancion c4 = new Cancion("Smells Like Teen Spirit", "Nirvana", 301);
        Cancion c5 = new Cancion("Hotel California", "Eagles", 391);

        // Agregar canciones
        pl.agregar(c1);
        pl.agregar(c2);
        pl.agregar(c3);
        pl.agregar(c4);
        pl.agregar(c5);

        System.out.println("===== Playlist inicial =====");
        pl.imprimirLista();

        // Mostrar canción actual
        System.out.println("\nCanción actual: " + pl.getCancionActual());

        // Avanzar y retroceder
        System.out.println("\nAvanzando...");
        pl.avanzar();
        System.out.println("Canción actual: " + pl.getCancionActual());

        System.out.println("\nAvanzando...");
        pl.avanzar();
        System.out.println("Canción actual: " + pl.getCancionActual());

        System.out.println("\nRetroceder...");
        pl.retroceder();
        System.out.println("Canción actual: " + pl.getCancionActual());

        // Mover canción: llevar la canción en índice 4 (Hotel California) a la posición 1
        System.out.println("\nMoviendo 'Hotel California' (índice 4) -> posición 1");
        pl.moverCancion(4, 1);
        pl.imprimirLista();
        System.out.println("Cursor ahora en índice: " + pl.cursorIndex + " (canción: " + pl.getCancionActual() + ")");

        // Eliminar la canción en índice 2
        System.out.println("\nEliminando canción en índice 2");
        pl.eliminarEn(2);
        pl.imprimirLista();

        // Ir a una posición concreta
        System.out.println("\nIr a índice 0");
        pl.irA(0);
        System.out.println("Canción actual: " + pl.getCancionActual());

        // Insertar en la mitad
        System.out.println("\nInsertar nueva canción en posición 2");
        Cancion nueva = new Cancion("New Song", "New Artist", 200);
        pl.insertarEn(2, nueva);
        pl.imprimirLista();
    }
}
