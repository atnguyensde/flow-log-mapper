package org.example.mapwriter;

import org.example.NetworkRecord;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class NetworkRecordMapWriter implements MapWriter<NetworkRecord, Integer> {

    private static final String NETWORK_RECORD_COUNT_HEADER = "Port,Protocol,Count\n";
    private static final String COMMA_FIELD_DELIMITER = ",";
    private static final String LINE_DELIMITER = "\n";

    @Override
    public void write(Map<NetworkRecord, Integer> map, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append(NETWORK_RECORD_COUNT_HEADER);
            for (Map.Entry<NetworkRecord, Integer> entry : map.entrySet()) {
                writer.append(entry.getKey().dstPort())
                        .append(COMMA_FIELD_DELIMITER)
                        .append(entry.getKey().protocol())
                        .append(COMMA_FIELD_DELIMITER)
                        .append(String.valueOf(entry.getValue()))
                        .append(LINE_DELIMITER);
            }
        }
    }
}
