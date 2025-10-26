package com.mst.io;

import com.mst.model.MSTResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.util.List;

public class JSONWriter {

    public void writeResultsToFile(List<MSTResult> results, String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(filePath)) {
            JsonResultWrapper wrapper = new JsonResultWrapper();
            wrapper.setResults(results);
            gson.toJson(wrapper, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class JsonResultWrapper {
        private List<MSTResult> results;

        public List<MSTResult> getResults() { return results; }
        public void setResults(List<MSTResult> results) { this.results = results; }
    }
}