# B-Tree Implementation in Java

## Overview
This project implements a generic B-Tree data structure in Java, providing an efficient way to store and retrieve data that maintains sorted order. It includes a custom node implementation and supports generic data types that extend `Comparable`.

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


## Running Tests
The project uses JUnit for testing. To run tests:

1. Compile the test files (ensure JUnit jar is in the lib directory):
    javac -d bin -cp ".\lib\junit-platform-console-standalone-1.11.3.jar;.\bin" -sourcepath test test\*.java
2. Execute the tests:
    java -jar .\lib\junit-platform-console-standalone-1.11.3.jar --class-path .\bin --scan-classpath

## 2024 Updates
Added comprehensive test cases to cover new functionality and ensure robustness.
Revamped certain implementations to improve efficiency and maintainability.
Updated documentation to reflect the latest changes and usage instructions.
Contributions

# Contributions are welcome. Please fork the repository, make your changes, and submit a pull request.