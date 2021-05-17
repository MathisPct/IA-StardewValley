/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.modules.automate;

import ia.base.metier.actions.Action;
import ia.base.modules.Module_Decision;
import ia.base.modules.Module_Memoire;
import ia.base.modules.automate.etats.EtatInitial;
import java.util.ArrayList;

/**
 *
 * @author Mathis Poncet
 */
public class Automate {
    /**
     * Le module de décision de l'IA
     */
    private Module_Decision moduleDecision;
    
    /**
     * Le module de mémoire de l'IA. Initialisé grâce au module de décision
     */
    private Module_Memoire moduleMemoire;
    
    /**
     * Liste des actions faites par l'IA via le module de décisions
     */
    private ArrayList<Action> listeDesActionsARealiser;
    
    /**
     * Etat courant de l'automate
     */
    private Etat etatCourant;
    
    /**
     * Constructeur par initialisation
     * @param moduleDecision le module de décision de l'IA
     */
    public Automate(Module_Decision moduleDecision) {
        this.etatCourant = new EtatInitial(this);
        this.moduleDecision = moduleDecision;
        this.moduleMemoire = this.moduleDecision.getIA().getModuleMemoire();
        this.listeDesActionsARealiser = new ArrayList<>();
    }
    
    /**
     * Fait évoluer l'automate vers un prochain état
     * @return l'action à réaliser
     */
    public Action evoluer(){
        Action prochaineAction = null;
        while (prochaineAction == null){
            etatCourant = etatCourant.transition();
            prochaineAction = etatCourant.action();
        }
        return prochaineAction;
    }
    
    public Module_Decision getModuleDecision() {
        return moduleDecision;
    }

    public Module_Memoire getModuleMemoire() {
        return moduleMemoire;
    }

    public ArrayList<Action> getListeDesActionsARealiser() {
        return listeDesActionsARealiser;
    }
}
