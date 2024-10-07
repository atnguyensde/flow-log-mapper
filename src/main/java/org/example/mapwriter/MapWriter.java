package org.example.mapwriter;

import java.io.IOException;
import java.util.Map;

public interface MapWriter<K,V> {
    void write(Map<K,V> map, String filePath) throws IOException;
}
