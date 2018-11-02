/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazknn;
import java.io.*;
import java.util.Arrays;
import java.util.*;

public class LeerArchivo {
//Lo vamos a  hacer de una forma general 
	    public  String nomArchivo;
    	public String nomUbicacion;
    	String arre[][];
    	String arregloValor[];
		int tamaArreglo[]=new int[2];
	//Ahora necesitamos que lo que lea el archivo y lo guarde en una estructura en este caso una matriz bidireccional
    public LeerArchivo(String nomArchivo,String nomUbicacion) {
		this.nomArchivo=nomArchivo;
    	this.nomUbicacion=nomUbicacion;
    }
public String[][] mostrarMatriz(){
	System.out.println(nomArchivo+"\n");
		for(int i=0;i<tamaArreglo[0];i++){
		for(int j=0;j<tamaArreglo[1];j++){
			System.out.print(arre[i][j]+" ");
		}
		System.out.println();
	}
	System.out.println("\n\n\n");
return arre;
}
public String[] getVectorAtributos(){
System.out.println("Vector de Atributos:  ");
for (int i=0;i<arregloValor.length ;i++ ) {
	System.out.print(arregloValor[i]+" ");	
}
System.out.println("\n\n\n");
return arregloValor;
}

public void relacionarArchivo(){
int cont=0;
int contadorLineas=0;
//Aqui vamos a pasar los datos a la matriz como ya tenemos el numero de filas y el numero de columnas
	arre=new String [tamaArreglo[0]][tamaArreglo[1]];
	arregloValor=new String[tamaArreglo[1]];
	String vacia="";
//Aqui creamos la matriz con lo que necesitamos meter al arreglo
//Ahora vamos a vaciar a la matriz
	FileReader fich=null;
    String LineaActual="";
    try {
        	fich= new FileReader(nomUbicacion);        }
        catch(Exception e ){System.out.println("problemas con filereader");}
       	BufferedReader fileArchivo= new BufferedReader(fich);
	try{
		while((vacia=fileArchivo.readLine())!=null){
	
			if(contadorLineas==2){
			String [] cade=vacia.split(",");
			for (int i=0;i<cade.length ;i++ ) {
				arregloValor[i]=cade[i];
			}
			}
			if(contadorLineas>=3){
		String[] partes=vacia.split(",");
			for(int i = 0;i<partes.length;i++){
				arre[cont][i]=partes[i];
			}
			cont++;
		}
		contadorLineas++;
		}
	}catch(Exception e){System.out.println("Problemas al leer readLine");}
}

public int [] longitudMatriz(){
	int countFilas=0,aux=0;
	String cad="",primera="";
	FileReader fichLong=null;
	int numFilas=0,numCol=0;
	try{
	fichLong = new FileReader(nomUbicacion);
	}catch(Exception e){}
	BufferedReader fileArch = new BufferedReader(fichLong);
	try {
		//Aqui vamos a contar el numero de filsas y de columnas
		while((cad=fileArch.readLine())!=null){
			if(aux==3)
			{
				primera=cad;
			}
			aux++;
			countFilas++;
		}
	}catch(Exception e){}	
	String[] parts=primera.split(",");
	//Como solo necesito saber la longitud solo hare uso del 	
	tamaArreglo[0]=countFilas-3;
	tamaArreglo[1]=parts.length;

	aux=0;
	return tamaArreglo;
}

}