package model.logic;

import model.InformacionArco;
import model.InformacionVertice;
import model.data_structures.Edge;
import model.data_structures.SeparteChainingHashST;
import model.data_structures.UnGraph;
import model.data_structures.Vertex;

public class MallaVialBogota 
{
	// Atributos

	/**
	 * Atributo para crear un grafo no dirigido
	 */
	private UnGraph<String, InformacionVertice, InformacionArco> UnDiGraph;

	/**
	 * Tabla de hash donde se guardaran los vertices con su informacion
	 */
	private SeparteChainingHashST<Integer, Vertex<String, InformacionVertice, InformacionArco>> vertices;

	/**
	 * Tabla de hash donde se guardaran los arcos con su informacion de costo
	 */
	private SeparteChainingHashST<String, Edge<String, InformacionArco>> arcos;

	// Metodo Constructor

	/**
	 * Inicializa el grafo, la tablas de hash de vertices y arcos
	 */
	public MallaVialBogota()
	{
		UnDiGraph = new UnGraph<String, InformacionVertice, InformacionArco>();
		vertices = new SeparteChainingHashST<Integer, Vertex<String, InformacionVertice, InformacionArco>>();
		arcos = new SeparteChainingHashST<String, Edge<String, InformacionArco>>();
	}

	// Metodos

	/**
	 * Obtener la distancia haversiana de acuerdo con la latitudes y longitudes que entran como parametro
	 * @param startLat Latitud de inicio
	 * @param startLong Longitud de inicio
	 * @param endLat Latitud de final
	 * @param endLong Longitud de final
	 * @return La distancia entre los dos puntos establecidos
	 */
	public double getDistanceHaversian(double startLat, double startLong, double endLat, double endLong) 
	{

		final int EARTH_RADIUS = 6371;
		double dLat  = Math.toRadians((endLat - startLat));
		double dLong = Math.toRadians((endLong - startLong));

		startLat = Math.toRadians(startLat);
		endLat   = Math.toRadians(endLat);

		double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return EARTH_RADIUS * c; // <-- d
	}
	
	/**
	 * Haversin funcion matematica
	 * @param val Valor de entrada
	 * @return El valor haversiano de la distancias
	 */
	public static double haversin(double val)
	{
        return Math.pow(Math.sin(val / 2), 2);
    }


}
