package cardealer.lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author Moussa
 */
public class Lambdas {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, -1, 2, -2, 3, -3);
        System.out.println("Original numbers: " + numbers);

        List<Integer> positiveNumbers = filter(numbers, n -> n > 0);
        System.out.println("Positive numbers: " + positiveNumbers);

        List<Integer> squaredNumbers = map(numbers, n -> n * n);
        System.out.println("Squared numbers: " + squaredNumbers);

        List<Integer> sortedNumbers = new ArrayList<>(numbers);
        sortedNumbers.sort((n1, n2) -> n1.compareTo(n2));
        System.out.println("Sorted numbers: " + sortedNumbers);

        List<String> strings = map(numbers, n -> n.toString());
        List<String> sortedStrings = sorted(strings, (s1, s2) -> s1.compareTo(s2));
        System.out.println("Numbers Sorted as strings: " + sortedStrings);

        int sum = reduce(numbers, 0, (n1, n2) -> n1 + n2);
        System.out.println("Sum of numbers: " + sum);

        String concatenated = reduce(strings, "", (s1, s2) -> s1 + s2);
        System.out.println("Concatenated strings: " + concatenated);
    }

    public static <T> List<T> filter(List<T> items, Predicate<T> predicate) {
        List<T> filtered = new ArrayList<>();
        for (T item : items) {
            if (predicate.test(item)) {
                filtered.add(item);
            }
        }
        return filtered;
    }

    public static <T, K> List<K> map(List<T> items, Function<T, K> mapper) {
        List<K> mapped = new ArrayList<>();
        for (T item : items) {
            mapped.add(mapper.apply(item));
        }
        return mapped;
    }

    public static <T> T reduce(List<T> items, T identity, Reducer<T> reducer) {
        T accumulator = identity;
        for (T item : items) {
            accumulator = reducer.reduce(accumulator, item);
        }
        return accumulator;
    }

    public static <T> List<T> sorted(List<T> items, Comparator<T> comparator) {
        List<T> sorted = new ArrayList<>(items);
        sorted.sort(comparator);
        return sorted;
    }
}
