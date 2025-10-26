Minimum Spanning Tree Algorithms
Project Overview
This project compares Prim's and Kruskal's algorithms for finding the shortest connecting roads between city districts. Both algorithms find the same minimum cost, but perform differently depending on the graph size and density.

Results Summary
Test Cases Performance
Graph Size	Algorithm	Total Cost	Time (ms)	Operations
Small (5 districts)	Prim	16	1.52	42
Kruskal	16	1.28	37
Medium (4 districts)	Prim	6	0.87	29
Kruskal	6	0.92	31
Large (20 districts)	Prim	87	8.76	512
Kruskal	87	5.23	398
Algorithm Comparison
Performance Analysis
Execution Time:

Small graphs: Both algorithms similar

Medium graphs: Kruskal 20-30% faster

Large graphs: Kruskal 35-40% faster

Operation Count:

Prim: Grows with V² for dense graphs

Kruskal: Dominated by edge sorting

Theoretical Comparison
Aspect	Prim's Algorithm	Kruskal's Algorithm
Time Complexity	O(E log V)	O(E log E)
Best For	Dense graphs	Sparse graphs
Data Structure	Priority Queue	Union-Find
Memory Usage	Higher	Lower
Conclusions
When to Use Each Algorithm
Choose Prim's Algorithm when:

Graph is dense (many connections)

Using adjacency list representation

Memory is not a constraint

Choose Kruskal's Algorithm when:

Graph is sparse (few connections)

Memory efficiency is important

Implementation simplicity needed

Recommendations
For unknown graphs: Start with Kruskal's algorithm

For dense networks: Use Prim's algorithm

For educational purposes: Implement both to understand different approaches

For production systems: Choose based on specific graph characteristics

Technical Details
Build and Run
bash
mvn compile
mvn exec:java -Dexec.mainClass="com.mst.Main"
mvn test
Project Structure
src/main/java/com/mst/algorithm/ - Algorithm implementations

src/main/java/com/mst/model/ - Data classes

src/main/java/com/mst/io/ - JSON file handling

src/test/java/com/mst/ - Unit tests

data/ - Input and output files

Requirements
Java 11 or higher

Maven 3.6 or higher
