package ru.job4j.tree;

import static ru.job4j.tree.MyTreeWalkAlgo.Tree.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Java. Деревья ч.1. Рекурсивный обход в глубину.
 * <a href="https://www.youtube.com/watch?v=rwjtcJSNpBs">...</a>
 * <br>Java. Деревья ч.2. Итеративный обход дерева в глубину и ширину.
 * <a href="https://www.youtube.com/watch?v=pcicxEjCPZY">...</a>
 */
public class MyTreeWalkAlgo {
    public static void main(String[] args) {
        Tree root = new Tree(20,
                new Tree(7,
                        new Tree(4, null, new Tree(6)), new Tree(9)),
                new Tree(35,
                        new Tree(31, new Tree(28), null),
                        new Tree(40, new Tree(38), new Tree(52))));

        System.out.println("Сумма дерева (sumDeepRecursiveWalk): " + root.sumDeepRecursiveWalk());
        System.out.println("Сумма дерева (sumStackDeepWalk): " + sumStackDeepWalk(root));
        System.out.println("Сумма дерева (sumQueueWideWalk): " + sumQueueWideWalk(root));
    }

    static class Tree {
        int value;
        Tree left;
        Tree right;

        public Tree(int value, Tree left, Tree right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public Tree(int value) {
            this.value = value;
        }

        /**
         * Рекурсивно обходит в глубину
         * и считает сумму значений для всего дерева.
         *
         * @return
         */
        public int sumDeepRecursiveWalk() {
            int sum = value;
            if (left != null) {
                sum += left.sumDeepRecursiveWalk();
            }
            if (right != null) {
                sum += right.sumDeepRecursiveWalk();
            }
            return sum;
        }

        /**
         * Обходит дерево в глубину
         * используя Stack и считает сумму
         * значений для всего дерева.
         *
         * @param root
         * @return
         */
        public static int sumStackDeepWalk(Tree root) {
            Stack<Tree> stack = new Stack<>();
            stack.push(root);
            int sum = 0;
            while (!stack.isEmpty()) {
                Tree node = stack.pop();
                sum += node.value;
                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }
            }
            return sum;
        }

        /**Обходит дерево в ширину слева-направо
         * использую Queue и считает сумму
         * значений для всего дерева.
         * @param root
         * @return
         */
        public static int sumQueueWideWalk(Tree root) {
            Queue<Tree> queue = new LinkedList<>();
            queue.add(root);
            int sum = 0;
            while (!queue.isEmpty()) {
                Tree node = queue.remove();
                sum += node.value;
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            return sum;
        }
    }
}