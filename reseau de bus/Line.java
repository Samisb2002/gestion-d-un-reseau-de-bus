import java.util.ArrayList;

class Line {
    /* 
    Cette classe permeet d'implémenter les lignes de bus
    Attention : les lignes de bus ont un sens, une ligne de bus comme on l'entend au sens classique 
        sera donc ici implémenté par deux objets Line, composé des mêmes stations mais dans un sens opposé
    */

    public String line_name;
    public ArrayList<Station> station_list;

    public Line (String aName) {
        line_name = aName;
        station_list = new ArrayList<Station>();
    }

    public void addStation(Station s) {
        /*Méthode pour ajouter un station dans une ligne*/
        station_list.add(s);
        s.addLine(this);
    }

    // Getters and Setters

    public String getLine_name() {
        return line_name;
    }

    public void setLine_name(String line_name) {
        this.line_name = line_name;
    }

    public ArrayList<Station> getStation_list() {
        return station_list;
    }

    public void setStation_list(ArrayList<Station> station_list) {
        this.station_list = station_list;
    }

    @Override
    public String toString () {
        String res = "Ligne " + this.line_name;
        res = res.substring(0, res.length()-2);
        res += "\n";
        for (int i = 0; i < station_list.size(); i++) {
            res = res + "   " + station_list.get(i) + "\n";
        }
        return res;

    }
}