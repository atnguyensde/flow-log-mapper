package org.example.lookupmapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProtocolLookupTableMapper implements LookupTableMapper<String, String> {
    private static final int PROTOCOL_LOOKUP_LENGTH = 2;
    private static final String LOOKUP_TABLE_FIELD_DELIMITER = ",";

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
                String decimal = parts[0], keyword = null;
                if (parts.length == PROTOCOL_LOOKUP_LENGTH) {
                    keyword = parts[1];
                }
                protocolLookupMap.put(decimal, keyword);
            }
        }
        return protocolLookupMap;
    }
}
