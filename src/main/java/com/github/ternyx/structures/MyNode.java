package com.github.ternyx.structures;

/**
 * MyNode
 */
public class MyNode<T> {
    private T value;
    private MyNode<T> next;

    public MyNode(T value, MyNode<T> next) {
        this.value = value;
        this.next = next;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public MyNode<T> getNext() {
        return next;
    }

    public void setNext(MyNode<T> next) {
        this.next = next;
    }
}
