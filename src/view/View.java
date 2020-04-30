package view;

import model.logic.Modelo;

public class View 
{
	/**
	 * Metodo constructor
	 */
	public View()
	{

	}

	/**
	 * Imprime los mensajes iniciales que se le muestran al usuario
	 */
	public void printMenu()
	{
		System.out.println("Taller 6 -  Arbole Rojo - Negro");
		System.out.println("1. Lectura/carga de los comparendos del 2018 (formato JSON) en el formato actualizado.");
		System.out.println("2. Requerimiento 2: Consultar un comparendo por id.");
		System.out.println("3. Requerimiento 3: Consultar los comparendos con un id en un rango especifico.");
		System.out.println("4. Cerrar");
	}

	/**
	 * Imprime mensajes de tipo string
	 * @param mensaje Mensaje correspondiente
	 */
	public void printMessage(String mensaje) 
	{
		System.out.println(mensaje);
	}		

	/**
	 * Imprime mensajes de tipo modelo, sacando la informacion
	 * @param modelo Modelo clase
	 */
	public void printModelo(Modelo modelo)
	{
		System.out.println(modelo);
	}
}
