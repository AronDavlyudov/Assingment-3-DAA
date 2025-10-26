package com.mst.algorithm;

import com.mst.model.Edge;
import com.mst.model.Graph;
import com.mst.model.MSTResult.AlgorithmResult;
import java.util.*;

public class PrimAlgorithm {

    public AlgorithmResult findMST(Graph graph) {
        AlgorithmResult result = new AlgorithmResult();
        long startTime = System.nanoTime();
        int operations = 0;

        List<Edge> mstEdges = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));

        if (!graph.getNodes().isEmpty()) {
            String startNode = graph.getNodes().get(0);
            visited.add(startNode);
            operations++;

            // Add all edges from start node to priority queue
            for (Edge edge : graph.getEdges()) {
                operations++;
                if (edge.getFrom().equals(startNode) || edge.getTo().equals(startNode)) {
                    pq.offer(edge);
                    operations++;
                }
            }

            while (!pq.isEmpty() && visited.size() < graph.getNodes().size()) {
                operations++;
                Edge minEdge = pq.poll();
                operations++;

                String nextNode = null;
                if (visited.contains(minEdge.getFrom()) && !visited.contains(minEdge.getTo())) {
                    nextNode = minEdge.getTo();
                } else if (visited.contains(minEdge.getTo()) && !visited.contains(minEdge.getFrom())) {
                    nextNode = minEdge.getFrom();
                }

                if (nextNode != null) {
                    visited.add(nextNode);
                    mstEdges.add(minEdge);
                    operations += 2;

                    // Add edges from the new node
                    for (Edge edge : graph.getEdges()) {
                        operations++;
                        if ((edge.getFrom().equals(nextNode) && !visited.contains(edge.getTo())) ||
                                (edge.getTo().equals(nextNode) && !visited.contains(edge.getFrom()))) {
                            pq.offer(edge);
                            operations++;
                        }
                    }
                }
            }
        }

        long endTime = System.nanoTime();
        double executionTimeMs = (endTime - startTime) / 1_000_000.0;

        result.setMstEdges(mstEdges);
        result.setTotalCost(calculateTotalCost(mstEdges));
        result.setOperationsCount(operations);
        result.setExecutionTimeMs(executionTimeMs);

        return result;
    }

    private int calculateTotalCost(List<Edge> edges) {
        return edges.stream().mapToInt(Edge::getWeight).sum();
    }
}