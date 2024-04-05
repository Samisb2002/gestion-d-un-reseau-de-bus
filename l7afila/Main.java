import java.util.ArrayList;
import java.util.Arrays;

class Main {
    

    public static void main(String[] args) {
        System.out.println("\n[DEBUT DU PROGRAMME]\n");
        
        Data myData = new Data();
        Graph G = new Graph("monGraph");
        ArrayList<String> nom_des_lignes = new ArrayList<String>(Arrays.asList("1", "2"));
        
        myData.addPath("C://Users//samis//Desktop//l7afila/1_Poisy-ParcDesGlaisins.txt");
        myData.addPath("C://Users//samis//Desktop//l7afila/2_Piscine-Patinoire_Campus.txt");

        for (int i = 0; i < myData.paths_list.size(); i++) {
            String nom_ligne = nom_des_lignes.get(i);
            new ReadFile(nom_ligne, myData.paths_list.get(i)).createLines(G);
        }

 
        new Shortest("Courier", "Arcadium", new Horaire (10, 20, null), 0, G);

   

        System.out.println("[FIN DU PROGRAMME]\n");

        
    }

}
