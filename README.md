# B-Tree Implementation in Java

## Overview
This project started as a standalone implementation of a generic B-Tree data structure in Java, designed for efficient storage and retrieval of sorted data. The B-Tree supports generic data types that extend `Comparable` and includes features like dynamic node splitting and searching.

In 2024, the project was expanded into a Spring Boot REST API, enabling users to interact with the B-Tree via HTTP endpoints. The API allows operations such as creating B-Trees, adding elements, checking for element existence, and retrieving the tree structure in a JSON format. This back-end development extension demonstrates the integration of data structures with modern web service frameworks.

## Components
- **AbstractBTree**: A public interface defining the basic operations of a B-Tree.
- **BTree**: A concrete implementation of the `AbstractBTree`, providing mechanisms for adding elements, searching for elements, and splitting nodes when necessary.
- **Node**: Represents a node in the B-Tree, encapsulating the logic for node capacity, children, and operations like checking if the node is a leaf or is full.
- **NodeIndexPair**: A utility class that pairs a node with an index, typically used for search operations to return both the node and the position of an element within that node.

## Key Features
- **Generic Type Support**: Works with any data type that implements the `Comparable` interface.
- **Efficient Searching and Insertion**: Ensures that data remains in sorted order, optimizing search and insertion operations.
- **Dynamic Node Splitting**: Automatically handles node splitting during insertions to maintain the B-Tree properties.

## Setup and Compilation
To set up and compile the project:

1. Ensure Java is installed on your system.
2. Download or clone the repository to your local machine.
3. Navigate to the project directory and compile the source files:
```bash
   javac -d bin -cp ".\lib\junit-platform-console-standalone-1.11.3.jar" -sourcepath src src\*.java
```

## Running Tests
The project uses JUnit for testing. To run tests:

1. Compile the test files (ensure JUnit jar is in the lib directory):
    javac -d bin -cp ".\lib\junit-platform-console-standalone-1.11.3.jar;.\bin" -sourcepath test test\*.java
2. Execute the tests:
    java -jar .\lib\junit-platform-console-standalone-1.11.3.jar --class-path .\bin --scan-classpath

## 2024 Updates
- Implemented a server-side REST API using Spring Boot to enable interaction with the B-Tree data structure via HTTP.
    1. GET endpoints to retrieve B-Tree structure and check for element existence.
    2. POST endpoints to create new B-Trees and add elements.
- Added new test cases for the REST API functionality, now located in `src/test/java/com/example/btree`.
- Legacy tests for the standalone B-Tree implementation remain in the original `Test` folder for reference.
- Revamped documentation to reflect the new API endpoints, usage instructions, and project structure.
- Improved maintainability by reorganizing the codebase to align with Spring Boot's standard directory structure.

# Note on Test Folder Structure:
Due to the updated REST API implementation with Spring Boot, the original Test folder still contains the legacy tests designed for the initial B-Tree implementation. However, the new tests for the Spring Boot REST API are now located in src/main/java/com/example/btree, alongside the REST API and B-Tree implementation. This change reflects the transition from a standalone B-Tree implementation to a full-fledged API-based structure.

# Contributions are welcome. Please fork the repository, make your changes, and submit a pull request.