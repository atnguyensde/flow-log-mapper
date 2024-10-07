package org.example.lookupmapper;

import java.io.IOException;
import java.util.Map;

public interface LookupTableMapper<K, V> {
    Map<K, V> map(String filePath) throws IOException;
}
