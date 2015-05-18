package AlgoritmosGeneticosSimples;

public class Algoritmo {

    /* parametros del Algoritmos Geneticos */
    private static final double tasaUniforme = 0.5;
    private static final double tasaDeMutacion = 0.015;
    private static final int tamannoDelTorneo = 5;
    private static final boolean elitismo = true;

    
    // Evolucionar una poblacion
    public static Poblacion evolucionarPoblacion(Poblacion pop) {
        Poblacion nuevaPoblacion = new Poblacion(pop.tamanno(), false);

        // Mantener nuestro mejor individuo
        if (elitismo) {
            nuevaPoblacion.guardarIndividuo(0, pop.obtenerFittest());
        }

        // Crossover population
        int elitismoOffset;
        if (elitismo) {
            elitismoOffset = 1;
        } else {
            elitismoOffset = 0;
        }
        //Iterar por todo el largo de la poblacion y crear
        //nuevos individuos con cruzamiento.
        for (int i = elitismoOffset; i < pop.tamanno(); i++) {

            Individuo indiv1 = seleccionPorTorneo(pop);
            Individuo indiv2 = seleccionPorTorneo(pop);
            Individuo nuevoIndividuo = cruzamiento(indiv1, indiv2);
            nuevaPoblacion.guardarIndividuo(i, nuevoIndividuo);
        }

        // Mutar la poblacion
        for (int i = elitismoOffset; i < nuevaPoblacion.tamanno(); i++) {
            mutar(nuevaPoblacion.obtenerIndividuo(i));
        }

        return nuevaPoblacion;
    }

    // Cruzamiento de los individuos
    private static Individuo cruzamiento(Individuo indiv1, Individuo indiv2) {
        Individuo nuevaSolucion = new Individuo();
        // Interar a traves de los genes
        for (int i = 0; i < indiv1.tamanno(); i++) {
            // Cruzamiento
            if (Math.random() <= tasaUniforme) {
                nuevaSolucion.establecerGen(i, indiv1.obtenerGen(i));
            } else {
                nuevaSolucion.establecerGen(i, indiv2.obtenerGen(i));
            }
        }
        return nuevaSolucion;
    }

    // Mutar un individuo
    private static void mutar(Individuo indiv) {
        // Iterar entre los genes
        for (int i = 0; i < indiv.tamanno(); i++) {
            if (Math.random() <= tasaDeMutacion) {
                // Crear un gen aleatorio
                byte gen = (byte) Math.round(Math.random());
                indiv.establecerGen(i, gen);
            }
        }
    }

    // Seleccionar individuos para el cruce
    private static Individuo seleccionPorTorneo(Poblacion pop) {
        // Crear una poblacion para el torneo
        Poblacion torneo = new Poblacion(tamannoDelTorneo, false);
        //Para cada lugar en el torneo obtener un individuo al azar
        for (int i = 0; i < tamannoDelTorneo; i++) {
            int randomId = (int) (Math.random() * pop.tamanno());
            torneo.guardarIndividuo(i, pop.obtenerIndividuo(randomId));
        }
        // Obtener el mejor
        Individuo mejor = torneo.obtenerFittest();
        return mejor;
    }
}
