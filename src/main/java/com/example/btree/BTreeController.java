package com.example.btree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/btrees")
public class BTreeController {

    @Autowired
    private BTreeService service;

    @PostMapping("/{id}/add")
    public String addElement(@PathVariable int id, @RequestParam int element) {
        BTree<Integer> btree = service.getBTree(id);
        if (btree == null) return "BTree not found";
        btree.add(element);
        return "Element added"; 
    }


    @GetMapping("/{id}/contains/{value}")
    public ResponseEntity<Boolean> contains(@PathVariable int id, @PathVariable int value) {
        BTree<Integer> btree = service.getBTree(id);
        if (btree == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
        boolean found = btree.contains(value) != null;
        return ResponseEntity.ok(found);
    }

    @GetMapping("/{id}/structure")
    public ResponseEntity<String> getStructure(@PathVariable int id) {
        BTree<Integer> btree = service.getBTree(id);
        if (btree == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("BTree not found");
        }
        return ResponseEntity.ok(btree.toString());
    }

    @GetMapping
    public ResponseEntity<?> handleGetRequests() {
        Map<Integer, BTree<Integer>> allBTrees = service.getAllBTrees();
        if (allBTrees.isEmpty()) {
            return ResponseEntity.ok("No B-Trees available.");
        }
        return ResponseEntity.ok(allBTrees);
    }


    @GetMapping("/{id}/structure/paginated")
    public ResponseEntity<List<Integer>> getStructurePaginated(
            @PathVariable int id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        BTree<Integer> btree = service.getBTree(id);
        if (btree == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        List<Integer> elements = btree.getElements(); 
        int start = Math.min(page * size, elements.size());
        int end = Math.min(start + size, elements.size());
        return ResponseEntity.ok(elements.subList(start, end));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBTree(@PathVariable int id) {
        BTree<Integer> removed = service.removeBTree(id); 
        if (removed == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("BTree not found");
        }
        return ResponseEntity.ok("BTree deleted successfully");
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createBTree(@RequestParam int minDegree) {
        if (minDegree < 2) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
            errorResponse.put("error", "Minimum degree must be at least 2");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        int id = service.createBTree(minDegree);
        Map<String, Object> response = new HashMap<>();
        response.put("id", id);
        response.put("message", "BTree created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}/range")
    public ResponseEntity<List<Integer>> getRange(
            @PathVariable int id,
            @RequestParam int start,
            @RequestParam int end) {
        BTree<Integer> btree = service.getBTree(id);
        if (btree == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        List<Integer> range = btree.getRange(start, end);
        return ResponseEntity.ok(range);
    }


    
}
