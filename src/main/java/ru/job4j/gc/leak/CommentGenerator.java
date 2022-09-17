package ru.job4j.gc.leak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**Генератор комментариев.
 * Также при создании заполним список фразами,
 * а при вызове generate зачистим список,
 * а затем сгенерируем 50 комментариев из случайных фраз.
 */
public class CommentGenerator implements Generate {

    public static final String PATH_PHRASES = "src/main/java/ru/job4j/gc/leak/files/phrases.txt";

    private static List<Comment> comments = new ArrayList<>();

    public static final Integer COUNT = 50;

    private static List<String> phrases;

    private UserGenerator userGenerator;

    private Random random;

    public CommentGenerator(Random random, UserGenerator userGenerator) {
        this.userGenerator = userGenerator;
        this.random = random;
        read();
    }

    private void read() {
        try {
            phrases = read(PATH_PHRASES);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static List<Comment> getComments() {
        return comments;
    }

    @Override
    public void generate() {
        comments.clear();
        List<Integer> ints = new ArrayList<>();
        random.ints(0, phrases.size())
                .distinct().limit(3).forEach(ints::add);
        for (int i = 0; i < COUNT; i++) {
            String comment = String.format("%s %s %s",
                    phrases.get(ints.get(0)),
                    phrases.get(ints.get(1)),
                    phrases.get(ints.get(2)));
            comments.add(new Comment(comment,
                    userGenerator.randomUser()));
        }
    }
}