/**
 * Урок 3. Структуры данных. Связный список.
 * Необходимо реализовать метод разворота связного списка (двухсвязного или односвязного на выбор).
 */
public class Seminar_3_HMW_Revert {
    public static void main(String[] args) {
        for (int i = 1; i <= 7; i++) {
            dList.pushFront(i);
        }
        System.out.print("Исходный двусвязный список:    ");
        dList.print();
        dList.revert();
        System.out.print("Развернутый двусвязный список: ");
        dList.print();
    }

    static class dList {
        static Node head;
        static Node tail;

        static class Node {
            int value;
            Node next;
            Node previous;
        }

        /**
         * pushFront - метод, предназначенный для добавления новых элементов в начало двусвязного списка.
         */
        public static void pushFront(int value) {
            Node node = new Node();
            node.value = value;
            if (head == null) {
                tail = node;
            } else {
                node.next = head;
                head.previous = node;
            }
            head = node;
        }

        /**
         * revert - метод, предназначенный для разворота двусвязного списка.
         */
        public static void revert() {
            Node currentNode = head;
            while (currentNode != null) {
                Node next = currentNode.next;
                Node previous = currentNode.previous;
                currentNode.next = previous;            // Разворот ссылок.
                currentNode.previous = next;
                if (previous == null) {                  // Концу списка присвоим значение первого элемента.
                    tail = currentNode;
                }
                if (next == null) {                      // Началу списка присвоим значение последнего элемента.
                    head = currentNode;
                }
                currentNode = next;
            }
        }

        /**
         * print - метод, предназначенный для вывода на печать двусвязного списка.
         */
        public static void print() {
            Node currentNode = head;
            while (currentNode != null) {
                System.out.printf("%d ", currentNode.value);
                currentNode = currentNode.next;
            }
            System.out.println();
        }
    }
}


