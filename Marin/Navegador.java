import java.util.ArrayDeque;

public class Navegador {
    private ArrayDeque<String> atras = new ArrayDeque<>();
    private ArrayDeque<String> adelante = new ArrayDeque<>();
    private String paginaActual;

    public void visitar(String url) {
        if (paginaActual != null) {
            atras.push(paginaActual); // guardo la actual en "atrás"
        }
        paginaActual = url;
        adelante.clear(); // visitar nueva página borra el historial "adelante"
        System.out.println("Visitas: " + paginaActual);
    }

    public void irAtras() {
        if (atras.isEmpty()) {
            System.out.println("No hay páginas atrás");
            return;
        }
        adelante.push(paginaActual); // la actual va a "adelante"
        paginaActual = atras.pop();  // recupero la última de "atrás"
        System.out.println("Atrás → " + paginaActual);
    }

    public void irAdelante() {
        if (adelante.isEmpty()) {
            System.out.println("No hay páginas adelante");
            return;
        }
        atras.push(paginaActual);    // la actual va a "atrás"
        paginaActual = adelante.pop(); // recupero la última de "adelante"
        System.out.println("Adelante → " + paginaActual);
    }

    public static void main(String[] args) {
        Navegador nav = new Navegador();

        nav.visitar("google.com");
        nav.visitar("openai.com");
        nav.visitar("github.com");

        nav.irAtras();     // vuelve a openai
        nav.irAtras();     // vuelve a google
        nav.irAdelante();  // adelante a openai
        nav.visitar("stackoverflow.com"); // visitar nueva limpia "adelante"
        nav.irAdelante();  // ya no hay adelante
    }
}
