package com.mst.model;

import java.util.List;

public class MSTResult {
    private int graphId;
    private InputStats inputStats;
    private AlgorithmResult prim;
    private AlgorithmResult kruskal;

    public MSTResult() {}

    public int getGraphId() { return graphId; }
    public void setGraphId(int graphId) { this.graphId = graphId; }

    public InputStats getInputStats() { return inputStats; }
    public void setInputStats(InputStats inputStats) { this.inputStats = inputStats; }

    public AlgorithmResult getPrim() { return prim; }
    public void setPrim(AlgorithmResult prim) { this.prim = prim; }

    public AlgorithmResult getKruskal() { return kruskal; }
    public void setKruskal(AlgorithmResult kruskal) { this.kruskal = kruskal; }

    public static class InputStats {
        private int vertices;
        private int edges;

        public InputStats() {}

        public InputStats(int vertices, int edges) {
            this.vertices = vertices;
            this.edges = edges;
        }

        public int getVertices() { return vertices; }
        public void setVertices(int vertices) { this.vertices = vertices; }

        public int getEdges() { return edges; }
        public void setEdges(int edges) { this.edges = edges; }
    }

    public static class AlgorithmResult {
        private List<Edge> mstEdges;
        private int totalCost;
        private int operationsCount;
        private double executionTimeMs;

        public AlgorithmResult() {
            this.mstEdges = new ArrayList<>();
        }

        public List<Edge> getMstEdges() { return mstEdges; }
        public void setMstEdges(List<Edge> mstEdges) { this.mstEdges = mstEdges; }

        public int getTotalCost() { return totalCost; }
        public void setTotalCost(int totalCost) { this.totalCost = totalCost; }

        public int getOperationsCount() { return operationsCount; }
        public void setOperationsCount(int operationsCount) { this.operationsCount = operationsCount; }

        public double getExecutionTimeMs() { return executionTimeMs; }
        public void setExecutionTimeMs(double executionTimeMs) { this.executionTimeMs = executionTimeMs; }
    }
}