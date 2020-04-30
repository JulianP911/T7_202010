package controller;

import java.util.Iterator;
import java.util.Scanner;

import model.Comparendo;
import model.LlaveComparendo2;
import model.logic.Modelo;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;

	/* Instancia de la Vista*/
	private View view;

	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
	}

	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
			case 1:
				modelo.darArbolRedBlack();
				view.printMessage("\nLectura de comparendorendos: ");
				view.printMessage("El numero de comparendos es de: " + modelo.darArbolRedBlack().size());
				view.printMessage("El comparendo con el mayor OBJECTID es: "+ modelo.darObjectidMayor());
				view.printMessage("El comparendo con el menor OBJECTID es: "+ modelo.darObjectidMenor() + " \n");
				break;
				
			case 2:
				view.printMessage("Por favor ingrese el numero del Id del comparendo para ser buscado: ");
				int pObjectId = lector.nextInt();
				
				if(modelo.darComparendoPorObjectID(pObjectId).equals("No hay un comparendo con ese identificador"))
				{
					view.printMessage(modelo.darComparendoPorObjectID(pObjectId));
				}
				else
				{
					view.printMessage("El comparendo es: " + modelo.darComparendoPorObjectID(pObjectId));
				}
				break;
				
			case 3:
				view.printMessage("Por favor ingrese el ObjectId Inferior: ");
				int pObjectInf = lector.nextInt();
				view.printMessage("Por favor ingrese el ObjectId superior: ");
				int pObjectSup = lector.nextInt();
				view.printMessage("Los comparendos que se encuntran dentro de este rango son: ");
				
				Iterator<LlaveComparendo2> it1 = modelo.darComparendosEntreDosObjectId(pObjectInf, pObjectSup).keys().iterator();
				Iterator<Comparendo> it2 = modelo.darComparendosEntreDosObjectId(pObjectInf, pObjectSup).Values().iterator();
				
				while(it1.hasNext() && it2.hasNext())
				{
					LlaveComparendo2 llaveComparendo =  it1.next();
					Comparendo Comparendo = it2.next();
					
					view.printMessage(llaveComparendo.getObjectid() + ", " + Comparendo.getFecha_hora() + ", " + Comparendo.getTipo_servi() + ", " +
		                              Comparendo.getClase_vehi() + ", " + Comparendo.getInfraccion());
					
				}
				break;

			case 4:
				lector.close();
				fin = true;
				break;

			default: 
				view.printMessage("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}

	}	
}
