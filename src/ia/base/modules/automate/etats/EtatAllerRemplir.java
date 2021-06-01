/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.modules.automate.etats;

import ia.base.metier.TypeMouvement;
import ia.base.metier.actions.Action;
import ia.base.metier.actions.FabriqueAction;
import ia.base.metier.algorithmes.Dijkstra;
import ia.base.metier.carte.cases.Case;
import ia.base.metier.carte.cases.TypeCase;
import ia.base.modules.automate.Automate;
import ia.base.modules.automate.Etat;

/**
 * Va remplir l'arrosoir
 * @author Mathis Poncet
 */
public class EtatAllerRemplir extends Etat{

    public EtatAllerRemplir(Automate automate) {
        super(automate);
    }

    @Override
    public Etat transition() {
        return new EtatCheckAction(getAutomate());
    }

    @Override
    public Action action() {
        Dijkstra dijkstra = new Dijkstra(getAutomate().getModuleMemoire().getCarte());
        dijkstra.calculerDistancesDepuis(getAutomate().getModuleMemoire().getCaseJoueur());
        Case caseBordDeLeauLaPlusProche = null;
        TypeMouvement direction = null;
        int distanceMinimale = -1;
        for (Case c : getAutomate().getModuleMemoire().getCarte().getCases()) {
            if(c.estAccessible()){
                boolean bordDeLEau = false;
                for (Case v : c.getVoisins()) {
                    if(v.getType() == TypeCase.EAU){
                        bordDeLEau = true;
                    }
                }
                if(bordDeLEau){
                    if(caseBordDeLeauLaPlusProche == null || dijkstra.getDistance(c) < distanceMinimale){
                        caseBordDeLeauLaPlusProche = c;
                        distanceMinimale = dijkstra.getDistance(c);
                    }
                }
            }
        }
        if(caseBordDeLeauLaPlusProche != null){
            seDeplacerEn(caseBordDeLeauLaPlusProche.getCoordonnee());
            getAutomate().getModuleMemoire().setCaseDestinationFinale(caseBordDeLeauLaPlusProche);
            for (Case v : caseBordDeLeauLaPlusProche.getVoisins()) {
                if(v.getType() == TypeCase.EAU){
                    direction = caseBordDeLeauLaPlusProche.getMouvementPourAller(v);
                }
            }
            getAutomate().getListeDesActionsARealiser().add(FabriqueAction.creerActionRemplir(direction));
        }
        return null;
    }
    
}
