/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.modules.automate.etats;

import ia.base.metier.actions.Action;
import ia.base.metier.actions.FabriqueAction;
import ia.base.metier.actions.TypeActionRecolte;
import ia.base.metier.algorithmes.Dijkstra;
import ia.base.metier.carte.cases.Case;
import ia.base.metier.carte.objet.TypeObjet;
import ia.base.modules.automate.Automate;
import ia.base.modules.automate.Etat;

/**
 * Détermine l'arbre le plus proche
 * Etat de réflexion
 * @author Mathis Poncet
 */
public class EtatAllerVersArbre extends Etat{
    /**
     * Indique si un arbre est trouvé ou non
     */
    private boolean arbreTrouve;
    
    public EtatAllerVersArbre(Automate automate) {
        super(automate);
        this.arbreTrouve = false;
    }

    @Override
    public Etat transition() {
        Etat etat = new EtatAllerDormir(super.getAutomate());
        if(this.arbreTrouve){
            etat = new EtatCheckAction(super.getAutomate());
        }
        return etat;
    }

    @Override
    public Action action() {
        Dijkstra dijkstra = new Dijkstra(getAutomate().getModuleMemoire().getCarte());
        dijkstra.calculerDistancesDepuis(getAutomate().getModuleMemoire().getCaseJoueur());
        Case caseArbrePlusProche = null;
        int distanceMinimale = -1;
        for (Case c : getAutomate().getModuleMemoire().getCarte().getCases()) {
            if(c.getObjet() != null && c.getObjet().getType() == TypeObjet.ARBRE){
                if(caseArbrePlusProche == null || dijkstra.getDistance(c) < distanceMinimale){
                    caseArbrePlusProche = c;
                    distanceMinimale = dijkstra.getDistance(c);
                }
            }
        }
        if(caseArbrePlusProche != null){
            seDeplacerEn(caseArbrePlusProche.getCoordonnee());
            getAutomate().getModuleMemoire().setCaseDestinationFinale(caseArbrePlusProche);
            this.arbreTrouve = true;
            if(!getAutomate().getListeDesActionsARealiser().isEmpty()) {
                Action action = getAutomate().getListeDesActionsARealiser().get(getAutomate().getListeDesActionsARealiser().size()-1);
                getAutomate().getListeDesActionsARealiser().remove(getAutomate().getListeDesActionsARealiser().size()-1);
                getAutomate().getListeDesActionsARealiser().add(FabriqueAction.
                creerActionRecolte(TypeActionRecolte.COUPERARBRE, action.
                getDirection())) ;
                getAutomate().getListeDesActionsARealiser().add(
                FabriqueAction.
                creerActionRecolte(TypeActionRecolte.COUPERARBRE, action.
                getDirection())) ;
            }
        }
        return null; //car etat de réflexion
    }
    
}
