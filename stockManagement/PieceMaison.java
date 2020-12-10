package stockManagement;

/**
 * Piece Maison
 */
public class PieceMaison {
    private static int counter = 0;
    private int id;
    private int commandeId;
    private static String[] piecesPossible = { "", "", "", "", "", "" };
    private String nomPiece;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static String[] getPiecesPossible() {
        return piecesPossible;
    }

    public static void setPiecesPossible(String[] piecesPossible) {
        PieceMaison.piecesPossible = piecesPossible;
    }

    public String getNomPiece() {
        return nomPiece;
    }

    public void setNomPiece(String nomPiece) {
        this.nomPiece = nomPiece;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        PieceMaison.counter = counter;
    }

    public int getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(int commandeId) {
        this.commandeId = commandeId;
    }

    public PieceMaison(String nomPiece) {
        this.nomPiece = nomPiece;
        this.id = PieceMaison.counter++;
    }

    public PieceMaison(int commandeId, String nomPiece) {
        this.commandeId = commandeId;
        this.nomPiece = nomPiece;
        this.id = PieceMaison.counter++;
    }

    @Override
    public String toString() {
        return "PieceMaison [commandeId=" + commandeId + ", id=" + id + ", nomPiece=" + nomPiece + "]";
    }

}
