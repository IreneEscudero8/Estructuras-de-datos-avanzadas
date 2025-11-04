/*
 * PROYECTO ESTRUCTURA DE DATOS AVANZADAS
 * Equipo: Luis Fernando Reyes, Andre Gorostieta, Irene Escudero
 * Clase de prueba para verificar los diferentes metodos de cálculo de distancia de edición
 */
public class Prueba{

    private static final int COSTO_HUECO = 1;      // Delta
    private static final int COSTO_SUSTITUCION = 2; // Alfa
    
    private static final CalculaDistancia CALCULATOR = new CalculaDistancia(COSTO_HUECO, COSTO_SUSTITUCION);

    public static void main(String[] args) {
        System.out.println("Costos de la práctica: Hueco (Delta) = 1, Sustitución (Alfa) = 2");
        // Cadenas Idénticas (Distancia = 0)
        String a1 = "hola";
        String b1 = "hola";
        int esperado1 = 0;
        int obtenido1 = CALCULATOR.editDistanceDP(a1, b1);
        System.out.println("1. Idénticas, resultado esperado: " + esperado1 +" ,obtenido: "+ obtenido1);

        // Sustitución Simple (Costo = 2)
        String a2 = "casa";
        String b2 = "cosa";
        int esperado2 = 2; 
        int obtenido2 = CALCULATOR.editDistanceDP(a2, b2);
        System.out.println("2. Sustitución, resultado esperado: " + esperado2 +" ,obtenido: "+ obtenido2);

        // Inserción Simple (Costo = 1)
        String a3 = "sol";
        String b3 = "solo";
        int esperado3 = 1;
        int obtenido3 = CALCULATOR.editDistanceDP(a3, b3);
        System.out.println("3. Inserción, resultado esperado: " + esperado3 +" ,obtenido: "+ obtenido3);

        // Borrado (Costo = 1)
        String a4 = "casas";
        String b4 = "casa";
        int esperado4 = 1;
        int obtenido4 = CALCULATOR.editDistanceDP(a4, b4);
        System.out.println("4. Borrado, resultado esperado: " + esperado4 +" ,obtenido: "+ obtenido4);

        // Transformación Larga (Borrar + Sustituir + Insertar)
        String a5 = "sabor";
        String b5 = "color";
        // s->c (2) + a->o (2) + b->l (2) + o->o (0) + r->r (0) = 6
        int esperado5 = 6;
        int obtenido5 = CALCULATOR.editDistanceDP(a5, b5);
        System.out.println("5. Transformación larga, resultado esperado: " + esperado5 +" ,obtenido: "+ obtenido5);
        
        // Cadena vacía 
        String a6 = "";
        String b6 = "prueba";
        int esperado6 = 6;
        int obtenido6 = CALCULATOR.editDistanceDP(a6, b6);
        System.out.println("6. Vacia, resultado esperado: " + esperado6 +" ,obtenido: "+ obtenido6);
    }
}
