/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.actions;

import ia.base.metier.TypeMouvement;

/**
 *
 * @author math7
 */
public class FabriqueAction {
    public static Action creerMouvement(TypeMouvement mouvement){
        ActionMouvement action = new ActionMouvement(mouvement);
        return action;
    }
    
    public static Action creerDemande(TypeDemande demande){
        ActionDemande action = null;
        switch(demande){
            case MAP:
                action = new ActionDemande("" + TypeDemande.MAP);
                break;
        }
        return action;
    }
}
