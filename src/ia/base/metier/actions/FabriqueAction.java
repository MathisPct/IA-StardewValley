/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.actions;

import ia.base.metier.TypeMouvement;
import ia.base.metier.carte.ressources.TypeRessource;

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
    
    /**
     * Créer une action d'achat d'un ressource
     * @param typeRessource que l'on souhaite acheter
     * @return l'action d'achat à réaliser
     */
    public static Action creerActionAcheter(TypeRessource typeRessource){
        Action action = null;
        switch(typeRessource){
            case PARSNIPSEED:
                action = new ActionAcheter(TypeRessource.PARSNIPSEED);
                break;
            case CAULIFLOWERSEED:
                action = new ActionAcheter(TypeRessource.CAULIFLOWERSEED);
                break;
            case CHICKEN:
                action = new ActionAcheter(TypeRessource.CHICKEN);
                break;                
        }
        return action;
    }
    
    /**
     * Créer une action de vente d'une ressource
     * @param typeRessource que l'on souhaite vendre
     * @return l'action de vente de la ressource à réaliser
     */
    public static Action creerActionVendre(TypeRessource typeRessource){
        Action action = null;
        switch(typeRessource){
            case PARSNIPMATURE:
                action = new ActionVendre(TypeRessource.PARSNIPMATURE);
                break;
            case CAULIFLOWERMATURE:
                action = new ActionVendre(TypeRessource.CAULIFLOWERMATURE);
                break;
            case CHICKEN:
                action = new ActionVendre(TypeRessource.CHICKEN);
                break;    
        }
        return action;
    }
    
    /**
     * Créer une action permettant d'aller planter des graines
     * @param typeRessource le type de graine à planter
     * @return l'action à faire
     */
    public static Action creerActionPlanter(TypeRessource typeRessource){
        Action action = null;
        switch(typeRessource){
            case CAULIFLOWERSEED:
                action = new ActionPlanter(typeRessource);
                break;
            case PARSNIPSEED:
                action = new ActionPlanter(typeRessource);
                break;
        }
        return action;
    }
    
    /**
     * Permet de créer l'action arroser
     * @return l'action à effectuer pour arroser
     */
    public static Action creerActionArroser(){
        return new ActionArroser();
    }
    
    /**
     * Permet de créer une action de remplissage d'arrosoir
     * @param direction 
     * @return 
     */
    public static Action creerActionRemplir(TypeMouvement direction){
        Action action = null;
        switch(direction){
            case TOP:
                action = new ActionRemplir(TypeMouvement.TOP);
                break;
            case BOTTOM:
                action = new ActionRemplir(TypeMouvement.BOTTOM);
                break;
            case LEFT:
                action = new ActionRemplir(TypeMouvement.LEFT);
                break;
            case RIGHT:
                action = new ActionRemplir(TypeMouvement.RIGHT);
                break;
        }
        return action;
    }
    
    /**
     * Permet de créer une action de cueillette
     * @return l'action de cueillette
     */
    public static Action creerActionCueillir(){
        return new ActionCueillir();
    }
}
