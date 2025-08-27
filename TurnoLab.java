import java.util.LinkedList;
import java.util.Scanner;
public class TurnoLab {
    public static void main(String[] args) {
        LinkedList<String> cola = new LinkedList<>();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n ** Menú Turnos de Laboratorio **");
            System.out.println("1. Agregar estudiante (turno normal)");
            System.out.println("2. Agregar estudiante (turno preferencial)");
            System.out.println("3. Atender siguiente estudiante");
            System.out.println("4. Mostrar cola de espera");
            System.out.println("0. Salir");
            System.out.print("Seleccione opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Nombre del estudiante: ");
                    String nombre = sc.nextLine();
                    cola.addLast(nombre);
                    System.out.println(nombre + " agregado al final de la cola.");
                }
                case 2 -> {
                    System.out.print("Nombre del estudiante: ");
                    String nombre = sc.nextLine();
                    cola.addFirst(nombre); // entra al inicio
                    System.out.println(nombre + " agregado al inicio de la cola (preferencial).");
                }
                case 3 -> {
                    if (cola.isEmpty()) {
                        System.out.println("No hay estudiantes en la cola.");
                    } else {
                        String atendido = cola.pollFirst(); // atiende al primero
                        System.out.println("Atendiendo a: " + atendido);
                    }
                }
                case 4 -> {
                    System.out.println("Cola actual: " + cola);
                }
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);

        sc.close();
    }
}


