package humanResources;
/**
 * ChefStock
 */
public abstract class ChefEquipe extends Personnel {

    private float salaire;

    public float getSalaire() {
        return salaire;
    }

    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }

    public ChefEquipe(String nom, String prenom) {
        super(nom, prenom);
        this.salaire = 10;
    }

    public ChefEquipe(String nom, String prenom, float salaire, float balance) {
        super(nom, prenom);
        this.salaire = salaire;
    }

    @Override
    public String toString() {
        return "ChefEquipe [" + super.toString() + ", salaire=" + salaire + "]";
    }

}
