package stockManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Rangee class
 */
public class Rangee {
    private static int counter;
    private int id;
    private List<Lot> lots;

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Rangee.counter = counter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Lot> getLots() {
        return lots;
    }

    public void setLots(List<Lot> lots) {
        this.lots = lots;
    }

    public Rangee(List<Lot> lots) {
        this.lots = lots;
        this.id = Rangee.counter++;
    }

    public Rangee() {
        this.lots = new ArrayList<Lot>();
        this.id = Rangee.counter++;
    }

    @Override
    public String toString() {
        return "Rangee [id=" + id + ", lots=" + lots + "]";
    }

    // My Methods
    public int ajouterLot(Lot l) {
        if ((Entrepot.longueur - getCurrentSize()) > l.getVolume()) {
            this.lots.add(l);
            return 1;
        }
        System.out.println("Espace Insufisant");
        return 0;
    }

    public int getCurrentSize() {
        int size = 0;
        for (Lot lot : lots) {
            size += lot.getVolume();
        }
        return size;
    }

    public void supprimerLot(Lot l) {
        lots.remove(l);
    }

    public void supprimerLot(int lotId) {
        for (Lot lot : lots) {
            if (lot.getId() == lotId) {
                lots.remove(lot);
                return;
            }
        }
    }

    public Lot getInventaire() {
        int rand = new Random().nextInt((this.lots.size() - 1));
        return lots.get(rand);
    }

    public Rangee examinerLot(Lot l) {
        if ((Entrepot.longueur - getCurrentSize()) > l.getVolume()) {
            return this;
        }
        return null;
    }

	public int getAvailableVolByType(String lotType) {
        int vol = 0;
        for (Lot lot : lots) {
            if (lot.getNomPieces().equals(lotType)) {
                vol+=lot.getVolume();
            }
        }
        return vol;
	}

}
