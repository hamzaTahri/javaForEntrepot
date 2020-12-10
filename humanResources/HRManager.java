package humanResources;
/**
 * HRManager
 */
public class HRManager extends Personnel {

    public HRManager(String nom, String prenom) {
        super(nom, prenom);
    }

    @Override
    public String toString() {
        return "HRManager [" + super.toString() + "]";
    }

    public void recruterPersonnel(Personnel p) {
        // TODO
    }

    public void licencierPersonnel(Personnel p) {
        // TODO
    }

}