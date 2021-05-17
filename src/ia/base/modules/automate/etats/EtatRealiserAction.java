/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.modules.automate.etats;

import ia.base.metier.actions.Action;
import ia.base.metier.actions.ActionCouperArbre;
import ia.base.metier.actions.FabriqueAction;
import ia.base.metier.actions.TypeAction;
import ia.base.metier.actions.TypeActionRecolte;
import ia.base.metier.carte.Coordonnee;
import ia.base.metier.carte.cases.Case;
import ia.base.metier.carte.objet.TypeObjet;
import ia.base.modules.automate.Automate;
import ia.base.modules.automate.Etat;

/**
 * Réalise la première action de la liste des actions
 * @author Mathis Poncet
 */
public class EtatRealiserAction extends Etat{

    public EtatRealiserAction(Automate automate) {
        super(automate);
    }

    @Override
    public Etat transition() {
        return new EtatCheckAction(getAutomate());
    }

    @Override
    public Action action() {
        Action action = getAutomate().getListeDesActionsARealiser().get(0);
        if(action.getType() == TypeAction.MOUVEMENT){
            Coordonnee coordonneeDestination = getAutomate().getModuleMemoire().getCaseJoueur().getCoordonnee().getVoisin(action.getDirection());
            Case caseDestination = getAutomate().getModuleMemoire().getCarte().getCase(coordonneeDestination);
            if(caseDestination.getObjet() != null && caseDestination.getObjet().getType() == TypeObjet.ARBRE){
                super.getAutomate().getListeDesActionsARealiser().add(0,FabriqueAction.creerActionRecolte(TypeActionRecolte.COUPERARBRE,action.getDirection()));
                super.getAutomate().getListeDesActionsARealiser().add(0,FabriqueAction.creerActionRecolte(TypeActionRecolte.COUPERARBRE,action.getDirection()));
            }
        }
        action = getAutomate().getListeDesActionsARealiser().get(0);
        getAutomate().getListeDesActionsARealiser().remove(0);
        return action;
    }
    
}
