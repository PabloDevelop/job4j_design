package srp;

import java.util.ArrayList;
import java.util.List;

public class SRPCase1 {
    public void consolePrint(List<Integer> ages) {
        ages.forEach(System.out::println);
    }

    public List<Integer> ages(int numbers) {
        List<Integer> agesList = new ArrayList<>();
        agesList.add(numbers);
        return agesList;
    }
}