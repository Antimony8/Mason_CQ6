import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {
    public static List<Integer> icecreamParlor(int m, List<Integer> arr) {
        List<Integer> bestCombination = new ArrayList<>();
        findCombination(arr, m, 0, new ArrayList<>(), bestCombination);
        return bestCombination;
    }

    private static void findCombination(List<Integer> arr, int m, int startIndex, List<Integer> currentCombination, List<Integer> bestCombination) {
        if (currentCombination.size() == 3) {
            int currentCost = currentCombination.stream().mapToInt(arr::get).sum();
            int bestCost = bestCombination.stream().mapToInt(arr::get).sum();

            if (Math.abs(m - currentCost) < Math.abs(m - bestCost) && currentCost <= m) {
                bestCombination.clear();
                bestCombination.addAll(currentCombination);
            }
            return;
        }

        for (int i = startIndex; i < arr.size(); i++) {
            currentCombination.add(i);
            findCombination(arr, m, i + 1, currentCombination, bestCombination);
            currentCombination.remove(currentCombination.size() - 1);
        }
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int m = Integer.parseInt(bufferedReader.readLine().trim());
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

                List<Integer> result = Result.icecreamParlor(m, arr);

                bufferedWriter.write(
                    result.stream()
                        .map(Object::toString)
                        .collect(joining(" "))
                    + "\n"
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
