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
                etat = new EtatAllerPlanter(getAutomate());
            }
        }
        return etat;
    }

    @Override
    public Action action() {
        Action action = null;
        if(getAutomate().getModuleMemoire().hasStockMagasin()){
            if(getAutomate().getModuleMemoire().getQuantiteRessource(TypeRessource.GOLD) >= 20){
                //si le magasin possède suffisamment de stock de graine de panais ou de chois
                if(getAutomate().getModuleMemoire().getStockMagasin(TypeRessource.PARSNIPSEED) >= 1 || (getAutomate().getModuleMemoire().getStockMagasin(TypeRessource.CAULIFLOWERSEED) >=1 && getAutomate().getModuleMemoire().getQuantiteRessource(TypeRessource.GOLD) >= 80)){
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
                    seDeplacerEn(caseMagasinPlusProche.getCoordonnee());
                    this.vaAcheter = true;
                    if(getAutomate().getModuleMemoire().getQuantiteRessource(TypeRessource.GOLD) - 1000 >= 20){
                        action = FabriqueAction.creerActionAcheter(TypeRessource.CHICKEN);
                    }
                    //si il reste des graines de panais 
                    else if(getAutomate().getModuleMemoire().getStockMagasin(TypeRessource.PARSNIPSEED) >= 1){
                        action = FabriqueAction.creerActionAcheter(TypeRessource.PARSNIPSEED);
                    }
                    //sinon on vérifie nos golds si on peut acheter des graines de choux fleurs 
                    else{  
                        if(getAutomate().getModuleMemoire().getQuantiteRessource(TypeRessource.GOLD) >= 80)
                        action = FabriqueAction.creerActionAcheter(TypeRessource.CAULIFLOWERSEED); 
                    }
                    if(action==null){
                        this.vaAcheter = false;
                    }else{
                        getAutomate().getListeDesActionsARealiser().add(action);
                    }
                    
                }
            }
        }
        return null;
    }
    
}
