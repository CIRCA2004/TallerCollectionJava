package Cuervo;
import java.util.LinkedHashMap;
import java.util.Map;

public class CacheLRU<K, V> extends LinkedHashMap<K, V> {
    private final int capacidadMaxima;

    public CacheLRU(int capacidadMaxima) {
        // initialCapacity=16, loadFactor=0.75f, accessOrder=true
        super(16, 0.75f, true);
        this.capacidadMaxima = capacidadMaxima;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacidadMaxima;
    }

    // Método de prueba
    public static void main(String[] args) {
        CacheLRU<Integer, String> cache = new CacheLRU<>(3);

        // Insertar 3 elementos
        cache.put(1, "Uno");
        cache.put(2, "Dos");
        cache.put(3, "Tres");
        System.out.println("Inicial: " + cache);

        // Acceder a la clave 1 → se promueve a “más reciente”
        cache.get(1);
        System.out.println("Acceso a 1: " + cache);

        // Insertar un nuevo elemento (clave 4) → se elimina el menos usado (clave 2)
        cache.put(4, "Cuatro");
        System.out.println("Tras insertar 4: " + cache);

        // Acceder a la clave 3 → se promueve
        cache.get(3);
        System.out.println("Acceso a 3: " + cache);

        // Insertar nuevo elemento (clave 5) → se elimina el menos usado (clave 1)
        cache.put(5, "Cinco");
        System.out.println("Tras insertar 5: " + cache);
    }
}

