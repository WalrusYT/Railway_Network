import Railway.dataStructures.Iterator;
import Railway.dataStructures.List;


public class Main2 {
    public static void main(String[] args) {
        System.out.println(Integer.parseInt("01"));
    }

    private static void printList(List<Integer> list) {
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.printf("%d ", iterator.next());
        }
        System.out.println();
    }
}
