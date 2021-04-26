/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.carte.cases;

import ia.base.metier.carte.Coordonnee;
import ia.base.metier.carte.cases.TypeCase;
/**
 *
 * @author mp671721
 */
public class CaseEau extends Case{
    public CaseEau(Coordonnee coordonnees){
        super(coordonnees);
    }

    @Override
    public TypeCase getType() {
        return TypeCase.EAU;
    }
}
