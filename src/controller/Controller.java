package controller;

import java.util.Scanner;
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
				
				break;
				
			case 2:
			
				break;
				
			case 3:
				
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
