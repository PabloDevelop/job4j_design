package ru.job4j.question;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int changed = 0;
        int deleted = 0;
        Map<Integer, String> curr = current.stream().
                collect(Collectors.toMap(User::getId, User::getName));
        for (User prev : previous) {
            if (!curr.containsKey(prev.getId())) {
                deleted++;
            } else if (!prev.getName().equals(curr.get(prev.getId()))) {
                changed++;
            }
        }
        int added = current.size() + deleted - previous.size();
        return new Info(added, changed, deleted);
    }
}