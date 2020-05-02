package Mapa;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.swing.MapView;
import com.teamdev.jxmaps.*;

public class Mapa extends MapView
{
	/**
	 * Serial version Mapa
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Atributo creacion del mapa
	 */
	private Map map;
	
	/**
	 * Metodo constructor del mapa
	 * @param pNombre Nombre del mapa
	 */
	public Mapa(String pNombre)
	{
		JFrame frame = new JFrame(pNombre);
		setOnMapReadyHandler(new MapReadyHandler() {
			
			@Override
			public void onMapReady(MapStatus status)
			{
				if(status == MapStatus.MAP_STATUS_OK)
				{
					map = getMap();
					MapOptions mapOptions = new MapOptions();
					MapTypeControlOptions controlOptions = new MapTypeControlOptions();
					mapOptions.setMapTypeControlOptions(controlOptions);
					
					map.setOptions(mapOptions);
					map.setCenter(new LatLng(4.6097102, -74.081749));
					map.setZoom(11.0);
					pintarCirculosYPoligon(4.597714, 4.621360, -74.094723, -74.062707);
				}
			}	
		});
		
		frame.add(this, BorderLayout.CENTER);
		frame.setSize(700, 500);
		frame.setVisible(true);
	}
	
	/**
	 * Graficar arco entre dos verices dados en la guia
	 * @param latMin Latitud Minima
	 * @param latMax Latitud Maxima
	 * @param lonMin Longitud Minima
	 * @param lonMax Longitud Maxima
	 */
	public void pintarCirculosYPoligon(double latMin, double latMax, double lonMin, double lonMax)
	{
		LatLng[] arco = new LatLng[2];
		LatLng vertice1 = new LatLng(latMin,lonMin);
		LatLng vertice2 = new LatLng(latMax,lonMax);
		arco[0] = vertice1;
		arco[1] = vertice2;
		
		Circle ver1 = new Circle(map);
		ver1.setCenter(vertice1);
		ver1.setRadius(10.0);
		CircleOptions op1 = new CircleOptions();
		op1.setFillColor("#ff0000");
		ver1.setOptions(op1);
		
		Circle ver2 = new Circle(map);
		ver2.setCenter(vertice2);
		ver2.setRadius(10.0);
		CircleOptions op2 = new CircleOptions();
		op2.setFillColor("#ff0000");
		ver2.setOptions(op2);
		
		Polygon union = new Polygon(map);
		union.setPath(arco);
	}
	
}
