import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
//Dans cette classe, on definit les fenetres à ouvrir dans l'execution du programme et elle contient le "main"
//La premiere fenetre est la fenetre d'acceuil , contient le graphe initial de l'application (donc il definit le panel en initialisant la liste des sommets et des arcs a partir du fichier texte
//elle definit aussi l'ensemble des algorithmes qu'on peut executer par des boutons! 
//En cliquant sur l'un de ces boutons , une nouvelle fenetre , intitulé "Algorithme choisi" s'ouvre dans on peut saisir le point de depart ainsi que le point d'arrivée 
//Le bouton "ok" sert a executer ce dernier . Si le resultat retourné est différent de "null" , le graphe de chemin final sera affiché sinon toute l'exécution apparait sur la console
//Le bouton "QUIT" nous permet de quitter le programme ! 
public class Tester {
	public static void main(String[]args) throws IOException {
	JFrame f=new JFrame();
 	f.setTitle("Graphe des sommet ");
    f.setSize(900, 700);
    f.setLocationRelativeTo(null);               
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
 
        MonPanel p = new MonPanel ();  
        JPanel panel = new JPanel() ;
        panel.setBackground(Color.darkGray);
       
        JSplitPane splitPane = new JSplitPane();
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel, p);
 
 
        
        
        JButton largeur = new JButton("Recherche en largeur");
        largeur.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		
 

        		JFrame largeurWindow=new JFrame() ;
        		 largeurWindow.setTitle(" Recherche en largeur");
        		 largeurWindow.setSize(900, 700);
        		 largeurWindow.setLocationRelativeTo(null);               
        		 largeurWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
        		largeurWindow.setVisible(true);
        	  
                JPanel panel2 = new JPanel() ;
                JPanel panel3 = new JPanel() ; 
                panel2.setBackground(Color.gray);
                JSplitPane splitPane1 = new JSplitPane();
    	        splitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel2,panel3);
    	        panel.setLayout( new GridLayout(3,2)) ;
    	        JLabel label = new JLabel("Saisir EI :");
    	        JTextArea text =new JTextArea ("         ") ;
    	        JTextArea text2 =new JTextArea ("         ") ;
    	        panel2.add(label) ; 
    	        panel2.add(text) ; 
    	        JLabel label2 = new JLabel("Saisir EF :");
    	        panel2.add(label2) ; 
    	        panel2.add(text2) ; 
    	       
    	        JButton button = new JButton("OK !");
    	        button.addActionListener(new ActionListener() {
    	        	public void actionPerformed(ActionEvent arg0) {
    	        		MethodeAveugle av;
    	        		try {
    	        			 
    						av = new MethodeAveugle();
    						
    						Sommet EI = av.panel.rechercheS(text.getText().trim().toUpperCase()) ; 
    		        		Sommet EF = av.panel.rechercheS(text2.getText().trim().toUpperCase()) ; 
    		        		 
    		        		if(EI==null || EF==null)
    		        		{System.out.println("Veuillez Vérifier les sommets choisis !");}
     		        		else{
    		        		 ArrayList<Sommet> res=new ArrayList<Sommet>() ;
    		        		res=av.rechLargeur(EI, EF) ; 
    		        		if(res!=null){
     		        		ArrayList<Arc>resultat= new ArrayList<Arc>() ;//liste des sommets du chemin joignant EI vers EF
    		        		resultat=p.rechArc(res,p.arcs) ; //liste Des arcs entre ses sommets
    		        		MonPanel pRL =new  MonPanel(res,resultat) ;
    		        		 
							largeurWindow.getContentPane().removeAll();
							
			    	        JSplitPane splitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel2,pRL);

			    	        largeurWindow.getContentPane().add(splitPane1);

     		    	        largeurWindow.repaint();
    		    	        largeurWindow.validate();} }
    		        		
    					} catch (IOException e) {
    						 
    						e.printStackTrace();
    					}
    	        	}
    	        });
    	        panel2.add(button);
    	        
    	        JButton btnQuit = new JButton("QUIT");
    	        btnQuit.addActionListener(new ActionListener() {
    	        	public void actionPerformed(ActionEvent e) {
    	        		largeurWindow.dispose();
    	        	}
    	        });
    	        panel2.add(btnQuit);
    	        largeurWindow.getContentPane().add(splitPane1);
    	        
    	         
    	
				}
        		
        	
        });
        
        JButton  bRProfondeur = new JButton("Recherche en Profondeur");
      
        bRProfondeur.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		JFrame profWindow=new JFrame() ;
        		profWindow.setTitle(" Recherche en profondeur");
        		profWindow.setSize(900, 700);
        		profWindow.setLocationRelativeTo(null);               
        		profWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
        		profWindow.setVisible(true);
       	  
               JPanel panel4 = new JPanel() ;
               JPanel panel5 = new JPanel() ; 
               panel4.setBackground(Color.gray);
               JSplitPane splitPane1 = new JSplitPane();
   	        splitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel4,panel5);
   	        panel.setLayout( new GridLayout(3,2)) ;
   	        JLabel label = new JLabel("Saisir EI :");
   	        JTextArea text =new JTextArea ("         ") ;
   	        JTextArea text2 =new JTextArea ("         ") ;
   	        panel4.add(label) ; 
   	        panel4.add(text) ; 
   	        JLabel label2 = new JLabel("Saisir EF :");
   	        panel4.add(label2) ; 
   	        panel4.add(text2) ; 
   	        
   	        JButton button = new JButton("OK !");
   	        button.addActionListener(new ActionListener() {
   	        	public void actionPerformed(ActionEvent arg0) {
   	        		MethodeAveugle av;
   	        		try {
   						av = new MethodeAveugle();
   						Sommet EI = av.panel.rechercheS(text.getText().trim() ) ; 
   		        		Sommet EF = av.panel.rechercheS(text2.getText().trim() ) ; 
   		        		 
   		        		 ArrayList<Sommet> res2=new ArrayList<Sommet>() ;
   		        		res2=av.rechProfondeur(EI, EF) ; 
   		        		if(res2!=null){
    		        		ArrayList<Arc>resultat2= new ArrayList<Arc>() ;//liste des sommets du chemin joignant EI vers EF
   		        		resultat2=p.rechArc(res2,p.arcs) ; //liste Des arcs entre ses sommets
   		        		MonPanel pRP =new  MonPanel(res2,resultat2) ;
   		        		 
   		        		profWindow.getContentPane().removeAll();
							
			    	        JSplitPane splitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel4,pRP);

			    	        profWindow.getContentPane().add(splitPane1);

			    	        profWindow.repaint();
			    	        profWindow.validate();}
   		        		
   					} catch (IOException e) {
   						 
   						e.printStackTrace();
   					}
   	        	}
   	        });
   	        panel4.add(button);
   	        
   	        JButton btnQuit = new JButton("QUIT");
   	        btnQuit.addActionListener(new ActionListener() {
   	        	public void actionPerformed(ActionEvent e) {
   	        		profWindow.dispose();
   	        	}
   	        });
   	        panel4.add(btnQuit);
   	     profWindow.getContentPane().add(splitPane1);
   	        
   	         
   	
				}
       		
       	
       });
        panel.add(bRProfondeur);
        panel.add(largeur);
        
        JButton rechEpli = new JButton("Recherche en EPLI");
      
        rechEpli.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		JFrame epliW=new JFrame() ;
        		epliW.setTitle(" Recherche en EPLI");
        		epliW.setSize(900, 700);
        		epliW.setLocationRelativeTo(null);               
        		epliW.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
        		epliW.setVisible(true);
       	  
               JPanel panel6 = new JPanel() ;
               JPanel panel7 = new JPanel() ; 
               panel6.setBackground(Color.gray);
               JSplitPane splitPane2 = new JSplitPane();
   	        splitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel6,panel7);
   	        panel.setLayout( new GridLayout(3,2)) ;
   	        JLabel label = new JLabel("Saisir EI :");
   	        JTextArea text =new JTextArea ("         ") ;
   	        JTextArea text2 =new JTextArea ("         ") ;
   	        panel6.add(label) ; 
   	        panel6.add(text) ; 
   	        JLabel label2 = new JLabel("Saisir EF :");
   	        panel6.add(label2) ; 
   	        panel6.add(text2) ; 
   	        
   	        JButton button = new JButton("OK !");
   	        button.addActionListener(new ActionListener() {
   	        	public void actionPerformed(ActionEvent arg0) {
   	        		MethodeAveugle av;
   	        		try {
   						av = new MethodeAveugle();
   						Sommet EI = av.panel.rechercheS(text.getText().trim() ) ; 
   		        		Sommet EF = av.panel.rechercheS(text2.getText().trim() ) ;
   		        	 
   		        		 ArrayList<Sommet> res3=new ArrayList<Sommet>() ;
   		        		res3=av.EPLI(EI, EF) ; 
   		        		if(res3!=null){
    		        		ArrayList<Arc>resultat2= new ArrayList<Arc>() ;//liste des sommets du chemin joignant EI vers EF
   		        		resultat2=p.rechArc(res3,p.arcs) ; //liste Des arcs entre ses sommets
   		        		MonPanel rEPLI =new  MonPanel(res3,resultat2) ;
   		        		 
   		        		epliW.getContentPane().removeAll();
							
			    	        JSplitPane splitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel6,rEPLI);

			    	        epliW.getContentPane().add(splitPane2);

			    	        epliW.repaint();
			    	        epliW.validate();}
   		        		
   					} catch (IOException e) {
   						 
   						e.printStackTrace();
   					}
   	        	}
   	        });
   	        panel6.add(button);
   	        
   	        JButton btnQuit = new JButton("QUIT");
   	        btnQuit.addActionListener(new ActionListener() {
   	        	public void actionPerformed(ActionEvent e) {
   	        		epliW.dispose();
   	        	}
   	        });
   	        panel6.add(btnQuit);
   	     epliW.getContentPane().add(splitPane2);
   	        
   	         
   	
				}
        });
        panel.add(rechEpli);
        
        JButton bcUniforme = new JButton("CoutUniforme");
      
        bcUniforme.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JFrame uniW=new JFrame() ;
        		uniW.setTitle(" Recherche en Cout Uniforme");
        		
        		uniW.setSize(900, 700);
        		uniW.setLocationRelativeTo(null);               
        		uniW.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
        		uniW.setVisible(true);
       	  
               JPanel panel8 = new JPanel() ;
               JPanel panel9 = new JPanel() ; 
               panel8.setBackground(Color.gray);
               JSplitPane splitPane3 = new JSplitPane();
   	        splitPane3 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel8,panel9);
   	        panel.setLayout( new GridLayout(3,2)) ;
   	        JLabel label = new JLabel("Saisir EI :");
   	        JTextArea text =new JTextArea ("         ") ;
   	        JTextArea text2 =new JTextArea ("         ") ;
   	        panel8.add(label) ; 
   	        panel8.add(text) ; 
   	        JLabel label2 = new JLabel("Saisir EF :");
   	        panel8.add(label2) ; 
   	        panel8.add(text2) ; 
   	        
   	        JButton button = new JButton("OK !");
   	        button.addActionListener(new ActionListener() {
   	        	public void actionPerformed(ActionEvent arg0) {
   	        		MethodeAveugle av;
   	        		try {
   						av = new MethodeAveugle();
   						Sommet EI = av.panel.rechercheS(text.getText().trim() ) ; 
   		        		Sommet EF = av.panel.rechercheS(text2.getText().trim() ) ;
   		        	 
   		        		 ArrayList<Sommet> res4=new ArrayList<Sommet>() ;
   		        		res4=av.coutUniforme(EI, EF);
   		        		if(res4!=null){
    		        		ArrayList<Arc>resultat2= new ArrayList<Arc>() ;//liste des sommets du chemin joignant EI vers EF
   		        		resultat2=p.rechArc(res4,p.arcs) ; //liste Des arcs entre ses sommets
   		        		MonPanel rUni =new  MonPanel(res4,resultat2) ;
   		        		 
   		        		uniW.getContentPane().removeAll();
							
			    	        JSplitPane splitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel8,rUni);

			    	        uniW.getContentPane().add(splitPane2);

			    	        uniW.repaint();
			    	        uniW.validate();}
   		        		
   					} catch (IOException e) {
   						 
   						e.printStackTrace();
   					}
   	        	}
   	        });
   	        panel8.add(button);
   	        
   	        JButton btnQuit = new JButton("QUIT");
   	        btnQuit.addActionListener(new ActionListener() {
   	        	public void actionPerformed(ActionEvent e) {
   	        		uniW.dispose();
   	        	}
   	        });
   	        panel8.add(btnQuit);
   	     uniW.getContentPane().add(splitPane3);
   	        
   	         
   	
				}
        });
        panel.add(bcUniforme);
        
        JButton btnGloutonne = new JButton("Gloutonne");
        btnGloutonne.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		JFrame glouW=new JFrame() ;
        		glouW.setTitle(" Recherche en gloutonne");
        		
        		glouW.setSize(900, 700);
        		glouW.setLocationRelativeTo(null);               
        		glouW.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
        		glouW.setVisible(true);
       	  
               JPanel panel10 = new JPanel() ;
               JPanel panel11= new JPanel() ; 
               panel10.setBackground(Color.gray);
               JSplitPane splitPane4 = new JSplitPane();
   	        splitPane4 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel10,panel11);
   	        panel.setLayout( new GridLayout(3,2)) ;
   	        JLabel label = new JLabel("Saisir EI :");
   	        JTextArea text =new JTextArea ("         ") ;
   	        JTextArea text2 =new JTextArea ("         ") ;
   	        panel10.add(label) ; 
   	        panel10.add(text) ; 
   	        JLabel label2 = new JLabel("Saisir EF :");
   	        panel10.add(label2) ; 
   	        panel10.add(text2) ; 
   	        
   	        JButton button = new JButton("OK !");
   	        button.addActionListener(new ActionListener() {
   	        	public void actionPerformed(ActionEvent arg0) {
   	        		RechInformes ri;
   	        		try {
   						ri = new  RechInformes();
   						Sommet EI = ri.panel.rechercheS(text.getText().trim() ) ; 
   		        		Sommet EF = ri.panel.rechercheS(text2.getText().trim() ) ;
   		        	 
   		        		 ArrayList<Sommet> res5=new ArrayList<Sommet>() ;
   		        		res5=ri.gloutonne(EI, EF);
   		        		if(res5!=null){
    		        		ArrayList<Arc>resultat3= new ArrayList<Arc>() ;//liste des sommets du chemin joignant EI vers EF
   		        		resultat3=p.rechArc(res5,p.arcs) ; //liste Des arcs entre ses sommets
   		        		MonPanel rGlou =new  MonPanel(res5,resultat3) ;
   		        		 
   		        		glouW.getContentPane().removeAll();
							
			    	        JSplitPane splitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel10,rGlou);

			    	        glouW.getContentPane().add(splitPane2);

			    	        glouW.repaint();
			    	        glouW.validate();}
   		        		
   					} catch (IOException e) {
   						 
   						e.printStackTrace();
   					}
   	        	}
   	        });
   	        panel10.add(button);
   	        
   	        JButton btnQuit = new JButton("QUIT");
   	        btnQuit.addActionListener(new ActionListener() {
   	        	public void actionPerformed(ActionEvent e) {
   	        		glouW.dispose();
   	        	}
   	        });
   	        panel10.add(btnQuit);
   	     glouW.getContentPane().add(splitPane4);
   	        
   	         
   	
				}
        	
        });
        panel.add(btnGloutonne);
        
        JButton bAEtoil = new JButton("Recherche en A*");
        bAEtoil.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JFrame etoilW=new JFrame() ;
        		etoilW.setTitle(" Recherche en A*");
        		
        		etoilW.setSize(900, 700);
        		etoilW.setLocationRelativeTo(null);               
        		etoilW.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
        		etoilW.setVisible(true);
       	  
               JPanel panel12 = new JPanel() ;
               JPanel panel13= new JPanel() ; 
               panel12.setBackground(Color.gray);
               JSplitPane splitPane3 = new JSplitPane();
   	        splitPane3 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel12,panel13);
   	        panel.setLayout( new GridLayout(3,2)) ;
   	        JLabel label = new JLabel("Saisir EI :");
   	        JTextArea text =new JTextArea ("         ") ;
   	        JTextArea text2 =new JTextArea ("         ") ;
   	        panel12.add(label) ; 
   	        panel12.add(text) ; 
   	        JLabel label2 = new JLabel("Saisir EF :");
   	        panel12.add(label2) ; 
   	        panel12.add(text2) ; 
   	        
   	        JButton button = new JButton("OK !");
   	        button.addActionListener(new ActionListener() {
   	        	public void actionPerformed(ActionEvent arg0) {
   	        		RechInformes ri;
   	        		try {
   						ri = new  RechInformes();
   						Sommet EI = ri.panel.rechercheS(text.getText().trim() ) ; 
   		        		Sommet EF = ri.panel.rechercheS(text2.getText().trim() ) ;
   		        	 
   		        		 ArrayList<Sommet> res6=new ArrayList<Sommet>() ;
   		        		res6=ri.aEtoile(EI, EF);
   		        		if(res6!=null){
    		        		ArrayList<Arc>resultat4= new ArrayList<Arc>() ;//liste des sommets du chemin joignant EI vers EF
   		        		resultat4=p.rechArc(res6,p.arcs) ; //liste Des arcs entre ses sommets
   		        		MonPanel retoil =new  MonPanel(res6,resultat4) ;
   		        		 
   		        		etoilW.getContentPane().removeAll();
							
			    	        JSplitPane splitPane4 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel12,retoil);

			    	        etoilW.getContentPane().add(splitPane4);

			    	        etoilW.repaint();
			    	        etoilW.validate();}
   		        		
   					} catch (IOException e) {
   						 
   						e.printStackTrace();
   					}
   	        	}
   	        });
   	        panel12.add(button);
   	        
   	        JButton btnQuit = new JButton("QUIT");
   	        btnQuit.addActionListener(new ActionListener() {
   	        	public void actionPerformed(ActionEvent e) {
   	        		etoilW.dispose();
   	        	}
   	        });
   	        panel12.add(btnQuit);
   	     etoilW.getContentPane().add(splitPane3);
   	        
   	         
   	
				}
        });
        panel.add(bAEtoil);
        f.getContentPane().add(splitPane);
        f.setVisible(true);
       
 
   }
}
