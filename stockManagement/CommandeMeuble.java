package stockManagement;
import java.util.Map;

/**
 * CommandeMeuble Class
 */
public class CommandeMeuble {
    private static int counter = 0;

    private int id;
    private Map<String, Integer> lotsRequis;
    private boolean accepted;

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        CommandeMeuble.counter = counter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<String, Integer> getLotsRequis() {
        return lotsRequis;
    }

    public void setLotsRequis(Map<String, Integer> lotsRequis) {
        this.lotsRequis = lotsRequis;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    @Override
    public String toString() {
        return "CommandeMeuble [accepted=" + accepted + ", id=" + id + ", lotsRequis=" + lotsRequis + "]";
    }

    public CommandeMeuble(Map<String, Integer> lotsRequis) {
        this.id = CommandeMeuble.counter++;
        this.lotsRequis = lotsRequis;
        this.accepted = false;
    }

}
