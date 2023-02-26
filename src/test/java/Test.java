import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class Test {
    public static void main(String[] args) {
        List<Integer> lists = Arrays.asList(1, 2, 3, 4, 5, 6);
        ListIterator<Integer> integerListIterator = lists.listIterator();
        while (integerListIterator.hasNext()) {
            System.out.println(integerListIterator.next());
            integerListIterator.remove();
        }
    }
}

