import java.awt.Color;
import java.util.Random;
//Class Sommet : 
//un sommet a un nom , une valeur heuristique , un couple (x,y) présentant sa position dans le graphe et un couleur calculé aléatoirement !
public class Sommet {
	 String nom;  int h ;  int x;    int y;    Color c;
	    Sommet (String nom,int h, int x,int y ) {  
	    	this.nom=nom;
	    	this.h=h ;
	        this.x=x;
	        this.y=y;
	        Random rand = new Random();
	        float r = rand.nextFloat();
	        float g = rand.nextFloat();
	        float b = rand.nextFloat();

	        this.c=new Color(r, g, b);
; 
}
}