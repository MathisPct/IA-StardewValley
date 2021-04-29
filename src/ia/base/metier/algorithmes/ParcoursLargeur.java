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
import java.util.Collections;

/**
 *
 * @author mp671721
 */
public class ParcoursLargeur extends AlgorithmeCalculDistance{
    /**
     * Constructeur par initialisation
     * @param carte que l'on veut traitée
     */
    public ParcoursLargeur(Carte carte) {
        super(carte);
    }

    @Override
    public void calculerDistancesDepuis(Case depart) {
        Case caseEnCours;
        
        ArrayList<Case> aTraiter = new ArrayList<Case>();
        super.reintialisationDistances();
        aTraiter.add(depart);
        super.setDistance(depart, 0);
        do{
            caseEnCours = aTraiter.get(0);
            aTraiter.remove(aTraiter.get(0));
            //pour tout voisin de la caseEnCours
            for (Case v : caseEnCours.getVoisins()) {
                if(super.getDistance(v) == null){
                    //si les voisins sont accessibles
                    if(v.estAccessible()){
                        super.setDistance(v, super.getDistance(caseEnCours)+1);
                        aTraiter.add(v);
                    }
                }
            }
        }while(!aTraiter.isEmpty());
    }
        
    @Override
    public ArrayList<TypeMouvement> getChemin(Case arrivee) {
        ArrayList<TypeMouvement> mouvements = new ArrayList<>();
        Case caseEnCours = arrivee;
        Case casePrecedente = null;
        
        //calcul
        if(super.getDistance(arrivee) != null){
            do{
                for(Case v : caseEnCours.getVoisins()){
                    if(super.getDistance(v) != null && super.getDistance(v).equals(super.getDistance(caseEnCours)-1)){
                        casePrecedente = v;
                    }
                }
                mouvements.add(casePrecedente.getMouvementPourAller(caseEnCours));
                caseEnCours = casePrecedente;
            }while(super.getDistance(caseEnCours) > 0 );
        }
        Collections.reverse(mouvements);
        return mouvements;
    }
}
