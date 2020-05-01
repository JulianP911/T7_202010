package model.data_structures;

/**
 * Clase del Grafo no dirigido para Taller 7
 * @author Julian Padilla - Pablo Pastrana
 * Los metodos de está clase fueron obtenidos del libro Algorthims 4 edition - Universidad de Princeton
 * Autores de los metodos son: Robert Sedgewick y Kevin Wayne
 */
public class Graph<K extends Comparable<K>, V>
{
	// Constantes
	
	/**
	 * Esta constante establece en el sistema una linea de separador
	 */
	@SuppressWarnings("unused")
	private static final String NEWLINE = System.getProperty("line.separator");

	// Atributos
	
	/**
	 * Numero de vertices del grafo
	 */
    private int numeroVertices;
    
    /**
     * Numero de aristas entre los vertices del grafo no dirigido
     */
    private int numeroAristas;
    
    /**
	 * Los vertices del grafo
	 */
	private SeparteChainingHashST<K, Vertex<K, V>> vertices;
    
    /**
     * Bolsa de enteros con las adjacencias de los vertices del grafo no dirigido
     */
    private Bag<Integer>[] adj;
    
    // Metodos constructor
    
    public Graph() 
    {
    	vertices = new SeparteChainingHashST<K, Vertex<K, V>>();
		numeroVertices = 0;
		numeroVertices = 0;
	}
    
    /**
     * Inicializa el grafo vacio con vertices que entran como parametro y con cero aristas.
     * @param  V Numero de vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    @SuppressWarnings("unchecked")
	public Graph(int V) 
    {
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.numeroVertices = V;
        this.numeroAristas = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    /**
     * Retorna el numero de vertices en el grafo
     * @return Numero de vertices en el grafo
     */
    public int V() 
    {
        return vertices.size();
    }

    /**
     * Retorna el numero de aristas en el grafo
     * @return Numero de aristas en el grafo
     */
    public int E() 
    {
        return numeroAristas;
    }
    
    /**
     * Cuenta el numero de arcos en el el grafo no dirigido
     * @return Numero de arcos en el el grafo no dirigido
     */
    public int countEdges() 
    { 
        int sum = 0; 
  
        // traverse all vertex 
        for (int i = 0; i < numeroVertices; i++) 
  
            // add all edge that are linked to the 
            // current vertex 
            sum += adj[i].size(); 
  
        // The count of edge is always even because in 
        // undirected graph every edge is connected 
        // twice between two vertices 
        return sum / 2; 
    }
    
    /**
     * Anadir vertice en un tabla de hash donde el id es el numero y el valor es la long y lat
     * @param idVertex Id de vertice
     * @param infoVertex Valor del vertice
     */
    public void addVertex(K idVertex, V infoVertex)
    {
    	Vertex<K,V> v = new Vertex<K,V>(idVertex, infoVertex);
		vertices.put(idVertex, v);
    	numeroVertices++;
    }

    /**
     * Añade un arista sin direccion en este grafo
     * @param  v un vertice del arco
     * @param  el otro vertice en el arco
     * @throws IllegalArgumentException unless both {@code 0 <= v < V} and {@code 0 <= w < V}
     */
    public void addEdge(K idVertexIni, K idVertexFin, double pCost) 
    {
    	Vertex<K,V> startVertice = vertices.get(idVertexIni);
		Vertex<K,V> endVertice = vertices.get(idVertexFin);
		
		if(!idVertexIni.equals(idVertexFin))
		{
			if(startVertice != null && endVertice != null)
			{
				Edge<K> edge = new Edge<K>(idVertexIni, idVertexFin, pCost);
				startVertice.addOutEdge(edge);
				endVertice.addInEdge(edge);
				numeroAristas++;
			}							
		}
    }
    
    /**
     * Obtener informacion de un vertice si existe de lo contrario null
     * @param idVertex Id del vertice
     * @return la informacion del vertice 
     */
    public V getInfoVertex(K idVertex)
	{
    	Vertex<K,V> v = vertices.get(idVertex);
    	if(v != null)
    	{
    		return vertices.get(idVertex).getValorVertice();
    	}
    	else
    	{
    		return null;
    	}	
	}
    
    /**
	 * Cambia la informacion que contiene el vertice
	 * @param idVertex El vertice a ser modificado
	 * @param infoVertex Nueva informacion del vertice
	 */
	public void setInfoVertex(K idVertex, V infoVertex)
	{
		Vertex<K,V> v = vertices.get(idVertex);
		if(v != null)
		{
			v.setValorVertice(infoVertex);;
		}
	}
	
	/**
	 * Obtener costo entre dos vertices
	 * @param idVertexIni Vertice de inicio en el grafo
	 * @param idVertexFin Vertice final en el grafo
	 * @return El valor de costo entre dos vertices
	 */
	public double getCostArc(K idVertexIni, K idVertexFin)
	{
		double costArc = 0.0;
		
		Edge<K> v = getEdge(idVertexIni, idVertexFin);
		
		if(v != null)
		{
			costArc = v.getCostArc();
		}
		else if(v == null)
		{
			costArc = -1.0;
		}	
		
		return costArc;
	}
	
	/**
	 * Cambiar costo entre dos vertices es decir en el arco
	 * @param idVertexIni Vertice de inicio en el grafo
	 * @param idVertexFin Vertice final en el grafo
	 * @param pCost Nuevo costo entre los vertices
	 */
	public void setCostArc(K idVertexIni, K idVertexFin, double pCost)
	{
		Edge<K> v = getEdge(idVertexIni, idVertexFin);
		
		if(v != null)
		{
			v.setCostArc(pCost);
		}	
	}
	
	/**
	 * obtener el arista entre dos vertices en el grafo
	 * @param idVertexIni Vertice de inicio en el grafo
	 * @param idVertexFin Vertice final en el grafo
	 * @return Arista entre los arcos
	 */
	public Edge<K> getEdge(K idVertexIni, K idVertexFin)
	{
		Vertex<K,V> v = vertices.get(idVertexIni);
		Edge<K> e = null;
		if(v != null)
		{			
			Edge<K> arista = v.getEdge(idVertexFin);
			if(arista != null)
			{
				e = arista;
			}
		}
		return e;
	}
    
    /**
     * Retorna los vertices adyacente del vertice de entrada
     * @param  v El vertice de entrada
     */
    public Iterable<Integer> adj(K idVertex) 
    {
    	Vertex<K,V> v = vertices.get(idVertex);
        return adj[(int)v.getIdVertice()];
    }
}
