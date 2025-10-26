package com.mst.io;

import com.mst.model.Edge;
import com.mst.model.Graph;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JSONReader {

    public List<Graph> readGraphsFromFile(String filePath) {
        List<Graph> graphs = new ArrayList<>();
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(filePath)) {
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            JsonArray graphsArray = jsonObject.getAsJsonArray("graphs");

            for (JsonElement graphElement : graphsArray) {
                JsonObject graphObject = graphElement.getAsJsonObject();

                int id = graphObject.get("id").getAsInt();
                JsonArray nodesArray = graphObject.getAsJsonArray("nodes");
                JsonArray edgesArray = graphObject.getAsJsonArray("edges");

                List<String> nodes = new ArrayList<>();
                for (JsonElement nodeElement : nodesArray) {
                    nodes.add(nodeElement.getAsString());
                }

                List<Edge> edges = new ArrayList<>();
                for (JsonElement edgeElement : edgesArray) {
                    JsonObject edgeObject = edgeElement.getAsJsonObject();
                    String from = edgeObject.get("from").getAsString();
                    String to = edgeObject.get("to").getAsString();
                    int weight = edgeObject.get("weight").getAsInt();
                    edges.add(new Edge(from, to, weight));
                }

                graphs.add(new Graph(id, nodes, edges));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return graphs;
    }
}