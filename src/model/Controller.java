package model;

import java.util.Random;

public class Controller {

    private Equipo[] equipos;
    private Arbitro[] arbitros;
    private final Random random = new Random();

    private final int CANTIDAD_EQUIPOS = 4;
    private final int CANTIDAD_ARBITROS = 4;


    /**
     * Constructor de la clase Controller para inicializar variables globales.
     *
     * @pre No se requieren precondiciones específicas.
     * @post Se crea una instancia de Controller con un arreglo de personas vacío.
     */
    public Controller() {
        equipos = new Equipo[CANTIDAD_EQUIPOS];
        arbitros = new Arbitro[CANTIDAD_ARBITROS];
    }

    public String fixture() {
        String fixture = "";
        Random random = new Random();
        int equipo1 = random.nextInt(4);
        int equipo2;
        do {
            equipo2 = random.nextInt(4);
        } while (equipo2 == equipo1);

        fixture += "Partido 1: Equipo " + equipo1 + " vs Equipo " + equipo2;
        fixture += "\n";

        do {
            equipo1 = random.nextInt(4);
            equipo2 = random.nextInt(4);
        } while (equipo2 == equipo1);

        fixture += "Partido 2: Equipo " + equipo1 + " vs Equipo " + equipo2;
        return fixture;
    }

          public void prechargeinfo(){
        
        arbitros[0] = new ArbitroPrincipal("Alberto", 40);
        arbitros[1] = new JuezDeLinea("Juan", 35);

    
        equipos[0] = new Equipo();
        equipos[0].nombreEquipo = "Halcones";

     
        equipos[0].agregarJugador(new JugadorHockey("Javier", 28, Posicion.PORTERO), 0); 
        equipos[0].agregarJugador(new JugadorHockey("Santiago", 25, Posicion.DEFENSA), 1);
        equipos[0].agregarJugador(new JugadorHockey("Felipe", 30, Posicion.ALA), 2);
        equipos[0].agregarJugador(new JugadorHockey("Camilo", 27, Posicion.CENTRO), 3);
        equipos[0].agregarJugador(new JugadorHockey("Daniel", 29, Posicion.DEFENSA), 4);
        equipos[0].agregarJugador(new JugadorHockey("Andres", 26, Posicion.ALA), 5);
    }
    

    public String simularJugadaGol() {
  

        StringBuilder jugada = new StringBuilder();
        Equipo equipoAtacante = equipos[0]; 
        JugadorHockey[] jugadoresAtacantes = equipoAtacante.getJugadores();

        
        int[] indicesJugadores = new int[random.nextInt(3) + 3]; 
        for (int i = 0; i < indicesJugadores.length; i++) {
            indicesJugadores[i] = random.nextInt(jugadoresAtacantes.length);
        }

        
        boolean porteroIncluido = false;
        for (int indice : indicesJugadores) {
            if (jugadoresAtacantes[indice].getPosicion() == Posicion.PORTERO) {
                porteroIncluido = true;
                break;
            }
        }
        if (!porteroIncluido) {
            indicesJugadores[0] = 0; 
        }

       
        int pases = 0;
        int jugadorActual = indicesJugadores[0];
        while (pases < 5) {
            int jugadorSiguiente = indicesJugadores[random.nextInt(indicesJugadores.length)];
            if (jugadorSiguiente != jugadorActual) {
                jugada.append("Jugador numero ").append(jugadoresAtacantes[jugadorActual].numero)
                        .append(" se la pasa a Jugador ").append(jugadoresAtacantes[jugadorSiguiente].numero)
                        .append("\n");
                
                
                Arbitro arbitroAleatorio = arbitros[random.nextInt(2)]; 
                jugada.append(arbitroAleatorio.getClass().getSimpleName()).append(" ").append(arbitroAleatorio.desplazarse()).append("\n");

                jugadorActual = jugadorSiguiente;
                pases++;
            }
        }

       
        jugada.append("Jugador ").append(jugadoresAtacantes[jugadorActual].numero).append(" entra el disco en la red. ¡Gol!");
        return jugada.toString();
    }
}

