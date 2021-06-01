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
 * Classe représentant un oeuf qui est semé par une piule sur les différentes 
 * cases
 * @author Mathis Poncet
 */
public class Oeuf extends Objet{

    public Oeuf(Case postion) {
        super(postion);
    }

    @Override
    public TypeObjet getType() {
        return TypeObjet.OEUF;
    }

    @Override
    public boolean estBloquant() {
        return false;
    }

    @Override
    public HashMap<TypeRessource, Integer> getLoot() {
        HashMap<TypeRessource, Integer> loot = new HashMap<>();
        loot.put(TypeRessource.EGG, 1);
        return loot;
    }
    
}
