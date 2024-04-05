import java.util.ArrayList;

public class Graph {
    /*
    Cette classe permet de stocker la liste de toutes les stations du réseau
    Elle permettra de contruire des graph grâce à l'algorithle de Dijkstra
    */
    
    public String nom;
    public ArrayList<Station> liste_stations;

    public Graph (String unNom) {
        nom = unNom;
        liste_stations = new ArrayList<Station>();
    }

    // Getters and Setters

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Station> getListe_stations() {
        return liste_stations;
    }

    public void setListe_stations(ArrayList<Station> liste_stations) {
        this.liste_stations = liste_stations;
    }

    @Override
    public String toString() {
        String res = "Graph : " + nom + '\n';
        for (int h = 0; h < liste_stations.size(); h++) {
            res += "   " + liste_stations.get(h).toString() + '\n';
        }
        return res;
    }
}
