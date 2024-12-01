package com.example.btree;

import java.util.*;
public class Node<E> {
    private final int capacity;
    private final ArrayList<E> elements;
    private final ArrayList<Node<E>> children;

    public Node(int min) {

        this.capacity = 2 * min - 1;

        this.elements = new ArrayList<>(2 * min - 1);

        this.children = new ArrayList<>(2 * min);
    }

    @Override
    public String toString() {
        return toString(0);
    }
    private String toString(int depth) {
        StringBuilder s = new StringBuilder();
        String tab = new String(new char[depth]).replace("\0", "\t");
        List<String> printedElements = new LinkedList<>();
        for (E e : elements) printedElements.add(e.toString());
        String printedString = String.join(" :) ", printedElements);
        s.append(tab).append(printedString).append("\n");
        children.forEach(c -> s.append(c.toString(depth + 1)));
        return s.toString();
    }

    public boolean isLeaf() {
        return this.getChildren().size() == 0;
    }

    public boolean isFull() {
        return this.getElements().size() == this.capacity;
    }

    public ArrayList<E> getElements() {
        return this.elements;
    }

    public ArrayList<Node<E>> getChildren() {
        return this.children;
    }
}
