import java.util.HashMap;
import java.util.Map;

class Producto {
    private String nombre;
    private double precio;
    private int stock;

    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int nuevoStock) {
        this.stock = nuevoStock;
    }

    @Override
    public String toString() {
        return nombre + " - $" + precio + " (Stock: " + stock + ")";
    }
}

public class Ferreteria {
    public static void main(String[] args) {
        HashMap<String, Producto> inventario = new HashMap<>();

        // 1. Agregar productos
        inventario.put("P001", new Producto("Martillo", 25000, 10));
        inventario.put("P002", new Producto("Destornillador", 8000, 0));
        inventario.put("P003", new Producto("Taladro", 120000, 5));
        inventario.put("P004", new Producto("Serrucho", 18000, 0));

        // 2. Actualizar stock
        inventario.get("P001").setStock(7); // Martillo ahora con stock=7
        inventario.get("P003").setStock(0); // Taladro se agotó

        // 3. Consultar precio por código
        String codigo = "P002";
        if (inventario.containsKey(codigo)) {
            System.out.println("Precio de " + codigo + ": $" + inventario.get(codigo).getPrecio());
        } else {
            System.out.println("No existe producto con código " + codigo);
        }

        // 4. Listar productos faltantes (stock = 0)
        System.out.println("\nProductos faltantes:");
        for (Map.Entry<String, Producto> entry : inventario.entrySet()) {
            if (entry.getValue().getStock() == 0) {
                System.out.println(entry.getKey() + " -> " + entry.getValue());
            }
        }
    }
}
