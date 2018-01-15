import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
//Class MonPanel : 
//Cette classe contient deux listes : liste des sommets et liste des arcs : 
//ces deux listes seront déterminées par le constructeur à partir d'une fichier texte ! 
//Principe : lire le texte ligne par ligne : si la ligne commence par un "s" : il s'agit d'un sommet sinon c 'est un arc ! 
public class MonPanel extends JPanel {	
	public ArrayList<Sommet> sommets; 
	 ArrayList<Arc> arcs;  
	 FileInputStream fis = null;
    public MonPanel () throws FileNotFoundException {
    	
    	this.sommets = new ArrayList<Sommet>();
    	this.arcs = new ArrayList<Arc>();
        File file= new File("src/texte.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        try {
        	String line ;
			while((line=br.readLine()) != null){	
				String values[]  = line.split(" "); 
				if (values[0].equals("S")){
					String nom=values[1].toString() ;
					int h=Integer.parseInt(values[2]) ;
					int x=Integer.parseInt(values[3]) ;
					int y=Integer.parseInt(values[4]) ;
					this.sommets.add(new Sommet(nom,h,x,y)) ;
				}
				else if (values[0].equals("A")) {
					int k =0 ; 
					int cout =0 ;
					Sommet source=null ; 
					Sommet destination=null ; 
					while(k<this.sommets.size()  ){
						if (this.sommets.get(k).nom.equals(values[1])) {
							  source = this.sommets.get(k) ;
						}
						if (this.sommets.get(k).nom.equals(values[2])){
						  destination = this.sommets.get(k) ;
						}
						k=k+1;
					}
					cout=Integer.parseInt(values[3]) ;

					this.arcs.add(new Arc(source,destination,cout)) ;

				}
			}
		} catch (IOException e) {
		 
			e.printStackTrace();
		}

    } 
    //2eme constructeur : Construire un panel ayant deux listes passées en paramètres !
    public MonPanel(ArrayList<Sommet> liste,ArrayList<Arc> Arcs){
     	this.sommets= new ArrayList<Sommet>() ;
    	this.sommets.addAll(liste) ; 	 
    	this.arcs=new ArrayList<Arc>();
    	this.arcs.addAll(Arcs) ; 
    	    }
    public ArrayList<Arc>rechArc(ArrayList<Sommet> Sommets,ArrayList<Arc> listArc){
    	ArrayList<Arc> Arcs = new ArrayList<Arc>();
  
    	for(int i=0;i<Sommets.size()-1;i++){
    		boolean	test=false ;
    		int  j=0 ; 
    		while(j<listArc.size() && ! test){
    			if(listArc.get(j).source.nom.equals(Sommets.get(i).nom) && listArc.get(j).cible.nom.equals(Sommets.get(i+1).nom)){
    				Arcs.add(listArc.get(j)) ; 
					test=true ;
    			}
    			j++ ;
    		}
    	     
    	}    	
		return Arcs;
}
    //Methode : rechArc : elle retourne le cout d'un arc en passant en paramètre la surce et la cible d'arc 
    public int rechArc(Sommet source,Sommet cible){
     	int i =0 ; boolean test=false ; 
    	while (i<this.arcs.size()&& !test){
    		if(this.arcs.get(i).source==source && this.arcs.get(i).cible==cible){
    			test=true ; 
    			return this.arcs.get(i).cout ;
    		}
    		i++ ; 
    	}
		return 0;
    	
    }
    	
   //cette methode sert a dessiner la liste des sommets et la liste des arcs 
    //Sommet : cercle plein
    //arc : s'il s'agit d'une boucle , on dessine un arc , sinon une ligne reliant la source et la cible
    public void paintComponent(Graphics g){
        for(Sommet s : sommets) {        
            g.setColor(s.c);
            g.fillOval(s.x, s.y, 75, 75);
            g.setColor(Color.WHITE);
            g.drawString(s.nom,s.x+27,s.y+40);
            g.drawString("h="+s.h,s.x+40,s.y+55);          
            for(Arc a : arcs) {   
	        	g.setColor(Color.blue);
	        	if (!a.source.nom.equals(a.cible.nom)){
	        	g.drawLine(a.source.x+35,a.source.y+35,a.cible.x+35,a.cible.y+35);
	            g.drawString("cout="+a.cout,(a.cible.x+a.source.x)/2 ,a.cible.y-10);          
	        	}
	        	else { 
		        	g.drawArc(a.source.x, a.source.y,100 ,90 , 90, 180);
		            g.drawString("cout="+a.cout,a.source.x+5 ,a.source.y+5);          

		        	}

	        	}
	        }
        
        }
    
    //Pour ajouter un sommet a la liste des sommets :  
    public void addSommet(Sommet s) {
    	sommets.add(s);
    }
    //Pour ajouter un arc à la liste des arcs : 
    public void addArc(Arc x) {
	    arcs.add(x);
    }
    //Cette methode renvoie un objet sommet , en passant en parametre le nom de celui ci !
    //elle retourne null en cas d'inéxistance .
    public Sommet rechercheS(String nom){
    	boolean test=false ; 
    	int i =0 ;
    	while (! test && i<this.sommets.size()){
    		if (this.sommets.get(i).nom.equals(nom) ){
    			test=true ;
    			return this.sommets.get(i) ;
    		}
    		i++ ;
    	}
		return null;
    }
    //Cette methode retourne les successeurs d'un sommet 
    //Principe : s'il existe un arc entre ce sommet(en tant que souce) et un autre point , on l'ajoute à la liste des successeurs
    public ArrayList<Sommet> succList(Sommet s){
    	ArrayList<Sommet> SuccSommets = new ArrayList<Sommet>();
    	for(Arc c :this.arcs){
    	 
    		if(c.source.equals(s)){
    			SuccSommets.add(c.cible) ;
    		}
    	}    	
		if( SuccSommets.size()!=0){return SuccSommets ; }
		return null ;
}
}