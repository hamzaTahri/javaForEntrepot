package stockManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import humanResources.Personnel;

public class Entrepot {
    public static int longueur = 10;
    public static int largeur = 1;
    public static int hauteur = 1;
    public static double tresorie = 10000;
    public static List<Rangee> rangees = new ArrayList<>();
    public static List<Personnel> personnels = new ArrayList<>();

    public Lot getInventaire() {
        int rand = new Random().nextInt((rangees.size() - 1));
        return rangees.get(rand).getInventaire();
    }

    public Rangee examinerLot(Lot l) {
        for (Rangee rangee : rangees) {
            if (rangee.examinerLot(l) != null) {
                return rangee;
            }
        }
        return null;
    }

    public void ajouterLot(Lot l) {
        Rangee r = examinerLot(l);
        if (r != null) {
            r.ajouterLot(l);
            return;
        }
        System.out.println("Ajout Impossible Free Up some Space");
    }

    public int verifierDispoPersonnel() {
        return getDispoPersonnel().size();
    }

    public List<Personnel> getDispoPersonnel() {
        List<Personnel> pers = new ArrayList<>();
        for (Personnel personnel : personnels) {
            if (!personnel.isActive()) {
                pers.add(personnel);
            }
        }
        return pers;
    }

    public void lancerConstruction(CommandeMeuble cmd){
                        
    }

}
