package com.github.ternyx.main;
import java.util.Arrays;
import com.github.ternyx.logic.MethodCalls;
import com.github.ternyx.logic.NumberCalls;
import com.github.ternyx.logic.StudentMarks;
import com.github.ternyx.structures.MyLinkedStack;
import com.github.ternyx.structures.Queue;
import com.github.ternyx.structures.Student;

public class Main {
    public static void main(String[] args) {
        /*test1(true);
        StudentMarks.printMaxMarks(args[0]);
        test3();
        NumberCalls.simulateNumberCalls(10, 2);*/


        MethodCalls calls = new MethodCalls();
        calls.enqueueMethod("test1");
        calls.enqueueMethod("printMaxMarks", StudentMarks.class, new Object[] { args[0] });
        calls.enqueueMethod("test3");
        calls.enqueueMethod("simulateNumberCalls", NumberCalls.class, new Object[] { 10, 2 });

        calls.executeMethods();
    }

    public static void test1() {
        System.out.format("Testing task 1\n\n");

        System.out.format("Testing int stack\n");
        MyLinkedStack<Integer> intStack = new MyLinkedStack<>();

        for (int i = 1; i <= 2; i++) {
            System.out.format("Pushing %d on stack\n", i);
            intStack.push(i);
            System.out.format("Top: %s, Length %d\n", intStack.top(), intStack.getLength());
        }

        for (int i = 1; i <= 3; i++) {
            try {
                System.out.format("Popping from stack: %d\n", intStack.pop());
            } catch (NullPointerException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                System.out.format("Top: %s, Length %d\n", intStack.top(), intStack.getLength());
            } catch (NullPointerException ex) {
                System.out.println(ex.getMessage());
            }
        }

        System.out.format("\nTesting student stack\n\n");
        MyLinkedStack<Student> studentStack = new MyLinkedStack<>();


        for (Student t : Arrays.asList(new Student("name1", 5), new Student("name2", 7))) {
            System.out.format("Pushing %s\n", t);
            studentStack.push(t);
            System.out.format("Top: %s, Length %d\n", studentStack.top(), studentStack.getLength());
        }

        for (int i = 0; i < studentStack.getLength() + 1; i++) {
            try {
                System.out.format("Popping from stack: %s\n", studentStack.pop());
            } catch (NullPointerException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                System.out.format("Top: %s, Length %d\n", studentStack.top(),
                        studentStack.getLength());
            } catch (NullPointerException ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println("\n");
    }

    public static void test3() {
        System.out.format("\nTesting queqe\n\n");
        Queue<Integer> intQueue = new Queue<>();

        final int N = 3;
        for (int i = 1; i <= N; i++) {
            System.out.format("Enquing %d\n", i);
            intQueue.enqueue(i);
            System.out.format("FrontNode %d, RearNode %d, Length %d\n", intQueue.inFront(),
                    intQueue.inRear(), intQueue.getLength());
        }

        for (int i = 0; i <= N; i++) {
            try {
                System.out.format("Dequing %d\n", intQueue.dequeue());
            } catch (NullPointerException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                System.out.format("FrontNode %d, RearNode %d, Length %d\n", intQueue.inFront(),
                        intQueue.inRear(), intQueue.getLength());
            } catch (NullPointerException ex) {
                System.out.println(ex.getMessage());
            }
        }

        System.out.format("Length: %d\n\n", intQueue.getLength());
    }
}
