package org.example.dataproviders;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonConverter {
    static ObjectMapper objectMapper;

    public static String convertFileToJson(String filePath) {
        try {
            objectMapper = new ObjectMapper();
            File file = new File(filePath);
            JsonNode jsonNode = objectMapper.readTree(file);
            return String.valueOf(jsonNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
