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
import ia.base.metier.carte.cases.TypeCase;
import ia.base.metier.carte.ressources.TypeRessource;
import ia.base.modules.automate.Automate;
import ia.base.modules.automate.Etat;

/**
 * Va planter ses graines
 * @author Mathis Poncet
 */
public class EtatAllerPlanter extends Etat{
    
    private boolean  aPlante;
    
    public EtatAllerPlanter(Automate automate) {
        super(automate);
        this.aPlante = false;
    }

    @Override
    public Etat transition() {
        Etat etat = new EtatCheckAction(getAutomate());
        if(!aPlante){
            etat = new EtatAllerDormir(getAutomate());
        }
        return etat;
    }

    @Override
    public Action action() {
        if(super.getAutomate().getModuleMemoire().getQuantiteRessource(TypeRessource.PARSNIPSEED) >= 1){
            Dijkstra dijkstra = new Dijkstra(getAutomate().getModuleMemoire().getCarte());
            dijkstra.calculerDistancesDepuis(getAutomate().getModuleMemoire().getCaseJoueur());
            Case caseTerreVide = null;
            int distanceMinimale = -1;
            for (Case c : getAutomate().getModuleMemoire().getCarte().getCases()) {
                if(c.getType() == TypeCase.TERRE && c.getObjet() == null){
                    int distance = dijkstra.getDistance(c);
                    if(caseTerreVide == null ||  distance < distanceMinimale){
                        caseTerreVide = c;
                        distanceMinimale = distance;
                    }
                }
            }
            if(caseTerreVide != null){
                seDeplacerEn(caseTerreVide.getCoordonnee());
                getAutomate().getListeDesActionsARealiser().add(FabriqueAction.creerActionPlanter(TypeRessource.PARSNIPSEED));
                aPlante = true;
            }
        }
        return null;
    }
    
}
