package stockManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import humanResources.ChefBrico;
import humanResources.ChefStock;
import humanResources.Ouvrier;
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

    public void lancerConstruction(CommandeMeuble cmd) {
        
        //suivi des travaux
        Map<Personnel, Integer> perSal = new HashMap<>();

        //Nb d'etapes totale
        int pasReq = getNbPasTemps(cmd);
        
        //Arragement des Employes Dispo
        List<Ouvrier> ouvriers = new ArrayList<>();
        List<ChefStock> chefStocks = new ArrayList<>();
        List<ChefBrico> chefBricos = new ArrayList<>();
        diviserPersonnels(getDispoPersonnel(), ouvriers, chefStocks, chefBricos);

        //Deplacement des lots

        perSal = consomerLots(cmd,chefStocks,ouvriers);

    }

    private Map<Personnel, Integer> consomerLots(CommandeMeuble cmd, List<ChefStock> chefStocks,
            List<Ouvrier> ouvriers) {
        //Foreach element in commande : -remove needed volume using one employee than affect him and his work to the map 
        //- return the map
        return null; 
    }

    private void diviserPersonnels(List<Personnel> dispoPersonnel, List<Ouvrier> ouvriers, List<ChefStock> chefStocks,
            List<ChefBrico> chefBricos) {
        for (Personnel disPersonnel : dispoPersonnel) {
            if (disPersonnel instanceof Ouvrier) {
                ouvriers.add((Ouvrier) disPersonnel);
            } else if (disPersonnel instanceof ChefStock) {
                chefStocks.add((ChefStock) disPersonnel);
            } else if (disPersonnel instanceof ChefBrico) {
                chefBricos.add((ChefBrico) disPersonnel);
            }
        }
    }

    public int getNbPasTemps(CommandeMeuble cmd) {
        int val = 0;
        for (Integer nbPas : cmd.getLotsRequis().values()) {
            val += nbPas;
        }
        return val * 2;
    }

    public boolean examinerCommande(CommandeMeuble cmd) {
        for (String lotType : cmd.getLotsRequis().keySet()) {
            int volReq = cmd.getLotsRequis().get(lotType);
            int volFound = 0;
            for (Rangee range : rangees) {
                volFound += range.getAvailableVolByType(lotType);
            }
            if (volFound < volReq) {
                return false;
            }
        }
        return true;
    }

    public void payerPersonnels(Map<Personnel, Integer> perSal) {
        for (Personnel personnel : perSal.keySet()) {
            if (personnel instanceof Ouvrier) {
                personnel.setBalance(personnel.getBalance() + (perSal.get(personnel) * 5));
            } else {
                personnel.setBalance(personnel.getBalance() + (perSal.get(personnel) * 10));
            }
        }
    }

}
