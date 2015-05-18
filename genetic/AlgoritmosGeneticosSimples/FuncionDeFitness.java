package AlgoritmosGeneticosSimples;

public class FuncionDeFitness {

    static byte[] solucion = new byte[64];

    // Establecer una solucion candidata como un arreglo de bytes
    public static void establecerSolucion(byte[] nuevaSolucion) {
        solucion = nuevaSolucion;
    }

    static void establecerSolucion(String nuevaSolucion) {
        solucion = new byte[nuevaSolucion.length()];
        for (int i = 0; i < nuevaSolucion.length(); i++) {
            String caracter = nuevaSolucion.substring(i, i + 1);
            if (caracter.contains("0") || caracter.contains("1")) {
                solucion[i] = Byte.parseByte(caracter);
            } else {
                solucion[i] = 0;
            }
        }
    }

    // Calculate inidividuals fittness by comparing it to our candidate solution
    static int obtenerFitness(Individuo individuo) {
        int fitness = 0;
        // Iterar por todos los genes del individuo y compararlo
        // con los de nuestro candidato.
        for (int i = 0; i < individuo.tamanno() && i < solucion.length; i++) {
            if (individuo.obtenerGen(i) == solucion[i]) {
                fitness++;
            }
        }
        return fitness;
    }
    
    // Obtener el fitness optimo
    static int obtenerMaxFitness() {
        int maxFitness = solucion.length;
        return maxFitness;
    }
}
