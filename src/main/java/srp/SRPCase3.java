package srp;

import java.util.ArrayList;
import java.util.List;

public class SRPCase3 {
    public Integer validate(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Isn't correct age");
        }
        return number;
    }

    public List<Integer> ages(int number) {
        List<Integer> agesList = new ArrayList<>();
        agesList.add(number);
        return agesList;
    }

    public void consolePrint(List<Integer> ages) {
        ages.forEach(System.out::println);
    }
}