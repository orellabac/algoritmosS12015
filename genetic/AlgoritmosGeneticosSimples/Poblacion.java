package AlgoritmosGeneticosSimples;

public class Poblacion {

    Individuo[] individuos;


    // Crear una poblacion
    public Poblacion(int tammanoPoblacion, boolean inicializar) {
        individuos = new Individuo[tammanoPoblacion];
        // inicializar poblacion
        if (inicializar) {
            // Iterar y crear individuos
            for (int i = 0; i < tamanno(); i++) {
                Individuo nuevoindividuo = new Individuo();
                nuevoindividuo.generarIndividuo();
                guardarIndividuo(i, nuevoindividuo);
            }
        }
    }

    /* Getters */
    public Individuo obtenerIndividuo(int index) {
        return individuos[index];
    }

    public Individuo obtenerFittest() {
        Individuo mejor = individuos[0];
        // Iterar entre los individuos y devolver el mejor
        for (int i = 0; i < tamanno(); i++) {
            if (mejor.obtenerFitness() <= obtenerIndividuo(i).obtenerFitness()) {
                mejor = obtenerIndividuo(i);
            }
        }
        return mejor;
    }

    // Obtener el tamanno de la poblacion
    public int tamanno() {
        return individuos.length;
    }

    // Guardar el individuo
    public void guardarIndividuo(int index, Individuo indiv) {
        individuos[index] = indiv;
    }
}