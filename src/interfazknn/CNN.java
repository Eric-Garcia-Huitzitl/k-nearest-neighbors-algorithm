/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazknn;

import java.util.LinkedList;
class CNN{
	String [][] ConjuntoT;//Ahora mi conjunto T va a ser mi conjunto S
	String [][] ConjuntoP;//Ahora mi conjunto T va a ser mi conjunto P 	
	String [][] ConjuntoS;
	String [] valores;
	String [] clasesDefault;
        String [] arregloIndices;
	LinkedList <String> auxiliar =new LinkedList <String>();
	LinkedList <String> obtener = new LinkedList <String>();
	LinkedList <Integer> indices= new LinkedList <Integer>();
	int numVecinos=0,count=0,contador=0,coun3=0;
	CNN(String[][] ConjuntoT,int numVecinos, String [] valores){//CNN necesita de una matriz para poder trabajar 
		this.ConjuntoP=ConjuntoT;
		this.numVecinos=numVecinos;
		this.valores=valores;
	}
//LO PRIMERO QUE HAREMOS SERA PASAR AL ConjuntoT LA PRIMER INSTANCIA DE CADA CLASE  
	public String [][] aplicarCNN(){		
		KNN x=null;	
		clasesDefault=eliminaElementosRepetidos(ConjuntoP);
		relacionar();
		ConjuntoS=new String[clasesDefault.length][ConjuntoP[0].length];
//Aqui copiamos a la lista ligada los mejores con toda la fila
for (int y=0;y<clasesDefault.length ;y++ ) {
		for (int j=0;j<ConjuntoP[0].length;j++) {
			auxiliar.add(ConjuntoP[obtener.indexOf(clasesDefault[y])][j]);
			}
		System.out.println();
}		
	//ahora hay que pasarlo a la matriz
	for (int j=0;j<ConjuntoS.length ;j++ ) {
					for (int k=0;k<ConjuntoS[0].length ;k++ ) {
							ConjuntoS[j][k]=auxiliar.get(count);
							count++;
						}
				}
        
for (int i=0;i<ConjuntoP.length;i++ ) {	
    System.out.println(numVecinos);
    x= new KNN(ConjuntoS,valores,numVecinos);
if (x.distanciaKNNdos(ConjuntoP[i])==0){
indices.add(i);
contador++;
//Si no clasifico bien 	
//agrego ConjuntoP[i] al Conjunto S 
//ConjuntoS=null;

ConjuntoS=new String[ConjuntoS.length+1][ConjuntoP[0].length];//va a tener un nuevo tamaño que va hacer el tamaño que tiene mas 1 
for (int j=0;j<ConjuntoP[0].length;j++) {
auxiliar.add(ConjuntoP[i][j]);	

} //Agrego los nuevos String.
for (int jk=0;jk<ConjuntoS.length ;jk++ ) {
					for (int ks=0;ks<ConjuntoS[0].length ;ks++ ) {
							ConjuntoS[jk][ks]=auxiliar.get(coun3);
							coun3++;
						}
				}//cON ESTE FOR VACIAMOS NUESTRA LISTA LIGADA AL NUEVO CONJUNTO S
}

//FIN DE SI CLASIFICO
if (x.distanciaKNNdos(ConjuntoP[i])==1) {
	}
coun3=0;
}//FIN DE LAS INSTANCIAS EN T 

System.out.println("El conjunto Reducido:  \n\n\n");

for (int j=0;j<ConjuntoS.length ;j++ ) {
					for (int k=0;k<ConjuntoS[0].length ;k++ ) {
							System.out.print(ConjuntoS[j][k]+" ");
						}
						System.out.println();
				}
arregloIndices=new String[indices.size()];
for (int i=0;i<indices.size() ;i++ ) {
	System.out.println(indices.get(i));
        arregloIndices[i]=String.valueOf(indices.get(i));
}
//Ahora aqui vamos a mandar a un archivo 

return ConjuntoS;

}
        
public String [] getArregloIndices(){
    return arregloIndices;
}        
        
        
public void imprimeMatriz(){
	System.out.println("IMprimo matriz");
	for (int j=0;j<ConjuntoS.length ;j++ ) {
					for (int k=0;k<ConjuntoS[0].length ;k++ ) {
							System.out.print(ConjuntoS[j][k]+" ");
						
						}
						System.out.println();
				}	
}

public String[] regresaIndices(LinkedList <Integer> indices){
    String [] indi=new String[indices.size()];
    
    for(int i=0;i<indices.size();i++){
    indi[i]=String.valueOf(indices.get(i));
    }
    return indi;
}



public String [] eliminaElementosRepetidos(String [][] ConjuntoP){
		String [] arre=new String[ConjuntoP.length];
		String [] clases;
		
		for (int i=0;i<ConjuntoP.length ;i++ ) {
			arre[i]=ConjuntoP[i][ConjuntoP[0].length-1];
		}
		for(int i=0;i<arre.length;i++){
			for(int j=0;j<arre.length-1;j++){
				if(i!=j){
					if(arre[i].equals(arre[j])){
						// eliminamos su valor
						arre[i]="";
					}
				}
			}
		}
int n=arre.length;
int contador=0;
		for (int k=0;k<=n-1;k++){
			if(arre[k]!=""){
				contador++;
			}
		}
clases=new String[contador];
int conta=0;
for (int k=0;k<=n-1;k++){
			if(arre[k]!=""){
				clases[conta]=arre[k];
			System.out.println(clases[conta]);
			conta++;
			}
		}
return clases;
}


public void relacionar(){
for (int i=0;i<ConjuntoP.length ;i++) {
	obtener.add(ConjuntoP[i][ConjuntoP[0].length-1]);
}



}
}



