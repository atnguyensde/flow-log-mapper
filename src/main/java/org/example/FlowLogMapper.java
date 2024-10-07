package org.example;

public interface FlowLogMapper {
    void execute(
            ProcessFlowLogStrategy processFlowLogStrategy,
            String tagLookupTablePath,
            String flowLogPath,
            String tagCountOutputPath,
            String networkRecordCountOutputPath
    );
}
