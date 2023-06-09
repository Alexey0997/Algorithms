import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/*
Дано целое число N из отрезка [1; 1000].
Также даны N целых чисел Ai из отрезка [1; 1000000].
Требуется для каждого числа Ai вывести количество различных делителей этого числа.
В этой задаче несколько верных решений, попробуйте найти наиболее оптимальное.
Для полученного решения укажите сложность в О-нотации.
*/
public class Seminar_1_Homework {
    public static void main(String[] args) {
        // Шаг № 1 определим число N из диапазона[1; 1000]
        int n = 1;
        List<Integer> algorithmO = new ArrayList<>();

        // Шаг № 2 выберем N случайных чисел Ai из диапазона [1; 1 000 000]
        int min = 1;
        int max = 1000;
        List<Integer> listAi =  generateRandomNumbers(n, min, max);
        System.out.print("Случайное число Ai, в диапазоне [1; 1000000]: ");
        System.out.println(listAi);

        
        // Шаг № 3 определим делители для каждого числа Ai
        AtomicInteger counter = new AtomicInteger(0);
        finDividers(listAi, counter);
        System.out.printf("Количество операций: %s\n", counter.get());
        algorithmO.add(counter.get());


        // Шаг № 4 сравним, как изменяется сложность алгоритма с увеличением объема данных
        n = 2;
        listAi =  generateRandomNumbers(n, min, max);
        System.out.println("\nУВЕЛИЧИМ КОЛИЧЕСТВО ЧИСЕЛ Ai = Ai + 1");
        System.out.printf("Список из %s случайных чисел Ai, в диапазоне [1; 1000000]: ", n);
        System.out.println(listAi);
        finDividers(listAi, counter);
        System.out.printf("Количество операций: %s\n", counter.get());
        algorithmO.add(counter.get());

        n = 3;
        listAi =  generateRandomNumbers(n, min, max);
        System.out.println("\nУВЕЛИЧИМ КОЛИЧЕСТВО ЧИСЕЛ Ai = Ai + 2");
        System.out.printf("Список из %s случайных чисел Ai, в диапазоне [1; 1000000]: ", n);
        System.out.println(listAi);
        finDividers(listAi, counter);
        System.out.printf("Количество операций: %s\n", counter.get());
        algorithmO.add(counter.get());

        n = 4;
        listAi =  generateRandomNumbers(n, min, max);
        System.out.println("\nУВЕЛИЧИМ КОЛИЧЕСТВО ЧИСЕЛ Ai = Ai + 3");
        System.out.printf("Список из %s случайных чисел Ai, в диапазоне [1; 1000000]: ", n);
        System.out.println(listAi);
        finDividers(listAi, counter);
        System.out.printf("Количество операций: %s\n", counter.get());
        algorithmO.add(counter.get());
        System.out.printf("\nДинамика изменения количества операций: %s", algorithmO);

    }

    public static List<Integer> generateRandomNumbers (int n, int min, int max){
        List<Integer> listAi = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
            listAi.add(randomNum);
        }
        return listAi;
    }

    // ФУНКЦИЯ ПОДСЧЕТА КОЛИЧЕСТВА ДЕЛИТЕЛЕЙ ДЛЯ ЗАДАННОГО ЧИСЛА
    public static void finDividers (List<Integer> listAi, AtomicInteger counter){
        int count = 0;                                                                           // O(1)
        for (Integer integer : listAi) {                                                         // O(n)
            for (int i = 1; i * i <= integer; i++) {                                             // O(sqrt(integer))
                counter.incrementAndGet();                                                       // O(1)
                if (integer % i == 0) {                                                          // O(1)
                    count++;                                                                     // O(1)
                }
            }
            System.out.printf("Количество делителей числа %s: %s", integer,  count * 2 + "\n");  // O(1)
            count = 0;                                                                           // O(1)
        }                      // СЛОЖНОСТЬ О-НОТАЦИИ = O(1) + O(n) * O(sqrt(integer)) + 5 * O(1) = O(n*sqrt(integer))
    }
}

