package humanResources;

public class Ouvrier extends Personnel {
    private String specialite;
    public static float salaire = 5;

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public Ouvrier(String nom, String prenom) {
        super(nom, prenom);
    }

    public Ouvrier(String nom, String prenom, String specialite, float salaire) {
        super(nom, prenom);
        this.specialite = specialite;
        Ouvrier.salaire = salaire;
    }

    public Ouvrier(String nom, String prenom, String specialite) {
        super(nom, prenom);
        this.specialite = specialite;
    }

    @Override
    public String toString() {
        return "Ouvrier [" + super.toString() + ", salaire=" + salaire + ", specialite=" + specialite + "]";
    }

}
