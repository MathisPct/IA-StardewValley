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
public class PlanteChouFleur extends Plante{
    
    public PlanteChouFleur(Case postion) {
        super(postion);
    }
    
    @Override
    public HashMap<TypeRessource, Integer> getLoot() {
        HashMap<TypeRessource, Integer> loot = new HashMap<>();
        loot.put(TypeRessource.CAULIFLOWERMATURE, 2);
        return loot;
    }
    
    @Override
    public TypeObjet getType() {
        return TypeObjet.CHOUFLEUR;
    }
    
}
