package org.example.mapwriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class TagMapWriter implements MapWriter<String, Integer> {

    private static final String TAG_COUNT_HEADER = "Tag,Count\n";
    private static final String COMMA_FIELD_DELIMITER = ",";
    private static final String LINE_DELIMITER = "\n";
    @Override
    public void write(Map<String, Integer> map, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append(TAG_COUNT_HEADER);

            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                writer.append(entry.getKey())
                        .append(COMMA_FIELD_DELIMITER)
                        .append(String.valueOf(entry.getValue()))
                        .append(LINE_DELIMITER);
            }
        }
    }
}
