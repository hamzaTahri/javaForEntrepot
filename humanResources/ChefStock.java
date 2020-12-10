package humanResources;
/**
 * ChefStock
 */
public class ChefStock extends ChefEquipe {

    public ChefStock( String nom, String prenom) {
        super( nom, prenom);
    }

    public ChefStock( String nom, String prenom, float salaire, float balance) {
        super( nom, prenom, salaire, balance);
    }

    @Override
    public String toString() {
        return "ChefStock [" + super.toString() + "]";
    }

    public boolean deplacerLot(/** Lot l , Rangee r , int newLocation */
    ) {
        return true;
    }

    public boolean ajouterLot(/** Lot l,Rangee r, int position */
    ) {
        return true;
    }

    public boolean retirerLot(/** Lot l */
    ) {
        return true;
    }

}
