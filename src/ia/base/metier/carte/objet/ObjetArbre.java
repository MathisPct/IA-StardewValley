/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.carte.objet;

import ia.base.metier.carte.cases.Case;
import ia.base.metier.carte.ressources.TypeRessource;
import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author math7
 */
public class ObjetArbre extends Objet{

    public ObjetArbre(Case postion) {
        super(postion);
    }

    @Override
    public TypeObjet getType() {
        return TypeObjet.ARBRE;
    }
    
    /** 
     * Abigail peut casser l'arbre pour passer
     * @return true car Abigail peut casser l'arbre
     */
    @Override
    public boolean estBloquant() {
        return false;
    }

    @Override
    public boolean estRecoltable() {
        return true;
    }
    
    @Override
    public int coutRecolte(){
        return 2;
    } 

    @Override
    public HashMap<TypeRessource, Integer> getLoot() {
        HashMap<TypeRessource, Integer> arbres = new HashMap<>();
        arbres.put(TypeRessource.BOIS, 12);
        return arbres;
    }
}
