import java.util.Comparator;
import java.io.Serializable;

public class ComparaInt implements Serializable, Comparator<Integer>{
 
    public int compare(Integer t, Integer t1) {
        return t - t1;
    }
}