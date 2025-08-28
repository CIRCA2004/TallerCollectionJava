package Cuervo;
import java.math.BigDecimal;
import java.util.TreeMap;
import java.util.Map;

public class TarifasTramos {
    public static void main(String[] args) {
        // TreeMap con la frontera como clave y la tarifa como valor
        TreeMap<Integer, BigDecimal> tarifas = new TreeMap<>();
        tarifas.put(0, new BigDecimal("5000"));
        tarifas.put(1000, new BigDecimal("10000"));
        tarifas.put(2000, new BigDecimal("20000"));
        tarifas.put(3000, new BigDecimal("30000"));

        // Ejemplo de pesos de vehículos
        int[] pesos = {800, 1500, 2500, 3500};

        for (int peso : pesos) {
            BigDecimal tarifa = obtenerTarifa(tarifas, peso);
            System.out.println("Vehículo " + peso + " kg → Tarifa: $" + tarifa);
        }
    }

    // Determinar la tarifa según el peso
    public static BigDecimal obtenerTarifa(TreeMap<Integer, BigDecimal> tarifas, int peso) {
        // Encuentra la entrada cuya clave es <= peso
        Map.Entry<Integer, BigDecimal> entry = tarifas.floorEntry(peso);

        if (entry != null) {
            return entry.getValue();
        } else {
            // Si no hay ninguna frontera menor o igual, se podría usar ceilingEntry
            // según las reglas del negocio (ej: vehículos muy livianos).
            return tarifas.firstEntry().getValue();
        }
    }
}
