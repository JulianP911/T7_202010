package model.data_structures;

/**
 * Clase de vetice de tipo generica K
 * @author Julian Padilla - Pablo Pastrana
 * Obtuvimos metodos de internet en la implementación con variaciones implementadas por nosotros
 * @param <K> Vertice de tipo generico
 */
public class Edge<K extends Comparable<K>>
{	
	// Atributos
	
	/**
	 * Vertice de incio en el grafo no dirigido
	 */
	private K idVerticeInicio;
	
	/**
	 * Vertice de final en el grafo no dirigido
	 */
	private K idVerticeFinal;
	
	/**
	 * Costo de distancia entre arcos
	 */
	private double costoArco;
	
	// Metodo constructor
	
	/**
	 * Inicializa los datos para tener los vertices que se conectan mediante un arco
	 * @param pVerIni Vertice de inicio 
	 * @param pVerFin Verice del fina
	 */
	public Edge(K pVerIni, K pVerFin, double pCost)
	{
		idVerticeInicio = pVerIni;
		idVerticeFinal = pVerFin;
		costoArco = pCost;
	}
	
	/**
	 * Obtener el vertice de inicio del grafo no dirigido
	 * @return Vertice de inicio
	 */
	public K getIdVerticeInicio()
	{
		return idVerticeInicio;
	}
	
	/**
	 * Obtener el vertice de final del grafo no dirigido
	 * @return Vertice de final
	 */
	public K getIdVerticeFinal()
	{
		return idVerticeFinal;
	}
	
	/**
     * Retorna el costo que tiene el arco
     * @return Costo que tiene el arco
     */
    public double getCostArc() 
    {
        return costoArco;
    }
    
    /**
     * cambia el costo que tiene el arco
     */
    public void setCostArc(double pCost) 
    {
        costoArco = pCost;
    }
}
