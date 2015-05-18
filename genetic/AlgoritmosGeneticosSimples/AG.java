package AlgoritmosGeneticosSimples;

public class AG {

    public static void main(String[] args) {

        // Establecer una solucion candidata
        FuncionDeFitness.establecerSolucion("1111000000000000000000000000000000000000000000000000000000001111");

        // Crear una poblacion inicial
        Poblacion poblacion = new Poblacion(50, true);
        
        // Evolve our Poblacion until we reach an optimum solution
        int contadorDeGeneraciones = 0;
        while (poblacion.obtenerFittest().obtenerFitness() < FuncionDeFitness.obtenerMaxFitness()) {
            contadorDeGeneraciones++;
            System.out.println("Generacion: " + contadorDeGeneraciones + " Mejor: " + poblacion.obtenerFittest().obtenerFitness());
            poblacion = Algoritmo.evolucionarPoblacion(poblacion);
        }
        System.out.println("Solucion encontrada!");
        System.out.println("Generacion: " + contadorDeGeneraciones);
        System.out.println("Genes:");
        System.out.println(poblacion.obtenerFittest());

    }
}
