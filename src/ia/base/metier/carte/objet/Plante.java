/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.carte.objet;

import ia.base.metier.carte.cases.Case;
import ia.base.metier.carte.ressources.TypeRessource;
import java.util.HashMap;

/**
 *
 * @author Mathis Poncet
 */
public class Plante extends Objet{

    public Plante(Case postion) {
        super(postion);
    }

    @Override
    public TypeObjet getType() {
        return null;
    }
    
    /**
     * Une plante peut-être récoltée par le joueur
     * @return si l'objet est bloquant 
     */
    @Override
    public boolean estBloquant() {
        return false;
    }

    @Override
    public HashMap<TypeRessource, Integer> getLoot() {
        return null;
    }
    
}
