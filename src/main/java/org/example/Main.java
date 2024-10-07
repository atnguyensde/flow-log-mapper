package org.example;

public class Main {
    public static void main(String[] args) {
        String lookupTablePath = args[0];
        String flowLogPath = args[1];
        String tagCountOutputPath = args[2];
        String networkRecordCountOutputPath = args[3];

        FlowLogMapper flowLogMapper = new FlowLogMapperImpl();
        ProcessFlowLogStrategy processFlowLogStrategy = new ProcessFlowLogVersion2();
        flowLogMapper.execute(processFlowLogStrategy, lookupTablePath, flowLogPath, tagCountOutputPath, networkRecordCountOutputPath);
    }
}