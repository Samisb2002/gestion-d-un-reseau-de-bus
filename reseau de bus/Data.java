import java.util.ArrayList;

class Data {
    /*
    Cette classe permet de stocker tous les chemins menant aux fichiers à lire
    */

    public ArrayList<String> paths_list;

    public Data () {
        paths_list = new ArrayList<String>();
    }

    public void addPath(String s) {
        /*Méthode permettant d'ajouter un chemin dans l'ArrayList*/
        this.paths_list.add(s);
    }

    //Getters and Setters

    public ArrayList<String> getPaths_list() {
        return paths_list;
    }

    public void setPaths_list(ArrayList<String> paths_list) {
        this.paths_list = paths_list;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < this.paths_list.size(); i++) {
            s += this.paths_list.get(i) + "\n";
        }
        return s;
    }
}