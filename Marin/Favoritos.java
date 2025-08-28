import java.util.LinkedHashSet;
import java.util.Objects;

class Curso {
    private String nombre;

    public Curso(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    // Para que el Set detecte duplicados correctamente
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Curso)) return false;
        Curso curso = (Curso) o;
        return Objects.equals(nombre, curso.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    @Override
    public String toString() {
        return nombre;
    }
}

public class Favoritos {
    public static void main(String[] args) {
        LinkedHashSet<Curso> favoritos = new LinkedHashSet<>();

        // Agregar cursos
        favoritos.add(new Curso("Java"));
        favoritos.add(new Curso("Python"));
        favoritos.add(new Curso("C++"));
        favoritos.add(new Curso("JavaScript"));

        // Intentar agregar duplicados
        favoritos.add(new Curso("Java"));
        favoritos.add(new Curso("Python"));

        System.out.println("Favoritos iniciales: " + favoritos);

        // Eliminar un curso
        favoritos.remove(new Curso("C++"));
        System.out.println("Tras eliminar C++: " + favoritos);

        // Volver a agregar un curso eliminado
        favoritos.add(new Curso("C++"));
        System.out.println("Tras volver a agregar C++: " + favoritos);
    }
}
