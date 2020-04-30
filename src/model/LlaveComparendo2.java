package model;

/**
 * Clase LlaveComparendo que se basa por el objectid
 * @author Julian Padilla - Pablo Pastrana
 */
public final class LlaveComparendo2 implements Comparable<LlaveComparendo2>
{
	/**
	 * Objectid del comparendo
	 */
	private int objectid;

	// Metodo Constructor
	
	/**
	 * Metodo constructor de la llave del comparendo
	 */
	public LlaveComparendo2(int pObjectid) 
	{
		objectid = pObjectid;
	}
	
	/**
	 * Retorna el objectid del comparendo
	 * @return Objectid
	 */
	public int getObjectid()
	{
		return objectid;
	}

	/**
	 * CompareTo para verificar si estan organizados por el objectid
	 */
	@Override
	public int compareTo(LlaveComparendo2 pObjeto) 
	{
		int resultado = 0;
		
		if(this.getObjectid() < pObjeto.getObjectid())
		{
			resultado = -1;
		}
		else if(this.getObjectid() == pObjeto.getObjectid())
		{
			resultado = 0;
		}
		else if(this.getObjectid() > pObjeto.getObjectid())
		{
			resultado = 1;
		}

		return resultado;
	}
}