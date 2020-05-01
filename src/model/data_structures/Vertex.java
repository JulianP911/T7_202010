package model.data_structures;

import java.util.ArrayList;

/**
 * Clase de vetice de tipo generica K
 * @author Julian Padilla - Pablo Pastrana
 * Obtuvimos metodos de internet en la implementación con variaciones implementadas por nosotros
 * @param <K> Vertice de tipo generico
 */
public class Vertex<K extends Comparable<K>,V>
{
	// Atributos
	
	/**
	 * Identificador del vertice
	 */
	private K idVeritice;
	
	/**
	 * Informacion del vertice
	 */
	public V valorVertice;
	
	/**
	 * Identificador numero del vertice
	 */
    private int idNumumeroVertice;
    
    private ArrayList<Edge<K>> outEdges;
    
    private ArrayList<Edge<K>> inEdges;

    // Metodo constructor
    
	public Vertex(K pIdVertice, V pValorVertice)
	{
		this.idVeritice = pIdVertice;
		this.valorVertice = pValorVertice;
		outEdges = new ArrayList<Edge<K>>();
		inEdges =  new ArrayList<Edge<K>>();
	}
	
	/**
	 * Obtener el numero del vertice dentro del grafo
	 * @return Numero del vertice
	 */
	public K getIdVertice()
	{
		return idVeritice;
	}
	
	/**
	 * Obtener el numero vertice con el valor en el grafo
	 * @return Numero vertice con el valor
	 */
	public int getIdNumeroVertice()
	{
		return idNumumeroVertice;
	}
	
	/**
	 * Obtener valor del vertice 
	 * @return Valor vertice
	 */
	public V getValorVertice()
	{
		return valorVertice;
	}
	
	/**
	 * Cambiar el valor del vertice
	 * @param pValor Nuevo valor al vertice
	 */
	public void setValorVertice(V pValor)
	{
		valorVertice = pValor;
	}
	
	public void addInEdge(Edge<K> e)
	{
		inEdges.add(e);
	}
	
	public void addOutEdge(Edge<K> e)
	{
		outEdges.add(e);
	}
	
	/**
	 * Obtener arco entre dos vertices especificos
	 * @param idVertexFin Vertice final
	 * @return Arista entre los vertices que se especificaron
	 */
	public Edge<K> getEdge(K idVertexFin)
	{
		Edge<K> searched = null;
		for (int i = 0; i < outEdges.size() && searched == null; i++) 
		{
			if(outEdges.get(i).getIdVerticeFinal().compareTo(idVertexFin) == 0)
			{
				searched = outEdges.get(i);
			}
		}
		return searched;
	}
}
