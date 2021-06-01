package ia.base.modules;

import ia.base.IA;
import java.util.ArrayList;

/**
 * Module en charge de la réaction de l'IA à un message du serveur
 * @author Matthieu
 */
public class Module_Reaction extends Module {

    /**
     * Constructeur
     * @param ia l'IA dont ce module fait partie
     */
    public Module_Reaction(IA ia) {
        super(ia);
    }
    
    /**
     * Méthode principale de réaction à un message reçu
     * @param messageEnvoye dernier message envoyé par l'IA
     * @param messageRecu dermier message reçu par l'IA
     */
    public void reagirAuMessageRecu(String messageEnvoye, String messageRecu) {
        switch(messageEnvoye){
            case "MAP":
                reactionCarte(messageRecu);
                break;
            case "STORE":
                reactionMagasin(messageRecu);
                break;
            case "EGG":
                reactionOeufs(messageRecu);
                break;
            case "CHICKEN":
                reactionPoules(messageRecu);
                break;
        }
    }
    
    /**
     * Réaction lors de la réception de la carte 
     * @param messageRecu la chaîne de caractères représentant la carte
     */
    public void reactionCarte(String messageRecu){
        this.getIA().getModuleMemoire().genererCarte(messageRecu);
    }
    
    /**
     * Réaction lors de la réception des informations des ressources contenues
     * dans le magasin
     * @param messageRecu la chaine de caractère représentant le contenu 
     * du magasin
     */
    private void reactionMagasin(String messageRecu){
        String[] graines = messageRecu.split("\\|");
        this.getIA().getModuleMemoire().genererStockMagasin(Integer.parseInt(graines[0]), Integer.parseInt(graines[1]));
    }
    
    /**
     * Réaction lors de la demande des oeufs présents sur la carte
     * @param messageRecu la chaine de caractère (ligne ou colonne où se trouve 
     * l'oeuf sur la carte)
     */
    private void reactionOeufs(String messageRecu){
        //si un oeuf est présent sur la carte
        if(!messageRecu.isEmpty()){
            String[]oeufs = messageRecu.split("\\|");
            getIA().getModuleMemoire().initListeOeufs();
            int cpt = 1;
            int ligne = 0;
            int col = 0;
            for (int i = 0; i < oeufs.length; i++) {
                if(cpt == 1){
                    ligne = Integer.parseInt(oeufs[i]);
                }
                if(cpt == 2){
                    col = Integer.parseInt(oeufs[i]);
                    getIA().getModuleMemoire().genererOeufsCarte(ligne, col);
                    cpt = 0;
                }
                cpt ++;
            }
        }
    }
    
    /**
     * Réaction lors de la demande de la liste des poules présents sur la carte
     * @param messageRecu la chaine de caractère (ligne|colonne d'une poule)
     */
    private void reactionPoules(String messageRecu){
        //si des poules existent sur la carte
        if(!messageRecu.isEmpty()){
            String[] posPoules = messageRecu.split("\\|");
            getIA().getModuleMemoire().initListePoules();
            int cpt = 1;
            int ligne = 0;
            int col = 0;
            for (int i = 0; i < posPoules.length; i++) {
                if(cpt == 1){
                    ligne = Integer.parseInt(posPoules[i]);
                }
                if(cpt == 2){
                    col = Integer.parseInt(posPoules[i]);
                    getIA().getModuleMemoire().genererPoulesCarte(ligne, col);
                    cpt = 0;
                }
                cpt ++;
            }
        }
    }
}
