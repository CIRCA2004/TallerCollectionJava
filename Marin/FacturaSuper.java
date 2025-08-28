import java.util.LinkedHashMap;
import java.util.Map;

class Detalle {
    private String nombre;
    private double precioUnitario;
    private int cantidad;

    public Detalle(String nombre, double precioUnitario, int cantidad) {
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
    }

    public void sumarCantidad(int cant) {
        this.cantidad += cant;
    }

    public double getSubtotal() {
        return cantidad * precioUnitario;
    }

    @Override
    public String toString() {
        return nombre + " x" + cantidad + " @ $" + precioUnitario + " = $" + getSubtotal();
    }
}

public class FacturaSuper {
    public static void main(String[] args) {
        LinkedHashMap<String, Detalle> factura = new LinkedHashMap<>();

        // Simulación de escaneo de productos
        escanear(factura, "001", "Leche", 3000, 1);
        escanear(factura, "002", "Pan", 2500, 2);
        escanear(factura, "003", "Huevos", 12000, 1);
        escanear(factura, "002", "Pan", 2500, 1); // Se vuelve a escanear Pan
        escanear(factura, "004", "Café", 18000, 1);

        // Calcular totales
        double subtotal = 0;
        for (Detalle d : factura.values()) {
            subtotal += d.getSubtotal();
        }
        double iva = subtotal * 0.19;
        double total = subtotal + iva;

        // Imprimir factura final
        System.out.println("=== FACTURA SUPERMERCADO ===");
        for (Map.Entry<String, Detalle> entry : factura.entrySet()) {
            System.out.println(entry.getValue());
        }
        System.out.println("----------------------------");
        System.out.println("Subtotal: $" + subtotal);
        System.out.println("IVA (19%): $" + iva);
        System.out.println("TOTAL: $" + total);
    }

    private static void escanear(LinkedHashMap<String, Detalle> factura, String codigo,
                                String nombre, double precio, int cantidad) {
        if (factura.containsKey(codigo)) {
            factura.get(codigo).sumarCantidad(cantidad);
        } else {
            factura.put(codigo, new Detalle(nombre, precio, cantidad));
        }
    }
}
