package com.example.AeroSpikeUploader.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

public class JSONToCSV {
    public static void main(String[] args) throws JSONException {
        String jsonData = "{\"computed_at\":\"2024-04-10T06:35:46.885465+00:00\",\"headers\":[\"$event\",\"dpYMD\",\"$distinct_id\"],\"date_range\":{\"from_date\":\"2024-03-11T00:00:00+05:30\",\"to_date\":\"2024-04-10T12:05:45.844552+05:30\"},\"meta\":{\"min_sampling_factor\":1,\"is_segmentation_limit_hit\":true,\"report_sections\":{\"group\":[{\"bookmark\":{\"dataset\":\"$mixpanel\",\"value\":\"dpYMD\",\"resourceType\":\"events\",\"search\":\"\",\"joinPropertyType\":\"string\",\"propertyType\":\"string\",\"customGroups\":[{\"filterOperator\":\"equals\",\"filterValue\":[\"20240304\"],\"segmentLabel\":\"20240304\"}]}},{\"bookmark\":{\"dataset\":\"$mixpanel\",\"value\":\"$distinct_id\",\"resourceType\":\"events\",\"search\":\"\",\"propertyType\":\"string\"}}],\"show\":[{\"metric_key\":\"productView - Unique\"}]}},\"series\":{\"productView - Unique\":{\"$overall\":{\"all\":37417},\"$remaining_values\":{\"$overall\":{\"all\":37417},\"c068cc1c-b08c-40b9-93c9-94214a666224\":{\"all\":1},\"740adc72-1ea6-42e7-b24a-078259454553\":{\"all\":1},\"8ac3cb14-5af5-456e-8f3d-5cd544b765e0\":{\"all\":1},\"d6eee1dd-a010-4fea-b24f-6f016e9c3fec\":{\"all\":1},\"5999eba3-c612-45e9-89e0-de6cb0adf21b\":{\"all\":1},\"a286c86e-2114-446d-99b2-678922db60a3\":{\"all\":1}}}}}";

        JSONObject jsonObject = new JSONObject(jsonData);

        // Extract headers
        JSONArray headersArray = jsonObject.getJSONArray("headers");
        StringBuilder headers = new StringBuilder();
        for (int i = 0; i < headersArray.length(); i++) {
            headers.append(headersArray.getString(i)).append(",");
        }
        headers.deleteCharAt(headers.length() - 1); // Remove trailing comma

        // Extract values
        JSONArray valuesArray = jsonObject.getJSONObject("series")
                .getJSONObject("productView - Unique")
                .getJSONObject("$remaining_values")
                .names();

        StringBuilder values = new StringBuilder();
        for (int i = 0; i < valuesArray.length(); i++) {
            String key = valuesArray.getString(i);
            values.append(",,").append(key).append("\n");
        }

        // Write to file
        try (FileWriter fileWriter = new FileWriter("output.csv")) {
            fileWriter.write(headers.toString() + "\n");
            fileWriter.write(values.toString()); // Remove leading comma and space
            System.out.println("Output written to output.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

