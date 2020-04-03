package com.github.ternyx.structures;

import java.lang.NullPointerException;

/**
 * MyLinkedStack
 */
public class MyLinkedStack<T> {
    private MyNode<T> topNode = null;
    private int length = 0;

    public MyLinkedStack() {  }

    public boolean isEmpty() {
        return this.length == 0;
    }
    
    public int getLength() {
        return this.length;
    }

    public boolean push(T value) {
        if (isFull()) {
            return false;
        }

        if (topNode == null) {
            topNode = new MyNode<T>(value, null);
        } else {
            MyNode<T> newNode = new MyNode<>(value, topNode);
            topNode = newNode;
        }
        ++length;
        return true;
    }

    public T pop() {
        if (length == 0) {
            throw new NullPointerException("Cant pop from empty stack");
        }

        MyNode<T> oldNode = topNode;
        topNode = oldNode.getNext();
        --length;

        return oldNode.getValue();
    }

    public T top() {
        if (length == 0) {
            throw new NullPointerException("Cant top from empty stack");
        }
        return topNode.getValue();
   }

    public void clear() {
        this.topNode = null;
        this.length = 0;
        System.gc();
    }

    public boolean isFull() {
        try {
            new MyNode<T>(null, null);
        } catch (OutOfMemoryError err) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        MyNode<T> node = topNode;
        boolean isNull = node == null;

        while (!isNull) {
            builder.append(node.getValue());
            node = node.getNext();
            isNull = node == null;

            if (!isNull) {
                builder.append(" ");
            }
        } 

        return builder.toString();
    }
}
