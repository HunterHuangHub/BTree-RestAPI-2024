package com.example.btree;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpMethod;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BtreeAppApplicationTests {

	@Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
    }

	@Test
	void testCreateBTree() {
		ResponseEntity<Map> response = restTemplate.postForEntity("/btrees?minDegree=3", null, Map.class);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getBody().get("id"));
	}

	@Test
	void testCreateBTreeInvalidDegree() {
		ResponseEntity<Map> response = restTemplate.postForEntity("/btrees?minDegree=1", null, Map.class);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertTrue(response.getBody().get("error").toString().contains("Minimum degree must be at least 2"));
	}

	@Test
	void testAddElement() {
		int id = createTestBTree(3); 
		ResponseEntity<String> response = restTemplate.postForEntity("/btrees/" + id + "/add?element=10", null, String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Element added", response.getBody());
	}

	@Test
	void testAddElementToNonExistentBTree() {
		ResponseEntity<String> response = restTemplate.postForEntity("/btrees/999/add?element=10", null, String.class);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("BTree not found", response.getBody());
	}

	@Test
	void testContainsElement() {
		int id = createTestBTree(3);
		restTemplate.postForEntity("/btrees/" + id + "/add?element=15", null, String.class);
		ResponseEntity<Boolean> response = restTemplate.getForEntity("/btrees/" + id + "/contains/15", Boolean.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.getBody());
	}

	@Test
	void testContainsElementInNonExistentBTree() {
		ResponseEntity<Boolean> response = restTemplate.getForEntity("/btrees/999/contains/10", Boolean.class);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	void testGetStructure() {
		int id = createTestBTree(3);
		restTemplate.postForEntity("/btrees/" + id + "/add?element=20", null, String.class);
		ResponseEntity<String> response = restTemplate.getForEntity("/btrees/" + id + "/structure", String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.getBody().contains("20")); // Verify tree structure
	}

	@Test
	void testGetStructureOfNonExistentBTree() {
		ResponseEntity<String> response = restTemplate.getForEntity("/btrees/999/structure", String.class);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	void testDeleteBTree() {
		int id = createTestBTree(3);
		restTemplate.delete("/btrees/" + id);
		ResponseEntity<String> response = restTemplate.getForEntity("/btrees/" + id + "/structure", String.class);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	void testDeleteNonExistentBTree() {
		ResponseEntity<String> response = restTemplate.exchange("/btrees/999", HttpMethod.DELETE, null, String.class);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	void testGetStructurePaginated() {
		int id = createTestBTree(3);
		restTemplate.postForEntity("/btrees/" + id + "/add?element=10", null, String.class);
		restTemplate.postForEntity("/btrees/" + id + "/add?element=20", null, String.class);
		ResponseEntity<List> response = restTemplate.getForEntity("/btrees/" + id + "/structure/paginated?page=0&size=1", List.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, response.getBody().size());
	}

	private int createTestBTree(int minDegree) {
		ResponseEntity<Map> response = restTemplate.postForEntity("/btrees?minDegree=" + minDegree, null, Map.class);
		return (int) response.getBody().get("id");
	}


}
