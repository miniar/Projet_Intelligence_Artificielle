# Projet_Intelligence_Artificielle
Ce projet consiste à présenter une application montrant le fonctionnement des algorithmes de recherche pour la résolution des problèmes généraux. Les algorithmes à mettre en oeuvre sont les algorithmes aveugles ainsi que les algorithmes non informés .
On crée un fichier texte dans lequel on décrit les sommets et les arcs.
Une ligne commençant par "S" désigne un sommet, chaque sommet a un nom une heuristique et une position x et y.
Une ligne commençant par "A" désigne un arc, chaque arc a un point de départ et un point d'arrivée et un cout. 
Les classes utilisées sont les suivantes:
Classe "Sommet":
Un sommet a un nom, une valeur heuristique, un couple (x,y) présentant sa position dans le graphe et une couleur calculée aléatoirement.
Classe "Arc":
Chaque arc est caractérisé par un point de départ qui est la source et point d'arrivée qui est la cible et le cout de chemin entre cette source et la cible.
Classe "MonPanel":
Elle contient deux listes: la liste des sommets et la liste des arcs.
Ces deux listes seront déterminées par le constructeur à partir d'un fichier texte.
Le principe est de lire le texte ligne par ligne et si la ligne commence par "S" alors c'est un sommet sinon c'est un arc. 
On déclare un deuxième constructeur prenant en paramètres une liste des sommets et une liste des arcs pour construire un panel.
La méthode "rechArc()": retourne le cout d'un arc en passant en paramètre la source et la cible de l'arc.
La méthode "paintComponent()": sert à dessiner la liste des sommets présentés par des cercles pleins et la liste des arcs présentés des ligne reliant la source à la cible. S'il s'agit d'une boucle on dessine un arc.
La méthode "addSommet()": permet d'ajouter un sommet à la liste des sommets.
La méthode "addArc()": permet d'ajouter un arc à la liste des arcs.
La méthode "rechercheS()": renvoie un objet de type sommet, prend en paramètre le nom de ce sommet. Elle retourne null en cas d'inexistence. 
La méthode "succList()": retourne les successeurs d'un sommet. S'il existe un arc entre ce sommet en tant que source et un autre point, on l'ajoute à la liste des successeurs.
La classe "listeA" est utilisée pour réaliser l'algorithme A*.
La classe "AlgoEPLI" est utilisée pour réaliser l'algorithme EPLI.
La classe "AlgoCoutUni" est utilisée pour réaliser l'algorithme à cout uniforme.
La classe "MethodeAveugle":
Dans cette classe on définit la liste des méthodes aveugles.
La méthode "rechLargeur()": effectue la recherche en largeur. Si EI=EF alors le point de départ coïncide avec le point d'arrivée et la méthode retourne null. Sinon, tant que la frontière n'est pas vide, on génère la liste des successeurs du premier élément dans la frontière. les successeurs seront ajoutés à la fin de la frontière. Si on atteint le but, on retourne le chemin de EI à EF.                                                 Puis on calcule le temps d'exécution.
Dans un premier lieu, la frontière contient un seul élément qui est le sommet de départ.
On déclare une variable booléenne "test" qui vaut true si le but est atteint.
Tant qu'il existe des éléments dans la frontière et qu'on n'a pas atteint le but, on relance le traitement de la recherche en largeur.
La méthode "RechProfondeur()": c'est le même traitement que la recherche en largeur, la seule différence est que l'ajout des successeurs se fait en tête de la frontière à l'aide d'une variable auxiliaire. On déclare un compteur pour éviter les boucles infinies.
La méthode "EPLI()": c'est le même traitement que la recherche en profondeur d'abord mais l'avancement se fait par niveau. La liste des successeurs est liée à la profondeur qui est initiée à 0. Pour effectuer ce traitement, on a besoin d'une autre classe appelée AlgoEPLI pour contrôler les différents niveaux et par la suite les successeurs de chaque sommet visité.
La méthode "coutUniforme()": cette méthode de recherche est basée sur le cout de chemin d'un sommet à un autre. On utilise le même traitement mais le choix d'un élément de la frontière se fait selon le sommet ayant le plus petit cout jusqu'à atteindre le but. 
La liste "Frontiere" contient la liste des sommets dans la frontière. D'abord elle contient un seul élément qui est le point de départ. Puis au lieu de faire le test sur le premier élément de la frontière, on cherche parmi les sommets dans la frontière celui ayant le minimum comme cout.
La méthode "rechMin()": sert à chercher le sommet ayant le cout le plus petit. Elle retourne son indice. On l'utilise dans l'algorithme de la recherche par cout uniforme et la liste est la frontière.
Classe "RechInformes":
Cette classe définit principalement les méthodes de recherche informées tel que Recherche meilleur d'abord gloutonne et A*.
On définit une liste des nœuds ouverts et fermés et à partir de la première liste, on choisit un nœud pour développer ses successeurs. Le choix se fait selon le cout du sommet qui est l'heuristique de ce dernier. 
La méthode "aEtoile()": On définit une liste des nœuds ouverts et fermés, à partir de la première liste on choisit un nœuds pour développer ses successeurs. 
Le choix se fait selon le cout du sommet qui est l'heuristique + cout réel du chemin.
La méthode "exist()": vérifie si le nœud choisit existe déjà dans la liste des ouverts ou bien dans la liste des nœuds fermés (selon l'entrée). Elle retourne -1 si l'élément n'appartient ni à la liste des ouverts ni à la liste des fermés. Dans le cas contraire elle retourne l'indice ou se trouve.
La méthode" coutReel()": calculer le cout réel d'une liste des sommets.
 La méthode "rechHeuristiqueMin()": recherche le sommet ayant l'heuristique la plus minimale dans la liste des nœuds ouverts.
La méthode "rechMin()": utilisée dans l'algorithme de recherche A*. Elle retourne l'indice du sommet ayant le cout le plus minimum.
Classe "tester": 
Cette classe contient le main, elle définit les fenêtres à ouvrir dans l'exécution  du programme.
La première fenêtre est la fenêtre d'accueil, contient le graphe initial de l'application (donc il définit le panel en initialisant la liste des sommets et des arcs à partir du fichier texte. Elle définit aussi l'ensemble des algorithmes qu'on peut exécuter par des boutons.
En cliquant sur l'un de ces boutons, une nouvelle fenêtre,  intitulé "Algorithme choisi" s'ouvre dans lequel on peut saisir le point de départ ainsi que le point d'arrivée.
Le bouton "OK" sert à exécuter ce dernier. Si le résultat retourné est différent de null, le graphe de chemin final sera affiché sinon toute l'exécution apparait sur la console.
Le bouton "QUIT" nous permet de quitter le programme.
Les traces seront enregistrées dans un fichier texte intitulé "algoRecherche".

