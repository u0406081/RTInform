import static java.util.Objects.isNull;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Java {
    public static void main(String[] args) {
        int[] array = {1, 3, 4, 5, 1, 5, 4};
        int[][] result = firstSolution(array);
        //int[][] result = secondSolution(array);
        //int[][] result = thirdSolution(array);
        System.out.println(Arrays.deepToString(result));
    }

    /**
     * Решение с использованием Map
     */
    private static int[][] firstSolution(int[] array) {
        Map<Integer, Long> map = new HashMap<>();

        for (int i = 0; i < array.length; i++) {
            int element = array[i];
            Long amount = map.get(element);

            if (isNull(amount)) {
                map.put(element, 1L);
            } else {
                map.put(element, (amount + 1));
            }
        }
        return getAssociativeArray(map);
    }

    /**
     * Решение с использованием Stream
     */
    private static int[][] secondSolution(int[] array) {
        Map<Integer, Long> map =
                IntStream.of(array).boxed().collect(Collectors.toList())
                        .stream()
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return getAssociativeArray(map);
    }

    /**
     * Решение с использованием Set
     */
    private static int[][] thirdSolution(int[] array) {
        Set<Integer> nums = new HashSet<>();
        List<int[]> result = new ArrayList<>();

        for (int i =0; i<array.length; i++) {
            int element = array[i];
            if (nums.contains(element))
                continue;

            int count = 1;
            for (int j = i + 1; j < array.length; j++) {
                if (element == array[j]) {
                    count++;
                }
            }
            result.add(new int[]{element, count});
            nums.add(element);
        }

        return result.toArray(new int[0][]);
    }

    private static int[][] getAssociativeArray(Map<Integer, Long> map) {
        return map.entrySet()
                .stream()
                .map(e -> new int[]{e.getKey(), e.getValue().intValue()})
                .toArray(int[][]::new);
    }
}
