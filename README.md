# Flow Log Mapper

A Java application that parses flow log data, maps each log entry to a tag based on a lookup table defined in a CSV file, and generates statistics on the number of matches for each tag and port/protocol combination.

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Running the Application](#running-the-application)
- [Notes](#notes)

## Features

- Parses flow log entries from a log file.
- Maps each entry to a tag based on a predefined CSV lookup table.
- Generates a summary of counts for each tag and port/protocol combination.
- Processes log entries in a memory-efficient manner by handling each line immediately.

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/atnguyensde/flow-log-mapper.git
   ```
2. Navigate to the project directory:
   ```bash
   cd flow-log-mapper
   ```
3. (If applicable) Build the project using Maven:
   ```bash
   mvn clean package
   ```

## Usage

Ensure you have the following files ready:

1. **Flow Log File**: A text file containing flow log entries in the specified format.
2. **Lookup Table CSV File**: A CSV file containing the mapping of `dstport`, `protocol`, and `tag`.

### Example Flow Log Entry
```
2 123456789012 eni-0a1b2c3d 10.0.1.201 198.51.100.2 443 49153 6 25 20000 1620140761 1620140821 ACCEPT OK
```

### Example Lookup Table (lookup.csv)
```
dstport,protocol,tag
25,tcp,sv_P1
68,udp,sv_P2
23,tcp,sv_P1
31,udp,SV_P3
443,tcp,sv_P2
...
```

## Running the Application

To run the application, use the following command:

```bash
java -jar target/flow-log-mapper-1.0-SNAPSHOT.jar <path_to_lookup_table_csv> <path_to_flow_log_file> <path_to_tag_count_output_file> <path_to_port_protocol_count_output_file>
```

### Example
```bash
java -jar target/flow-log-mapper-1.0-SNAPSHOT.jar src/main/resources/input/tag_lookup_table.csv src/main/resources/input/flow_log.txt src/main/resources/output/tag_count.csv src/main/resources/output/network_record_count.csv
```

## Notes

- Project uses only Java Core.
- Flow log version 2 is supported, with log format as specified in https://docs.aws.amazon.com/vpc/latest/userguide/flow-log-records.html .
- Manual testing is done with sample log file and lookup table.
