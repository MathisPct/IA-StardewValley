package ia.base.modules;

import ia.base.IA;

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
}
