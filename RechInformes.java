import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
//cette classe definit principalement les methodes de recherches informés tel que Recherche meilleur d'abord gloutonne et A*
public class RechInformes {
	
	
	MonPanel panel ;
 public RechInformes() throws FileNotFoundException{
		panel=new MonPanel() ; 

 }
//On definit une liste des noeuds ouverts , une liste des noeuds fermés et à partir de la 1ere liste on choisit un noeud pour developper ses successeurs 
 //le choix se fait selon le cout du sommet qui est l'heuristique de ce dernier !
 public ArrayList<Sommet> gloutonne(Sommet EI, Sommet EF) throws IOException{
	 PrintWriter pWriter = new PrintWriter(new FileWriter("src/algosRecherche.txt", true));
		int captBloc =0  ; // c'est un compteur qui sert a detecter les boucles infinies

		long debut = System.currentTimeMillis();
  	ArrayList<ArrayList <Sommet>> nOuvert = new ArrayList<ArrayList<Sommet>>() ;
 	ArrayList<ArrayList <Sommet>> nFerme = new ArrayList<ArrayList<Sommet>>() ;
 	System.out.println("Recherche meilleur d'abord gloutonne : ") ; 
	System.out.println("Point de départ : "+EI.nom);
	System.out.println("Point d'arrivée : "+EF.nom);
	pWriter.println("Recherche meilleur d'abord gloutonne : ");
	pWriter.println("Point de départ : "+EI.nom);
	pWriter.println("Point d'arrivée : "+EF.nom);
	if(EI.nom.equals(EF.nom)){
		System.out.println("le point de depart et le point d'arrivée sont confondus");
		pWriter.println("le point de depart et le point d'arrivée sont confondus");
		pWriter.close();
		return null ;
	}
	
	
	if(this.panel.succList(EI)==null){
		System.out.println("le point de depart n'a pas de successeurs");   
		pWriter.println("le point de depart n'a pas de successeurs");
		pWriter.close();
		return null;
	}
	
	String parcours="" ; 
	ArrayList <Sommet> listEI = new ArrayList <Sommet>() ; 
	listEI.add(EI) ;
	nOuvert.add(listEI) ;
	boolean test=false ;
	while (!test && nOuvert.size()!=0) {
		int indice =rechHeuristiqueMin(nOuvert) ;
		ArrayList<Sommet> noeud = nOuvert.get(indice) ;
	    System.out.println("Resultat du choix min:"+noeud.get(noeud.size()-1).nom+" ayant comme heuristique "+noeud.get(noeud.size()-1).h);
	    pWriter.println("Resultat du choix min:"+noeud.get(noeud.size()-1).nom+" ayant comme heuristique "+noeud.get(noeud.size()-1).h);
		nOuvert.remove(indice) ;
		if ( noeud.get(noeud.size()-1).nom.equals(EF.nom)){
				System.out.println("Etude du sommet "+noeud.get(noeud.size()-1).nom);
				pWriter.println("Etude du sommet "+noeud.get(noeud.size()-1).nom);
				pWriter.println("But atteint ");
				System.out.println("But atteint ");
				parcours=parcours+EF.nom ;
				String chemin="" ;
				for(Sommet s : noeud){
					chemin=chemin+s.nom ;
					
				}
				test=true ; 
				System.out.println("Temps d'éxecution en ms: "+(System.currentTimeMillis()-debut)) ;
				System.out.println("Chemin="+chemin);
				System.out.println("Parcours="+parcours);
				pWriter.println("Temps d'éxecution en ms: "+(System.currentTimeMillis()-debut));
				pWriter.println("Chemin="+chemin) ; 
				pWriter.println("Parcours="+parcours);

				ArrayList<Arc>resultat= new ArrayList<Arc>() ;//liste des sommets du chemin joignant EI vers EF
    		resultat=this.panel.rechArc(noeud,this.panel.arcs) ;
    		int cout = 0 ;
    		for(Arc c : resultat){
    			cout=cout+c.cout ;
     		}
    		System.out.println("Cout="+cout);
    		pWriter.println("Cout="+cout);
    		pWriter.close();
				return noeud ;
				
			} 
		else{
			parcours=parcours+noeud.get(noeud.size()-1).nom ;
			System.out.println("Etude du sommet "+noeud.get(noeud.size()-1).nom);
			pWriter.println("Etude du sommet "+noeud.get(noeud.size()-1).nom);
 			ArrayList<Sommet> succ =this.panel.succList( noeud.get(noeud.size()-1)) ;
 			if(succ==null){
				
				System.out.println("L'element 1 de la frontiere n'a pas de successeur donc on le supprime et on passe au suivant");
				pWriter.println("L'element 1 de la frontiere n'a pas de successeur donc on le supprime et on passe au suivant");

			}else{
				System.out.println("Liste des successeurs :");
				pWriter.println("Liste des successeurs :");
				for(Sommet k : succ){
					System.out.println(k.nom);
					pWriter.println( k.nom);
				}
				
				ArrayList<Sommet> newElement ; 
				 for (int i =0;i<succ.size();i++){
					 newElement=new ArrayList<Sommet>() ; 
					 newElement.addAll(noeud) ; 
					 newElement.add(succ.get(i)) ;
					 nOuvert.add(newElement) ;
				 }
				 int size =nOuvert.get(rechHeuristiqueMin(nOuvert)).size() ;
				 if(noeud.get(noeud.size()-1).nom.equals(nOuvert.get(rechHeuristiqueMin(nOuvert)).get(size-1).nom)){
						captBloc++ ; 
						if(captBloc==3){
							System.out.println("Boucle infinie ==> pas de solution");
							pWriter.println("Boucle infinie ==> pas de solution");
							break ;
						
						}
						
					}else{//initier le capteur 
						captBloc=0 ;}
				 } 
 			nFerme.add(noeud) ;
				System.out.println("Dans ce stade la liste des noeuds ouverts contient :");
				pWriter.println("Dans ce stade la liste des neouds ouverts contient :");
				for(ArrayList<Sommet> liste : nOuvert){
					System.out.println("Liste:") ;
					pWriter.println("Liste:");
					for(Sommet s : liste){
						System.out.println(s.nom) ;
						pWriter.println(s.nom);
					}
			
			}
		}
		
	}
	pWriter.close();
	return null;
	  
	 
 }
//On definit une liste des noeuds ouverts , une liste des noeuds fermés et à partir de la 1ere liste on choisit un noeud pour developper ses successeurs 
//le choix se fait selon le cout du sommet qui est l'heuristique+coutRellel du chemin  !
 public ArrayList<Sommet>aEtoile(Sommet EI, Sommet EF) throws IOException{
	 PrintWriter pWriter = new PrintWriter(new FileWriter("src/algosRecherche.txt", true));
	 long debut = System.currentTimeMillis();
	  	ArrayList< listeA> nOuvert = new ArrayList<listeA>() ;
	 	ArrayList<listeA> nFerme = new ArrayList<listeA>() ;
	 	System.out.println("Recherche en A* : ") ; 
		System.out.println("Point de départ : "+EI.nom);
		System.out.println("Point d'arrivée : "+EF.nom);
		pWriter.println("Recherche en A* : ");
		pWriter.println("Point de départ : "+EI.nom);
		pWriter.println("Point d'arrivée : "+EF.nom);
		if(EI.nom.equals(EF.nom)){
			System.out.println("le point de depart et le point d'arrivée sont confondus");
			pWriter.println("le point de depart et le point d'arrivée sont confondus");
			pWriter.close();
			return null ;
		}
		
		
		if(this.panel.succList(EI)==null){
			System.out.println("le point de depart n'a pas de successeurs");   
			pWriter.println("le point de depart n'a pas de successeurs");
			pWriter.close();
			return null;
		}
		
		String parcours="" ; 
		ArrayList <Sommet> listEI = new ArrayList <Sommet>() ; 
		listEI.add(EI) ;
		nOuvert.add(new listeA(listEI,0+EI.h)) ;
		boolean test=false ;
		listeA noeud ;
		while (!test && nOuvert.size()!=0) {
			int indice =rechMin(nOuvert) ;	
			 noeud =new listeA( nOuvert.get(indice).liste,nOuvert.get(indice).cout) ;
			 nFerme.add(noeud) ; 
			    System.out.println("Resultat du choix min:"+noeud.liste.get(noeud.liste.size()-1).nom+" ayant comme cout "+noeud.cout);
			    pWriter.println("Resultat du choix min:"+noeud.liste.get(noeud.liste.size()-1).nom+" ayant comme cout "+noeud.cout);
			    nOuvert.remove(indice) ; 
			    if(noeud.liste.get(noeud.liste.size()-1).nom.equals(EF.nom)){
			    	System.out.println("But atteint ");
			    	parcours=parcours+EF.nom ;
					String chemin="" ;
					for(Sommet s : noeud.liste){
						chemin=chemin+s.nom ;	
					}
					test=true ; 
					System.out.println("Temps d'éxecution en ms: "+(System.currentTimeMillis()-debut)) ;
					System.out.println("Chemin="+chemin);
					System.out.println("Parcours="+parcours);
					pWriter.println("Temps d'éxecution en ms: "+(System.currentTimeMillis()-debut));
					pWriter.println("Chemin="+chemin) ; 
					pWriter.println("Parcours="+parcours);
					 
	    		System.out.println("Cout="+noeud.cout);
	    		pWriter.println("Cout="+noeud.cout);
	    		pWriter.close();
 			    	return noeud.liste ;
			    }
			    else{
					parcours=parcours+noeud.liste.get(noeud.liste.size()-1).nom ;
					System.out.println("Etude du sommet "+noeud.liste.get(noeud.liste.size()-1).nom);
					pWriter.println("Etude du sommet "+noeud.liste.get(noeud.liste.size()-1).nom);
					ArrayList<Sommet> succ =this.panel.succList( noeud.liste.get(noeud.liste.size()-1)) ;
		 			if(succ==null){
						
						System.out.println("Cet element  n'a pas de successeur donc on le supprime et on passe au suivant");
						pWriter.println("Cet element  n'a pas de successeur donc on le supprime et on passe au suivant");

					}else{
						System.out.println("Liste des successeurs :");
						pWriter.println("Liste des successeurs :");
						for(Sommet k : succ){
							System.out.println(k.nom);
							pWriter.println( k.nom);
						}
						ArrayList<Sommet> newElement ; 
						listeA newNoeud  ;
						 for (int i =0;i<succ.size();i++){
							 newElement=new ArrayList<Sommet>() ; 
							 newElement.addAll(noeud.liste) ; 
							 newElement.add(succ.get(i)) ;
							 newNoeud=new listeA(newElement,succ.get(i).h+ coutReel(newElement))  ;
							 int existe=exist(newNoeud,nOuvert) ; 
							 int existe2=exist(newNoeud,nFerme) ; 
 							 if(existe==-1 && existe2==-1){
								 System.out.println("L'element "+succ.get(i).nom+" n'existe pas ni dans la liste des ouverts ni dans la liste des fermés dans on l'ajoute dans nOuvert!");
								 pWriter.println("L'element "+succ.get(i).nom+" n'existe pas ni dans la luste des ouverts ni dans la liste des fermés dans on l'ajoute dans nOuvert!");
							 nOuvert.add(newNoeud);
							 System.out.println("l'ajout de "+ succ.get(i).nom +" a la liste des ouverts avec un cout egal a "+newNoeud.cout ) ;
							 pWriter.println("l'ajout de "+ succ.get(i).nom +" a la liste des ouverts avec un cout egal a "+newNoeud.cout) ;}
							 else{
								 if(existe!=-1){ 
									 if(nOuvert.get(existe).cout>newNoeud.cout){
										 System.out.println("Suppression de l'element "+succ.get(i).nom+" de la liste num "+existe + "avec un cout = "+nOuvert.get(existe).cout ) ;
										 pWriter.println("Suppression de l'element "+succ.get(i).nom+" de la liste num "+existe);
										 nOuvert.remove(existe) ; 
										 nOuvert.add(newNoeud) ; 
										 System.out.println("l'ajout de "+ succ.get(i).nom +" a la liste des ouverts avec un cout egal a "+newNoeud.cout ) ;
										 pWriter.println("l'ajout de "+ succ.get(i).nom+" a la liste des ouverts avec un cout egal a "+newNoeud.cout) ;
									 }else{
										 
									 System.out.println("on conserve l'ancien noeud sans ajout du nouveau ! ("+nOuvert.get(existe).cout+"<"+ newNoeud.cout) ;
									 pWriter.println("on conserve l'ancien noeud sans ajout du nouveau !" ) ;
									 }
								 	}
								 if(existe2!=-1){
									 System.out.println("Existance dans la liste des noeuds fermés : ");
									 if(nFerme.get(existe2).cout>newNoeud.cout){
										 
 										 nFerme.remove(existe2) ; 
										 System.out.println("Suppression de l'element "+succ.get(i).nom+" de la liste num "+existe2 ) ;
										 pWriter.println("Suppression de l'element "+succ.get(i).nom+" de la liste num "+existe2);
										 nOuvert.add(newNoeud) ; 
										 System.out.println("l'ajout de "+ succ.get(i).nom +" a la liste des ouverts avec un cout egal a "+newNoeud.cout ) ;
										 pWriter.println("l'ajout de "+ succ.get(i).nom +" a la liste des ouverts avec un cout egal a "+newNoeud.cout) ;
									 }else{
										 
									 System.out.println("on conserve l'ancien noeud sans ajout du nouveau ! ("+nFerme.get(existe2).cout+"<"+ newNoeud.cout) ;
									 pWriter.println("on conserve l'ancien noeud sans ajout du nouveau !" ) ;
									 }
								 	}
							 }
						 }
						
							System.out.println("Dans ce stade la liste des noeuds ouverts contient :");
							pWriter.println("Dans ce stade la liste des neouds ouverts contient :");
							for(listeA listeouv : nOuvert){
								System.out.println("Liste:cout="+listeouv.cout) ;
								pWriter.println("Liste:");
								for(Sommet s : listeouv.liste){
									System.out.println(s.nom) ;
									pWriter.println(s.nom);
								}}
							System.out.println("Dans ce stade la liste des noeuds fermes contient :");
							pWriter.println("Dans ce stade la liste des noeuds fermes contient :");
 							for(listeA listeouv : nFerme){
								System.out.println("Liste: avec "+listeouv.cout) ;
								pWriter.println("Liste:");
								for(Sommet s : listeouv.liste){
									System.out.println(s.nom ) ;
									pWriter.println(s.nom);
 								}}
					}
					
			    
		}}
	
	pWriter.close(); 
	return null;}
 //cette methode va verifier si le noeud choisit existe deja dans la liste des ouverts  ou bien dans la liste des noeuds fermés(selon l'entrée)
 //cette methode va retournée -1 si l'element n'appartient ni a la liste des ouverts ni a la liste des fermés 
 //dans le cas contraire ella va retournée l'indice ou se trouve ! 
 public int exist(listeA noeud,ArrayList<listeA> nListe){
	 boolean test=false ; 
	 for(int i=0;i<nListe.size();i++){
		 if( nListe.get(i).liste.get(nListe.get(i).liste.size()-1).equals(noeud.liste.get(noeud.liste.size()-1))){
 			 test=true ; 
			 return i ; 
		 }
	 }
	 return -1; 
 }
 //Calculer le cout reel d"une liste des sommets 
 public int coutReel(ArrayList<Sommet> total){
	 int somme =0 ; 
	 
	 for (int i=0;i<(total.size()-1);i++){
		 somme=somme+this.panel.rechArc(total.get(i), total.get(i+1)) ;
		 
	 }
	 	 
	return somme; 
	 
 }
//rechercher le sommet ayant le plus minimumheuristique dans la liste des noeuds ouverts
public int rechHeuristiqueMin(ArrayList<ArrayList<Sommet>> nOuvert) {
	int min = nOuvert.get(0).get(nOuvert.get(0).size()-1 ).h ;
	int indice = 0 ;
	if(nOuvert.size()==1){
		return 0 ; 
	}
	for(int i=0;i<nOuvert.size();i++){
		if(nOuvert.get(i).get(nOuvert.get(i).size()-1).h<min){
			indice = i ; 
			min=nOuvert.get(i).get(nOuvert.get(i).size()-1).h ;
		}
	}
 	return  indice ;
}
//cette methode est utilisé dans l'algo de recherche en A*.Elle retourne l'indice su sommet ayant le plus min cout 
public int rechMin(ArrayList<listeA> nOuvert) {
	ArrayList<Sommet>Element1=nOuvert.get(0).liste ;
	if(nOuvert.size()==1){
		return 0 ; 
	}
	int min=nOuvert.get(0).cout ; 
	int indice = 0 ;
	
	for(int i=1;i<nOuvert.size();i++){
		if(nOuvert.get(i).cout<min){
			min=nOuvert.get(i).cout ;  	
			indice=i ;
 		}
 	}
  	return  indice;
}
  
}
