package ru.job4j.jdbc;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) throws ClassNotFoundException, SQLException {
        this.properties = properties;
        initConnection();
    }

    /**Создает подключение используя параметры из файла
     * через класс Properties и присваивает его полю connection.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("driver"));
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
    }

    /**Создает пустую таблицу без столбцов с указанным именем.
     * @param tableName
     */
    public void createTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "create table if not exists "
                            + tableName
                            + "(%s, %s);",
                    "id serial primary key",
                    "name varchar(255)"
            );
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**Удаляет таблицу по указанному имени.
     * @param tableName
     */
    public void dropTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
            String sql = "DROP TABLE "
                    + tableName;
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**Добавляет столбец в таблицу.
     * @param tableName
     * @param columnName
     * @param type
     */
    public void addColumn(String tableName, String columnName, String type) {
        try (Statement statement = connection.createStatement()) {
            String sql = "ALTER TABLE "
                    + tableName
                    + " ADD "
                    + columnName
                    + " "
                    + type;
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**Удаляет столбец из таблицы.
     * @param tableName
     * @param columnName
     */
    public void dropColumn(String tableName, String columnName) {
        try (Statement statement = connection.createStatement()) {
            String sql = "ALTER TABLE "
                    + tableName
                    + " DROP COLUMN "
                    + columnName;
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**Переименовывает столбец.
     * @param tableName
     * @param columnName
     * @param newColumnName
     */
    public void renameColumn(String tableName, String columnName, String newColumnName) {
        try (Statement statement = connection.createStatement()) {
            String sql = "ALTER TABLE "
                    + tableName
                    + " RENAME COLUMN "
                    + columnName
                    + " TO "
                    + newColumnName;
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**Выводит на консоль текущую схему таблицы.
     * @param connection
     * @param tableName
     * @return
     * @throws Exception
     */
    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Properties config = new Properties();
        try (InputStream fis = new FileInputStream("./src/main/resources/app.properties")) {
            config.load(fis);
            TableEditor testTable = new TableEditor(config);
            testTable.createTable("testDB");
            System.out.println(getTableScheme(testTable.connection, "testDB"));
            testTable.addColumn("testDB", "date", "date");
            System.out.println(getTableScheme(testTable.connection, "testDB"));
            testTable.dropColumn("testDB", "date");
            System.out.println(getTableScheme(testTable.connection, "testDB"));
            testTable.renameColumn("testDB", "id", "index");
            System.out.println(getTableScheme(testTable.connection, "testDB"));
            testTable.dropTable("testDB");
            System.out.println(getTableScheme(testTable.connection, "testDB"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}