/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.algorithmes;

import ia.base.metier.carte.Carte;
import ia.base.metier.carte.cases.Case;
import java.util.HashMap;

/**
 *
 * @author mp671721
 */
public abstract class AlgorithmeCalculDistance {
    private Carte carte;
    private HashMap<Case, Integer> distances;

    /**
     * Constructeur par initialisation
     * @param carte du jeu
     */
    public AlgorithmeCalculDistance(Carte carte) {
        this.carte = carte;
        this.distances = new HashMap<>();
    }
    
    protected Carte getCarte(){
        return this.carte;
    }
    
    protected void setDistance(Case position, int valeur){
        this.distances.put(position, valeur);
    }
    
    /**
     * Renvoie la valeur stockée dans la Hashmap pour la case donnée en paramètre
     * @param arrivee la case vers laquelle on veut aller
     * @return la distance (Integer) pour arriver à la case arrivee
     */
    public Integer getDistance(Case arrivee){
        return this.distances.get(arrivee);
    }
    
    /**
     * Permet de vider toutes les valeurs contenues dans distances
     */
    protected void reintialisationDistances(){
        this.distances.clear();
    }
    
    /**
     * 
     * @param depart la case de départ 
     */
    public abstract void calculerDistancesDepuis(Case depart);
}
