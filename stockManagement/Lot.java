package stockManagement;


import java.util.ArrayList;
import java.util.List;

/**
 * Class Lot
 */
public class Lot {
    private static int counter = 0;
    private int id;
    private int volume;
    private String nomPieces;
    private float poidsPieces;
    private float prixPieces;
    private List<PiecesDetachee> piecesDetachee;

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Lot.counter = counter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getNomPieces() {
        return nomPieces;
    }

    public void setNomPieces(String nomPieces) {
        this.nomPieces = nomPieces;
    }

    public float getPoidsPieces() {
        return poidsPieces;
    }

    public void setPoidsPieces(float poidsPieces) {
        this.poidsPieces = poidsPieces;
    }

    public float getPrixPieces() {
        return prixPieces;
    }

    public void setPrixPieces(float prixPieces) {
        this.prixPieces = prixPieces;
    }

    public List<PiecesDetachee> getPiecesDetachee() {
        return piecesDetachee;
    }

    public void setPiecesDetachee(List<PiecesDetachee> piecesDetachee) {
        this.piecesDetachee = piecesDetachee;
    }

    public Lot(int volume, String nomPieces, float poidsPieces, float prixPieces, List<PiecesDetachee> piecesDetachee) {
        this.volume = volume;
        this.nomPieces = nomPieces;
        this.poidsPieces = poidsPieces;
        this.prixPieces = prixPieces;
        this.piecesDetachee = piecesDetachee;
        this.id = Lot.counter++;
    }

    public Lot(int volume, String nomPieces, float poidsPieces, float prixPieces) {
        this.volume = volume;
        this.nomPieces = nomPieces;
        this.poidsPieces = poidsPieces;
        this.prixPieces = prixPieces;
        this.piecesDetachee = new ArrayList<PiecesDetachee>();
        this.id = Lot.counter++;
    }

    public Lot(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Lot other = (Lot) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Lot [id=" + id + ", nomPieces=" + nomPieces + ", poidsPieces=" + poidsPieces + ", prixPieces="
                + prixPieces + ", volume=" + volume + "]";
    }

}
