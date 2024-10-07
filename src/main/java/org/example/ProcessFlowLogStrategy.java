package org.example;

import java.io.IOException;

public interface ProcessFlowLogStrategy {
    void process(String tagLookupTablePath, String flowLogPath, String tagCountOutputPath, String networkRecordCountOutputPath) throws IOException;
}
