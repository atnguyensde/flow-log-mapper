package org.example.lookupmapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProtocolLookupTableMapper implements LookupTableMapper<String, String> {
    private static final int PROTOCOL_LOOKUP_LENGTH = 2;
    private static final String LOOKUP_TABLE_FIELD_DELIMITER = ",";
    private static final String RANGE_REGEX = "\\d+-\\d+";
    private static final String RANGE_DELIMITER = "-";

    @Override
    public Map<String, String> map(String filePath) throws IOException {
        Map<String, String> protocolLookupMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(LOOKUP_TABLE_FIELD_DELIMITER);
                if (parts.length == 0 || parts.length > PROTOCOL_LOOKUP_LENGTH) {
                    System.out.println("Invalid mapping of protocol.");
                    continue;
                }
                String decimal = parts[0];
                String keyword;
                if (decimal.matches(RANGE_REGEX)) {
                    String[] range = decimal.split(RANGE_DELIMITER);
                    int start = Integer.parseInt(range[0]);
                    int end = Integer.parseInt(range[1]);
                    for (int num = start; num <= end; num++) {
                        keyword = parts.length == PROTOCOL_LOOKUP_LENGTH ? parts[1] : String.valueOf(num);
                        protocolLookupMap.put(String.valueOf(num), keyword);
                    }
                } else {
                    keyword = parts.length == PROTOCOL_LOOKUP_LENGTH ? parts[1] : parts[0];
                    protocolLookupMap.put(decimal, keyword);
                }
            }
        }
        return protocolLookupMap;
    }
}
