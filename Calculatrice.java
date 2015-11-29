/*

"Calculatrice" project by Benmes_m 

*/

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class Calculatrice extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	JPanel[] Ligne = new JPanel[5]; //Notre cadre comportera 5 lignes
    JButton[] Bouton = new JButton[19];//Mettre 19 Boutons 
    //Nous allons mettre les valeurs de chaîne pour chaque bouton dans un tableau 
    //à utiliser pour notre boucle plus tard dans l'ordre.
    String[] BoutonString = {"7", "8", "9", "+",
                             "4", "5", "6", "-",
                             "1", "2", "3", "*",
                             ".", "/", "C", "V",
                             "+/-", "=", "0"};
    //créer des tableaux pour la largeur et la hauteur des touches.
    int[] dimLargeur = {260,45,100,90};//des largeurs de 300, 45, 100 et 90 pour les différents types de composants.
    int[] dimHauteur = {35, 40}; //hauteurs pour l'affichage 35 et 40 pour les touches.
    //initialiser nos dimensions ici
    Dimension DimensionAffichage = new Dimension(dimLargeur[0], dimHauteur[0]);//Utilise le premier nombre entier en largeur, et le premier nombre entier en hauteur.
    Dimension DimensionReguliere = new Dimension(dimLargeur[1], dimHauteur[1]);//Utilise le deuxième nombre entier en largeur, et le deuxième nombre entier en hauteur.
    Dimension DimensionColonne = new Dimension(dimLargeur[2], dimHauteur[1]); //Utilise le troisième nombre entier en largeur, et le deuxième nombre entier en hauteur.
    Dimension zeroDimension = new Dimension(dimLargeur[3], dimHauteur[1]); //Utilise le quatrième nombre entier en largeur, et le deuxième nombre entier en hauteur.
    boolean[] function = new boolean[4];//Déclarer des booléens pour nos fonctions d'addition, soustraction, multiplication et la division.
    double[] TemporaireVar = {0, 0};//double temporaire pour le calcule
    JTextArea Affichage = new JTextArea(1,19);//définire notre zone d'affichage 
    Font font = new Font("Serif", Font.BOLD, 18);//délarer une font
    
    //Crée notre constructeur
    Calculatrice() {//Même nom que notre classe
        super("Calculatrice");//super ("Titre");Cela est nécessaire, sinon on aura des erreurs par la suite
        DesignFenetre();//Appeler une méthode 
        setSize(300, 250);//dimension de notre appli (largeur, hauteur) 
        setResizable(false);//désactivé la redimension
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        GridLayout grid = new GridLayout(5,5);// 5 lignes, et le second 5 composants
        setLayout(grid);//mise en page de notre GridLayout
        //On va utiliser une boucle pour définir nous booléens
        for(int i = 0; i < 4; i++)
            function[i] = false;
        
        FlowLayout f1 = new FlowLayout(FlowLayout.CENTER);//Pour la ligne 1
        FlowLayout f2 = new FlowLayout(FlowLayout.CENTER,1,1);//Pour le reste des lignes
        //Initialiser note JPanel
        for(int i = 0; i < 5; i++)
            Ligne[i] = new JPanel();
        Ligne[0].setLayout(f1);
        for(int i = 1; i < 5; i++)
            Ligne[i].setLayout(f2);
        //Pour tout les bouton une boucle
        for(int i = 0; i < 19; i++) {
            Bouton[i] = new JButton();
            Bouton[i].setText(BoutonString[i]);
            Bouton[i].setFont(font);
            Bouton[i].addActionListener(this);
        }
        
        Affichage.setFont(font);
        Affichage.setEditable(false);
        Affichage.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        Affichage.setPreferredSize(DimensionAffichage);
        for(int i = 0; i < 14; i++)
            Bouton[i].setPreferredSize(DimensionReguliere);
        for(int i = 14; i < 18; i++)
            Bouton[i].setPreferredSize(DimensionColonne);
        Bouton[18].setPreferredSize(zeroDimension);
        
        Ligne[0].add(Affichage);
        add(Ligne[0]);
        //On boucle à travers les quatre premiers boutons 
        //et on les ajoute, puis nous ajoutons bouton 15.
        for(int i = 0; i < 4; i++)
            Ligne[1].add(Bouton[i]);
        Ligne[1].add(Bouton[14]);
        add(Ligne[1]);
        
        for(int i = 4; i < 8; i++)
            Ligne[2].add(Bouton[i]);
        Ligne[2].add(Bouton[15]);
        add(Ligne[2]);
        
        for(int i = 8; i < 12; i++)
            Ligne[3].add(Bouton[i]);
        Ligne[3].add(Bouton[16]);
        add(Ligne[3]);
        
        Ligne[4].add(Bouton[18]);
        for(int i = 12; i < 14; i++)
            Ligne[4].add(Bouton[i]);
        Ligne[4].add(Bouton[17]);
        add(Ligne[4]);
        
        setVisible(true);
    }
    //effacer l'affichage
    public void Effacer() {
        try {
            Affichage.setText("");
            for(int i = 0; i < 4; i++)
                function[i] = false;
            for(int i = 0; i < 2; i++)
                TemporaireVar[i] = 0;
        } catch(NullPointerException e) {  
        }
    }
    
    public void Racinecarre() {
        try {
            double value = Math.sqrt(Double.parseDouble(Affichage.getText()));
            Affichage.setText(Double.toString(value));
        } catch(NumberFormatException e) {
        }
    }
    
    public void PosNeg() {
        try {
            double value = Double.parseDouble(Affichage.getText());
            if(value != 0) {
                value = value * (-1);
                Affichage.setText(Double.toString(value));
            }
            else {
            }
        } catch(NumberFormatException e) {
        }
    }
    //Le calcule 
    public void Resultat() {
        double result = 0;//Variable Résultat
        TemporaireVar[1] = Double.parseDouble(Affichage.getText()); //Avoir la deuxième valeur rentrer 
        String temp0 = Double.toString(TemporaireVar[0]);//Enregistre la 1 valeur en String
        String temp1 = Double.toString(TemporaireVar[1]);//Enregistre la 2 valeur en String
        try {
            if(temp0.contains("-")) { // si la 1er valeur contient un moins 
                String[] temp00 = temp0.split("-", 2); //// Fractionne en deux chaînes en -
                TemporaireVar[0] = (Double.parseDouble(temp00[1]) * -1); // mettre la chaîne de retour en double avec la valeur réelle.
            }
            if(temp1.contains("-")) {//Pareil comme le 1er
                String[] temp11 = temp1.split("-", 2);
                TemporaireVar[1] = (Double.parseDouble(temp11[1]) * -1);
            }
        } catch(ArrayIndexOutOfBoundsException e) {
        }
        try {
            if(function[2] == true)
                result = TemporaireVar[0] * TemporaireVar[1];//Multiplication
            else if(function[3] == true)
                result = TemporaireVar[0] / TemporaireVar[1];//division
            else if(function[0] == true)
                result = TemporaireVar[0] + TemporaireVar[1];//Addition
            else if(function[1] == true)
                result = TemporaireVar[0] - TemporaireVar[1];//Soustraction
            Affichage.setText(Double.toString(result));//Affichage de résultat
            for(int i = 0; i < 4; i++)
                function[i] = false;
        } catch(NumberFormatException e) {
        }
    }
    //créer une méthode pour le designe
    public final void DesignFenetre() {
        try {
            UIManager.setLookAndFeel(
                    "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch(Exception e) {   
        }
    }
    
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == Bouton[0])
            Affichage.append("7");
        if(ae.getSource() == Bouton[1])
            Affichage.append("8");
        if(ae.getSource() == Bouton[2])
            Affichage.append("9");
        if(ae.getSource() == Bouton[3]) {
            TemporaireVar[0] = Double.parseDouble(Affichage.getText());
            function[0] = true;
            Affichage.setText("");
        }
        if(ae.getSource() == Bouton[4])
            Affichage.append("4");
        if(ae.getSource() == Bouton[5])
            Affichage.append("5");
        if(ae.getSource() == Bouton[6])
            Affichage.append("6");
        if(ae.getSource() == Bouton[7]) {
            TemporaireVar[0] = Double.parseDouble(Affichage.getText());
            function[1] = true;
            Affichage.setText("");
        }
        if(ae.getSource() == Bouton[8])
            Affichage.append("1");
        if(ae.getSource() == Bouton[9])
            Affichage.append("2");
        if(ae.getSource() == Bouton[10])
            Affichage.append("3");
        if(ae.getSource() == Bouton[11]) {
            //multiply function[2]
            TemporaireVar[0] = Double.parseDouble(Affichage.getText());
            function[2] = true;
            Affichage.setText("");
        }
        if(ae.getSource() == Bouton[12])
            Affichage.append(".");
        if(ae.getSource() == Bouton[13]) {
            TemporaireVar[0] = Double.parseDouble(Affichage.getText());
            function[3] = true;
            Affichage.setText("");
        }
        if(ae.getSource() == Bouton[14])
            Effacer();
        if(ae.getSource() == Bouton[15])
            Racinecarre();
        if(ae.getSource() == Bouton[16])
            PosNeg();
        if(ae.getSource() == Bouton[17])
            Resultat();
        if(ae.getSource() == Bouton[18])
            Affichage.append("0");
    }

}
