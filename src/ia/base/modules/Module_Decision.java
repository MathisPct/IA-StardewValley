package ia.base.modules;

import ia.base.IA;
import java.util.ArrayList;

/**
 * Module en charge de la prise de décision
 * @author Matthieu
 */
public class Module_Decision extends Module {
    private int compteur;
    /**
     * Constructeur
     * @param ia l'IA dont ce module fait partie
     */
    public Module_Decision(IA ia) {
        super(ia);
        this.compteur = 0;
    }
    
    /**
     * Méthode principale de prise de décision
     * @param messageRecu dernier message reçu par l'IUA
     * @return le message à envoyer au serveur
     */
    public String determinerNouvelleAction(String messageRecu) {
        String messageAEnvoye = "";
        //si le module mémoire ne dispose pas de carte la méthode renvoie "MAP"
        if ( !this.getIA().getModuleMemoire().hasCarte() ){
            messageAEnvoye = "MAP";
        }
        //si le module mémoire dispose de la carte
        else{
            messageAEnvoye = "END";
            this.getIA().arretDiscussion(); //arrêt discussion
        }
        
        /* REPRISE DU TP1
        //si le perso es bloqué msg "END" envoyé au serveur
        if(messageRecu.contains("NOK")){
            messageAEnvoye = "END";
            super.getIA().arretDiscussion();
        }else if(!messageRecu.equals("END")){
            messageAEnvoye = this.script();
        }

        */
        return messageAEnvoye;
    }
    
    private String script(){
        String message = "";
        final ArrayList<String> listeMov = new ArrayList<>(); 
        listeMov.add("MOVE|RIGHT");
        listeMov.add("MOVE|LEFT");
        listeMov.add("MOVE|BOTTOM");
        listeMov.add("MOVE|TOP");
        message = "MOVE|BOTTOM"; //quand le compteur est <5 le perso se déplace vers 
        //déplacement aléatoire à partir du 5 ème déplacement
        if (compteur>=5){
            message = listeMov.get((int) (Math.random()*4)); //déplacement aléatoire
        }
        compteur ++;
        return message;
    }
}
