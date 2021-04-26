/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.carte.cases;

import ia.base.metier.carte.Coordonnee;

/**
 *
 * @author math7
 */
public class FabriqueCase {
    
    /**
     * Créer le type de case suivant la lettre passée en entré
     * @param coordonnee
     * @param lettre
     * @return 
     */
    public static Case creer(Coordonnee coordonnee, Character lettre){
        Case caseCree = null;
        switch(lettre){
            case 'M':
                caseCree = new CaseHerbe(coordonnee);
                break;
            case 'D':
                caseCree = new CaseHerbe(coordonnee);
                break;
            case 'E':
                caseCree = new CaseEau(coordonnee);
                break;
            case 'A':
                caseCree = new CaseHerbe(coordonnee);
                break;
            case 'T':
                caseCree = new CaseTerre(coordonnee);
                break;
            case 'H':
                caseCree = new CaseHerbe(coordonnee);
                break;
        }
        return caseCree;
    }
}
