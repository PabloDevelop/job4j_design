package ru.job4j.dip;

public class DIPCase2 {

    public class ParseHabr {
        public static void parse(String url) {
            SaveToDB.save();
        }
    }

    public class SaveToDB {
        public static void save() {

        }

        public static String get() {
            return "https://www.habr.com";
        }
    }

    public class StartParse {
        public void goParse() {
            String prev = SaveToDB.get();
            ParseHabr.parse(prev + "/vacancies?page=2&type=all");
        }
    }
}