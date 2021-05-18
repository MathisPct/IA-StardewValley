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
 * Va acheter des graines
 * @author Mathis Poncet
 */
public class EtatAcheter extends Etat{
    /**
     * Indique si l'ia achète ou non une chose dans le magasin
     */
    private boolean vaAcheter;
    
    public EtatAcheter(Automate automate) {
        super(automate);
        this.vaAcheter = false;
    }

    @Override
    public Etat transition() {
        Etat etat = new EtatDemandeMagasin(this.getAutomate());
        if(super.getAutomate().getModuleMemoire().hasStockMagasin()){
            if(vaAcheter){
                etat = new EtatCheckAction(getAutomate());
            }else{
                etat = new EtatAllerDormir(getAutomate());
            }
        }
        return etat;
    }

    @Override
    public Action action() {
        if(getAutomate().getModuleMemoire().hasStockMagasin()){
            if(getAutomate().getModuleMemoire().getQuantiteRessource(TypeRessource.GOLD) >= 20){
                if(getAutomate().getModuleMemoire().getStockMagasin(TypeRessource.PARSNIPSEED) >= 1){
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
                    this.vaAcheter = true;
                    seDeplacerEn(caseMagasinPlusProche.getCoordonnee());
                    Action action = FabriqueAction.creerActionAcheter(TypeRessource.PARSNIPSEED);
                    getAutomate().getListeDesActionsARealiser().add(action);
                }
            }
        }
        return null;
    }
    
}
