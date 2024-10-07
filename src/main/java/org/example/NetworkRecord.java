package org.example;

import java.util.Objects;

public record NetworkRecord(String dstPort, String protocol) {

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NetworkRecord that = (NetworkRecord) obj;
        return Objects.equals(dstPort, that.dstPort) && Objects.equals(protocol, that.protocol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dstPort, protocol);
    }
}