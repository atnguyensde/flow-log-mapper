package org.example;

import java.io.IOException;

public class FlowLogMapperImpl implements FlowLogMapper {
    public void execute(
            ProcessFlowLogStrategy processFlowLogStrategy,
            String tagLookupTablePath,
            String flowLogPath,
            String tagCountOutputPath,
            String networkRecordCountOutputPath
    ) {
        try {
            processFlowLogStrategy.process(
                    tagLookupTablePath,
                    flowLogPath,
                    tagCountOutputPath,
                    networkRecordCountOutputPath
            );
        } catch (IOException e) {
            System.out.println("Oops. Something is wrong :O");
            e.printStackTrace();
        }
    }
}
