/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.carte;

import ia.base.metier.TypeMouvement;
import ia.base.metier.carte.cases.Case;
import ia.base.metier.carte.cases.FabriqueCase;
import ia.base.metier.carte.cases.TypeCase;
import ia.base.metier.carte.objet.FabriqueObjet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author mp671721
 */
public class Carte {
    private int taille;
    private HashMap<Coordonnee, Case> cases;
    /**
     * position de départ du joueur
     */
    private Coordonnee coordonneeDepart;
    
    /**
     * Coordonnées des cases où se trouve le magasin
     */
    private ArrayList<Coordonnee> coordonneesMagasin;
    
    public Carte(String messageRecu){
        this.cases = new HashMap<>();
        this.taille = (int) Math.sqrt(messageRecu.length());
        for (int i = 0; i < this.taille; i++) {
            for(int j = 0; j < this.taille; j++){
                this.ajouterCase(new Coordonnee(i, j), messageRecu.charAt(j+this.taille*i));
            }
        }
        
        //détermination des coordonnées de l'emplacement du magasin
        this.coordonneesMagasin = new ArrayList<>();
        this.coordonneesMagasin.add(0, new Coordonnee(coordonneeDepart.getLigne()+2, coordonneeDepart.getColonne() -4));
        this.coordonneesMagasin.add(1, new Coordonnee(coordonneeDepart.getLigne()+2, coordonneeDepart.getColonne() -3));
        
        Case caseEscalierMilieu = FabriqueCase.creer(coordonneeDepart.getVoisin(TypeMouvement.BOTTOM), 'S');
        Case caseEscalierDroite = FabriqueCase.creer(caseEscalierMilieu.getCoordonnee().getVoisin(TypeMouvement.RIGHT), 'S');
        Case caseEscalierGauche = FabriqueCase.creer(caseEscalierMilieu.getCoordonnee().getVoisin(TypeMouvement.LEFT), 'S');
        this.cases.replace(caseEscalierMilieu.getCoordonnee(), caseEscalierMilieu);
        this.cases.replace(caseEscalierDroite.getCoordonnee(), caseEscalierDroite);
        this.cases.replace(caseEscalierGauche.getCoordonnee(), caseEscalierGauche);
        this.cases.put(caseEscalierGauche.getCoordonnee(), caseEscalierGauche);
        this.cases.put(caseEscalierDroite.getCoordonnee(), caseEscalierDroite);
        this.cases.put(caseEscalierMilieu.getCoordonnee(), caseEscalierMilieu);
        
        //Gestions des voisins
	for(int i=0 ;i<this.taille ;i++) {
            for(int j=0 ;j<this.taille ;j++) {
                Coordonnee cooCase = new Coordonnee(i,j);
                //parcours tous les voisins d'une case (haut,bas, gauche, droite)
                for(TypeMouvement mouvement : TypeMouvement.values()) {
                    Coordonnee cooVoisin = cooCase.getVoisin(mouvement) ;
                    //si la case voisine existe (qu'elle est pas en dehors de la carte)
                    if(this.cases.get(cooVoisin) != null) {
                        //on l'ajoute à la liste des voisins de la case courante
                        this.cases.get(cooCase).ajouterVoisin(this.cases.get(cooVoisin)) ;
                    }
                }
            }
	}
        afficheConsole();
        /*
        System.out.println("Test des voisins");
        System.out.println("Case (0,0)");
        //voisins de la case 1 : ligne 0, colonne 0
        Coordonnee c1 = new Coordonnee(0, 0);
        for (Case c : this.cases.get(c1).getVoisins()) {
            System.out.println(c.getCoordonnee().toString());
        }
        System.out.println("Case (3,3)");
        //voisins de la case 2: ligne 3 et colonne 3
        for (Case c : this.cases.get(new Coordonnee(3, 3)).getVoisins()) {
            System.out.println(c.getCoordonnee().toString());
        }
        System.out.println("Case (6,4)");
        //voisins de la case 2: ligne 3 et colonne 3
        for (Case c : this.cases.get(new Coordonnee(6, 4)).getVoisins()) {
            System.out.println(c.getCoordonnee().toString());
        }
        System.out.println("Fin test des voisins");
        */
    }
    
    public int getTaille() {
        return taille;
    }
    
    private void ajouterCase(Coordonnee coordonnee, Character lettre){
        Case caseCreer = FabriqueCase.creer(coordonnee, lettre);
        //si la case est la case de départ on initialise les coordonnées de départ
        if(lettre == 'D'){
            this.coordonneeDepart = coordonnee;
        }
        //ajout de la case dans la hashMap
        cases.put(coordonnee, caseCreer);
    }
    
    
    public void afficheConsole() {
        for (int i = 0; i < this.taille; i++) {
            for (int j = 0; j < this.taille; j++) {
                String affichage = "E";
                Case caseEnCours = this.cases.get(new Coordonnee(i,j));
                if(caseEnCours.getType() == TypeCase.HERBE){
                    if(caseEnCours.getObjet() == null){
                        affichage = "H";
                    }
                    else{
                        switch(caseEnCours.getObjet().getType()){
                            case ARBRE: affichage = "A"; break;
                            case MAISON : affichage = "M" ; break ;
                            case ESCALIER : affichage = "S" ; break ;
                            case DEPART : affichage = "D" ; break ;
                        }
                    }
                }
                else if(caseEnCours.getType() == TypeCase.TERRE){
                    affichage = "T";
                }
                System.out.print(affichage);
            }
            System.out.println("");
        }
    }

    public Coordonnee getCoordonneeDepart() {
        return coordonneeDepart;
    }
    
    /**
     * retourne la case situé aux coordonnées passées en paramètre
     * @param coordonnee d'une case sur la carte
     * @return la case qui est située aux coordonnées passées en paramètre
     */
    public Case getCase(Coordonnee coordonnee){
        return this.cases.get(coordonnee);
    }
    
    /**
     * Fonction qui renvoie la liste des valeurs stockées dans la Hashmaps
     * cases
     * @return la liste des vlaeurs stcokées dans la Hashmap qui contient toutes
     * les cases de la map
     */
    public Collection<Case> getCases(){
        return this.cases.values();
    }

    public ArrayList<Coordonnee> getCoordonneesMagasin() {
        return coordonneesMagasin;
    }
}
