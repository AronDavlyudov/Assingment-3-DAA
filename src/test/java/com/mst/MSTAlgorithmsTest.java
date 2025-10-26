package com.mst;

import com.mst.algorithm.PrimAlgorithm;
import com.mst.algorithm.KruskalAlgorithm;
import com.mst.model.Edge;
import com.mst.model.Graph;
import com.mst.model.MSTResult.AlgorithmResult;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

class MSTAlgorithmsTest {

    @Test
    void testSmallGraph() {
        Graph graph = new Graph(1,
                Arrays.asList("A", "B", "C", "D"),
                Arrays.asList(
                        new Edge("A", "B", 1),
                        new Edge("A", "C", 4),
                        new Edge("B", "C", 2),
                        new Edge("C", "D", 3),
                        new Edge("B", "D", 5)
                )
        );

        PrimAlgorithm prim = new PrimAlgorithm();
        KruskalAlgorithm kruskal = new KruskalAlgorithm();

        AlgorithmResult primResult = prim.findMST(graph);
        AlgorithmResult kruskalResult = kruskal.findMST(graph);

        assertEquals(primResult.getTotalCost(), kruskalResult.getTotalCost());

        assertEquals(graph.getVertexCount() - 1, primResult.getMstEdges().size());
        assertEquals(graph.getVertexCount() - 1, kruskalResult.getMstEdges().size());
e
        assertTrue(primResult.getExecutionTimeMs() >= 0);
        assertTrue(kruskalResult.getExecutionTimeMs() >= 0);

        assertTrue(primResult.getOperationsCount() >= 0);
        assertTrue(kruskalResult.getOperationsCount() >= 0);
    }

    @Test
    void testCorrectMSTCost() {
        Graph graph = new Graph(2,
                Arrays.asList("A", "B", "C", "D", "E"),
                Arrays.asList(
                        new Edge("A", "B", 4),
                        new Edge("A", "C", 3),
                        new Edge("B", "C", 2),
                        new Edge("B", "D", 5),
                        new Edge("C", "D", 7),
                        new Edge("C", "E", 8),
                        new Edge("D", "E", 6)
                )
        );

        PrimAlgorithm prim = new PrimAlgorithm();
        KruskalAlgorithm kruskal = new KruskalAlgorithm();

        AlgorithmResult primResult = prim.findMST(graph);
        AlgorithmResult kruskalResult = kruskal.findMST(graph);

        assertEquals(16, primResult.getTotalCost());
        assertEquals(16, kruskalResult.getTotalCost());
    }

    @Test
    void testSingleNodeGraph() {
        Graph graph = new Graph(3,
                Arrays.asList("A"),
                Arrays.asList()
        );

        PrimAlgorithm prim = new PrimAlgorithm();
        KruskalAlgorithm kruskal = new KruskalAlgorithm();

        AlgorithmResult primResult = prim.findMST(graph);
        AlgorithmResult kruskalResult = kruskal.findMST(graph);

        assertEquals(0, primResult.getTotalCost());
        assertEquals(0, kruskalResult.getTotalCost());
        assertEquals(0, primResult.getMstEdges().size());
        assertEquals(0, kruskalResult.getMstEdges().size());
    }
}