package stockManagement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import humanResources.ChefBrico;
import humanResources.ChefEquipe;
import humanResources.ChefStock;
import humanResources.Ouvrier;
import humanResources.Personnel;

public class Entrepot {

    public static int longueur = 5;
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

        // suivi des travaux
        Map<Personnel, Integer> perSal = new HashMap<>();

        // Nb d'etapes totale
        int pasReq = getNbPasTemps(cmd);

        // Arragement des Employes Dispo
        List<Ouvrier> ouvriers = new ArrayList<>();
        List<ChefStock> chefStocks = new ArrayList<>();
        List<ChefBrico> chefBricos = new ArrayList<>();
        diviserPersonnels(getDispoPersonnel(), ouvriers, chefStocks, chefBricos);

        // Deplacement des lots

        perSal = consomerLots(cmd, chefStocks, ouvriers, pasReq / 2);

    }

    private Map<Personnel, Integer> consomerLots(CommandeMeuble cmd, List<ChefStock> chefStocks, List<Ouvrier> ouvriers,
            int nbSteps) {
        int stepForUser = 0;
        stepForUser = (int) nbSteps / (ouvriers.size() + chefStocks.size());
        int valToAdd = nbSteps % (ouvriers.size() + chefStocks.size());
        Map<Personnel, Integer> map = new HashMap<>();

        // To distribute the work and affect the values
        if (valToAdd == 0) {
            for (Ouvrier ouvrier : ouvriers) {
                ouvrier.setActive(true);
                map.put(ouvrier, stepForUser);
            }
            for (ChefStock chef : chefStocks) {
                chef.setActive(true);
                map.put(chef, stepForUser);
            }
        } else {
            for (Ouvrier ouvrier : ouvriers) {
                ouvrier.setActive(true);
                map.put(ouvrier, stepForUser);
            }
            for (int i = 0; i < chefStocks.size(); i++) {
                if (i != chefStocks.size() - 1) {
                    chefStocks.get(i).setActive(true);
                    map.put(chefStocks.get(i), stepForUser);
                    break;
                }
                chefStocks.get(i).setActive(true);
                map.put(chefStocks.get(i), stepForUser + valToAdd);
            }
        }
        for (String typeLot : cmd.getLotsRequis().keySet()) {
            int initialVol = cmd.getLotsRequis().get(typeLot);
            for (Rangee range : rangees) {
                if (initialVol == 0) {
                    break;
                } else {
                    initialVol -= range.consumeLot(typeLot, initialVol);
                }
            }
        }
        return map;
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

    public static void traiterNouveauLot(String[] commandeStrings) {
        try {
            Integer.parseInt(commandeStrings[0]);
            String nom = commandeStrings[2];
            int poids = Integer.parseInt(commandeStrings[3]);
            float prix = Float.parseFloat(commandeStrings[4]);
            int volume = Integer.parseInt(commandeStrings[5]);

            if (getTotalFreeSpace() < volume || !isAvailableEmployees()) {
                System.out.println(
                        "\u001B[33m" + "Espace Insuffisant ou pas d'employes inactive attendez SVP" + "\u001B[0m");
                return;
            } else {
                List<Personnel> employeList = getEmployeesForVol(volume);
                Rangee r = checkFreeSpaceInOneR(volume);
                if (r != null) {
                    r.ajouterLot(new Lot(volume, nom, poids, prix));
                    freeUpEmployees(employeList);
                    System.out.println("\u001B[31m" + "\u001B[32m" + "Lot Added Successfully " + "\u001B[0m");
                } else {
                    distributedStorage(volume, nom, poids, prix);
                    freeUpEmployees(employeList);
                    System.out.println(
                            "\u001B[31m" + "\u001B[32m" + "Lot distributed and Added Successfully " + "\u001B[0m");

                }
                payerPersonnels(employeList, volume);

            }

        } catch (Exception e) {
            System.out.println("\u001B[31m" + "Commande MissFormed Must be : <id> lot <nom> <poids> <prix> <volume>"
                    + "\u001B[0m");
        }

    }

    private static void distributedStorage(int volume, String nom, int poids, float prix) {
        Map<Integer, Rangee> freeSpaces = new TreeMap<>();
        for (Rangee rangee : rangees) {
            freeSpaces.put(rangee.getFreeSpace(), rangee);
        }

        int currentVol = volume;

        for (Integer rvol : freeSpaces.keySet()) {
            if (currentVol == 0) {
                System.out.println("Volume Get to be 0");
                return;
            } else {
                if ((currentVol - rvol) > 0) {
                    System.out.println("Ajout de " + rvol);
                    Rangee r = freeSpaces.get(rvol);
                    r.ajouterLot(new Lot(rvol, nom, poids, prix));
                    return;

                } else {
                    System.out.println("Ajout de " + rvol);
                    Rangee r = freeSpaces.get(rvol);
                    r.ajouterLot(new Lot(rvol, nom, poids, prix));
                    currentVol -= rvol;
                }
            }
        }
    }

    public static <K, V extends Comparable<V>> Map<K, V> sortByValues(final Map<K, V> map) {
        Comparator<K> valueComparator = new Comparator<K>() {
            public int compare(K k1, K k2) {
                int compare = map.get(k1).compareTo(map.get(k2));
                if (compare == 0)
                    return 1;
                else
                    return compare;
            }
        };

        Map<K, V> sortedByValues = new TreeMap<K, V>(valueComparator);
        sortedByValues.putAll(map);
        return sortedByValues;
    }

    private static void freeUpEmployees(List<Personnel> employeList) {
        for (Personnel personnel : employeList) {
            personnel.setActive(false);
        }
    }

    private static void payerPersonnels(List<Personnel> employeList, int volume) {
        int payChef = 0, payOuvrier = 0, nbChefs = 0, nbOuvriers = 0;

        if (employeList.size() == volume) {
            for (Personnel personnel : employeList) {
                if (personnel instanceof Ouvrier) {
                    nbOuvriers++;
                    payOuvrier += (float) Ouvrier.salaire;
                    personnel.setBalance(personnel.getBalance() + Ouvrier.salaire);
                    Entrepot.tresorie -= (float) Ouvrier.salaire;
                } else {
                    nbChefs++;
                    payChef += ChefEquipe.salaire;
                    personnel.setBalance(personnel.getBalance() + ChefEquipe.salaire);
                    Entrepot.tresorie -= (float) ChefEquipe.salaire;
                }
            }
        } else {
            int bal = volume / employeList.size();
            for (Personnel personnel : employeList) {
                personnel.setActive(true);
                if (personnel instanceof Ouvrier) {
                    nbOuvriers++;
                    payOuvrier += (float) Ouvrier.salaire * bal;
                    personnel.setBalance(personnel.getBalance() + Ouvrier.salaire * bal);
                    Entrepot.tresorie -= (float) Ouvrier.salaire;
                } else {
                    nbChefs++;
                    payChef += (float) ChefEquipe.salaire * bal;
                    personnel.setBalance(personnel.getBalance() + ChefEquipe.salaire * bal);
                    Entrepot.tresorie -= (float) ChefEquipe.salaire;
                }

            }
            Personnel lastP = employeList.get(employeList.size() - 1);
            if (lastP instanceof Ouvrier) {
                nbOuvriers++;
                payOuvrier += (float) Ouvrier.salaire * (volume % employeList.size());
                lastP.setBalance(lastP.getBalance() + Ouvrier.salaire * (volume % employeList.size()));
                Entrepot.tresorie -= (float) Ouvrier.salaire;
            } else {
                nbChefs++;
                payChef += (float) ChefEquipe.salaire * (volume % employeList.size());
                lastP.setBalance(lastP.getBalance() + ChefEquipe.salaire * (volume % employeList.size()));
                Entrepot.tresorie -= (float) ChefEquipe.salaire;
            }

        }
        System.out.println("\u001B[35m" + "This Commande takes " + volume + " Step to realize");
        System.out.println("-------We Have paied " + payOuvrier + " To " + nbOuvriers + " Ouvriers");
        System.out.println("-------------------- and " + payChef + " To " + nbChefs + " Chef" + "\u001B[0m");
        System.out.println("\u001B[31m" + "We Still Have " + Entrepot.tresorie + " MAD" + "\u001B[0m");

    }

    private static List<Personnel> getEmployeesForVol(int volume) {
        List<Personnel> allPers = getAllFreeEmployees();
        if (allPers.size() < volume) {
            return allPers;
        }
        while (allPers.size() > volume) {
            allPers.remove(allPers.size() - 1);
        }
        return allPers;

    }

    private static List<Personnel> getAllFreeEmployees() {
        List<Personnel> allFreePers = new ArrayList<>();
        for (Personnel personnel : Entrepot.personnels) {
            if (!personnel.isActive()) {
                allFreePers.add(personnel);
            }
        }
        return allFreePers;
    }

    private static boolean isAvailableEmployees() {
        for (Personnel personnel : personnels) {
            if (!personnel.isActive()) {
                return true;
            }
        }
        return false;
    }

    public static int getTotalFreeSpace() {
        int tot = 0;
        for (Rangee rangee : rangees) {
            tot += rangee.getFreeSpace();
        }
        return tot;
    }

    public static Rangee checkFreeSpaceInOneR(int vol) {
        for (Rangee rangee : rangees) {
            if (rangee.isFree(vol)) {
                System.out.println(" I will return Range nb " + rangee.getId());
                return rangee;
            }
        }
        return null;
    }

    public static void ConstructionNouvelleCommande(String[] commandeStrings) {
        try {
            String nom = commandeStrings[2];
            String pieceMaison = commandeStrings[3];
            int dureeConstruction = Integer.parseInt(commandeStrings[4]);
            Map<String, Integer> typeLotAndSize = new HashMap<>();
            String typeLot = "";
            int sizeLot = 0;

            /**
             * We Are Creating a map Of lot's types and required Sizes
             */
            for (int i = 5; i < commandeStrings.length; i++) {
                if (i % 2 == 0) {
                    sizeLot = Integer.parseInt(commandeStrings[i]);
                } else {
                    typeLot = commandeStrings[i];
                }
                typeLotAndSize.put(typeLot, sizeLot);
            }

            /**
             * This Loop checkes existance Of lot
             */
            for (String nomLot : typeLotAndSize.keySet()) {
                if (!testerExistanceLot(nomLot, typeLotAndSize.get(nomLot))) {
                    throw new Exception("Lot Not Found");
                }
            }
            /**
             * Since You Get to this Point so the Loop Ends Without throwing the exception
             * means All Lots are there
             */

            // Now We Will Check Existance Of Employee

        } catch (Exception e) {
            System.out.println("\u001B[31m"
                    + "Commande Miss Formed It Must be <id(Int)> Meuble <nom(String)> <Piece(String)> <duree(Int)><typeLot(String)><volumeLot(Int)>"
                    + "\u001B[0m");
            e.printStackTrace();
        }

    }

    private static boolean testerExistanceLot(String nomLot, Integer volumeRequis) {
        for (Rangee rangee : rangees) {
            if (rangee.getAvailableVolByType(nomLot) >= volumeRequis) {
                return true;
            }
        }
        return false;
    }

    public static void showState() {
        for (Rangee range : rangees) {
            System.out.println("* " + range.toString());
            for (Lot lot : range.getLots()) {
                System.out.println("  ------> " + lot.toString());
            }
        }
    }

}
