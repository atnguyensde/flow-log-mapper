package org.example;

import org.example.lookupmapper.LookupTableMapper;
import org.example.lookupmapper.ProtocolLookupTableMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ProtocolLookupMap extends HashMap<String, String> {
    private static ProtocolLookupMap instance;
    private static final String PROTOCOL_LOOKUP_FILE_PATH = "src/main/resources/protocol_lookup_table.csv";

    private ProtocolLookupMap() {
    }

    public static ProtocolLookupMap getInstance() throws IOException {
        if (Objects.isNull(instance)) {
            LookupTableMapper<String, String> protocolLookupMapper = new ProtocolLookupTableMapper();
            Map<String, String> map = protocolLookupMapper.map(PROTOCOL_LOOKUP_FILE_PATH);
            instance = new ProtocolLookupMap();
            instance.putAll(map);
        }
        return instance;
    }
}
