import java.util.Comparator;
import java.io.Serializable;

public class ComparaString implements Serializable, Comparator<String>{
 
    public int compare(String t, String t1) {
        return t.compareTo(t1);
    }
}