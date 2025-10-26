package com.mst.algorithm;

import com.mst.model.Edge;
import com.mst.model.Graph;
import com.mst.model.MSTResult.AlgorithmResult;
import java.util.*;

public class KruskalAlgorithm {

    public AlgorithmResult findMST(Graph graph) {
        AlgorithmResult result = new AlgorithmResult();
        long startTime = System.nanoTime();
        int operations = 0;

        List<Edge> mstEdges = new ArrayList<>();
        List<Edge> sortedEdges = new ArrayList<>(graph.getEdges());

        sortedEdges.sort(Comparator.comparingInt(Edge::getWeight));
        operations += sortedEdges.size() * (int) Math.log(sortedEdges.size()); // Approximate sort operations

        UnionFind uf = new UnionFind(graph.getNodes());
        operations += graph.getNodes().size(); // UnionFind initialization

        for (Edge edge : sortedEdges) {
            operations++;
            if (mstEdges.size() == graph.getNodes().size() - 1) {
                break;
            }

            String fromRoot = uf.find(edge.getFrom());
            String toRoot = uf.find(edge.getTo());
            operations += 2;

            if (!fromRoot.equals(toRoot)) {
                mstEdges.add(edge);
                uf.union(edge.getFrom(), edge.getTo());
                operations += 2;
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

    private static class UnionFind {
        private Map<String, String> parent;
        private Map<String, Integer> rank;

        public UnionFind(List<String> nodes) {
            parent = new HashMap<>();
            rank = new HashMap<>();
            for (String node : nodes) {
                parent.put(node, node);
                rank.put(node, 0);
            }
        }

        public String find(String node) {
            if (!parent.get(node).equals(node)) {
                parent.put(node, find(parent.get(node)));
            }
            return parent.get(node);
        }

        public void union(String node1, String node2) {
            String root1 = find(node1);
            String root2 = find(node2);

            if (!root1.equals(root2)) {
                if (rank.get(root1) < rank.get(root2)) {
                    parent.put(root1, root2);
                } else if (rank.get(root1) > rank.get(root2)) {
                    parent.put(root2, root1);
                } else {
                    parent.put(root2, root1);
                    rank.put(root1, rank.get(root1) + 1);
                }
            }
        }
    }
}