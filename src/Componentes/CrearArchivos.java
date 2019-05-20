package Componentes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;

public class CrearArchivos {

	private static final String[] estados = { "Aguascalientes", "Baja California", "Baja California Sur", "Campeche", "Coahuila de Zaragoza", "Colima", "Chiapas", "Chihuahua", "Distrito Federal", 
						"Durango", "Guanajuato", "Guerrero", "Hidalgo", "Jalisco", "México", "Michoacán de Ocampo", "Morelos", "Nayarit", "Nuevo León", "Oaxaca", "Puebla",
						"Querétaro", "Quintana Roo", "San Luis Potosí", "Sinaloa", "Sonora", "Tabasco", "Tamaulipas", "Tlaxcala", "Veracruz de Ignacio de la Llave",
						"Yucatán", "Zacatecas" };
	
	private static final int[] municipiosPorEstado = new int[] { 11, 5, 5, 11, 124, 67, 16, 38, 10, 39, 46, 81, 84, 125, 125, 113, 33, 20, 51, 570, 217, 18, 11, 58, 18, 72, 17, 43, 60, 212, 106, 58 };
	private static final int ciudadesPorMunicipio = 5;
	
	private static final File fEstados = new File("estados.dat"),
				fMunicipios = new File("municipios.dat"),
				fCiudades = new File("ciudades.dat");
	
	public static void main(String[] args) throws IOException {
		
		// Eliminamos los archivos
		fEstados.delete();
		fMunicipios.delete();
		fCiudades.delete();
		
		// Los creamos de nuevo
		OutputStream out = new FileOutputStream(fEstados);
		out = new FileOutputStream(fMunicipios);
		out = new FileOutputStream(fCiudades);
		
		// Insertamos datos
		RandomAccessFile raf;
		StringBuffer sb;
		
		int nMunicipios = 0, nCiudades = 0;
		for(int i = 0; i < estados.length; i++) {
			raf = new RandomAccessFile(fEstados, "rw");
			raf.seek(raf.length());
			sb = new StringBuffer(estados[i]);
			sb.setLength(50);
			raf.writeInt(i+1);
			raf.writeChars(sb.toString());

			for(int j = 0; j < municipiosPorEstado[i]; j++) {
				raf = new RandomAccessFile(fMunicipios, "rw");
				raf.seek(raf.length());
				sb = new StringBuffer(estados[i].substring(0, 3) +"Mun" +(j+1));
				sb.setLength(50);
				
				raf.writeInt(i+1);
				raf.writeInt(++nMunicipios);
				raf.writeChars(sb.toString());

				raf = new RandomAccessFile(fCiudades, "rw");
				raf.seek(raf.length());
				for(int k = 0; k < ciudadesPorMunicipio; k++) {
					sb = new StringBuffer(estados[i].substring(0, 3) +"Mun" +(j+1) +"Ciu" +(k+1));
					sb.setLength(50);
					
					raf.writeInt(i+1);
					raf.writeInt(nMunicipios);
					raf.writeInt(++nCiudades);
					raf.writeChars(sb.toString());
				}
			}
		}
		
		System.out.println("Archivos creados");
	}
}
