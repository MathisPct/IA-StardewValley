/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.modules.automate.etats;

import ia.base.metier.actions.Action;
import ia.base.metier.actions.FabriqueAction;
import ia.base.metier.actions.TypeActionStatique;
import ia.base.modules.automate.Automate;
import ia.base.modules.automate.Etat;

/**
 * Renvoie le joueur au point de départ pour dormir
 * Etat de réflexion
 * @author Mathis Poncet
 */
public class EtatAllerDormir extends Etat{

    public EtatAllerDormir(Automate automate) {
        super(automate);
    }

    @Override
    public Etat transition() {
        return new EtatCheckAction(getAutomate());
    }

    @Override
    public Action action() {
        seDeplacerEn(getAutomate().getModuleMemoire().getCarte().getCoordonneeDepart());
        getAutomate().getModuleMemoire().setCaseDestinationFinale(getAutomate().getModuleMemoire().getCarte().getCase(getAutomate().getModuleMemoire().getCarte().getCoordonneeDepart()));
        getAutomate().getListeDesActionsARealiser().add(FabriqueAction.creerActionStatique(TypeActionStatique.DORMIR));
        return null; //car etat de réflexion
    }
    
}
