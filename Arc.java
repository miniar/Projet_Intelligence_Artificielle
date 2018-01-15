//Class arc : 
//chaque arc est caractérisé par un point de départ (c'est la souce) et un point d'arrivée (c'est la cible) et le cout de chemin entre cette source et la cible

public class Arc {
	Sommet source;
	Sommet cible;
	int cout ; 
	public Arc (Sommet S, Sommet C,int cout){
		this.source=S;
		this.cible=C;
		this.cout=cout ; 
	}
 
}