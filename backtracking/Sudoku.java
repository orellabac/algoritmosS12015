import java.util.*;

public class Sudoku implements Iterable<Sudoku> {

   // tamanno del Sudoku
  static int N = 9;

  //Representacion del estado
  int sudoku[][];
  public int filaActual;
  public int columnaActual;

  
  public Sudoku(int[][] sudoku,int filaActual, int columnaActual) {
    this.sudoku = new int[N][N];
    //Copia los datos
    for(int fila=0;fila<N;fila++) {
      for (int columna=0;columna<N;columna++) {
        this.sudoku[fila][columna] = sudoku[fila][columna];
      }
    }
    //Establece la casilla actual
    this.filaActual = filaActual;
    this.columnaActual = columnaActual;
  }


   // utilitario para imprimir el sudoku
   public void imprimir() {
    for (int fila = 0; fila < N; fila++) {
     for (int columna = 0; columna < N; columna++)
      System.out.print(sudoku[fila][columna]+ " ");
     System.out.println();
    }
   }

   //funcion objetivo
   public boolean estaResuelto() {
      return filaActual == N-1 && columnaActual == N-1;
   }



   public void actualizarCasillaActual(int valor) 
   {
    sudoku[filaActual][columnaActual] = valor;
   }


   //Calcula el siguiente estado posible
   //para generar nuevos estados de sudoku
   //Atencion a restricciones implicitas por ejemplo para la fila y la columna
   public Sudoku siguiente() {
    Sudoku siguiente = hacerCopia();
    int fila = this.filaActual;
    int columna =  this.columnaActual;

    do {
      // incrementamos la columna => col++
      columna++;
      // si la columna > 8, entonces columna = 0 y fila++
      // porque se llego al final de la fila y hay que
      // pasar a la siguiente fila
      if (columna > 8) {
       columna = 0;
       fila++;
      }
      // se llego al final de la matrix retornar nulo
      if (fila > 8)
         return null; // se llego al final
    }
      while( siguiente.sudoku[fila][columna]!=0); //Esto poda explorar nodos que no tengan sentido

    siguiente.filaActual = fila;
    siguiente.columnaActual = columna;
    //Esto devuelve un sucesor pero que todavia no esta inicializado
    //es decir en el no se han tratado nuevos valores
    return siguiente;
   }
 

  //Indica si este sudoko podria colocar ese valor en la casilla actual y aun asi ser valido
  //chequeo de restricciones explicitas e implicitas
  public boolean esValido(int valor) {

    if (sudoku[filaActual][columnaActual] != 0) {
     throw new RuntimeException(
       "No se debe llamar para un sudoku que ya tiene valores en la casilla actual");
    }
    //Revizar si en esta fila no esta el valor en ninguna de las columnas
    for (int columna = 0; columna < 9; columna++) {
     if (sudoku[filaActual][columna] == valor)
      return false;
    }
    //Revizar si el valor no esta en ninguna de las columnas
    for (int fila = 0; fila < 9; fila++) {
     if (sudoku[fila][columnaActual] == valor)
      return false;
    }


    //Ok. Ahora ahi que ver si en el cuadrito de 3x3 el valor no esta
    //presente

    int indiceFilaInicioCuadrito3x3    = 3 * (filaActual / 3);
    int indiceColumnaInicioCuadrito3x3 = 3 * (columnaActual / 3);
    int indiceFilaFinalCuadrito3x3     = indiceFilaInicioCuadrito3x3 + 2;
    int indiceColumnaFinalCuadrito3x3  = indiceColumnaInicioCuadrito3x3  + 2;

    for (int fila = indiceFilaInicioCuadrito3x3; fila <= indiceFilaFinalCuadrito3x3; fila++)
     for (int columna = indiceColumnaInicioCuadrito3x3; columna <= indiceColumnaFinalCuadrito3x3; columna++)
      if (sudoku[fila][columna] == valor)
       return false;

    return true;
 }

  public Sudoku hacerCopia()
  {
    return new Sudoku(this.sudoku,this.filaActual, this.columnaActual);
  }


  public Iterator<Sudoku> iterator() {
    return new HijosSudoku(this.siguiente());
  }


 public class HijosSudoku implements Iterator<Sudoku>
 {
    int valor;
    Sudoku sudoku;
    
    public HijosSudoku(Sudoku sudoku)
    {
      this.sudoku = sudoku;
      valor = 1;
    }

    public boolean hasNext() {
      //System.out.print("tratando sudoku" + sudoku.filaActual + " " + sudoku.columnaActual);
      while(sudoku!=null && valor<10)
      { 
        //System.out.print("tratando" + valor);
        boolean valido = sudoku.esValido(valor);
        if (!valido)
        {
          valor++;
        }
         else return true;
      }
      return false; 
    }

    public Sudoku next() {
      if (hasNext())
      {
          Sudoku copia = sudoku.hacerCopia();
          copia.actualizarCasillaActual(valor);
          valor++; //Ya este valor fue usado
          return copia;
      }
      throw new RuntimeException("Ya no hay mas valores");
    }

    public void remove() {}
 }


/*
1. If N is a goal node, return “success”
2. If N is a leaf node, return “failure”
3. For each child C of N,
3.1. Explore C
3.1.1. If C was successful, return “success”
4. Return “failure”
*/

  //Si el sudoku ser resuelve devuelve verdadero, sino falso
  Sudoku resolver(String pad) {
    System.out.println(pad + "Resolver " + filaActual + " " + columnaActual);
    this.imprimir();
    if (this.estaResuelto()) return this;
    Iterator<Sudoku> hijos = this.iterator();
    if (!hijos.hasNext()) { 
      System.out.println("no hay mas");
      return null;
    }
    for (Sudoku hijo : this)
    {
      Sudoku resuelto = hijo.resolver(pad + ">");
      if (resuelto!=null) return resuelto;
    }
    return null;
  }


   // sudoku ejemplo
   static int sudokuPrueba[][] = { 
     { 3, 0, 6,    5, 0, 8,    4, 0, 0 }, 
     { 5, 2, 0,    0, 0, 0,    0, 0, 0 }, 
     { 0, 8, 7,    0, 0, 0,    0, 3, 1 }, 

     { 0, 0, 3,    0, 1, 0,    0, 8, 0 }, 
     { 9, 0, 0,    8, 6, 3,    0, 0, 5 }, 
     { 0, 5, 0,    0, 9, 0,    6, 0, 0 }, 

     { 1, 3, 0,    0, 0, 0,    2, 5, 0 }, 
     { 0, 0, 0,    0, 0, 0,    0, 7, 4 }, 
     { 0, 0, 5,    2, 0, 6,    3, 0, 0 } };


   static int sudokuPrueba2[][] = { 
     { 1, 0, 0,    0, 0, 0,    0, 0, 0 }, 
     { 0, 0, 0,    0, 0, 0,    0, 0, 0 }, 
     { 0, 0, 0,    0, 0, 0,    0, 0, 0 }, 

     { 0, 0, 0,    0, 0, 0,    0, 0, 0 }, 
     { 0, 0, 0,    0, 0, 0,    0, 0, 0 }, 
     { 0, 0, 0,    0, 0, 0,    0, 0, 0 }, 

     { 0, 0, 0,    0, 0, 0,    0, 0, 0 }, 
     { 0, 0, 0,    0, 0, 0,    0, 0, 0 }, 
     { 0, 0, 0,    0, 0, 0,    0, 0, 0 } };


   public static void main(String[] args) {
      Sudoku sudoku = new Sudoku(sudokuPrueba,0,0);
      Sudoku resuelto = sudoku.resolver("");
      if (resuelto==null) {
       System.out.println("EL SUDOKU NO PUEDE SER RESUELTO.");
       return;
      }
      System.out.println("SOLUTION\n");
      resuelto.imprimir();
   }

}