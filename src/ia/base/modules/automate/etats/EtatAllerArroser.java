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
 * Va arroser ses plantes
 * @author Mathis Poncet
 */
public class EtatAllerArroser extends Etat{
    /**
     * Permet de savoir si une plante est à arroser ou non
     */
    private boolean aArrose;
    
    public EtatAllerArroser(Automate automate) {
        super(automate);
        this.aArrose = false;
    }

    @Override
    public Etat transition() {
        Etat etat = new EtatAllerDormir(getAutomate());
        if(aArrose){
            etat = new EtatCheckAction(getAutomate());
        }else{
            // si elle n'a plus d'eau
            if(getAutomate().getModuleMemoire().getQuantiteRessource(TypeRessource.EAU) == 0){
                etat = new EtatAllerRemplir(getAutomate());
            }else{
                etat = new EtatAllerDormir(getAutomate());
            }
        }
        return etat;
    }

    /**
     * Détermine la case contenant une plante non arrosée la plus proche puis
     * fait déplacer l'ia jusqu'à la case à arroser après avoir passer aArroser
     * à true
     * L'action est effectuée à condition qu'abigail ait encore de l'eau
     * @return null 
     */
    @Override
    public Action action() {
        if(getAutomate().getModuleMemoire().getQuantiteRessource(TypeRessource.EAU) > 0){
            Dijkstra dijkstra = new Dijkstra(getAutomate().getModuleMemoire().getCarte());
            dijkstra.calculerDistancesDepuis(getAutomate().getModuleMemoire().getCaseJoueur());
            Case caseAArroser = null;
            int distanceMinimale = -1;
            for (Case c : getAutomate().getModuleMemoire().getCarte().getCases()) {
                for (Plante p : getAutomate().getModuleMemoire().getListePlantes()) {
                    if(c == p.getPosition() && !p.isEstArrose()){
                        int distance = dijkstra.getDistance(p.getPosition());
                        if(caseAArroser == null || distance < distanceMinimale){
                            caseAArroser = c;
                            distanceMinimale = distance;
                        }
                    }
                }
            }
            if(caseAArroser != null){
                aArrose = true;
                getAutomate().getModuleMemoire().setCaseDestinationFinale(caseAArroser);
                seDeplacerEn(caseAArroser.getCoordonnee());
                getAutomate().getListeDesActionsARealiser().add(FabriqueAction.creerActionArroser());
            }
        }
        return null;
    }
    
}
