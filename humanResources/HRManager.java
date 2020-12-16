package humanResources;

import stockManagement.Entrepot;

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

    public static void recruterPersonnel(Personnel p) {
        Entrepot.personnels.add(p);
    }

    public static void licencierPersonnel(Personnel p) {
        Entrepot.personnels.remove(p);
    }

    public static void recruterEmp(String[] split) {
        switch (split[1]) {
            case "Ouvrier":
                Ouvrier o = new Ouvrier(split[2], split[3], split[4]);
                recruterPersonnel(o);
                break;
            case "ChefStock":
                ChefStock chfS = new ChefStock(split[2], split[3]);
                recruterPersonnel(chfS);
                break;
            case "ChefBrico":
                ChefBrico chfB = new ChefBrico(split[2], split[3]);
                recruterPersonnel(chfB);
                break;

            default:
                break;
        }
        System.out.println(Entrepot.ANSI_GREEN + "Operation done" + Entrepot.ANSI_RESET);
        return;
    }

    public static void licencierEmp(String[] split) {
        for (Personnel pers : Entrepot.personnels) {
            if (pers.getNom().equals(split[0]) && pers.getPrenom().equals(split[1])) {
                licencierPersonnel(pers);
                System.out.println(Entrepot.ANSI_GREEN + "Operation done" + Entrepot.ANSI_RESET);
                return;
            }
        }
    }

    public static void showState() {
        System.out.println(Entrepot.ANSI_CYAN + " Votre Entrepot Contient : " + Entrepot.ANSI_RESET);
        for (Personnel pers : Entrepot.personnels) {
            if (pers instanceof Ouvrier) {
                System.out.println(
                        Entrepot.ANSI_GREEN + " ----> Ouvrier " + ((Ouvrier) pers).toString() + Entrepot.ANSI_RESET);
            } else {
                System.out.println(Entrepot.ANSI_GREEN + " ----> ChefEquipe " + ((ChefEquipe) pers).toString()
                        + Entrepot.ANSI_RESET);
            }
        }
    }

}