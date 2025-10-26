package com.mst;

import com.mst.algorithm.PrimAlgorithm;
import com.mst.algorithm.KruskalAlgorithm;
import com.mst.io.JSONReader;
import com.mst.io.JSONWriter;
import com.mst.model.Graph;
import com.mst.model.MSTResult;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String inputFile = "data/input.json";
        String outputFile = "data/output.json";

        JSONReader reader = new JSONReader();
        List<Graph> graphs = reader.readGraphsFromFile(inputFile);

        PrimAlgorithm prim = new PrimAlgorithm();
        KruskalAlgorithm kruskal = new KruskalAlgorithm();

        List<MSTResult> results = new ArrayList<>();

        for (Graph graph : graphs) {
            System.out.println("Processing graph " + graph.getId() +
                    " with " + graph.getVertexCount() + " vertices and " +
                    graph.getEdgeCount() + " edges");

            MSTResult result = new MSTResult();
            result.setGraphId(graph.getId());

            MSTResult.InputStats inputStats = new MSTResult.InputStats(
                    graph.getVertexCount(), graph.getEdgeCount());
            result.setInputStats(inputStats);

            result.setPrim(prim.findMST(graph));

            result.setKruskal(kruskal.findMST(graph));

            results.add(result);

            printResults(result);
        }

        JSONWriter writer = new JSONWriter();
        writer.writeResultsToFile(results, outputFile);

        System.out.println("Results written to: " + outputFile);
    }

    private static void printResults(MSTResult result) {
        System.out.println("\n=== Graph " + result.getGraphId() + " Results ===");
        System.out.println("Vertices: " + result.getInputStats().getVertices() +
                ", Edges: " + result.getInputStats().getEdges());

        System.out.println("\nPrim's Algorithm:");
        System.out.println("  Total Cost: " + result.getPrim().getTotalCost());
        System.out.println("  Operations: " + result.getPrim().getOperationsCount());
        System.out.println("  Time: " + String.format("%.2f", result.getPrim().getExecutionTimeMs()) + " ms");

        System.out.println("\nKruskal's Algorithm:");
        System.out.println("  Total Cost: " + result.getKruskal().getTotalCost());
        System.out.println("  Operations: " + result.getKruskal().getOperationsCount());
        System.out.println("  Time: " + String.format("%.2f", result.getKruskal().getExecutionTimeMs()) + " ms");

        if (result.getPrim().getTotalCost() == result.getKruskal().getTotalCost()) {
            System.out.println("\n✓ Costs match! MST is correct.");
        } else {
            System.out.println("\n✗ Costs don't match! There might be an error.");
        }
    }
}