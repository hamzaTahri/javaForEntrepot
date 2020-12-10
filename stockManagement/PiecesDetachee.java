package stockManagement;

/**
 * PiecesDetachee class
 */
public class PiecesDetachee {
    private static int counter = 0;
    private int id;
    private String nom;
    private float poids;
    private float prix;
    private String type;

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        PiecesDetachee.counter = counter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getPoids() {
        return poids;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PiecesDetachee(String nom, float poids, float prix, String type) {
        this.nom = nom;
        this.poids = poids;
        this.prix = prix;
        this.type = type;
        this.id = PiecesDetachee.counter++;
    }

    @Override
    public String toString() {
        return "PiecesDetachee [id=" + id + ", nom=" + nom + ", poids=" + poids + ", prix=" + prix + ", type=" + type
                + "]";
    }
    

}
