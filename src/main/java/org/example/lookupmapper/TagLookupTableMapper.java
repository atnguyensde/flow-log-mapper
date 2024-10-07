package org.example.lookupmapper;

import org.example.NetworkRecord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TagLookupTableMapper implements LookupTableMapper<NetworkRecord, String> {
    private static final int TAG_LOOKUP_LENGTH = 3;
    private static final String LOOKUP_TABLE_FIELD_DELIMITER = ",";

    @Override
    public Map<NetworkRecord, String> map(String filePath) throws IOException {
        Map<NetworkRecord, String> lookupMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(LOOKUP_TABLE_FIELD_DELIMITER);
                if (parts.length != TAG_LOOKUP_LENGTH) {
                    System.out.println("Invalid mapping of tag.");
                    continue;
                }
                String dstPort = parts[0];
                String protocol = parts[1];
                String tag = parts[2];
                lookupMap.put(new NetworkRecord(dstPort, protocol), tag);
            }
        }
        return lookupMap;
    }
}
