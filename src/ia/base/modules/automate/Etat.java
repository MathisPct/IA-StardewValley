/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.modules.automate;

import ia.base.metier.TypeMouvement;
import ia.base.metier.actions.Action;
import ia.base.metier.actions.FabriqueAction;
import ia.base.metier.algorithmes.AlgorithmeCalculDistance;
import ia.base.metier.algorithmes.Dijkstra;
import ia.base.metier.carte.Carte;
import ia.base.metier.carte.Coordonnee;
import ia.base.metier.carte.cases.Case;
import java.util.List;

/**
 *
 * @author Mathis Poncet
 */
public abstract class Etat {
    /**
     * L'automate auquel appartient l'état
     */
    private Automate automate;
    
    /**
     * Constructeur par initialisation
     * @param automate auquel appartient l'état
     */
    public Etat(Automate automate) {
        this.automate = automate;
    }
    
    public abstract Etat transition();
    
    public abstract Action action();
    
    protected Automate getAutomate(){
        return automate;
    }
    
    protected void seDeplacerEn(Coordonnee coordonnee){
        System.out.println("--- Je veux aller en "+coordonnee+" ---");
        //On récupère la carte
        Carte carte = getAutomate().getModuleMemoire().getCarte();
        //On crée l'algo
        AlgorithmeCalculDistance algo = new Dijkstra(carte);
        //On récupère la case du joueur
        Case caseJoueur = getAutomate().getModuleMemoire().getCaseJoueur();
        //On lance les calculs de distance
        algo.calculerDistancesDepuis(caseJoueur);
        //On détermine la case de destination
        Case destination = carte.getCase(coordonnee);
        //On calcule le chemin
        List<TypeMouvement> listeMouvement = algo.getChemin(destination);
        //On crée les actions
        for(TypeMouvement typeMouvement : listeMouvement) {
            getAutomate().getListeDesActionsARealiser().add(FabriqueAction.creerMouvement(typeMouvement));
        }
        
    }
}
