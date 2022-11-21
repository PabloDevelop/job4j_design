package ru.job4j.dip;

public class DIPCase3 {
    public class MakeChoice {
        public static void main(String[] args) {
            Selector.select(2);
        }
    }

    public class Selector {
        public static void select(int number) {
            if (number == 1) {
                DoOneStep.doSmth();
            }
            if (number == 2) {
                DoTwoStep.doSmth();
            }
        }
    }

    public class DoOneStep {
        public static void doSmth() {
            DoAction.action1();
        }
    }

    public class DoTwoStep {
        public static void doSmth() {
            DoAction.action2();
        }
    }

    public class DoAction {
        public static String action1() {
            return null;
        }

        public static String action2() {
            return null;
        }
    }
}
