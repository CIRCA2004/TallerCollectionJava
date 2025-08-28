import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.PriorityQueue;

public class suportTecniSeve {
    public static void main(String[] args) {
        PriorityQueue<Ticket> pq = new PriorityQueue<>(Comparator
                .comparing(Ticket::getSeveridad)          // primero severidad
                .thenComparing(Ticket::getFechaCreacion)  // luego fecha más antigua
        );

        // Tickets inventados con distintas severidades y fechas
        pq.add(new Ticket("Servidor caído", Ticket.Severidad.CRITICA,
                LocalDateTime.of(2023, 5, 10, 9, 0)));
        pq.add(new Ticket("Error en login", Ticket.Severidad.ALTA,
                LocalDateTime.of(2023, 5, 9, 14, 30)));
        pq.add(new Ticket("Cambio de contraseña", Ticket.Severidad.BAJA,
                LocalDateTime.of(2023, 5, 8, 8, 15)));
        pq.add(new Ticket("Demora en reportes", Ticket.Severidad.MEDIA,
                LocalDateTime.of(2023, 5, 9, 11, 0)));
        pq.add(new Ticket("Base de datos lenta", Ticket.Severidad.ALTA,
                LocalDateTime.of(2023, 5, 8, 10, 0)));
        pq.add(new Ticket("Error menor en UI", Ticket.Severidad.BAJA,
                LocalDateTime.of(2023, 5, 7, 16, 45)));

        System.out.println("Orden de atención de tickets:");
        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
    }
}

class Ticket {
    enum Severidad { CRITICA, ALTA, MEDIA, BAJA }

    private String descripcion;
    private Severidad severidad;
    private LocalDateTime fechaCreacion;

    public Ticket(String descripcion, Severidad severidad, LocalDateTime fechaCreacion) {
        this.descripcion = descripcion;
        this.severidad = severidad;
        this.fechaCreacion = fechaCreacion;
    }

    public Severidad getSeveridad() {
        return severidad;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    @Override
    public String toString() {
        return severidad + " - " + descripcion + " (" + fechaCreacion + ")";
    }
}
