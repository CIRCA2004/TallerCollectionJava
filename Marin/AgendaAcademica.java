import java.time.LocalDate;
import java.util.*;

class Actividad {
    private String nombre;
    private String hora;

    public Actividad(String nombre, String hora) {
        this.nombre = nombre;
        this.hora = hora;
    }

    @Override
    public String toString() {
        return hora + " - " + nombre;
    }
}

public class AgendaAcademica {
    private TreeMap<LocalDate, List<Actividad>> agenda = new TreeMap<>();

    // Agregar actividad en una fecha específica
    public void agregarActividad(LocalDate fecha, Actividad act) {
        agenda.computeIfAbsent(fecha, k -> new ArrayList<>()).add(act);
    }

    // Obtener la actividad más próxima (hoy o la siguiente fecha disponible)
    public Map.Entry<LocalDate, List<Actividad>> proximaActividad(LocalDate hoy) {
        return agenda.ceilingEntry(hoy);
    }

    // Generar reporte de actividades entre dos fechas (incluidas)
    public NavigableMap<LocalDate, List<Actividad>> reporte(LocalDate inicio, LocalDate fin) {
        return agenda.subMap(inicio, true, fin, true);
    }

    public static void main(String[] args) {
        AgendaAcademica agenda = new AgendaAcademica();

        // Agregar actividades
        agenda.agregarActividad(LocalDate.of(2023, 5, 10), new Actividad("Examen Matemáticas", "08:00"));
        agenda.agregarActividad(LocalDate.of(2023, 5, 10), new Actividad("Laboratorio Física", "14:00"));
        agenda.agregarActividad(LocalDate.of(2023, 5, 12), new Actividad("Entrega Proyecto", "23:59"));
        agenda.agregarActividad(LocalDate.of(2023, 5, 15), new Actividad("Exposición Historia", "10:00"));

        // Hoy
        LocalDate hoy = LocalDate.of(2023, 5, 11);

        // Mostrar próxima actividad
        Map.Entry<LocalDate, List<Actividad>> proxima = agenda.proximaActividad(hoy);
        System.out.println("Próxima fecha con actividades a partir de " + hoy + ":");
        if (proxima != null) {
            System.out.println(proxima.getKey() + " -> " + proxima.getValue());
        } else {
            System.out.println("No hay actividades futuras");
        }

        // Reporte entre dos fechas
        LocalDate inicio = LocalDate.of(2023, 5, 10);
        LocalDate fin = LocalDate.of(2023, 5, 12);
        System.out.println("\nReporte de actividades entre " + inicio + " y " + fin + ":");
        for (Map.Entry<LocalDate, List<Actividad>> entry : agenda.reporte(inicio, fin).entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
