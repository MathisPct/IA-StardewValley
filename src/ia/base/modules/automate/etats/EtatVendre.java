/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.modules.automate.etats;

import ia.base.metier.actions.Action;
import ia.base.metier.actions.FabriqueAction;
import ia.base.metier.algorithmes.Dijkstra;
import ia.base.metier.carte.cases.Case;
import ia.base.metier.carte.cases.CaseHerbe;
import ia.base.metier.carte.ressources.TypeRessource;
import ia.base.modules.automate.Automate;
import ia.base.modules.automate.Etat;

/**
 * Va vendre ses légumes
 * @author Mathis Poncet
 */
public class EtatVendre extends Etat{
    /**
     * Indique si l'ia va vendre ses ressources dans le magasin
     */
    private boolean vaVendre;
    
    public EtatVendre(Automate automate) {
        super(automate);
        this.vaVendre = false;
    }

    @Override
    public Etat transition() {
       Etat etat = new EtatAcheter(getAutomate());
        if(vaVendre){
            etat = new EtatCheckAction(getAutomate());
        }
       return etat;
    }
    
    /**
     * Permet de vendre les légumes et les poulets d'abigail si leur quantité 
     * est bien supérieur à 0
     * @return null
     */
    @Override
    public Action action() {
        Action action = null;
        if(getAutomate().getModuleMemoire().getQuantiteRessource(TypeRessource.CAULIFLOWERMATURE) > 0 
            || getAutomate().getModuleMemoire().getQuantiteRessource(TypeRessource.PARSNIPMATURE) > 0
            || getAutomate().getModuleMemoire().getQuantiteRessource(TypeRessource.EGG) > 0){
            
            Dijkstra dijkstra = new Dijkstra(getAutomate().getModuleMemoire().getCarte());
            dijkstra.calculerDistancesDepuis(getAutomate().getModuleMemoire().getCaseJoueur());
            Case caseMagasinGauche = new CaseHerbe(getAutomate().getModuleMemoire().getCarte().getCoordonneesMagasin().get(0));
            Case caseMagasinDroite = new CaseHerbe(getAutomate().getModuleMemoire().getCarte().getCoordonneesMagasin().get(1));
            Case caseMagasinPlusProche = null;
            int distanceMinimale = -1;
            for (Case c : getAutomate().getModuleMemoire().getCarte().getCases()){
                if(c.getCoordonnee().equals(caseMagasinGauche.getCoordonnee()) || c.getCoordonnee().equals(caseMagasinDroite.getCoordonnee())){
                    if(caseMagasinPlusProche == null || dijkstra.getDistance(c) < distanceMinimale){
                        caseMagasinPlusProche = c;
                        distanceMinimale = dijkstra.getDistance(c);
                    }
                }
            }
            this.vaVendre = true;
            seDeplacerEn(caseMagasinPlusProche.getCoordonnee());
            getAutomate().getModuleMemoire().setCaseDestinationFinale(caseMagasinPlusProche);
            if(getAutomate().getModuleMemoire().getQuantiteRessource(TypeRessource.CAULIFLOWERMATURE) > 0 ){
                getAutomate().getListeDesActionsARealiser().add(FabriqueAction.creerActionVendre(TypeRessource.CAULIFLOWERMATURE));
            }else if(getAutomate().getModuleMemoire().getQuantiteRessource(TypeRessource.PARSNIPMATURE) > 0){
                getAutomate().getListeDesActionsARealiser().add(FabriqueAction.creerActionVendre(TypeRessource.PARSNIPMATURE));
            }else if(getAutomate().getModuleMemoire().getQuantiteRessource(TypeRessource.EGG) > 0){
                getAutomate().getListeDesActionsARealiser().add(FabriqueAction.creerActionVendre(TypeRessource.EGG));
            }
        }
        return null;
    }
    
}
