import java.util.Scanner;

public class DarVuelto {
	

	static int[] DENOMINACIONES = new int[] {1,5,10,25,50,100,500}; 
	static int INDICE_MONTO = DENOMINACIONES.length;

	
	/**
	 * Implementa un algoritmo para dar el vuelto con el minimo numero de monedas posible
	 * empleando una tecnica voraz
	 */
	public static int[] greedyVuelto(int n)
	{
		//El conjunto de candidatos esta dado por el arreglo de DENOMINACIONES
		//Usaremos el indice C para determinar el candidato actual
		int C=DENOMINACIONES.length-1; 
		int[] S = new int[] {0,0,0,0,0,0,0,n}; //S es una solucion parcial salida

		while (!esSolucion(S) && C>0) {
			int X = DENOMINACIONES[C];
			int monto = S[INDICE_MONTO];
			int monedas = monto / X;
			if (monedas > 0) // Es factible?
			{
				S[C] = monedas; //Incluir en la solucion
				S[INDICE_MONTO] = monto % X; //Actualizamos la solucion
			}
			else {
				S[C] = 0;
			}
			--C; //quitamos X del conjunto de candidatos
		}
		return S;
	}



	public static boolean esSolucion(int[] solucion)
	{
		return solucion[INDICE_MONTO] == 0;
	}


	public static void main(String[] args)
	{
		System.out.println("Por favor digite el monto:");
		Scanner cin = new Scanner(System.in);
		int vueltoPara = cin.nextInt();

		int[] solucion = greedyVuelto(vueltoPara);

		if (!esSolucion(solucion))
		{
			System.out.println("No se encontro solucion");
		}
		else {
			System.out.println("La solucion es:");
			for (int i=0;i<INDICE_MONTO;i++)
			{
				System.out.println(" monedas de " + DENOMINACIONES[i] + " = " + solucion[i]);
			}
		}
	}
}