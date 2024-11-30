import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BTreeTest {

    @SafeVarargs
    private static <T extends Comparable<T>> void addInThisOrder(BTree<T> theTree, T... items) {
        for (T item : items)
            theTree.add(item);
    }

    @Test
    public void testMultipleAdds() {
        BTree<Integer> integerBTree = new BTree<>(3);
        addInThisOrder(integerBTree, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        NodeIndexPair<Integer> found = integerBTree.contains(8);
        assertNotNull(found);
        assertEquals(8, (int) found.nodeLocation.getElements().get(found.index));
        System.out.println(integerBTree);
    }

    @Test
    public void testTreeStructureAfterAdditions() {
        BTree<Integer> integerBTree = new BTree<>(3);
        addInThisOrder(integerBTree, 1, 2, 3, 4, 5);
        assertEquals("Expected tree structure", integerBTree.toString());
    }
}

