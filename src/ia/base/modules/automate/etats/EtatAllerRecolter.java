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
import ia.base.metier.carte.objet.Plante;
import ia.base.metier.carte.ressources.TypeRessource;
import ia.base.modules.automate.Automate;
import ia.base.modules.automate.Etat;

/**
 * Détermine la plante la plus proche
 * @author Mathis Poncet
 */
public class EtatAllerRecolter extends Etat{
    /**
     * Permet de savoir si il faut aller récolter ou non
     */
    private boolean aRecolte;
    
    public EtatAllerRecolter(Automate automate) {
        super(automate);
        this.aRecolte = false;
    }

    @Override
    public Etat transition() {
        Etat etat = new EtatAcheter(getAutomate());
        if(aRecolte){
            etat = new EtatCheckAction(getAutomate());
        }else{
            if(getAutomate().getModuleMemoire().getQuantiteRessource(TypeRessource.CAULIFLOWERMATURE) > 0 
            || getAutomate().getModuleMemoire().getQuantiteRessource(TypeRessource.PARSNIPMATURE) > 0
            || getAutomate().getModuleMemoire().getQuantiteRessource(TypeRessource.EGG) > 0){
                etat = new EtatVendre(getAutomate());
            }
        }
        return etat;
    }
    
    /**
     * Permet de trouver la case la plus proche du joueur avec une plante qui est
     * arrivée à maturité afin de la cueillir
     * @return null
     */
    @Override
    public Action action() {
        Dijkstra dijkstra = new Dijkstra(getAutomate().getModuleMemoire().getCarte());
        dijkstra.calculerDistancesDepuis(getAutomate().getModuleMemoire().getCaseJoueur());
        Case caseARecolte = null;
        int distanceMinimale = -1;
        for (Case c : getAutomate().getModuleMemoire().getCarte().getCases()) {
            for (Plante p : getAutomate().getModuleMemoire().getListePlantes()) {
                if(c == p.getPosition() && p.estMature()){
                    int distance = dijkstra.getDistance(p.getPosition());
                    if(caseARecolte == null || distance < distanceMinimale){
                        caseARecolte = c;
                        distanceMinimale = distance;
                    }
                }
            }
        }
        if(caseARecolte != null){
            seDeplacerEn(caseARecolte.getCoordonnee());
            getAutomate().getModuleMemoire().setCaseDestinationFinale(caseARecolte);
            getAutomate().getListeDesActionsARealiser().add(FabriqueAction.creerActionCueillir());
            this.aRecolte = true;
        }
        return null;
    }
    
}
