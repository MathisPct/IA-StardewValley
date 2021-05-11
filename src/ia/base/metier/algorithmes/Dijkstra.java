/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.algorithmes;

import ia.base.metier.TypeMouvement;
import ia.base.metier.carte.Carte;
import ia.base.metier.carte.cases.Case;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Algorithme de djiktra pour calculer le chemin pour aller d'un point à un 
 * autre
 * @author Mathis Poncet
 */
public class Dijkstra extends AlgorithmeCalculDistance{
    /**
     * Associe des valeurs Case et Boolean afin de savoir si une case est visitée
     * Boolean = true si la case associée est visitée
     */
    private HashMap<Case, Boolean> estVisite;
    /**
     * Associe une case avec son predecesseur
     */
    private HashMap<Case, Case> predecesseur;
    
    private int infini;
    
    /**
     * Constructeur par initialisation 
     * @param carte du jeu
     */
    public Dijkstra(Carte carte) {
        super(carte);
        this.estVisite = new HashMap<>();
        this.predecesseur = new HashMap<>();
        this.infini = 1 + 16 * getCarte().getTaille()*getCarte().getTaille();
    }
    
    
    /**
     * Permet de connaître le coût pour aller vers une case destination
     * @param destination la case vers laquelle on veut aller
     * @return le coût du déplacement vers la case destination. Si elle est 
     * inaccessible la méthode renvoie "infini"
     */
    private int coutMouvementVers(Case destination){
        int cout = this.infini;
        if(destination.estAccessible()){
            if(destination.getObjet() == null || !destination.getObjet().estRecoltable()){
                cout = 1;
            }
            else{
                cout = 1 + destination.getObjet().coutRecolte();
            }
        }
        return cout;
    }
    
    /**
     * Initialise tous les sommets 
     * @param depart la case de départ dont la distance est initialisée à 0
     */
    private void initialisation(Case depart){
        for (Case v : super.getCarte().getCases()) {
            super.setDistance(v, infini);
            estVisite.put(v, false);
            predecesseur.put(v, null);
        }
        super.setDistance(depart, new Integer(0));
    }
    
    /**
     * Calcul le relachement entre 2 sommets
     * @param a le premier sommet
     * @param b le second sommet
     */
    private void relachement(Case a, Case b){
        if(super.getDistance(b) > super.getDistance(a) + coutMouvementVers(b)){
            super.setDistance(b, super.getDistance(a) + coutMouvementVers(b));
            this.predecesseur.put(b, a);
        }
    }
    
    /**
     * Permet de trouver la case la plus proche suivant une autre dans la carte
     * @return la case la plus proche d'une case
     */
    private Case getCaseLaPlusProche(){
        int distanceMin = this.infini;
        Case casePlusProche = null;
        for (Case c : super.getCarte().getCases()) {
            if(!this.estVisite.get(c) && super.getDistance(c) < distanceMin){
                distanceMin = super.getDistance(c);
                casePlusProche = c;
            }
        }
        return casePlusProche;
    }
    
    public void calculerDistancesDepuis(Case depart){
        this.initialisation(depart);
        Case casePlusProche = getCaseLaPlusProche();
        do{
            estVisite.put(casePlusProche, true);
            for (Case voisin : casePlusProche.getVoisins()) {
                relachement(casePlusProche, voisin);
            }
            casePlusProche = getCaseLaPlusProche();
        }while(casePlusProche != null);
    }
    
    public ArrayList<TypeMouvement> getChemin(Case arrivee){
        return null;
    }
    
}
