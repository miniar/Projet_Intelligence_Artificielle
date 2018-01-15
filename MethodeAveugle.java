import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
//Dans cette on va definir la liste des methodes aveugles : 
public class MethodeAveugle {
	MonPanel panel ;
	public MethodeAveugle() throws FileNotFoundException{
		panel=new MonPanel() ; 
	}
	//Recherche en largeur : 
	//Principe : 
	//Si EI=EF ==> le point de depart coincide avec le point d'arrivée==> retourne null
	//Si EI n'a pas de successeurs==> retourne null
	//Sinon : tant que la frontiere n'est pas vide , on genere la liste des successeurs du premier element dans la frontiere 
	//la liste des successeurs seront ajoutés a la fin de la frontiere  
	//Si on atteint le but, on retourne le chemin de EI à EF 
		public ArrayList<Sommet> rechLargeur(Sommet EI, Sommet EF) throws IOException{
			//Fichier de trace : algosRecherche.txt
	        PrintWriter pWriter = new PrintWriter(new FileWriter("src/algosRecherche.txt", true));
	        //Pour calculer le temps d'execution : 
			long debut = System.currentTimeMillis();
	    	ArrayList<ArrayList <Sommet>> Frontiere = new ArrayList<ArrayList<Sommet>>() ; //liste des sommets dans la frontiere
  			System.out.println("Recherche en Largeur d'abord : ") ; 
			System.out.println("Point de départ : "+EI.nom);
			System.out.println("Point d'arrivée : "+EF.nom);
			pWriter.println("Recherche en Largeur d'abord : ");
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
			//Dans un premier lieu , la frontiere contient un seul element qui est le sommet de depart	
			ArrayList <Sommet> listEI = new ArrayList <Sommet>() ; 
			listEI.add(EI) ;
			Frontiere.add(listEI) ; 
			boolean test=false ; //test vaut true si  on atteint le but
			//tant qu'il existe des elemnts dans la frontiere et du'on na pas atteint le but , on relance le traitement de la recherchce en profondeur
			while (!test && Frontiere.size()!=0) {
				//Tout l'etude se base sur le premier element de la frontiere
			ArrayList<Sommet> Element1 = Frontiere.get(0) ; 
	 			if ( Element1.get(Element1.size()-1).nom.equals(EF.nom)){
	 				System.out.println("Etude du sommet "+Element1.get(Element1.size()-1).nom);
	 				pWriter.println("Etude du sommet "+Element1.get(Element1.size()-1).nom);
	 				pWriter.println("But atteint ");
	 				System.out.println("But atteint ");
	 				parcours=parcours+EF.nom ;
	 				String chemin="" ;
	 				for(Sommet s : Element1){
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
	        		resultat=this.panel.rechArc(Element1,this.panel.arcs) ;
	        		int cout = 0 ;
	        		for(Arc c : resultat){
	        			cout=cout+c.cout ;
 	        		}
	        		System.out.println("Cout="+cout);
	        		pWriter.println("Cout="+cout);
	        		pWriter.close();
	 				return Element1 ;
	 				
					}  
					else { 
						parcours=parcours+Element1.get(Element1.size()-1).nom ;
						System.out.println("Etude du sommet "+Element1.get(Element1.size()-1).nom);
						pWriter.println("Etude du sommet "+Element1.get(Element1.size()-1).nom);
						//Generer la liste des successeurs du premier element dans la frontiere
						ArrayList<Sommet> succ =this.panel.succList( Element1.get(Element1.size()-1)) ; 
						Frontiere.remove(0) ; 
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
							 newElement.addAll(Element1) ; 
							 newElement.add(succ.get(i)) ;
							 Frontiere.add(newElement) ;
						 }	 
						 } 
						System.out.println("Dans ce stade la frontiere contient :");
						pWriter.println("Dans ce stade la frontiere contient :");
						for(ArrayList<Sommet> liste : Frontiere){
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
			return null ; 
			
	    }
		//meme traitement que la recherche en largeur , la seule différece est que l'ajout des successeurs se fait au debut de la frontiere à l'aide d'une variable auxiliaire
    //Pour eviter les boucles infinies , on a déclaré un compteur 
		public ArrayList<Sommet> rechProfondeur(Sommet EI, Sommet EF) throws IOException{
        PrintWriter pWriter = new PrintWriter(new FileWriter("src/algosRecherche.txt", true));
    	ArrayList<ArrayList <Sommet>> Frontiere = new ArrayList<ArrayList<Sommet>>() ; //liste des sommets dans la frontiere
  		int captBloc =0  ; // c'est un compteur qui sert a detecter les boucles infinies
		System.out.println("Recherche en Profondeur d'abord : ") ; 
		System.out.println("Point de départ : "+EI.nom);
		System.out.println("Point d'arrivée : "+EF.nom);
		pWriter.println("Recherche en Profondeur d'abord : ");
		pWriter.println("Point de départ : "+EI.nom);
		pWriter.println("Point d'arrivée : "+EF.nom);
		String parcours="" ;
		
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
		
		
		//Dans un premier lieu , la frontiere contint un seul element qui est le sommet de depart	
		ArrayList <Sommet> listEI = new ArrayList <Sommet>() ; 
		long debut = System.currentTimeMillis();
		listEI.add(EI) ;
		Frontiere.add(listEI) ; 
		boolean test=false ; //test vaut true si  on atteint le but
		//tant qu'il existe des elemnts dans la frontiere et du'on na pas atteint le but , on relance le traitement de la recherchce en profondeur
		while (!test && Frontiere.size()!=0) {
			//Tout l'etude se bae sur le premier element de la frontiere
		ArrayList<Sommet> Element1 = Frontiere.get(0) ; 
 			if ( Element1.get(Element1.size()-1).nom.equals(EF.nom)){
 				System.out.println("But atteint ");
 				pWriter.println("But atteint ");
 				test=true ; 
 				String chemin="" ;
 				for(Sommet s : Element1){
 					chemin=chemin+s.nom ;
 				}
 				
 				System.out.println("Chemin="+chemin);
 				pWriter.println("Chemin="+chemin);
 				ArrayList<Arc>resultat= new ArrayList<Arc>() ;//liste des sommets du chemin joignant EI vers EF
        		resultat=this.panel.rechArc(Element1,this.panel.arcs) ;
        		int cout = 0 ;
        		for(Arc c : resultat){
        			cout=cout+c.cout ;
        		}
 				System.out.println("Temps d'éxecution en ms: "+(System.currentTimeMillis()-debut)) ;
 				pWriter.println("Temps d'éxecution en ms: "+(System.currentTimeMillis()-debut));
        		System.out.println("Cout="+cout);
        		pWriter.println("Cout="+cout);
        		parcours=parcours+EF.nom ; 
        		System.out.println("Parcours = "+parcours);
        		pWriter.println("Parcours = "+parcours);
        		pWriter.close();
 				return Element1 ;
 				
 				
				}  
				else { 
					System.out.println("Etude du sommet "+Element1.get(Element1.size()-1).nom);
					pWriter.println("Etude du sommet "+Element1.get(Element1.size()-1).nom);
					parcours=parcours+Element1.get(Element1.size()-1).nom ; 
					//Generer la liste des successeurs du premier element dans la frontiere
					ArrayList<Sommet> succ =this.panel.succList( Element1.get(Element1.size()-1)) ; 
 					if(succ==null){
						Frontiere.remove(0) ; 
						System.out.println("L'element 1 de la frontiere n'a pas de successeur donc on le supprime et on passe au suivant");
						pWriter.println("L'element 1 de la frontiere n'a pas de successeur donc on le supprime et on passe au suivant");
					}else{
						System.out.println("Liste des successeurs :");
						pWriter.println("Liste des successeurs :");
						for(Sommet k : succ){
							System.out.println(k.nom);
							pWriter.println(k.nom);
						}
						if(Element1.get(Element1.size()-1).nom.equals(succ.get(0).nom)){
							captBloc++ ; 
							if(captBloc==3){
								System.out.println("Boucle infinie ==> pas de solution");
								pWriter.println("Boucle infinie ==> pas de solution");
								pWriter.close();
								break ;
							
							}
							
						}else{//initier le capteur 
							captBloc=0 ;}
						ArrayList<ArrayList<Sommet>> aux=new ArrayList<ArrayList<Sommet>>();
						if (Frontiere.size()>1) {
							//Principe de fonctionnement : 
							//Si la frontiere contient un seul element :on genere ses successeurs t on verifie s'il s'agit d'un but ou non
							//Sinon on utilise une liste intermediaire qui va prendre dans le depart la liste appartenant a la frontiere privée de 1ere element
							//Cette chaie nous aide a ajouter les successeurs d'un sommet au debut de la frontiere(En fait, c le principe general de la rech en Profondeur
							for(int l=1;l<Frontiere.size();l++)
								{
								aux.add(Frontiere.get(l)) ; 
						        }
						
						ArrayList<Sommet> newElement ; 
						Frontiere.removeAll(Frontiere);
					 for (int i =0;i<succ.size();i++){
						 newElement=new ArrayList<Sommet>() ; 
						 newElement.addAll(Element1) ; 
						 newElement.add(succ.get(i)) ;
						 Frontiere.add(newElement) ;
					 }
					 Frontiere.addAll(aux) ;}else{
						 ArrayList<Sommet> newElement ; 
							Frontiere.removeAll(Frontiere);
						 for (int i =0;i<succ.size();i++){
							 newElement=new ArrayList<Sommet>() ; 
							 newElement.addAll(Element1) ; 
							 newElement.add(succ.get(i)) ;
							 Frontiere.add(newElement) ;
						 }
					 }			}
					System.out.println("Dans ce stade la frontiere contient :");
					pWriter.println("Dans ce stade la frontiere contient :");
					for(ArrayList<Sommet> liste : Frontiere){
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
		return null ; 
    }
	
    
    
    
   //EPLI: c'est le meme traitement que la recherche en profondeur d'abord mais l'avancement se fait par niveau  : 
    // la liste des successeurs est liée à la profondeur qui est initiée à 0 
    //Pour effectuer ce traitement , on a besoin d'une autre classe appelée AlgoEPLI pour controler les différents niveaux et par la suite les successeurs de chaque somet visité
    
    public ArrayList<Sommet> EPLI(Sommet EI, Sommet EF) throws IOException{
        PrintWriter pWriter = new PrintWriter(new FileWriter("src/algosRecherche.txt", true));

		long debut = System.currentTimeMillis();
    	ArrayList<ArrayList <Sommet>> Frontiere  ; //liste des sommets dans la frontiere
  		int captBloc =0  ; // c'est un compteur qui sert a detecter les boucles infinies
		System.out.println("Recherche en EPLI: ") ; 
		System.out.println("Point de départ : "+EI.nom);
		System.out.println("Point d'arrivée : "+EF.nom);
		pWriter.println("Recherche en EPLI: ") ;
		pWriter.println("Point de départ : "+EI.nom) ;
		pWriter.println("Point d'arrivée : "+EF.nom) ;
		String parcours="" ;
		int profondeur= 0 ;
		
		if(EI.nom.equals(EF.nom)){
			System.out.println("le point de depart et le point d'arrivée sont confondus");
			pWriter.println("le point de depart et le point d'arrivée sont confondus") ;
			pWriter.close();
			return null ;
		}
		
		
		if(this.panel.succList(EI)==null){
			System.out.println("le point de depart n'a pas de successeurs");
			pWriter.println("le point de depart n'a pas de successeurs") ;
			pWriter.close();
			return null;
		}
		
 		
		while(true) {
			ArrayList<AlgoEPLI> ind= new ArrayList<AlgoEPLI>() ;
 			 Frontiere = new ArrayList<ArrayList<Sommet>>() ;
		int	nbre=0 ; //nbre de fois , on fait un appel a la methode succ
 		ArrayList <Sommet> listEI = new ArrayList <Sommet>() ; 
		listEI.add(EI) ;
		Frontiere.add(listEI) ; 
		ind.add(new AlgoEPLI(listEI,nbre)) ;
		boolean test=false ;
		System.out.println("Niveau num : "+profondeur);
		pWriter.println("Niveau num : "+profondeur) ;

		while (!test && Frontiere.size()!=0 ) {
			
 		ArrayList<Sommet> Element1 = Frontiere.get(0) ; 
 		 
 			if ( Element1.get(Element1.size()-1).nom.equals(EF.nom)){
 				System.out.println("But atteint ");
 				pWriter.println("But atteint ") ;

 				test=true ; 
 				String chemin="" ;
 				for(Sommet s : Element1){
 					chemin=chemin+s.nom ;
 				}
 				System.out.println("Temps d'éxecution en ms: "+(System.currentTimeMillis()-debut)) ;
 				System.out.println("Chemin="+chemin);
 				pWriter.println("Temps d'éxecution en ms: "+(System.currentTimeMillis()-debut)) ;
 				pWriter.println("Chemin="+chemin) ;

 				ArrayList<Arc>resultat= new ArrayList<Arc>() ;//liste des sommets du chemin joignant EI vers EF
        		resultat=this.panel.rechArc(Element1,this.panel.arcs) ;
        		int cout = 0 ;
        		for(Arc c : resultat){
        			cout=cout+c.cout ;
        		}
        		System.out.println("Cout="+cout);
        		pWriter.println("Cout="+cout);
        		parcours=parcours+EF.nom ; 
        		System.out.println("Parcours = "+parcours);
        		pWriter.println("Parcours = "+parcours);
        		pWriter.close();
 				return Element1 ;
				}  
				else { 
					
					System.out.println("Etude du sommet "+Element1.get(Element1.size()-1).nom);
					pWriter.println("Etude du sommet "+Element1.get(Element1.size()-1).nom);
					parcours=parcours+Element1.get(Element1.size()-1).nom ; 
					//Generer la liste des successeurs du premier element dans la frontiere
 					ArrayList<Sommet> succ = new ArrayList<Sommet>() ; 
					if(profondeur==0 ){
						System.out.println("Frontiere vide");
						pWriter.println("Frontiere vide");
						break ; 
						}
					if(ind.get(0).indice<profondeur ){
					  succ =this.panel.succList( Element1.get(Element1.size()-1)) ; 
					  ; }else{succ=null ;}
					
 					if(succ==null){
 						ind.remove(0) ;
						Frontiere.remove(0) ; 
						 
						System.out.println("L'element 1 de la frontiere n'a pas de successeur donc on le supprime et on passe au suivant");
						pWriter.println("L'element 1 de la frontiere n'a pas de successeur donc on le supprime et on passe au suivant");
					}else{
						System.out.println("Liste des successeurs :");
						pWriter.println("Liste des successeurs :");
						for(Sommet k : succ){
							System.out.println(k.nom);
							pWriter.println(k.nom);
						}
						if(Frontiere.size()==0){
							System.out.println("Frontiere vide");
							pWriter.println("Frontiere vide");
							break ;
						}

						  
						if(Element1.get(Element1.size()-1).nom.equals(succ.get(0).nom)){
							captBloc++ ; 
							if(captBloc==3){
								System.out.println("Boucle infinie ==> pas de solution");
								pWriter.println("Boucle infinie ==> pas de solution");
								break ;
							
							}
							
						}else{//initier le capteur 
							captBloc=0 ;}
						ArrayList<ArrayList<Sommet>> aux=new ArrayList<ArrayList<Sommet>>();
						ArrayList<AlgoEPLI> auxInd = new ArrayList<AlgoEPLI>() ;
						 
						if (Frontiere.size()>1) {
							for(int l=1;l<Frontiere.size();l++)
								{
								auxInd.add(ind.get(l)) ; 
 								aux.add(Frontiere.get(l)) ; 
						        }
						int indice = ind.get(0).indice ; 
						ArrayList<Sommet> newElement ; 
						Frontiere.removeAll(Frontiere);
						ind.removeAll(ind) ; 
					 for (int i =0;i<succ.size();i++){
						 newElement=new ArrayList<Sommet>() ; 
						 newElement.addAll(Element1) ; 
						 newElement.add(succ.get(i)) ;
						 Frontiere.add(newElement) ;
						 ind.add(new AlgoEPLI(newElement,indice+1)) ; 
						 
					 }
					 Frontiere.addAll(aux) ;
					 ind.addAll(auxInd) ;}else{
						 ArrayList<Sommet> newElement ; 
							Frontiere.removeAll(Frontiere);
							int indice = ind.get(0).indice ; 
							ind.removeAll(ind) ; 
							
						 for (int i =0;i<succ.size();i++){
							 newElement=new ArrayList<Sommet>() ; 
							 newElement.addAll(Element1) ; 
							 newElement.add(succ.get(i)) ;
							 Frontiere.add(newElement) ;
							 ind.add(new AlgoEPLI(newElement,indice+1)) ; 

						 }
					 }			}
					System.out.println("Dans ce stade la frontiere contient :");
					pWriter.println("Dans ce stade la frontiere contient :");
					for(ArrayList<Sommet> liste : Frontiere){
						System.out.println("Liste:") ;
						pWriter.println("Liste:");
						for(Sommet s : liste){
							System.out.println(s.nom) ;
							pWriter.println(s.nom);
						}
						
					}
 					 
				}
			}
		profondeur++ ;
	} } 
    //cette methode de recherche est basée sur le cout de chemin d'un sommet a un autre 
    //On utilise le meme traitement mais le choix d'un element de la frontiere se fait selon le sommet ayant le plus petit cout jusqu'à atteindre le but 
public ArrayList<Sommet> coutUniforme(Sommet EI,Sommet EF) throws IOException{
    PrintWriter pWriter = new PrintWriter(new FileWriter("src/algosRecherche.txt", true));

	long debut = System.currentTimeMillis();
	ArrayList<ArrayList <Sommet>> Frontiere  ; //liste des sommets dans la frontiere
 	System.out.println("Recherche en cout uniforme: ") ; 
 	pWriter.println("Recherche en cout uniforme: ");
	System.out.println("Point de départ : "+EI.nom);
	System.out.println("Point d'arrivée : "+EF.nom);
	pWriter.println("Point de départ : "+EI.nom);
	pWriter.println("Point d'arrivée : "+EF.nom);
	String parcours="" ;
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
	//D'abord , la frontiere contient un seul element qui est le point de depart 
 	 Frontiere = new ArrayList<ArrayList<Sommet>>() ;
 	 ArrayList <Sommet> listEI = new ArrayList <Sommet>() ; 
 	 listEI.add(EI) ;
 	 Frontiere.add(listEI) ; 
 	 boolean test=false ;//test=true si on atteint le but
 	 ArrayList<AlgoCoutUni> coutList = new ArrayList<AlgoCoutUni>() ; // elle  est sous la forme : liste / cout pour obtenir cette liste
 	 coutList.add(new AlgoCoutUni(listEI,0)) ;
 	 
 	 while (!test && Frontiere.size()!=0 ) {
 		 //Principe : au lieu de faire le test sur le 1ere element de la frontiere , on cherche parmi les sommets dans la frontiere celui 
 		 //qui a le minimum comme cout : 
 		   int indiceMin = rechMin(coutList) ; 
 		   AlgoCoutUni elem=coutList.get( indiceMin); 
			ArrayList<Sommet> Element1 = elem.front ; 
	 		   System.out.println("Cout minimal est "+elem.coutReel+" du sommet "+ Element1.get(Element1.size()-1).nom);
	 		  pWriter.println("Cout minimal est "+elem.coutReel+" du sommet "+ Element1.get(Element1.size()-1).nom);
 		if ( Element1.get(Element1.size()-1).nom.equals(EF.nom)){
				System.out.println("But atteint ");
				pWriter.println("But atteint ");
				test=true ; 
				parcours=parcours+EF.nom ;
 				String chemin="" ;
 				for(Sommet s : Element1){
 					chemin=chemin+s.nom ;
 					
 				}
 				System.out.println("Temps d'éxecution en ms: "+(System.currentTimeMillis()-debut)) ;
 				System.out.println("Chemin="+chemin);
 				System.out.println("Parcours="+parcours);
 				System.out.println("Cout="+elem.coutReel);
 				pWriter.println("Temps d'éxecution en ms: "+(System.currentTimeMillis()-debut));
 				pWriter.println("Chemin="+chemin);
 				pWriter.println("Parcours="+parcours);
 				pWriter.println("Cout="+elem.coutReel);
 				pWriter.close();
 				return Element1 ;}
 		else { 
 			parcours=parcours+Element1.get(Element1.size()-1).nom ;
 			System.out.println("Etude du sommet "+Element1.get(Element1.size()-1).nom);
 			pWriter.println("Etude du sommet "+Element1.get(Element1.size()-1).nom);
 			//Generer la liste des successeurs du sommet choisi depuis la frontiere
			ArrayList<Sommet> succ =this.panel.succList( Element1.get(Element1.size()-1)) ; 
			Frontiere.remove(indiceMin) ; 
			coutList.remove(indiceMin) ;
				if(succ==null){	
				System.out.println("L'element 1 de la frontiere n'a pas de successeur donc on le supprime et on passe au suivant");
				pWriter.println("L'element 1 de la frontiere n'a pas de successeur donc on le supprime et on passe au suivant");
			    }else{
				System.out.println("Liste des successeurs :");
				pWriter.println("Liste des successeurs :");
				for(Sommet k : succ){
					System.out.println(k.nom);
					pWriter.println(k.nom);
				}
				ArrayList<Sommet> newElement ; 
				int coutArc =0 ;
				 for (int i =0;i<succ.size();i++){
 					 newElement=new ArrayList<Sommet>() ; 
					 newElement.addAll(Element1) ; 
					 newElement.add(succ.get(i)) ;
					 Frontiere.add(newElement) ;
					 coutArc=this.panel.rechArc(Element1.get(Element1.size()-1),succ.get(i)) ;
					 coutList.add(new AlgoCoutUni(newElement,coutArc+elem.coutReel)) ; 
					 
				 }	 
				  
				System.out.println("Dans ce stade la frontiere contient :");
				pWriter.println("Dans ce stade la frontiere contient :");
				int i=0 ;
				for(ArrayList<Sommet> liste : Frontiere){
					System.out.println("Liste:") ;
					pWriter.println("Liste:");
					for(Sommet s : liste){
						System.out.println(s.nom) ;
						pWriter.println(s.nom);
					}System.out.println("Cout : "+coutList.get(i).coutReel);
					pWriter.println("Cout : "+coutList.get(i).coutReel); i++ ;
					}}}}
 				
	pWriter.close();		 
	return null; }
 
//cette methode sert à chercher le sommet ayant le cout le plus petit 
//elle retourne son indice
//On l'utilise dans l'algorithme de la recherche en Cout uniforme et liste est la frontiere ! 
public int rechMin(ArrayList<AlgoCoutUni> liste){
	 int min=liste.get(0).coutReel ; 
	 int indice= 0 ; 
	 if(liste.size()==1){
		 return (0) ;
	 }
	 for(int j=0;j<liste.size();j++){
		 if(liste.get(j).coutReel<min){
			min=liste.get(j).coutReel ;
			 indice=j ;
		 }
	 }
	 
	 
	return indice;
	 
}
	 
}
 