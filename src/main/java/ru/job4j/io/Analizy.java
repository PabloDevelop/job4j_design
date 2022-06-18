package ru.job4j.io;

import java.io.*;

public class Analizy {

    /** Метод должен находить диапазоны, когда сервер не работал.
     * Сервер не работал, если status = 400 или 500.
     * Диапазоном считается последовательность статусов 400 и 500.
     * @param source путь к файлу лога
     * @param target имя путь к файлу результата анализа
     */
    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            try (PrintWriter out = new PrintWriter(
                    new BufferedOutputStream(
                            new FileOutputStream(target)
                    ))) {
                boolean serverDown = true;
                while (read.ready()) {
                String line = read.readLine();
                if (serverDown && (line.startsWith("500") || line.startsWith("400"))) {
                    out.write(line.split(" ")[1] + ";");
                    serverDown = false;
                } else if (!serverDown && !line.startsWith("500") && !line.startsWith("400")) {
                    out.write(line.split(" ")[1] + ";" + System.lineSeparator());
                    serverDown = true;
                }
            }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("./io.data/server_available.log", "./io.data/unavailable.csv");
    }
}