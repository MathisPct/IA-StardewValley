/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.carte;

import ia.base.metier.TypeMouvement;

/**
 *
 * @author mp671721
 */
public class Coordonnee {
    private int ligne;
    private int colonne;

    public Coordonnee(int ligne, int colonne){
        this.ligne = ligne;
        this.colonne = colonne;
    }
    
    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.ligne;
        hash = 53 * hash + this.colonne;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Coordonnee other = (Coordonnee) obj;
        if (this.ligne != other.ligne) {
            return false;
        }
        if (this.colonne != other.colonne) {
            return false;
        }
        return true;
    }
    
    /**
     * Methode donnant le voisin de la case en cours
     * @param mouvement haut bas gauche ou droite de la case courante
     * @return les coordonnées de la case vosine obtenue à partir du mouvement
     */
    public Coordonnee getVoisin(TypeMouvement mouvement){
        Coordonnee c = null;
        switch(mouvement){
            case BOTTOM:
                c = new Coordonnee(ligne + 1, colonne);
                break;
            case TOP:
                c = new Coordonnee(ligne - 1, colonne);
                break;
            case LEFT:
                c = new Coordonnee(ligne, colonne - 1);
                break;
            case RIGHT:
                c = new Coordonnee(ligne, colonne + 1);
                break;
        }
        return c;
    }
    
    /**
     * Renvoie le type de mouvement pour aller de la case courante à la case
     * de destination avec ses coordonnées passées en paramètre
     * @param destination coordonnées de la case de sdestination
     * @return le type de mouvement à faire pour aller à la case de destination
     */
    public TypeMouvement getMouvementPourAller(Coordonnee destination){
        TypeMouvement res = null;
        for(TypeMouvement mouvement : TypeMouvement.values()){
            if(getVoisin(mouvement).equals(destination)){
                res = mouvement;
            }
        }
        return res;
    }

    @Override
    public String toString() {
        return "Coordonnee{" + " (" + ligne + " , " + colonne + ") }";
    }
}
