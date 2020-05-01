package controller;

import java.util.Scanner;

import model.logic.MallaVialBogota;
import model.logic.Modelo;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;
	
	/* Instancia del Malla vial*/
	private MallaVialBogota mallaVial;

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
		mallaVial = new MallaVialBogota();
	}

	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;

		while( !fin ){
			view.printMenu();
			int option = lector.nextInt();
			switch(option)
			{
			case 1:
				view.printMessage("Información del grafo:");
				mallaVial.cargarGrafo();
				break;
				
			case 2:
				System.out.println("Ingresar la ruta par crear el archvio: ");
				String entrada1 = lector.next();
				System.out.println("Nombre del archivo añadir la extension (.json): ");
				String entrada2 = lector.next();
				System.out.println("El archivo .json tendrá: ");
				mallaVial.generarJSON(entrada1, entrada2);
				System.out.println("El archivo ha sido generado perfectamente en el direccion especificado");
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
