import java.util.ArrayList;
import java.util.Arrays;

class Main {
    /* 
    Voici la classe exécutable
    Pour la faire fonctionner, il faut diposer de fichiers receuillants les horaires de bus (un fichier par ligne bus)
    Ajouter les chemins menant à ces fichiers grâce à la méthode 'addPath' comme montré ci-dessous
    Enfin, inserer les non des lignes dans l'ordre dans l'ArrayList 'nom_des_lignes'
    Enfin, choissisez deux stations et la méthode de calcul comme montré ci-dessous pour avoit votre résultat
    */

    public static void main(String[] args) {
        System.out.println("\n[DEBUT DU PROGRAMME]\n");
        
        Data myData = new Data();
        Graph G = new Graph("monGraph");
        ArrayList<String> nom_des_lignes = new ArrayList<String>(Arrays.asList("1", "17"));
        
        myData.addPath("C://Users//samis//Desktop//l7afila/1_Poisy-ParcDesGlaisins.txt");
        myData.addPath("C://Users//samis//Desktop//l7afila/2_Piscine-Patinoire_Campus.txt");

        for (int i = 0; i < myData.paths_list.size(); i++) {
            String nom_ligne = nom_des_lignes.get(i);
            new ReadFile(nom_ligne, myData.paths_list.get(i)).createLines(G);
        }

        /*Essayez le programme*/
        /*
        Pour essayer le programme, créer un objet selon la méthose que vous souhaitez utiliser
        Rentrer les noms de la station de départ et d'arrivée
        Rentrer l'horaire à laquelle vous souhaitez partir, pour cela, rentrer : 
                new Horaire ('heure', 'minutes', null), en remplacant 'heure' et 'minutes' par les entiers correspondant
        Rentrer ensuite 0 si vous vous déplacer un jour de semaine, ou 1 si vous vous déplacez un jour férié ou un Dimanche
        Renter ensuite G, le nom de Graph créé par le programme et qui contient toutes les données.
        */

        new Shortest("Chorus", "GARE", new Horaire (10, 10, null), 0, G);
        // new Shortest("Chorus", "Pommaries", new Horaire (10, 10, null), 1, G);

   

        System.out.println("[FIN DU PROGRAMME]\n");

        
    }

}