package Proyecto;


import java.util.Arrays;
import java.util.Random;


/**
 *Esta clase crea un grafo a partir de una matriz de adyacencia. En estre proyecto representará un sistema de vuelos nacionales.
 * Funciona con:
 * - Matriz de conexiones para vuelos
 * - Matriz de pesos (precio de los vuelos)
 * - Matriz de tiempos (tiempo de vuelo)
 **/
public class Grafo_MatrizAdyacencia {

    private int V; //Número de vértices
    private int A; //Número de Aristas
    private int[][] matrizAdyacencia; //Matriz de adyacencia del grafo
    private final float[][] matrizPesosCopy; //Copia de la matriz de pesos. Para restaurar alteraciones
    private float[][] matrizPesos; //Matriz de pesos
    private float[][] matrizTiempo; //Matriz de tiempos de vuelo
    
    /**
     * Método constructor de la clase 
     * @param nodos Numero de nodos del grafo
     */
    public Grafo_MatrizAdyacencia(int nodos) {
        this.V = nodos;
        this.A = 0;
        this.matrizAdyacencia = new int[nodos][nodos];
        this.matrizPesos = new float[nodos][nodos];
        this.matrizPesosCopy = new float[nodos][nodos];
        this.matrizTiempo = new float[nodos][nodos];
        inicializacionMatrizPesos();
        inicializacionMatrizTiempo();
    }

    /**
     * Inicializa la matriz de pesos
     */
    public  void inicializacionMatrizPesos() {
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                matrizPesos[i][j] = 999;
                matrizPesosCopy[i][j] = 999;
            }
        }
    }
    
    /**
     * Inicializa la matriz de tiempos
     */
    public  void inicializacionMatrizTiempo() {
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                matrizTiempo[i][j] = 999;
            }
        }
    }
    
    /**
     * Imprime el grafo a partir de la matriz de adyacencia
     */
    public void imprimirGrafo() {
        for (int v = 0; v < V; v++) {
            System.out.println("Fila " + v + ":");
            for (int w = 0; w < V; w++) {
                System.out.println(matrizAdyacencia[v][w] + " ");
            }
            System.out.println("");
        }
    }
    
    /**
     *  Imprime la matriz de pesos (precios)
     */
    public void imprimirGrafoPesos() {

        for (int v = 0; v < V; v++) {
            System.out.println("Fila " + v + ":");
            for (int w = 0; w < V; w++) {
                System.out.println(matrizPesos[v][w] + " ");
            }
            System.out.println("");
        }
    }
    
    /**
     * Imprime la matriz de tiempos
     */
    public void imprimirGrafoTiempo() {

        for (int v = 0; v < V; v++) {
            System.out.println("Fila " + v + ":");
            for (int w = 0; w < V; w++) {
                System.out.println(matrizTiempo[v][w] + " ");
            }
            System.out.println("");
        }
    }
    
    /**
     *  Agrega una arista al grafo
     * @param u Nodo origen
     * @param v Nodo destino
     * @param peso Peso de la arista
     * @param tiempo Valor de tiempo
     */
        public void agregarArista(int u, int v, int peso, int tiempo) {
        matrizPesos[u][v] = peso;
        matrizPesos[v][u] = peso;
        matrizPesosCopy[u][v] = peso;
        matrizPesosCopy[v][u] = peso;
        matrizTiempo[u][v] = tiempo;
        matrizTiempo[v][u] = tiempo;
        matrizAdyacencia[u][v] = 1;
        matrizAdyacencia[v][u] = 1;
        A++;
        

    }

   /**
    * Algoritmo de Prim para hallar el árbol recubridor mínimo del grafo 
    * @param matriz Matriz con la que trabajará el algortimo (precios o tiempo)
    * @param mapCiudades Mapeo de las ciudades para la salida de texto a la GUI
    * @param esTiempo Variable de control que permite saber si se está trabajando con el tiempo o con el precio para dar una salida correcta
    * @return Array de String's que contiene el conjunto de vuelos, el precio total y el tiempo total
    */
    public  String[] metodoPrim(float[][] matriz, String[] mapCiudades, boolean esTiempo) {
            boolean [] mst = new boolean[V];
            float [] pesos = new float [V];
            Resultado [] resultado = new Resultado [V];

            for (int i = 0; i < V; i++) {
                pesos[i] = Float.MAX_VALUE;
                resultado[i] = new Resultado();
            }
            pesos[0]=0;
            resultado[0].padre=-1;
            for (int i = 0; i < V; i++) {
                int vertice = minVert(mst, pesos);
                mst[vertice]= true;
                for (int j = 0; j < V; j++) {
                    if (matriz[vertice][j]>0) {
                        if (mst[j]==false && matriz[vertice][j]< pesos[j]) {
                            pesos[j] = matriz[vertice][j];
                            resultado[j].padre=vertice;
                            resultado[j].peso = pesos[j];
                        }                        
                    }                                        
                }                
            }
            return printPrim(resultado, mapCiudades, esTiempo);
        }
    
           /**
            * Halla el vértice que cuesta el mínimo
            * @param mst Banderas
            * @param pesos Arreglo de pesos
            * @return El vertice en cuestión
            */
        int minVert( boolean [] mst, float [] pesos){
            float minPeso = Integer.MAX_VALUE;
            int vertice = -1;

            for (int i = 0; i < V; i++) {
                if (mst[i]== false && minPeso > pesos [i]) {
                    minPeso =pesos [i];
                    vertice = i;
                }
            }
            return vertice;
        }
        
            /**
             * Arma el Array de String's resultado para retornar en el método de Prim
             * @param resultado
             * @param mapCiudades
             * @param esTiempo
             * @return Array de String's con los resultados
             */
        private String[] printPrim( Resultado [] resultado, String[] mapCiudades, boolean esTiempo ) {
            String[] salida = new String[3];
            float total_coste_min = 0;
            float total_tiempo = 0;
            salida[0] = "El tour por todas las ciudades y el precio más bajo sería: \n";
            for (int i = 1; i < V; i++) {
                if(!esTiempo){
                salida[0] += "Vuelo " + mapCiudades[i] + " - " + mapCiudades[resultado[i].padre] + " | precio: " + resultado[i].peso +"$\n";
                }
                else{
                    salida[0] += "Vuelo " + mapCiudades[i] + " - " + mapCiudades[resultado[i].padre] + " | tiempo: " + resultado[i].peso +"min\n";
                }
                total_coste_min +=matrizPesos[i][resultado[i].padre];
                total_tiempo += matrizTiempo[i][resultado[i].padre];
            }
            salida[1] = "Precio total: " + String.valueOf(total_coste_min) + "$";
            salida[2] = "Tiempo de vuelo: " + String.valueOf(total_tiempo) + " min";
            return salida;
        }
        
            /**Clase que representa el resultado. Es utilizada en el método de Prim* */
            class Resultado {
                int padre;
                float peso;
            }
        
/**
 * Algoritmo de Dijkstra para hallar el camino más corto
 * @param matriz Matriz para analizar (tiempo o precios)
 * @param origen Nodo de origen
 * @param mapCiudades Mapeo de las ciudades para la salida en la GUI
 * @param destino Nodo destino
 * @return  String con el camino
 */
public  String dijkstra(float[][] matriz, int origen, String[] mapCiudades, int destino) {
int n = matriz.length;
float[] distancias = new float[n];
boolean[] visited = new boolean[n];
int[] predecesores = new int[n];

// Inicializar todas las distancias como infinito
Arrays.fill(distancias, Float.MAX_VALUE);

// La distancia del nodo de origen a sí mismo es 0
distancias[origen] = 0.0f;

// Encontrar el camino más corto para todos los nodos
for (int i = 0; i < n - 1; i++) {
    int minVertex = findMinVertex(distancias, visited);
    visited[minVertex] = true;
    for (int j = 0; j < n; j++) {
        if (matriz[minVertex][j] != 0.0f && !visited[j] && distancias[minVertex] != Float.MAX_VALUE) {
            float newDistance = distancias[minVertex] + matriz[minVertex][j];
            if (newDistance < distancias[j]) {
                distancias[j] = newDistance;
                predecesores[j] = minVertex;
            }
        }
    }
}

// Imprimir las distancias más cortas desde el nodo de origen a todos los demás nodos
/**System.out.println("Distancias mas cortas desde el nodo " + source + " al resto de los nodos:");
for (int i = 0; i < n; i++) {
    System.out.println(source + " -> " + i + ": " + distancias[i]);
}*/

// Mostrar el camino más corto desde el nodo origen a cualquier otro nodo
String salida = obtenerCamino(predecesores, origen, destino, mapCiudades, matriz);
return salida;
}

/**
 * Arma el camino para poder darle salida en la GUI
 * @param predecesores Nodos predecesores (padres)
 * @param origen Nodo origen
 * @param destino Nodo destino
 * @param mapCiudades Mapeo de las ciudades para la salida en la GUI
 * @param matriz Matriz con la que va a trabajar
 * @return  String con el camino completo el costo total ya sea en precio o en tiempo
 */
private static String obtenerCamino(int[] predecesores, int origen, int destino, String[] mapCiudades, float[][] matriz) {
   String camino = mapCiudades[destino];
   float total = 0;
    while (destino != origen) {
        total += matriz[destino][predecesores[destino]];
       destino = predecesores[destino];
       camino = mapCiudades[destino] + " -> " + camino;
    }
    if(total >= 999){
        return "Una de las ciudades involucradas en la operación no se encuentra disponible para vuelos. Por favor revise la tabla de vuelos o el panel de novedades";
    }
    return camino += "\n Total: " + total;
}

    /**
     * Encuentrar el nodo no visitado con la distancia mínima desde el nodo de origen
     * @param distancias Distancias actuales desde el nodo de origen hasta cada nodo del grafo.
     * @param visited Array de nodos para saber si han sido visitados
     * @return Nodo en cuestión
     */
    private static int findMinVertex(float[] distancias, boolean[] visited) {
        int minVertex = -1;
        for (int i = 0; i < distancias.length; i++) {
            if (!visited[i] && (minVertex == -1 || distancias[i] < distancias[minVertex])) {
                minVertex = i;
            }
        }
        return minVertex;
    }  

    /**
     * Afecta al grafo simulando lluvias para subir los pesos (precios) de las aristas que representan los vuelos
     * @param salida Texto para el panel de novedades
     * @param mapCiudades Mapeo de las ciudades para generar una salida correcta
     * @return  salida La salida de texto actualizada con las nuevas novedades
     */
    public  String metodoLluvia(String salida, String[] mapCiudades) {
       Random random = new Random();
       String tempSal = salida;
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                // Generar un número aleatorio entre 0 y 99
                int probabilidad = random.nextInt(100);

                // Verificar si el número aleatorio es menor o igual a 2 (2% de probabilidad)
                if (probabilidad <= 2 && ( i != j)  && matrizAdyacencia[i][j] == 1) {
                    matrizPesos[i][j] *= 2;
                    salida = salida + "Se presentan lluvias en el trayecto " + mapCiudades[i] +"-"+mapCiudades[j] + " así que hay alzas en los precios. \n";
                }
            }
        }
        if(salida == tempSal){
                                salida = salida + "No se han podido generar lluvias, el clima está muy seco. \n";
        }
        
        return salida;
    }

    /**
     * Simula una catástrofe para afectar las conexiones de los vuelos, uno de los vértices será inhabilitado momentáneamente
     * @param vertex Vértice a deshabilitar
     */
    public void metodoCatastrofe(int vertex) {
        if (vertex < 0 || vertex >= V) {
            throw new IllegalArgumentException("Vértice inválido");
        }

        // Establecer los pesos de las aristas hacia el vértice a un valor específico
        for (int i = 0; i < V; i++) {
            if (i != vertex) {
                matrizPesos[i][vertex] = 999;
                matrizPesos[vertex][i] = 999;
            }
        }
    }
    
    /**
     * Anula una catástrofe causada
      */
      public void anularCatastrofe(int vertex){
              for (int i = 0; i < V; i++) {
                    if (i != vertex) {
                        matrizPesos[i][vertex] = matrizPesosCopy[i][vertex];
                        matrizPesos[vertex][i] = matrizPesosCopy[vertex][i];
                    }
                }
      }
      
    /**
     * Actualiza el peso de una arista para simular unn nuevo precio con descuento
     * @param idx1 Indice 1
     * @param idx2 Indice 2
     * @param precioDescuento Nuevo peso
     */
    public  void metodoOfertas(int idx1, int idx2, float precioDescuento) {
       matrizPesos[idx1][idx2] = precioDescuento;
       matrizPesos[idx2][idx1] = precioDescuento;
    }
    
    /**
     * Anula una oferta 
     * @param idx1 Indice 1
     * @param idx2  Indice 2
     */
    public  void anularOferta(int idx1, int idx2) {
       matrizPesos[idx1][idx2] = matrizPesosCopy[idx1][idx2];
       matrizPesos[idx2][idx1] = matrizPesosCopy[idx2][idx1];
    }
    
    /**
     * Restaura la matriz de pesos con una copia de la original
     */
    public void restaurarPesos(){
        matrizPesos = matrizPesosCopy;
    }
    
    /**
     * @return Número de vértices
     */
    public int getV() {
        return V;
    }

    /**
     * Set número de aristas
     * @param V Número de vértices
     */
    public void setV(int V) {
        this.V = V;
    }

    /**
     * @return Número de aristas
     */
    public int getA() {
        return A;
    }

    /**
     * Set número de aristas
     * @param A Número de aristas
     */
    public void setA(int A) {
        this.A = A;
    }

    /**
     * @return Matriz de adyacencia
     */
    public int[][] getMatrizAdyacencia() {
        return matrizAdyacencia;
    }

    /**
     * Set matriz de adyacencia
     * @param matrizAdyacencia 
     */
    public void setMatrizAdyacencia(int[][] matrizAdyacencia) {
        this.matrizAdyacencia = matrizAdyacencia;
    }

    /**
     * @return Matriz de pesos
     */
    public float[][] getMatrizPesos() {
        return matrizPesos;
    }

    /**
     * Set matriz de pesos
     * @param matrizPesos 
     */
    public void setMatrizPesos(float[][] matrizPesos) {
        this.matrizPesos = matrizPesos;
    }

    /**
     * @return  Matriz de tiempo
     */
    public float[][] getMatrizTiempo() {
        return matrizTiempo;
    }

    /**
     * Set matriz de tiempo
     * @param matrizTiempo 
     */
    public void setMatrizTiempo(float[][] matrizTiempo) {
        this.matrizTiempo = matrizTiempo;
    }
    
    /**FIN GETTERS Y SETTERS*/

    
}
