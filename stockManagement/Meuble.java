package stockManagement;

/**
 * Meuble
 */
public class Meuble {
    private static int counter = 0;
    private int id;
    private int dureeConstruction;
    private String nom;
    private int idConstructor;
    private int idCommande;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDureeConstruction() {
        return dureeConstruction;
    }

    public void setDureeConstruction(int dureeConstruction) {
        this.dureeConstruction = dureeConstruction;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getIdConstructor() {
        return idConstructor;
    }

    public void setIdConstructor(int idConstructor) {
        this.idConstructor = idConstructor;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public Meuble(int dureeConstruction, String nom) {
        this.id = Meuble.counter++;
        this.dureeConstruction = dureeConstruction;
        this.nom = nom;
    }
    

    public Meuble(int dureeConstruction, String nom, int idCommande) {
        this.dureeConstruction = dureeConstruction;
        this.nom = nom;
        this.idCommande = idCommande;
    }

}