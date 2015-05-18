package AlgoritmosGeneticosSimples;

public class Individuo {

    static int largoDeGenesPorDefecto = 64;
    private byte[] genes = new byte[largoDeGenesPorDefecto];
    // Cache
    private int fitness = 0;

    // crea un Individuo al azar
    public void generarIndividuo() {
        for (int i = 0; i < tamanno(); i++) {
            byte gen = (byte) Math.round(Math.random());
            genes[i] = gen;
        }
    }


    // Utilice esto para cambiar el largo de los genes
    public static void establecerLargoDeGenesPorDefecto(int largo) {
        largoDeGenesPorDefecto = largo;
    }
    
    public byte obtenerGen(int index) {
        return genes[index];
    }

    public void establecerGen(int index, byte valor) {
        genes[index] = valor;
        fitness = 0;
    }

    public int tamanno() {
        return genes.length;
    }

    public int obtenerFitness() {
        if (fitness == 0) {
            fitness = FuncionDeFitness.obtenerFitness(this);
        }
        return fitness;
    }

    @Override
    public String toString() {
        String stringDeGenes = "";
        for (int i = 0; i < tamanno(); i++) {
            stringDeGenes += obtenerGen(i);
        }
        return stringDeGenes;
    }
}