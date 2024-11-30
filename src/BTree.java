public class BTree<E extends Comparable<E>> implements AbstractBTree<E> {

    //Rules for a B tree:
    //Max num values for one node = 2(min) - 1
    //Every leaf at the same depth!
    //A node with x values has x + 1 children
    //Non-root nodes must have at least t-1 values…


    private final Node<E> root;
    private final int min;

    public BTree(int minimumDegree) {
        this.root = new Node<>(minimumDegree);
        this.min = minimumDegree;
    }
    @Override
    public NodeIndexPair<E> contains(E element) {
        return contains(root, element);
    }

    public NodeIndexPair<E> contains(Node<E> node, E element) {
        int index = 0;
        while (index < node.getElements().size() && (node.getElements().get(index)).compareTo(element) <= -1) {
            index ++;
        }
        if (index < node.getElements().size() && (node.getElements().get(index)).compareTo(element) == 0) {
            return new NodeIndexPair<>(node, index);
        } if (node.isLeaf()) {
            return null;
        }
        return contains(node.getChildren().get(index), element);
    }
    @Override
    public void add(E element) {
        add(this.root, element);
    }

    public void add(Node<E> node, E element) {
        if (!node.isFull() && node.isLeaf()) {
            add(node, element, 0);
        } else {
            if (node.isFull()) {
                splitRoot(node);
            }
            int j = 0;
            while (j < node.getElements().size()  && element.compareTo(node.getElements().get(j)) > 0) {
                j++;
            } if (j < node.getElements().size() && element.compareTo(node.getElements().get(j)) == 0) {
                return;
            } if (node.getChildren().get(j).isFull()) {
                splitChild(node, node.getChildren().get(j), j);
            } if (j < node.getElements().size() && node.getElements().get(j).compareTo(element) < 0) {
                j++;
            }
            add(node.getChildren().get(j), element);
        }
    }

    public void add(Node<E> node, E element, int i) { //insert non-full
        if (i < node.getElements().size() && node.getElements().get(i).compareTo(element) <= -1) {
            i = i + 1;
            add(node, element, i);
            return;
        } if (i < node.getElements().size() && element.compareTo(node.getElements().get(i)) == 0) {
            return;
        }
        node.getElements().add(i, element);
    }

    public void splitRoot(Node<E> root) {
        Node<E> left = new Node<>(min);
        Node<E> right = new Node<>(min);
        int i = 0;
        splitLeft(root, left, 0);
        int j = 0;
        while (j < min - 1) {
            right.getElements().add(j, root.getElements().remove(1));
            if (!root.isLeaf()) {
                right.getChildren().add(j, root.getChildren().remove(0));
            }
            j++;
        } if (!root.isLeaf()) {
            right.getChildren().add(i, root.getChildren().remove(0));
        }
        root.getChildren().add(0, left);
        root.getChildren().add(1, right);
    }

    public void splitChild(Node<E> node, Node<E> childNode, int index) {
        E median = childNode.getElements().remove(min - 1);
        Node<E> left = new Node<> (min);
        Node<E> right = new Node<> (min);
        node.getElements().add(index, median);
        splitLeft(childNode, left, 0);
        splitRight(childNode, right, 0);
        node.getChildren().add(index, left);
        node.getChildren().add(index + 1, right);
        node.getChildren().remove(childNode);
    }

    private Node<E> splitLeft(Node<E> node, Node<E> left, int i) { //0
        if (i < min - 1) {
            left.getElements().add(i, node.getElements().remove(0));
            if (!node.isLeaf()) {
                left.getChildren().add(i, node.getChildren().remove(0));
            }
            splitLeft(node, left, i + 1);
        }
        if (!node.isLeaf()) {
            left.getChildren().add(i, node.getChildren().remove(0));
        }
        return left;
    }

    private Node<E> splitRight(Node<E> node, Node<E> right, int i) { //0
        if (i < min - 1) {
            right.getElements().add(i, node.getElements().remove(0));
            if (!node.isLeaf()) {
                right.getChildren().add(i, node.getChildren().remove(0));
            }
            splitRight(node, right, i + 1);
        }
        if (!node.isLeaf()) {
            right.getChildren().add(i, node.getChildren().remove(0));
        }
        return right;
    }

    @Override
    public String toString() {
        return root.toString();
    }

}
