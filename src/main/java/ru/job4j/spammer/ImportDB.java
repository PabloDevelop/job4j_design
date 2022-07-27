package ru.job4j.spammer;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    /**Сохраняет в ArrayList объект User
     *с данными, прочитанными из файла.
     * @return
     * @throws IOException
     */
    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            while (rd.ready()) {
                String line = rd.readLine();
                if (line.length() > 0) {
                    String[] splitlines = line.split(";", 2);
                    if (splitlines.length != 2 || line.startsWith(";")) {
                        throw new IllegalArgumentException("Wrong format!");
                    }
                    users.add(new User(splitlines[0], splitlines[1].replace(";", " ")));
                }
            }
        }
        return users;
    }

    /**Устанавливает соединение используя
     *полученные из файла параметры.
     *Добавляет в таблицу данные из ArrayList.
     * @param users ArrayList c объектами User
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement(
                        "insert into users (name, email) values(?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (InputStream in = new FileInputStream("./src/main/resources/app1.properties")) {
            cfg.load(in);
            System.out.println("Parameters have loaded.");
        }
        ImportDB db = new ImportDB(cfg, "./src/main/resources/dump.txt");
        db.save(db.load());
        System.out.println("Users data has saved.");
    }
}