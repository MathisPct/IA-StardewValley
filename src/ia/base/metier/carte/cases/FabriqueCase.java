/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.carte.cases;

import ia.base.metier.carte.Coordonnee;
import ia.base.metier.carte.objet.FabriqueObjet;

/**
 *
 * @author math7
 */
public class FabriqueCase {
    
    /**
     * Créer le type de case suivant la lettre passée en entrée 
     * Si une case contient un objet alors la fabrique d'objet est utilisé pour
     * attribuer l'objet qui est contenu dans cette case
     * @param coordonnee de la case
     * @param lettre reçu par le serveur (type de la case)
     * @return la case créer avec son objet (null s'il y en a pas)
     */
    public static Case creer(Coordonnee coordonnee, Character lettre){
        Case caseCree = null;
        switch(lettre){
            case 'M':
                caseCree = new CaseHerbe(coordonnee);
                caseCree.setObjet(FabriqueObjet.creer(caseCree, lettre));
                break;
            case 'D':
                caseCree = new CaseHerbe(coordonnee);
                caseCree.setObjet(FabriqueObjet.creer(caseCree, lettre));
                break;
            case 'E':
                caseCree = new CaseEau(coordonnee);
                break;
            case 'A':
                caseCree = new CaseHerbe(coordonnee);
                caseCree.setObjet(FabriqueObjet.creer(caseCree, lettre));
                break;
            case 'T':
                caseCree = new CaseTerre(coordonnee);
                break;
            case 'H':
                caseCree = new CaseHerbe(coordonnee);
                break;
            case 'S':
                caseCree = new CaseHerbe(coordonnee);
                caseCree.setObjet(FabriqueObjet.creer(caseCree, lettre));
                break;
        }
        return caseCree;
    }
}
