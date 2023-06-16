import java.util.ArrayList;
import java.util.List;

public class App {

    //-----------------
    //obervador 
    //lobo
    //caperusita
    //uvas
    //----------------
    boolean caballeroIzq;
    boolean loboIzq;
    boolean caperucitaIzq;
    boolean uvasIzq;

    App(boolean caballeroIzq, boolean loboIzq, boolean caperucitaIzq, boolean uvasIzq) {
        this.caballeroIzq = caballeroIzq;
        this.loboIzq = loboIzq;
        this.caperucitaIzq = caperucitaIzq;
        this.uvasIzq = uvasIzq;
    }

    boolean esEstadoFinal() {
        return !caballeroIzq && !loboIzq && !caperucitaIzq && !uvasIzq;
    }

    boolean esValido() {
        // Verificar las restricciones del problema
        if ((loboIzq && caperucitaIzq && !caballeroIzq) || (uvasIzq && caperucitaIzq && !caballeroIzq)) {
            return false;
        }
        return true;
    }

    

    // Función recursiva para buscar una solución
        private static boolean buscarSolucion(App estadoActual, List<App> camino) {
        if (!estadoActual.esValido()) {
            return false;
        }

        camino.add(estadoActual);

        if (estadoActual.esEstadoFinal()) {
            return true;
        }

        // Intentar todas las posibles combinaciones de movimiento
        boolean encontrado = false;
        App nuevoEstado;

        // Mover al caballero solo
        nuevoEstado = new App(!estadoActual.caballeroIzq, estadoActual.loboIzq, estadoActual.caperucitaIzq, estadoActual.uvasIzq);
        if (!camino.contains(nuevoEstado)) {
            encontrado = buscarSolucion(nuevoEstado, camino);
        }

        // Mover al caballero y al lobo
        if (!encontrado) {
            nuevoEstado = new App(!estadoActual.caballeroIzq, !estadoActual.loboIzq, estadoActual.caperucitaIzq, estadoActual.uvasIzq);
            if (!camino.contains(nuevoEstado)) {
                encontrado = buscarSolucion(nuevoEstado, camino);
            }
        }

        // Mover al caballero y a la caperucita
        if (!encontrado) {
            nuevoEstado = new App(!estadoActual.caballeroIzq, estadoActual.loboIzq, !estadoActual.caperucitaIzq, estadoActual.uvasIzq);
            if (!camino.contains(nuevoEstado)) {
                encontrado = buscarSolucion(nuevoEstado, camino);
            }
        }

        // Mover al caballero y las uvas
        if (!encontrado) {
            nuevoEstado = new App(!estadoActual.caballeroIzq, estadoActual.loboIzq, estadoActual.caperucitaIzq, !estadoActual.uvasIzq);
            if (!camino.contains(nuevoEstado)) {
                encontrado = buscarSolucion(nuevoEstado, camino);
            }
        }

        if (!encontrado) {
            camino.remove(estadoActual);
        }

        return encontrado;
    }
    public static void main(String[] args) {
        App estadoInicial = new App(true, true, true, true);
        List<App> camino = new ArrayList<>();

        if (buscarSolucion(estadoInicial, camino)) {
            System.out.println("¡Solución encontrada!");

            for (App estado : camino) {
                System.out.println("Caballero: " + (estado.caballeroIzq ? "Izquierda" : "Derecha") +
                        ", Lobo: " + (estado.loboIzq ? "Izquierda" : "Derecha") +
                        ", Caperucita: " + (estado.caperucitaIzq ? "Izquierda" : "Derecha") +
                        ", Uvas: " + (estado.uvasIzq ? "Izquierda" : "Derecha"));
            }
        } else {
            System.out.println("No se encontró una solución.");
        }
    }
}
