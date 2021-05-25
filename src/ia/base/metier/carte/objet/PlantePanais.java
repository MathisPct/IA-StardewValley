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
public class PlantePanais extends Plante{
    
    public PlantePanais(Case postion) {
        super(postion);
    }
    
    @Override
    public HashMap<TypeRessource, Integer> getLoot() {
        HashMap<TypeRessource, Integer> loot = new HashMap<>();
        loot.put(TypeRessource.PARSNIPMATURE, 2);
        return loot;
    }
    
    @Override
    public TypeObjet getType() {
        return TypeObjet.PANAIS;
    }
    
    @Override
    public boolean estMature() {
        boolean estMature = false;
        if(getAge() >= 5){
            estMature = true;
        }
        return estMature;
    }
}
