public class Lecture_2_HeapSort {
    public static void main(String[] args) {
        int[] myArray = new int[]{
                1, 3, 5, 7, 9, 2, 4, 6, 8
        };
        int findValue = 5;
        System.out.println("СОРТИРОВКА КУЧЕЙ - СЛОЖНОСТЬ АЛГОРИТМА O(n log n)");
        System.out.print("Исходный массив:        [ ");
        for (int k : myArray) {
            System.out.print(k + " ");
        }
        System.out.print("]");

        sort(myArray);
        System.out.print("\nОтсортированный массив: [ ");
        for (int k : myArray) {
            System.out.print(k + " ");
        }
        System.out.print("]");
    }

    public static void sort(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--)                       // Построение кучи
            heapify(array, array.length, i);

        for (int i = array.length - 1; i >= 0; i--) {                         // Извлекаем элементы из кучи
            int temp = array[0];                                              // Перемещаем текущие корни в конец
            array[0] = array[i];
            array[i] = temp;

            heapify(array, i, 0);                                     // Вызываем процедуру на уменьшеной куче.
        }
    }

    public static void heapify(int[] array, int heapSize, int rootIndex) {
        int largest = rootIndex;                                                // Корень - наибольший элемент.
        int leftChild = 2 * rootIndex + 1;                                      // Левый ребенок.
        int rightChild = 2 * rootIndex + 2;                                     // Правый ребенок.
        if (leftChild < heapSize && array[leftChild] > array[largest]) {        // Если левый ребенок больше корня.
            largest = leftChild;
        }
        if (rightChild < heapSize && array[rightChild] > array[largest]) {      // Если правый ребенок больше корня.
            largest = rightChild;
        }
        if (largest != rootIndex) {                                             // Если самый большой элемент не корень.
            int temp = array[rootIndex];
            array[rootIndex] = array[largest];
            array[largest] = temp;

            heapify(array, heapSize, largest);                                  // Преобразуем в двоичную кучу
        }                                                                       // затронутое поддерево.
    }
}
