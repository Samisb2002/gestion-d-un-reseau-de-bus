import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Shortest {
    /*
    Cette classe permet d'obtenir le trajet le plus court en distance (nombre de stations)
    */

    public int ferie;
    public Horaire heure_depart;
    public Station depart, noeud_courant, arrivee;
    public ArrayList<Station> reste_a_visiter;

    public Shortest (String unDepart, String uneArrivee, Horaire uneHeure, int testFerie, Graph unGraph) {
        heure_depart = uneHeure;
        ferie = testFerie;
        reste_a_visiter = new ArrayList<Station>();
        for (int t = 0; t < unGraph.liste_stations.size(); t++) {
            reste_a_visiter.add(unGraph.liste_stations.get(t));
        }
        for (int v = 0; v < reste_a_visiter.size(); v++) {
            if (reste_a_visiter.get(v).station_name.equals(unDepart)) {
                depart = reste_a_visiter.get(v);
                noeud_courant = depart;
            }
            if (reste_a_visiter.get(v).station_name.equals(uneArrivee)) {
                arrivee = reste_a_visiter.get(v);
            }
        }
        if (depart == null || arrivee == null) {
            throw new java.lang.Error("Les noms de stations ne sont pas valides");
        }
        if (depart == arrivee) {
            throw new java.lang.Error("Vous avez rentré deux fois le même station");
        }
        Map<String, Integer> dictionnaire = new HashMap<>();
        for (int t = 0; t < reste_a_visiter.size(); t++) {
            if (reste_a_visiter.get(t) == depart) {
                dictionnaire.put(reste_a_visiter.get(t).station_name, 0);
            }
            else {dictionnaire.put(reste_a_visiter.get(t).station_name, Integer.MAX_VALUE);}
        }   

        while (!testFin(dictionnaire, reste_a_visiter)) {
            for (int l = 0; l < noeud_courant.voisins.size(); l++) {
                String nom_voisin = noeud_courant.getVoisins().get(l).station_name;
                Integer candidat = dictionnaire.get(noeud_courant.station_name) + 1;
                if (dictionnaire.get(nom_voisin) > candidat) {dictionnaire.replace(nom_voisin, candidat);}
            }
            for (int u = 0; u < reste_a_visiter.size(); u++) {
                if (reste_a_visiter.get(u).station_name.equals(noeud_courant.station_name)) {
                    reste_a_visiter.remove(u);
                }
            }
            noeud_courant = newNoeudCourant(dictionnaire, reste_a_visiter);
        }
        System.out.println(this.toString(dictionnaire));

    }  

    public boolean testFin (Map<String, Integer> dictionnaire, ArrayList<Station> reste ) {
        /*Méthode qui permet de tester si tous les noeuds ont bien été visités*/
        if (reste.size() == 0) {return true;}
        return false;
    }

    public Station newNoeudCourant(Map<String, Integer> dictionnaire, ArrayList<Station> reste) {
        /*Méthode qui permet de trouver le nouveau courant pour l'algorithme de Dijkstra*/
        Integer num_min = Integer.MAX_VALUE;
        Station candidat = null;
        for (int i = 0; i < reste.size(); i++) {
            String nom = reste.get(i).station_name;
            if (dictionnaire.get(nom) < num_min) {
                candidat = reste.get(i);
                num_min = dictionnaire.get(nom);
            }
        }
        return candidat;

    }

    public String toString (Map<String, Integer> dictionnaire) {
        /*Méthode pour afficher proprement les informations demandées par l'utilisateur*/
        String ligne = "";
        String affichage_ligne = "";
        Horaire horaire_depart = null;
        String res = "PARCOURS SHORTEST \nPour aller de " + depart.station_name + " à " + arrivee.station_name + ", ";
        for (int i = 0; i < depart.lines.size(); i++) {
            for (int j = 0; j < arrivee.lines.size(); j++) {
                if (depart.lines.get(i).equals(arrivee.lines.get(j)) && depart.lines.get(i).station_list.indexOf(depart) < arrivee.lines.get(j).station_list.indexOf(arrivee)) {
                    ligne = depart.lines.get(i).line_name;
                    affichage_ligne = ligne;
                }
            }
            if (ligne.equals("")) {
                affichage_ligne = depart.lines.get(i).line_name;
                affichage_ligne = affichage_ligne.substring(0, affichage_ligne.length() - 2);     
                affichage_ligne += " et la ligne " + arrivee.lines.get(i).line_name;
                ligne = depart.lines.get(i).line_name;
                ArrayList<Horaire> bonsHoraires = null;
                String sensLigne = String.valueOf(depart.lines.get(i).line_name.charAt(depart.lines.get(i).line_name.length() - 1));
                if (ferie == 0 && sensLigne.equals("B")) {
                    bonsHoraires = depart.horaires_normaux_sens_2;
                }
                else { if (ferie == 0 && sensLigne.equals("A")) {
                    bonsHoraires = depart.horaires_normaux_sens_1;
    
                }
                else { if (ferie == 1 && sensLigne.equals("B")) {
                    bonsHoraires = depart.horaires_feries_sens_2;
    
                }
                else {if (ferie == 1 && sensLigne.equals("A")) {
                    bonsHoraires = depart.horaires_feries_sens_1;
    
                }}}}
                for (int z = 0; i < bonsHoraires.size(); z++) {
                    if (bonsHoraires.get(z).superieurA(heure_depart) && bonsHoraires.get(z).getLigne().line_name == ligne) {
                        horaire_depart = bonsHoraires.get(z);
                        break;
                    }
                }
        }
        }
        res += "prenez la ligne " + affichage_ligne;
        res = res.substring(0, res.length()-2); 
        res += "\n";
        res += "Vous descendrez dans " + dictionnaire.get(arrivee.station_name) + " stations \n";
        if (getProchainHoraire(ligne) != null && getProchainHoraire(ligne).size() == 2) {
            res += "Votre prochain horaire est " + getProchainHoraire(ligne).get(0).toString();
            res = res.substring(0, res.length()-6); 
            res += "\nArrivée prévue à " + getProchainHoraire(ligne).get(1).toString();
            res = res.substring(0, res.length()-6);
        }
        else {
            res += "Votre prochain horaire est " + horaire_depart.toString();
            res = res.substring(0, res.length()-6);
        }
        res += "\n";

        return res;
    }

    public ArrayList<Horaire> getProchainHoraire(String nom_ligne) {
        /*Méthode pour trouver le prochain horaire de départ ainsi que l'horaire d'arrivée*/
        ArrayList<Horaire> res = new ArrayList<Horaire>();
        Line bonneLigne = null;
        ArrayList<Horaire> bonsHoraires = null;
        ArrayList<Horaire> bonsHoraires_arrivee = null;
        for (int u = 0; u < depart.lines.size(); u++)  {
            if (depart.lines.get(u).line_name.equals(nom_ligne)) {
                bonneLigne = depart.lines.get(u);
            }
        }
        if (bonneLigne != null) {
            String sensLigne = String.valueOf(bonneLigne.line_name.charAt(bonneLigne.line_name.length() - 1));
            if (ferie == 0 && sensLigne.equals("B")) {
                bonsHoraires = depart.horaires_normaux_sens_2;
                bonsHoraires_arrivee = arrivee.horaires_normaux_sens_2;
            }
            else { if (ferie == 0 && sensLigne.equals("A")) {
                bonsHoraires = depart.horaires_normaux_sens_1;
                bonsHoraires_arrivee = arrivee.horaires_normaux_sens_1;

            }
            else { if (ferie == 1 && sensLigne.equals("B")) {
                bonsHoraires = depart.horaires_feries_sens_2;
                bonsHoraires_arrivee = arrivee.horaires_feries_sens_2;

            }
            else {if (ferie == 1 && sensLigne.equals("A")) {
                bonsHoraires = depart.horaires_feries_sens_1;
                bonsHoraires_arrivee = arrivee.horaires_feries_sens_1;

            }}}}
            for (int i = 0; i < bonsHoraires.size(); i++) {
                if (bonsHoraires.get(i).superieurA(heure_depart) && bonsHoraires.get(i).getLigne().line_name == nom_ligne) {
                    res.add(bonsHoraires.get(i));
                    Integer compteur = 0;
                    Integer index = 0;
                    for (int y = 0; y < bonsHoraires.size(); y++) {
                        if (bonsHoraires.get(y).getLigne().line_name == nom_ligne) {
                            compteur += 1;
                            if (bonsHoraires.get(y) == bonsHoraires.get(i)) {
                                index = compteur;
                            }
                        }
                    }
                    compteur = 0;
                    for (int y = 0; y < bonsHoraires_arrivee.size(); y++) {
                        if (bonsHoraires_arrivee.get(y).getLigne().line_name == nom_ligne) {
                            compteur += 1;
                            if (compteur == index) {
                                res.add(bonsHoraires_arrivee.get(y));
                                return res;
                            }
                        }
                    }
                }
            }   
        }
        return null;
    }

}
