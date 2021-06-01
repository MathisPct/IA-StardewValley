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
import ia.base.metier.carte.objet.Oeuf;
import ia.base.modules.automate.Automate;
import ia.base.modules.automate.Etat;

/**
 * Etat permettant de ramasser tous les oeufs présent sur la carte
 * @author Mathis Poncet
 */
public class EtatAllerRamasserOeufs extends Etat{
    /**
     * Permet de savoir s'il faut prendre l'oeuf ou non
     */
    private boolean aPrendre;
    
    /**
     * Constructeur par initialisation
     * @param automate d'origine
     */
    public EtatAllerRamasserOeufs(Automate automate) {
        super(automate);
        this.aPrendre = false;
    }
    
    @Override
    public Etat transition() {
        Etat etat = new EtatAllerRecolter(getAutomate());
        //si les poules ont pondus des oeufs et qu'ils ont pas déjà été trouvé
        if(!super.getAutomate().getModuleMemoire().hasOeufsCarte() && getAutomate().getModuleMemoire().isOeufMatures()){
            etat = new EtatDemandeOeufs(getAutomate());
        }
        else if(super.getAutomate().getModuleMemoire().hasOeufsCarte()){
            if(aPrendre){
                etat = new EtatCheckAction(getAutomate());
            }
        }
        return etat;
    }

    @Override
    public Action action() {
        if(getAutomate().getModuleMemoire().hasOeufsCarte()){
            Dijkstra dijkstra = new Dijkstra(getAutomate().getModuleMemoire().getCarte());
            dijkstra.calculerDistancesDepuis(getAutomate().getModuleMemoire().getCaseJoueur());
            int distanceMinimale = -1;
            Case caseAvecOeufPlusProche = null;
            for (Case c : getAutomate().getModuleMemoire().getCarte().getCases()){     
                for (Oeuf o : getAutomate().getModuleMemoire().getListeOeufs()) {
                    if(c.getCoordonnee().equals(o.getPosition().getCoordonnee())){
                        int distance = dijkstra.getDistance(c);
                        if(caseAvecOeufPlusProche == null || distance < distanceMinimale){
                            caseAvecOeufPlusProche = c;
                            distanceMinimale = distance;
                        }
                    }
                }
            }
            if (caseAvecOeufPlusProche != null) {
                getAutomate().getModuleMemoire().setCaseDestinationFinale(caseAvecOeufPlusProche);
                seDeplacerEn(caseAvecOeufPlusProche.getCoordonnee());
                getAutomate().getListeDesActionsARealiser().add(FabriqueAction.creerActionPrendreOeuf());
                this.aPrendre = true;
            }
        }
        return null;
    }
}
