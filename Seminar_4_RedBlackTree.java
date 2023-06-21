import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Seminar_4_RedBlackTree {
    public static void main(String[] args) throws IOException {
        final Seminar_4_RedBlackTree tree = new Seminar_4_RedBlackTree();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                try {
                    System.out.print("\nВведите целое число: ");
                    int value = Integer.parseInt(reader.readLine());
                    tree.add(value);
                } catch (Exception ignored) {
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Node root;

    private static class Node {
        private int value;
        private Colour colour;
        private Node leftChild;
        private Node rightChild;
    }

    private enum Colour {
        RED, BLACK
    }

    /**
     * add - функция обработки рутовой ноды. Если рут существует, то создается новая нода относительно рута
     * и проводится балансировка.
     * Если рута нет, то он создается и ему присваивается черный цвет.
     */
    public boolean add(int value) {
        if (root != null) {
            boolean result = addNode(root, value);
            root = balance(root);
            root.colour = Colour.BLACK;
            return result;
        } else {
            root = new Node();                                     // Если рута нет, то создаем новую ноду
            root.colour = Colour.BLACK;                            // черного цвета.
            root.value = value;
            System.out.printf("Создан рут %s: ", value);
            return true;
        }
    }

    /**
     * addNode - функция добавления новой ноды. Если ноды с данным значением не существует,
     * то проводится рекурсивный обход в глубину для выбора оптимального места размещения новой ноды.
     * При этом вызывается метод проверки на необходимость балансировки
     */
    public boolean addNode(Node node, int value) {
        if (node.value == value) {                                  // Если нода с таким значением уже существует,
            System.out.printf("Нода со значением %s существует, введите другое число.", value);
            return false;                                           // то возвращаем сообщение об ошибке.
        } else {
            if (node.value > value) {
                if (node.leftChild != null) {
                    boolean result = addNode(node.leftChild, value);
                    node.leftChild = balance(node.leftChild);       // Вызов проверки на необходимость ребалансировки.
                    inorder(node);
                    return result;
                } else {
                    node.leftChild = new Node();                    // Генерируем новую ноду,
                    node.leftChild.colour = Colour.RED;             // присваиваем ей красный цвет
                    node.leftChild.value = value;                   // и заданное значение.
                    inorder(node);
                    return true;
                }
            } else {                                                // Если правого ребенка нет, то генерируем значение.
                if (node.rightChild != null) {                      // Если правый ребенок имеется,
                    boolean result = addNode(node.rightChild, value);
                    node.rightChild = balance(node.rightChild);     // то по правому ребенку рекурсивно запускаем
                    inorder(node);
                    return result;                                  // поиск возможности создать ноду для заданного
                } else {                                            // значения.
                    node.rightChild = new Node();
                    node.rightChild.colour = Colour.RED;
                    node.rightChild.value = value;
                    inorder(node);
                    return true;
                }
            }
        }
    }

    /**
     * balance - функция реализует проверку условий для проведения балансировки, определяет ее тип
     * (левый поворот, правый поворот или замена цвета) и вызывает соответствующие методы.
     */
    private Node balance(Node node) {
        Node result = node;
        boolean needRebalance;
        do {
            needRebalance = false;

            // Если правый ребенок красного цвета, а левого ребенка нет или он черного цвета,
            // то подтверждаем потребность в балансировке и выполняем "правый разворот".
            if (result.rightChild != null && result.rightChild.colour == Colour.RED &&
                    result.leftChild == null || result.leftChild.colour == Colour.BLACK) {
                needRebalance = true;
                result = rightSwap(result);
            }

            // Если левый ребенок красного цвета имеет своего левого ребенка красного цвета,
            // то подтверждаем потребность в балансировке и выполняем "левый разворот".
            if (result.leftChild != null && result.leftChild.colour == Colour.RED &&
                    result.leftChild.leftChild != null && result.leftChild.leftChild.colour == Colour.RED) {
                needRebalance = true;
                result = leftSwap(result);
            }

            // Если левый и правый наследники красного цвета,
            // то подтверждаем потребность в балансировке и выполняем "замену цвета".
            if (result.leftChild != null && result.leftChild.colour == Colour.RED &&
                    result.rightChild != null && result.rightChild.colour == Colour.RED) {
                needRebalance = true;
                colourSwap(result);
            }
        }
        while (needRebalance);                                    // Если ни одно из вышеуказанных условий не выполнено,
        return result;                                            // то выходим из цикла и возвращаем результат.
    }

    /**
     * rightSwap - функция, реализующая "правый поворот" в красно-черном дереве.
     */
    private Node rightSwap(Node node) {
        Node rightChild = node.rightChild;
        Node betweenChild = rightChild.leftChild;
        rightChild.leftChild = node;
        node.rightChild = betweenChild;
        rightChild.colour = node.colour;
        node.colour = Colour.RED;
        System.out.print("\nДля балансировки выполнен правый разворот.");
        return rightChild;
    }

    /**
     * leftSwap - функция, реализующая "левый поворот" в красно-черном дереве.
     */
    private Node leftSwap(Node node) {
        Node leftChild = node.leftChild;
        Node betweenChild = leftChild.rightChild;
        leftChild.rightChild = node;
        node.leftChild = betweenChild;
        leftChild.colour = node.colour;
        node.colour = Colour.RED;
        System.out.print("\nДля балансировки выполнен левый разворот.");
        return leftChild;
    }

    /**
     * colourSwap - функция, реализующая смену цвета в красно-черном дереве.
     */
    private void colourSwap(Node node) {
        node.rightChild.colour = Colour.BLACK;
        node.leftChild.colour = Colour.BLACK;
        node.colour = Colour.RED;
        System.out.print("\nДля балансировки проведена смена цвета.");
    }

    /**
     * inorder - функция, осуществляющая перебор узлов дерева и вывод на печать их цветовых символов:
     * '●' - красный цвет;
     * '◯' - черный цвет.
     */
    void inorder(Node node) {
        if (node != null) {
            inorder(node.leftChild);
            char col = '●';
            if (node.colour == Colour.BLACK) {
                col = '◯';
            }
            System.out.print(node.value + " " + col + " ");
            inorder(node.rightChild);
        }
    }
}

