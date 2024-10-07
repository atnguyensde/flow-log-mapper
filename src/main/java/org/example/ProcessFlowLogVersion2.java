package org.example;

import org.example.lookupmapper.TagLookupTableMapper;
import org.example.mapwriter.MapWriter;
import org.example.mapwriter.NetworkRecordMapWriter;
import org.example.mapwriter.TagMapWriter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ProcessFlowLogVersion2 implements ProcessFlowLogStrategy {
    private static final int FLOW_LOG_LENGTH = 14;
    private static final int DSTPORT_INDEX = 6;
    private static final int PROTOCOL_INDEX = 7;
    private static final String FLOW_LOG_FIELD_DELIMITER = " ";

    MapWriter<String, Integer> tagMapWriter = new TagMapWriter();
    MapWriter<NetworkRecord, Integer> networkRecordMapWriter = new NetworkRecordMapWriter();

    @Override
    public void process(String tagLookupTablePath,
                        String flowLogPath,
                        String tagCountOutputPath,
                        String networkRecordCountOutputPath) throws IOException {
        Map<NetworkRecord, String> tagLookUpMap = new TagLookupTableMapper().map(tagLookupTablePath);
        Map<String, Integer> tagCountOutputMap = new HashMap<>();
        Map<NetworkRecord, Integer> networkRecordCountOutputMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(flowLogPath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(FLOW_LOG_FIELD_DELIMITER);
                if (parts.length != FLOW_LOG_LENGTH) {
                    System.out.println("Invalid flow log.");
                    continue;
                }
                String dstPort = parts[DSTPORT_INDEX];
                String protocol = parts[PROTOCOL_INDEX];
                String protocolKeyword = ProtocolLookupMap.getInstance().get(protocol);
                NetworkRecord record = new NetworkRecord(dstPort, protocolKeyword);

                countTag(tagLookUpMap, tagCountOutputMap, record);
                countNetworkRecord(networkRecordCountOutputMap, record);
            }
        }

        tagMapWriter.write(tagCountOutputMap, tagCountOutputPath);
        networkRecordMapWriter.write(networkRecordCountOutputMap, networkRecordCountOutputPath);
    }

    private void countTag(Map<NetworkRecord, String> tagLookUpMap, Map<String, Integer> tagCountOutputMap, NetworkRecord record) {
        String tag = tagLookUpMap.get(record);
        if (!Objects.isNull(tag)) {
            tagCountOutputMap.put(tag, tagCountOutputMap.getOrDefault(tag, 0) + 1);
        }
    }

    private void countNetworkRecord(Map<NetworkRecord, Integer> networkRecordCountOutputMap, NetworkRecord record) {
        networkRecordCountOutputMap.put(record, networkRecordCountOutputMap.getOrDefault(record, 0) + 1);
    }
}
