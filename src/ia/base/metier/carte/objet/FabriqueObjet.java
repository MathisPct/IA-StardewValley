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

public class FabriqueObjet {
    /**
     * Methode qui créer un objet d'un type en particulier suivant la lettre passé
     * en paramètre (A= Arbre, D=Depart, M= Maison, E= Escalier) 
     * @param position
     * @param lettre 
     * @return null si la lettre donnée en entrée correspond à une case ne contenant pas d'objet
     */
    public static Objet creer(Case position, Character lettre){
        Objet objet = null;
        switch(lettre){
            case 'A':
                objet = new ObjetArbre(position);
                break;
            case 'D':
                objet = new ObjetDepart(position);
                break;
            case 'M':
                objet = new ObjetMaison(position);
                break;
            case 'S':
                objet = new ObjetEscalier(position);
                break;
        }   
        return objet;
    }
   
}
