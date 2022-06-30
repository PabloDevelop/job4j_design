package ru.job4j.io;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private final List<String> log = new ArrayList<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    /**Содержит логику чата;
     */
    public void run() {
        Scanner input = new Scanner(System.in);
        System.out.println("-Чат открыт-");
        String question = input.nextLine();
        String status = CONTINUE;
        List<String> answers = readPhrases();
        while (status.equals(CONTINUE) || status.equals(STOP)) {
            log.add(question);
            if (checkStatus(question).contains(OUT)) {
                saveLog(this.log);
                System.out.println("-Чат закрыт-");
                input.close();
                status = OUT;
            } else if (checkStatus(question).contains(STOP) || status.contains(STOP)) {
                question = input.nextLine();
                status = STOP;
            } else {
                String answer = answers.get(new Random().nextInt(answers.size()));
                log.add(answer);
                System.out.println(answer);
                question = input.nextLine();
            }
        }
    }

    /**Проверяет статус чата.
     * @param question
     * @return статус чата
     */
    private String checkStatus(String question) {
        String rsl = question;
        if (question.contains("стоп")) {
            rsl = STOP;
        } else if (question.equals("закончить")) {
            rsl = OUT;
        } else if (question.equals("продолжить")) {
            rsl = CONTINUE;
        }
        return rsl;
    }

    /**Читает фразы из файла
     * @return
     */
    private List<String> readPhrases() {
        List<String> string = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(this.botAnswers))) {
            string = in.lines()
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;
    }

    /**Сохраняет лог чата в файл
     */
    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(this.path)
                ))) {
            log.forEach(l -> out.write(l + System.lineSeparator()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./io.data/log.txt", "./io.data/answers.txt");
        cc.run();
    }
}