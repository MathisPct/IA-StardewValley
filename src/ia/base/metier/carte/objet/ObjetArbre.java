/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.carte.objet;

import ia.base.metier.carte.cases.Case;

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
    
}
