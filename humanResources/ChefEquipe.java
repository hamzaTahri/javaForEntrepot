package humanResources;

/**
 * ChefStock
 */
public abstract class ChefEquipe extends Personnel {

    public static float salaire = 10;

    public ChefEquipe(String nom, String prenom) {
        super(nom, prenom);
        ChefEquipe.salaire = 10;
    }

    public ChefEquipe(String nom, String prenom, float salaire, float balance) {
        super(nom, prenom);
        ChefEquipe.salaire = salaire;
    }

    @Override
    public String toString() {
        return "ChefEquipe [" + super.toString() + ", salaire=" + salaire + "]";
    }

}
