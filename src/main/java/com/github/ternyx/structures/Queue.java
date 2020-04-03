package com.github.ternyx.structures;

/**
 * Queue
 */
public class Queue<T> {
    private MyNode<T> rearNode = null;
    private MyNode<T> frontNode = null;
    private int length = 0;

    public Queue() { }

    private boolean isFull() {
        try {
            new MyNode<T>(null, null);
        } catch (OutOfMemoryError err) {
            return true;
        }
        return false;
    }

    public boolean isEmpty() {
        return this.length == 0;
    }

    public int getLength() {
        return this.length;
    }

    public T inFront() {
        if (frontNode == null)  {
            throw new NullPointerException("Queue is empty");
        }
        return frontNode.getValue();
    }

    public T inRear() {
        if (rearNode == null) {
            throw new NullPointerException("Queue is empty");
        }
        return rearNode.getValue();
    }

    public boolean enqueue(T val) {
        if (isFull()) { 
            return false;
        }

        MyNode<T> newNode = new MyNode<T>(val, null);

        if (length == 0) {
            this.rearNode = newNode;
            this.frontNode = newNode;
        } else {
            newNode.setNext(this.rearNode);
            this.rearNode = newNode;
        }
        ++length;
        return true;
    }

    public T dequeue() {
        if (length == 0) { 
            throw new NullPointerException("The queue is empty");
        }
        MyNode<T> prevNode = rearNode;
        if (length == 1) {
            this.rearNode = null;
            this.frontNode = null;
        } else {
            while (prevNode.getNext() != frontNode)  {
                prevNode = prevNode.getNext();
            }

            this.frontNode =  prevNode;
            prevNode = prevNode.getNext();
            this.frontNode.setNext(null);
        }

        --length;
        return prevNode.getValue();
    }

    public void clear() {
        this.length = 0;
        this.rearNode = null;
        this.frontNode = null;
        System.gc();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        MyNode<T> node = rearNode;
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
