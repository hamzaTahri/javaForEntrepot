package humanResources;


import java.util.List;

import stockManagement.Meuble;

/**
 * ChefBrico
 */
public class ChefBrico extends ChefEquipe {

    private List<Meuble> meubleList;

    public List<Meuble> getMeubleList() {
        return meubleList;
    }

    public void setMeubleList(List<Meuble> meubleList) {
        this.meubleList = meubleList;
    }

    public ChefBrico( String nom, String prenom) {
        super( nom, prenom);
    }

    public ChefBrico( String nom, String prenom, float salaire, float balance) {
        super( nom, prenom, salaire, balance);
    }

    public ChefBrico( String nom, String prenom, float salaire, float balance, List<Meuble> meubles) {
        super( nom, prenom, salaire, balance);
        this.meubleList = meubles;
    }

    @Override
    public String toString() {
        return "ChefBrico [" + super.toString() + "]";
    }

    public Meuble monterMeuble(/** CommandeMeuble cm, Lot[] lots */
    ) {
        return null;
    }

}
