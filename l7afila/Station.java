import java.util.ArrayList;

class Station {
    /* 
    Cette classe permet d'implémenter les stations de bus
    */

    public String station_name;
    public ArrayList<Line> lines;
    public ArrayList<Station> voisins;
    public ArrayList<Horaire> horaires_normaux_sens_1;
    public ArrayList<Horaire> horaires_normaux_sens_2;
    public ArrayList<Horaire> horaires_feries_sens_1; 
    public ArrayList<Horaire> horaires_feries_sens_2; 

    public Station (String aName) {
        station_name = aName;
        lines = new ArrayList<Line>();
        voisins = new ArrayList<Station>();
        horaires_normaux_sens_1 = new ArrayList<Horaire>();
        horaires_normaux_sens_2 = new ArrayList<Horaire>();
        horaires_feries_sens_1 = new ArrayList<Horaire>();
        horaires_feries_sens_2 = new ArrayList<Horaire>();
    }
    
    public void addLine(Line l) {
        /*Méthode qui permet d'affeter une ligne à une station*/
        lines.add(l);
    }

    public void addVoisin (Station s) {
        /*Méthode qui permet d'ajouter une station voisine à une station*/
        voisins.add(s);
        s.voisins.add(this);
    }

   // Getters and Setters

    public String getStation_name() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public ArrayList<Line> getLines() {
        return lines;
    }

    public void setLines(ArrayList<Line> lines) {
        this.lines = lines;
    }

    public ArrayList<Station> getVoisins() {
        return voisins;
    }

    public void setVoisins(ArrayList<Station> voisins) {
        this.voisins = voisins;
    }

    public ArrayList<Horaire> getHoraires_normaux_sens_1() {
        return horaires_normaux_sens_1;
    }

    public void setHoraires_normaux_sens_1(ArrayList<Horaire> horaires_normaux_sens_1) {
        this.horaires_normaux_sens_1 = horaires_normaux_sens_1;
    }

    public ArrayList<Horaire> getHoraires_normaux_sens_2() {
        return horaires_normaux_sens_2;
    }

    public void setHoraires_normaux_sens_2(ArrayList<Horaire> horaires_normaux_sens_2) {
        this.horaires_normaux_sens_2 = horaires_normaux_sens_2;
    }

    public ArrayList<Horaire> getHoraires_feries_sens_1() {
        return horaires_feries_sens_1;
    }

    public void setHoraires_feries_sens_1(ArrayList<Horaire> horaires_feries_sens_1) {
        this.horaires_feries_sens_1 = horaires_feries_sens_1;
    }

    public ArrayList<Horaire> getHoraires_feries_sens_2() {
        return horaires_feries_sens_2;
    }

    public void setHoraires_feries_sens_2(ArrayList<Horaire> horaires_feries_sens_2) {
        this.horaires_feries_sens_2 = horaires_feries_sens_2;
    }

    @Override
    public String toString () {
        String res = "Station : " + this.station_name + " (";
        for (int y = 0; y < lines.size(); y++) {
            String[] split = lines.get(y).line_name.split(" ");
            res += split[0] + ", ";
        }
        res = res.substring(0, res.length()-2);
        res = res + ')';
        return res;

    }
}