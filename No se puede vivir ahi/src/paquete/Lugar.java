package paquete;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Lugar {
	
	public int numero;
	public int temperaturaMinima;
	public int temperaturaMaxima;
	public int supera;
	public int noComparable;
	public int iguala;
	
	public Lugar(int numero, int tempMinima, int tempMaxima) {
		this.numero=numero;
		this.temperaturaMinima=tempMinima;
		this.temperaturaMaxima=tempMaxima;
		this.supera=0;
		this.noComparable=0;
		this.iguala=0;
	}
	
	public static void comparar(Lugar[] lugares) {
		for(int i=0; i<lugares.length-1; i++) {
			for(int j=i+1; j<lugares.length;j++) {
				if((lugares[i].temperaturaMinima < lugares[j].temperaturaMinima && lugares[i].temperaturaMaxima > lugares[j].temperaturaMaxima) ||
						(lugares[i].temperaturaMinima==lugares[j].temperaturaMinima && lugares[i].temperaturaMaxima > lugares[j].temperaturaMaxima) ||
						(lugares[i].temperaturaMaxima== lugares[j].temperaturaMaxima && lugares[i].temperaturaMinima < lugares[j].temperaturaMinima)) 
					lugares[i].supera++;
				else {
					if((lugares[i].temperaturaMinima > lugares[j].temperaturaMinima && lugares[i].temperaturaMaxima < lugares[j].temperaturaMaxima) ||
							(lugares[i].temperaturaMinima==lugares[j].temperaturaMinima && lugares[i].temperaturaMaxima < lugares[j].temperaturaMaxima) ||
							(lugares[i].temperaturaMaxima== lugares[j].temperaturaMaxima && lugares[i].temperaturaMinima > lugares[j].temperaturaMinima)) 
						lugares[j].supera++;
					else {
						if(lugares[i].temperaturaMinima == lugares[j].temperaturaMinima && lugares[i].temperaturaMaxima==lugares[j].temperaturaMaxima) {
							lugares[i].iguala++;
							lugares[j].iguala++;
						}
						else {
							lugares[i].noComparable++;
							lugares[j].noComparable++;
						}
					}
				}
			}
		}
	}
	
	public static int maximaHostilidad(Lugar[] lugares) {
		int maxHostilidad= lugares[0].supera;
		for(int i=1; i<lugares.length;i++) {
			if(lugares[i].supera > maxHostilidad)
				maxHostilidad=lugares[i].supera;
		}
		return maxHostilidad;
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner (new FileReader("clima.in"));
		int cantidadLugares= sc.nextInt();
		int tempMinima=0, tempMaxima=0, registros=0, tempMinimaRegistrada, tempMaximaRegistrada, hostilidad;
		Lugar[] lugares = new Lugar[cantidadLugares];
		for(int i=0; i<cantidadLugares;i++) {
			registros=sc.nextInt();
			tempMinima= sc.nextInt();
			tempMaxima= sc.nextInt();
			if(registros>1) {
				for(int j=0;j<registros-1;j++) {
					tempMinimaRegistrada=sc.nextInt();
					tempMaximaRegistrada=sc.nextInt();
					if(tempMinima > tempMinimaRegistrada) 
						tempMinima= tempMinimaRegistrada;
					if(tempMaxima < tempMaximaRegistrada)
						tempMaxima= tempMaximaRegistrada;
				}
			}
			lugares[i]= new Lugar(i+1,tempMinima,tempMaxima);
		}
		sc.close();
		
		PrintWriter pw= new PrintWriter(new FileWriter("clima.out"));
		
		comparar(lugares);
		hostilidad= maximaHostilidad(lugares);
		for(int i=0; i<lugares.length; i++) {
			if(lugares[i].supera == hostilidad) 
				pw.println(i+1 + " " + lugares[i].noComparable);
		}
		pw.close();
	}
	
}

	


