/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier;

import ia.base.metier.carte.Coordonnee;

/**
 *
 * @author math7
 */
public class Joueur {
    private Coordonnee coordonnee;

    public Joueur(Coordonnee coordonnee){
        this.coordonnee = coordonnee;
    }
    
    /**
     * déplace le joueur sur la carte dans une direction (haut, bas, gauche, droite)
     * @param mouvement direction dans lequel le joueur va être déplacer sur la carte 
     */
    public void deplacer(TypeMouvement mouvement){
        switch(mouvement){
            case TOP:
                this.coordonnee = new Coordonnee(coordonnee.getLigne() - 1, coordonnee.getColonne());
                break;
            case BOTTOM:
                this.coordonnee = new Coordonnee(coordonnee.getLigne() + 1, coordonnee.getColonne());
                break;
            case LEFT:
                this.coordonnee = new Coordonnee(coordonnee.getLigne(), coordonnee.getColonne() - 1);
                break;
            case RIGHT:
                this.coordonnee = new Coordonnee(coordonnee.getLigne(), coordonnee.getColonne() + 1);
                break;  
        }
    }
    
    public Coordonnee getCoordonnee() {
        return coordonnee;
    }    
}
