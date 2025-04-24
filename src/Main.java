/* Main test class used to implement primary functionality
    of the custom linked list class. */

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        CustomLinkedList myList = new CustomLinkedList();

        myList.insert(1, 2, 3, 4, 5, 6, 7);
        myList.delete(3);
        myList.fileInput("src/values.txt");

        Iterator<Integer> iterator = myList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next() + " ");
        }

        System.out.println("Notice that the 3 was deleted " +
                "\n and each value from the test file was added to " +
                "\n the end of the linked list");
    }
}