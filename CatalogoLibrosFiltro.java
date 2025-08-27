import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Libro {
    private String titulo;
    private String autor;
    private int anio;
    private String isbn;

    public Libro(String titulo, String autor, int anio, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
        this.isbn = isbn;
    }

    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getAnio() { return anio; }
    public String getIsbn() { return isbn; }

    @Override
    public String toString() {
        return titulo + " - " + autor + " (" + anio + ") ISBN: " + isbn;
    }
}

class Biblioteca {
    private List<Libro> libros = new ArrayList<>();

    // Agregar libro
    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    // Eliminar libro por ISBN
    public boolean eliminarPorISBN(String isbn) {
        return libros.removeIf(l -> l.getIsbn().equals(isbn));
    }

    // Buscar libros por autor
    public List<Libro> buscarPorAutor(String autor) {
        return libros.stream()
                .filter(l -> l.getAutor().equalsIgnoreCase(autor))
                .collect(Collectors.toList());
    }

    // Listar por año ascendente
    public List<Libro> listarPorAnioAscendente() {
        return libros.stream()
                .sorted(Comparator.comparingInt(Libro::getAnio))
                .collect(Collectors.toList());
    }

    // Obtener los 5 más recientes
    public List<Libro> obtener5MasRecientes() {
        return libros.stream()
                .sorted(Comparator.comparingInt(Libro::getAnio).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }
}

public class CatalogoLibrosFiltro {
    public static void main(String[] args) {
        Biblioteca biblio = new Biblioteca();

        biblio.agregarLibro(new Libro("Clean Code", "Robert C. Martin", 2008, "12345"));
        biblio.agregarLibro(new Libro("Effective Java", "Joshua Bloch", 2018, "67890"));
        biblio.agregarLibro(new Libro("Java Concurrency in Practice", "Brian Goetz", 2006, "11111"));
        biblio.agregarLibro(new Libro("Design Patterns", "Erich Gamma", 1994, "22222"));
        biblio.agregarLibro(new Libro("Refactoring", "Martin Fowler", 2019, "33333"));
        biblio.agregarLibro(new Libro("Domain-Driven Design", "Eric Evans", 2003, "44444"));

        System.out.println("=== Buscar por autor (Martin Fowler) ===");
        biblio.buscarPorAutor("Martin Fowler").forEach(System.out::println);

        System.out.println("\n=== Listar por año ascendente ===");
        biblio.listarPorAnioAscendente().forEach(System.out::println);

        System.out.println("\n=== 5 más recientes ===");
        biblio.obtener5MasRecientes().forEach(System.out::println);

        System.out.println("\n=== Eliminar por ISBN (11111) ===");
        biblio.eliminarPorISBN("11111");
        biblio.listarPorAnioAscendente().forEach(System.out::println);
    }
}
