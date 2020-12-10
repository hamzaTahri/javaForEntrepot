package humanResources;

public class Ouvrier extends Personnel {
    private String specialite;
    private float salaire;

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public float getSalaire() {
        return salaire;
    }

    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }

    public Ouvrier(String nom, String prenom) {
        super(nom, prenom);
        this.salaire = 5;
    }

    public Ouvrier(String nom, String prenom, String specialite, float salaire) {
        super(nom, prenom);
        this.specialite = specialite;
        this.salaire = salaire;
    }

    @Override
    public String toString() {
        return "Ouvrier [" + super.toString() + ", salaire=" + salaire + ", specialite=" + specialite + "]";
    }

}
