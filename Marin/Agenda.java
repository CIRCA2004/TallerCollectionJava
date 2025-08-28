import java.time.LocalDateTime;
import java.time.Month;
import java.util.NavigableSet;
import java.util.TreeSet;

class Reunion implements Comparable<Reunion> {
    private LocalDateTime fechaHora;
    private String asunto;

    public Reunion(LocalDateTime fechaHora, String asunto) {
        this.fechaHora = fechaHora;
        this.asunto = asunto;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    @Override
    public int compareTo(Reunion otra) {
        return this.fechaHora.compareTo(otra.fechaHora);
    }

    @Override
    public String toString() {
        return fechaHora + " - " + asunto;
    }
}

public class Agenda {
    public static void main(String[] args) {
        TreeSet<Reunion> reuniones = new TreeSet<>();

        // Agregar reuniones inventadas
        reuniones.add(new Reunion(LocalDateTime.of(2023, Month.MAY, 5, 10, 0), "Planificación"));
        reuniones.add(new Reunion(LocalDateTime.of(2023, Month.MAY, 1, 9, 0), "Kick-off"));
        reuniones.add(new Reunion(LocalDateTime.of(2023, Month.MAY, 15, 14, 0), "Revisión"));
        reuniones.add(new Reunion(LocalDateTime.of(2023, Month.MAY, 28, 16, 30), "Cierre mensual"));

        System.out.println("Todas las reuniones en orden:");
        for (Reunion r : reuniones) {
            System.out.println(r);
        }

        // Primera y última reunión
        System.out.println("\nPrimera reunión: " + reuniones.first());
        System.out.println("Última reunión: " + reuniones.last());

        // Subconjunto: desde hoy (ejemplo 2023-05-10) hasta fin de mes
        LocalDateTime hoy = LocalDateTime.of(2023, Month.MAY, 10, 0, 0);
        LocalDateTime finDeMes = LocalDateTime.of(2023, Month.MAY, 31, 23, 59);

        NavigableSet<Reunion> rango = reuniones.subSet(
                new Reunion(hoy, ""),        // desde "hoy"
                true,                        // incluir hoy
                new Reunion(finDeMes, ""),   // hasta fin de mes
                true                         // incluir fin de mes
        );

        System.out.println("\nReuniones desde el 10 de mayo hasta fin de mes:");
        for (Reunion r : rango) {
            System.out.println(r);
        }
    }
}
