class Horaire {
    public int heure, minutes;
    public Line ligne;

    public Horaire (int uneHeure, int uneMinute, Line uneLigne) {
        if ((0 <= uneHeure) && (uneHeure <= 23) && (0 <= uneMinute) && (uneMinute <= 59)) {
            heure = uneHeure;
            minutes = uneMinute;
            ligne = uneLigne;
        }
        else {
            throw new java.lang.Error("Vous n'avez pas défini correctement l'horaire");
        }
    }

    public Horaire addition(Horaire h) {
        int newMinute = this.minutes + h.minutes;
        int newHeure = this.heure + h.heure;
        if (newMinute >= 60) {
            newHeure ++;
            newMinute -= 60; // Correction here
        }
        if (newHeure >= 24) {
            newHeure -= 24; // Correction here
        }
        return new Horaire(newHeure, newMinute, ligne);
    }

    public Horaire soustraction(Horaire h) {
        int newMinute = this.minutes - h.minutes;
        if (newMinute < 0) {
            newMinute += 60;
            h.heure = h.heure == 0 ? 23 : h.heure - 1; // Corrected logic here
        }
        int newHeure = this.heure - h.heure;
        if (newHeure < 0) { // Correcting underflow issue
            newHeure += 24;
        }

        return new Horaire(newHeure, newMinute, ligne);
    }
    public boolean superieurA(Horaire h) {
        /*Méthode pour comparer deux horaires (this > h)*/
        if (this.heure > h.heure) {
            return true;
        }
        else {if ((this.heure == h.heure) & (this.minutes >= h.minutes)) {
            return true;
        }
        else { return false;}}
    }

    public boolean inferieurA(Horaire h) {
        /*Méthode pour comparer deux horaires (this < h)*/
        if (this.heure < h.heure){
            return true;
        }
        else {if ((this.heure == h.heure) & (this.minutes <= h.minutes)) {
            return true;
        }
        else {return false;}}
    }

    // Getters and Setters 

    public int getHeure() {
        return heure;
    }

    public void setHeure(int heure) {
        this.heure = heure;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public Line getLigne() {
        return ligne;
    }

    public void setLigne(Line ligne) {
        this.ligne = ligne;
    }

    @Override
    public String toString () {
        String res = "";
        if (minutes >= 10) {
            res += heure + ":" + minutes;
        }
        else {
            res += heure + ":0" + minutes;
        }
        if (this.ligne == null) {
            return res;
        }
        else {      
        res += "  (" ; 
        String[] split = ligne.line_name.split(" ");
        res += split[0] + ")";
        res += split[0];
        return res;
        }
    }
}