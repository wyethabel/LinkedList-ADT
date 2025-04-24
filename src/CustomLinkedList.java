/* Primary class for a custom linked list with inner classes
    that define individual node objects and the iterator that allows
    traversal of the collection. Each node object holds it's associated
    integer data and a reference to the next node. */

import java.io.*;
import java.util.*;

public class CustomLinkedList {
    private Node head;

    // ------ Methods ------
    // Builds the inner iterator class using the iterator interface.
    public Iterator<Integer> iterator() {
        return new LinkedListIterator();
    }

    // Method for adding data to the collection
    /* Operates by accepting multiple values as input, builds a new
        node object for each, validates that the collection is not
        empty, and traverses to the end to assign the node by
        defining the next attribute of the previous. */
    public void insert(int... valueList) {
        for (int data : valueList){
            Node tempNode = new Node(data);

            // Empty check.
            if (head == null) {
                head = tempNode;
            } else {

                // Iterate until the end and fill in the next attribute.
                Node current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = tempNode;
            }
        }
    }

    // Method for removing data from the collection
    /* Operates by first ending the method if the collection is empty,
        then checking if head is the focus of deletion and overwrites
        it with its next attribute if true, then performs a search over
        all nodes that are not null until the next attribute reflects the
        focus for deletion, which is finally overwritten with the
        next attribute of the target node, effectively deleting it. */
    public void delete(int data) {
        // Empty check.
        if (head == null) {
            return;
        }

        // Head check.
        if (head.data == data) {
            head = head.next;
            return;
        }

        // Iterate until the data is found and overwrite.
        Node current = head;
        while (current.next != null && current.next.data != data) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
        }
    }

    // Method for reading data from a file and applying it to a linked list.
    /* Operates by initially defining a buffer list to store each value in,
        attempts to read and accept each row as an integer, and if successful,
        adds each item to the buffer, which is finally converted to an array
        and inserted into the linked list. */
    public void fileInput(String file) {
        List<Integer> buffer = new ArrayList<>();

        // Read file and attempt to add each value.
        try (BufferedReader input = new BufferedReader(new FileReader(file))) {
            String tempRow;
            while((tempRow = input.readLine()) != null) {
                try {
                    int data = Integer.parseInt(tempRow.trim());
                    buffer.add(data);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input at row: " + tempRow);
                }
            }

            /* Convert the buffer into a stream, change each item in the
                stream into a primitive integer, and assign these values to
                an array for insertion. */
            insert(buffer.stream().mapToInt(i -> i).toArray());

        } catch (IOException e) {
            System.out.println("Issue with the input file: " + e.getMessage());
        }
    }

    // ------ Inner Classes ------
    // Defines the node objects that holds the data and next node reference.
    private class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    /* iterator class that makes use of the iterator interface and two
        methods that check for a next node and defines the iteration process
        by storing the current node data, while moving the pointer to
        the next node and returning the stored integer. */
    private class LinkedListIterator implements Iterator<Integer> {
        private Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }
        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int data = current.data;
            current = current.next;
            return data;
        }
    }
}
