/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazknn;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
class KNN{
	String [][] ConjuntoT;
	String [][] ConjuntoP;
	double [] valores; 
	double [] distanciaMenor;
	int apuntador=0;
        int valorClasificado2;
	String [] valoresAtributos;//En este vector tengo si es numerico o nominal
	int numKNN=0;
	public Double sumaDistancia=0.0;

	KNN(String[][] ConjuntoT,String[][] ConjuntoP,String [] valores,int numVecinos){
		this.ConjuntoT=ConjuntoT;
		this.ConjuntoP=ConjuntoP;
		this.valoresAtributos=valores;
		this.numKNN=numVecinos;
	}
        KNN(String[][] ConjuntoT,String[]valores,int numVecinos){
		this.ConjuntoT=ConjuntoT;
		this.valoresAtributos=valores;
		this.numKNN=numVecinos;
	}
public int distanciaKNNdos(String[] propuesta){

String [] cadenaAux=new String[ConjuntoT.length];
LinkedList<Double> cadAux;
double calculo;
int valorClasificado=0;
int band=0;
//Aqui vamos a guardar las distancias
double desviacion=0;
int valor=0;


valores=new double[ConjuntoT.length];//Aqui se almacenan todas las distancias
cadAux= new LinkedList<Double>();

	//Para todas las filas con conjunto P 
for (int j=0;j<ConjuntoT.length;j++) {		
	for (int i=0;i<ConjuntoT[0].length-1; i++) {
		if(valoresAtributos[i].equals("0")==true){
		sumaDistancia=sumaDistancia+(Math.sqrt(calcularDistanciaEuclideana(Double.parseDouble(ConjuntoT[j][i]),Double.parseDouble(propuesta[i]),ConjuntoT)));///Math.pow((4*calculaDesviacionEstandar(ConjuntoT,i)),3));	
		
		}
		if (valoresAtributos[i].equals("1")==true) {
			sumaDistancia=sumaDistancia+distanciaOverlap(Double.parseDouble(ConjuntoT[j][i]),Double.parseDouble(propuesta[i]));
		}
		}
		cadAux.add(sumaDistancia);
		valores[apuntador]=sumaDistancia;
		apuntador++;
		sumaDistancia=0.0;
}
//Aqui ya tengo todos los valores Mejores en distancias
Arrays.sort(valores);
//Aqui ya estan ordenados
valorClasificado=valorClasificado+vecinos(valores,numKNN,cadAux,Integer.parseInt(propuesta[propuesta.length-1]));
	apuntador=0;

if (valorClasificado==1){
	band=1;
}


	return band;//Una ves que calcula distancias te devuelve el indice de la clase

}

       
public double distanciaKNN(){
//Primero vamoa a recorrer la primer fila de ambas calculando la distancia que existe
//el length nos sirve para saber el numero de filas de una matriz
String [] cadenaAux=new String[ConjuntoT.length];
LinkedList<Double> cadAux;
double calculo;
int valorClasificado=0;
//Aqui vamos a guardar las distancias
double desviacion=0;
int valor=0;
for (int b=0;b<ConjuntoP.length;b++ ){
valores=new double[ConjuntoT.length];
cadAux= new LinkedList<Double>();
	//Para todas las filas con conjunto P 
for (int j=0;j<ConjuntoT.length;j++) {		
	for (int i=0;i<ConjuntoT[0].length-1; i++) {
		if(valoresAtributos[i].equals("0")==true){
		sumaDistancia=sumaDistancia+(Math.sqrt(calcularDistanciaEuclideana(Double.parseDouble(ConjuntoT[j][i]),Double.parseDouble(ConjuntoP[b][i]),ConjuntoT)));///(Math.pow((4*calculaDesviacionEstandar(ConjuntoT,i)),1));		
		}
		if (valoresAtributos[i].equals("1")==true) {
			sumaDistancia=sumaDistancia+distanciaOverlap(Double.parseDouble(ConjuntoT[j][i]),Double.parseDouble(ConjuntoP[b][i]));
		}
		}
		cadAux.add(sumaDistancia);
		valores[apuntador]=sumaDistancia;
		apuntador++;
		sumaDistancia=0.0;
}
//Aqui ya tengo todos los valores Mejores en distancias
Arrays.sort(valores);
Scanner a=new Scanner(System.in);

//Aqui ya estan ordenados
valorClasificado=valorClasificado+vecinos(valores,numKNN,cadAux,Integer.parseInt(ConjuntoP[b][ConjuntoP[0].length-1]));
	apuntador=0;
}
calculo=(100.0/ConjuntoP.length);
//System.out.println("Clasificaciones Correctas: "+valorClasificado);
//System.out.println("Clasificaciones Incorrectas: "+(ConjuntoP.length-valorClasificado));
valorClasificado2=valorClasificado;
	return (calculo*valorClasificado);//Una ves que calcula distancias te devuelve el indice de la clase
}
public  double calcularDistanciaEuclideana(double valorUno,double valorDos,String ConjuntoT[][]){
	return ((double)Math.pow((valorUno-valorDos),2));
}

//Este metodo solo recibe dos valores y compara si son iguales pone 1 si son distintos es 0 
public double distanciaOverlap(double valorUno,double valorDos){
	if (valorUno==valorDos){return 0.0;	}else{return 1.0;	}
}
public int vecinos(double [] vector,int numVecinos,LinkedList <Double> auxiliar,int claseReal){
//COnvertimos y damos formato 
int contador=0,con=0,clasCorracta=0,clasIncorrecta=0;
int res=0;
int [] ordena=new int [numVecinos];
int[] mejoresClases=new int[numVecinos];

for (int i=0;i<numVecinos;i++ ) {
//	sTexto=sTexto+String.valueOf(ConjuntoT[auxiliar.indexOf(vector[i])][ConjuntoT[0].length-1]+" ");
	mejoresClases[i]=Integer.parseInt(ConjuntoT[auxiliar.indexOf(vector[i])][ConjuntoT[0].length-1]);
}

for (int i=0;i<mejoresClases.length ;i++ ) {
	if (mejoresClases[i]==claseReal) {clasCorracta++;}
	else{clasIncorrecta++;}
}
if(clasCorracta>clasIncorrecta)
{ res++;}
return res;
}
public static double calculaDesviacionEstandar(String ConjuntoT[][],int ix){
double sumatorio=0,media=0,rango=0,varianza=0,desviacion=0;
//Primero necesitamos obtener la media entonces creamos una variable media
for (int i=0;i<ConjuntoT.length;i++) {
	sumatorio=sumatorio+Double.parseDouble(ConjuntoT[i][ix]);
}
media=sumatorio/ConjuntoT.length;
for (int j=0;j<ConjuntoT.length ;j++) {
	rango= Math.pow((Double.parseDouble(ConjuntoT[j][ix])-media),2);
 	varianza=varianza+rango;
}
varianza=varianza/ConjuntoT.length;
return Math.sqrt(varianza);
}
}