package model.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import model.Comparendo;
import model.LlaveComparendo2;
import model.data_structures.RedBlackBST;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo 
{
	public static String PATH = "./data/comparendos_DEI_2018_Bogotá_D.C_small.geojson";
	//	public static String PATH = "./data/comparendos_DEI_2018_Bogotá_50000_.geojson";
    //	public static String PATH = "./data/comparendos_DEI_2018_Bogotá_D.C.geojson";

	/**
	 * Lista de comparendos
	 */
	private List<Comparendo> datos1;

	/**
	 * Metodo que hace la carga de los datos comparendos
	 * @return Una lista con los comparendos leidos
	 */
	public List<Comparendo> cargarDatos() 
	{
		List<Comparendo> datos = new ArrayList<Comparendo>();

		JsonReader reader;
		try 
		{
			reader = new JsonReader(new FileReader(PATH));
			JsonElement elem = JsonParser.parseReader(reader);
			JsonArray e2 = elem.getAsJsonObject().get("features").getAsJsonArray();


			SimpleDateFormat parser=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

			for(JsonElement e: e2) 
			{
				int OBJECTID = e.getAsJsonObject().get("properties").getAsJsonObject().get("OBJECTID").getAsInt();

				String s = e.getAsJsonObject().get("properties").getAsJsonObject().get("FECHA_HORA").getAsString();
				String cambio1 = s.replaceFirst("T", " ");
				String cambio2 = cambio1.replaceAll("Z", "");
				Date FECHA_HORA = parser.parse(cambio2); 

				String MEDIO_DETE = e.getAsJsonObject().get("properties").getAsJsonObject().get("MEDIO_DETECCION").getAsString();
				String CLASE_VEHI = e.getAsJsonObject().get("properties").getAsJsonObject().get("CLASE_VEHICULO").getAsString();
				String TIPO_SERVI = e.getAsJsonObject().get("properties").getAsJsonObject().get("TIPO_SERVICIO").getAsString();
				String INFRACCION = e.getAsJsonObject().get("properties").getAsJsonObject().get("INFRACCION").getAsString();
				String DES_INFRAC = e.getAsJsonObject().get("properties").getAsJsonObject().get("DES_INFRACCION").getAsString();	
				String LOCALIDAD = e.getAsJsonObject().get("properties").getAsJsonObject().get("LOCALIDAD").getAsString();
				String MUNICIPIO = e.getAsJsonObject().get("properties").getAsJsonObject().get("MUNICIPIO").getAsString();

				double longitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(0).getAsDouble();

				double latitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(1).getAsDouble();

				Comparendo nuevo = new Comparendo(OBJECTID, FECHA_HORA, DES_INFRAC, MEDIO_DETE, CLASE_VEHI, TIPO_SERVI, INFRACCION, LOCALIDAD, MUNICIPIO, longitud, latitud);
				datos.add(nuevo);
			}

		} 
		catch (FileNotFoundException | ParseException e) 
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return datos;			
	}

	/**
	 * Convierte la lista de objetos cargados a un arbol rojo - negro
	 */
	public RedBlackBST<LlaveComparendo2, Comparendo> darArbolRedBlack()
	{
		RedBlackBST<LlaveComparendo2, Comparendo> arbolRedBlack = new RedBlackBST<LlaveComparendo2, Comparendo>();
		datos1 = cargarDatos();

		Iterator<Comparendo> it = datos1.iterator();
		while(it.hasNext())
		{
			for(int i = 0; i < datos1.size(); i++)
			{
				Comparendo elementoActual = it.next();
				LlaveComparendo2 llaveActual = new LlaveComparendo2(elementoActual.getObjective());
				Comparendo comparendoActual = new Comparendo(elementoActual.getObjective(), elementoActual.getFecha_hora(), elementoActual.getDes_infrac(), elementoActual.getMedio_dete(), elementoActual.getClase_vehi(), elementoActual.getTipo_servi(), elementoActual.getInfraccion(), elementoActual.getLocalidad(), elementoActual.getMunicipio(), elementoActual.getLongitud(), elementoActual.getLatitud());
				arbolRedBlack.put(llaveActual, comparendoActual);
			}
		}

		return arbolRedBlack;
	}
	
	/**
	 * Muestra la informacion con el mayor OBJECTID encontrado en el arbol rojo - negro
	 * @return El comparendo con mayor objectid encontrado recorriendo el arbol rojo - negro
	 */
	public String darObjectidMayor()
	{
		String mensaje = " ";
		Comparendo actual = datos1.get(0);

		Iterator<Comparendo> it = darArbolRedBlack().Values().iterator();
		while(it.hasNext())
		{
			Comparendo elemento = it.next();
			if(elemento.getObjective() > actual.getObjective())
			{
				actual = elemento;
			}
		}

		mensaje = actual.getObjective() + ", " + actual.getFecha_hora() + ", " + actual.getInfraccion() + ", " + 
				actual.getClase_vehi() + ", " + actual.getTipo_servi() + ", " +  actual.getLocalidad() + ", " +
				actual.getMunicipio();

		return mensaje;
	}
	
	/**
	 * Muestra la informacion con el menor OBJECTID encontrado en el arbol rojo - negro
	 * @return El comparendo con menor objectid encontrado recorriendo el arbol rojo - negro
	 */
	public String darObjectidMenor()
	{
		String mensaje = " ";
		Comparendo actual = datos1.get(0);

		Iterator<Comparendo> it = darArbolRedBlack().Values().iterator();
		while(it.hasNext())
		{
			Comparendo elemento = it.next();
			if(elemento.getObjective() < actual.getObjective())
			{
				actual = elemento;
			}
		}

		mensaje = actual.getObjective() + ", " + actual.getFecha_hora() + ", " + actual.getInfraccion() + ", " + 
				actual.getClase_vehi() + ", " + actual.getTipo_servi() + ", " +  actual.getLocalidad() + ", " +
				actual.getMunicipio();

		return mensaje;
	}
	
	/**
	 * Metodo que recibe el objectId y se busca en el arbol Rojo - Negro con su llave y su valor 
	 * @param pObjectId ObjectId del comparendo
	 * @return El comparendo si se encontro, de lo contrario retorna que no fue encontrado
	 */
	public String darComparendoPorObjectID(int pObjectId)
	{
		String comparendoEncontrado = " ";
		Comparendo comparendoObjectId = null;
		
		
		Iterator<LlaveComparendo2> it1 = darArbolRedBlack().keys().iterator();
		Iterator<Comparendo> it2 = darArbolRedBlack().Values().iterator();
		
		while(it1.hasNext() && it2.hasNext())
		{
			LlaveComparendo2 llaveComparendo =  it1.next();
			Comparendo Comparendo = it2.next();
			
			if(llaveComparendo.getObjectid() == pObjectId)
			{
				comparendoObjectId = Comparendo;
			}
			
		}
		
		if(comparendoObjectId == null)
		{
			comparendoEncontrado = "No hay un comparendo con ese identificador";
		}
		else if(comparendoEncontrado != null)
		{
			comparendoEncontrado = comparendoObjectId.getObjective() + ", " + comparendoObjectId.getFecha_hora() + ", " + comparendoObjectId.getTipo_servi() + ", " +
		                           comparendoObjectId.getClase_vehi() + ", " + comparendoObjectId.getInfraccion();
		}
		
		return comparendoEncontrado;
	}
	
	/**
	 * Retorna un arbo Rojo - Negro con los comparendos que se encuntran dentro de los rangos ingresados
	 * @param pObjectInf ObjectID inferior
	 * @param pObjectSup ObjectID superior
	 * @return Arbol Rojo - Negro con comparendos dentro del rango
	 */
	public RedBlackBST<LlaveComparendo2, Comparendo> darComparendosEntreDosObjectId(int pObjectInf, int pObjectSup)
	{
		RedBlackBST<LlaveComparendo2, Comparendo> rangoComparendos = new RedBlackBST<LlaveComparendo2, Comparendo>();
		
		Iterator<LlaveComparendo2> it1 = darArbolRedBlack().keys().iterator();
		Iterator<Comparendo> it2 = darArbolRedBlack().Values().iterator();
		
		while(it1.hasNext() && it2.hasNext())
		{
			LlaveComparendo2 llaveComparendo =  it1.next();
			Comparendo Comparendo = it2.next();
			
			if(llaveComparendo.getObjectid() >= pObjectInf && llaveComparendo.getObjectid() <= pObjectSup)
			{
				rangoComparendos.put(llaveComparendo, Comparendo);
			}
			
		}
		
		return rangoComparendos;	
	}
}
