import java.util.Comparator;

final class DefaultComparator implements Comparator {
    public int compare(Object a, Object b){
        return ((Comparable)a).compareTo(b);
    }
}
