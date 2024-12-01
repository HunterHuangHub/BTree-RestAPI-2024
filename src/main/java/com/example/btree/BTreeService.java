package com.example.btree;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class BTreeService {
    private final HashMap<Integer, BTree<Integer>> btrees = new HashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger();

    public int createBTree(int minDegree) {
        int id = idGenerator.incrementAndGet();
        btrees.put(id, new BTree<>(minDegree));
        return id;
    }

    public BTree<Integer> getBTree(int id) {
        return btrees.get(id);
    }

    public Map<Integer, BTree<Integer>> getAllBTrees() {
        return btrees; 
    }


    public BTree<Integer> removeBTree(int id) {
        return btrees.remove(id); 
    }
}
