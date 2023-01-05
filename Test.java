public class Test {
    @SafeVarargs
    private static <T extends Comparable<T>> void addInThisOrder(BTree<T> theTree, T... items) {
        for (T item : items)
            theTree.add(item);
    }
    public static void main(String[] args) {
        BTree<Integer> integerBTree = new BTree<>(3);
        addInThisOrder(integerBTree, 1, 2, 3, 4, 5);
        System.out.println(integerBTree);
        integerBTree.add(6);
        System.out.println(integerBTree);
        integerBTree.add(7);
        integerBTree.add(8);
        integerBTree.add(9);
        System.out.println(integerBTree);
        NodeIndexPair<Integer> found = integerBTree.contains(8);
        System.out.printf("Element %d found at index %d of the following node:\n%s%n",
                8,
                found.index,
                found.nodeLocation);

    }
}
