package humanResources;

/**
 * Personnel
 */
public abstract class Personnel {
    private static int counter = 0;
    private int id;
    private String nom;
    private String prenom;
    private boolean available;
    private boolean active;
    private float balance;

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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return "Personnel [id=" + id + ", nom=" + nom + ", prenom=" + prenom + "]";
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Personnel.counter = counter;
    }

    public Personnel(String nom, String prenom) {
        this.id = Personnel.counter++;
        this.nom = nom;
        this.prenom = prenom;
    }
}