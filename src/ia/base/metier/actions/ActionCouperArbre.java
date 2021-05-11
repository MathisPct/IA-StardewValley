/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.actions;

import ia.base.metier.TypeMouvement;

/**
 * Implémente l'action pour couper des arbres
 * @author Mathis Poncet
 */
public class ActionCouperArbre extends Action{
    /**
     * Lieu où se trouve l'arbre par rapport au joueur (TOP,BOTTOM,LEFT,RIGHT)
     */
    private TypeMouvement sensArbre;
    
    /**
     * Constructeur par initialisation
     * @param sensArbre mouvement du joueur pour aller vers cette arbre
     */
    public ActionCouperArbre(TypeMouvement sensArbre) {
        this.sensArbre = sensArbre;
    }
    
    @Override
    public String getMessage() {
        return "HARVEST|" + sensArbre; 
    }

    @Override
    public TypeAction getType() {
        return TypeAction.RECOLTE;
    }

    @Override
    public TypeMouvement getDirection() {
        return sensArbre;
    }
    
}
