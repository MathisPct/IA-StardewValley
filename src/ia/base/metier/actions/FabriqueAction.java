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
            case CARTE:
                action = new ActionDemande("MAP");
                break;
            case MAGASIN:
                action = new ActionDemande("STORE");
                break;
        }
        return action;
    }
    
    /**
     * Créer une action de type statique: dormir, cultiver, remplir son seau...
     * @param type de l'action statique
     * @return l'action statique à faire
     */
    public static Action creerActionStatique(TypeActionStatique type){
        Action action = null;
        switch(type){
            case DORMIR:
                action = new ActionDormir();
                break;
        }
        return action;
    }
    
    /**
     * Créer une action de récolte : arbre...
     * @param type de l'action : récolter arbre, légumes...
     * @param direction vers laquelle se trouve l'arbre
     * @return l'action à réaliser
     */
    public static Action creerActionRecolte(TypeActionRecolte type, TypeMouvement direction){
        Action action = null;
        switch(type){
            case COUPERARBRE:
                action = new ActionCouperArbre(direction);
                break;
        }
        return action;
    }
}
