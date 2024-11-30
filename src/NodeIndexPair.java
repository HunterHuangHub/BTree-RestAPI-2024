public class NodeIndexPair<E> {
    public final Node<E> nodeLocation;
    public final int index;
    public NodeIndexPair(Node<E> n, int i) {
        this.nodeLocation = n;
        this.index = i;
    }
}
