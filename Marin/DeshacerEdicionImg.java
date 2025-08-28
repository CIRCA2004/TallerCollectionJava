import java.util.Stack;

// Clase base para operaciones
abstract class Operacion {
    public abstract String descripcion();
}

// Operaciones concretas
class Rotar extends Operacion {
    private int grados;
    public Rotar(int grados) { this.grados = grados; }
    public String descripcion() { return "Rotar " + grados + "°"; }
}

class Recortar extends Operacion {
    private String area;
    public Recortar(String area) { this.area = area; }
    public String descripcion() { return "Recortar área " + area; }
}

class Brillo extends Operacion {
    private int nivel;
    public Brillo(int nivel) { this.nivel = nivel; }
    public String descripcion() { return "Ajustar brillo a " + nivel; }
}

public class DeshacerEdicionImg {
    public static void main(String[] args) {
        Stack<Operacion> historial = new Stack<>();

        // Secuencia de operaciones
        historial.push(new Rotar(90));
        historial.push(new Recortar("100x100"));
        historial.push(new Brillo(50));
        historial.push(new Rotar(180));
        historial.push(new Brillo(70));

        System.out.println("Historial inicial:");
        mostrarHistorial(historial);

        // Deshacer 3 operaciones
        for (int i = 0; i < 3; i++) {
            if (!historial.isEmpty()) {
                Operacion op = historial.pop();
                System.out.println("Deshacer: " + op.descripcion());
            }
        }

        System.out.println("\nHistorial tras 3 undo:");
        mostrarHistorial(historial);
    }

    static void mostrarHistorial(Stack<Operacion> historial) {
        if (historial.isEmpty()) {
            System.out.println("Sin operaciones.");
            return;
        }
        for (Operacion op : historial) {
            System.out.println(op.descripcion());
        }
    }
}