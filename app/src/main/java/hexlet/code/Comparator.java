package hexlet.code;

import hexlet.code.model.Data;
import hexlet.code.model.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Comparator {

    public static ArrayList<Data> compare(Map<String, Object> file1, Map<String, Object> file2) {

        HashMap<String, Object> mergedContent = new HashMap<>(file1);
        mergedContent.putAll(file2);
        var entries = new TreeMap<>(mergedContent).entrySet();
        ArrayList<Data> result = new ArrayList<>();

        for (var entry : entries) {
            var key = entry.getKey();

            if (!file1.containsKey(key)) {
                result.add(new Data(key, Status.ADDED, file2.get(key)));

            } else if (!file2.containsKey(key)) {
                result.add(new Data(key, Status.REMOVED, file1.get(key)));

            } else if (Objects.equals(file1.get(key), file2.get(key))) {
                result.add(new Data(key, Status.UNCHANGED, file1.get(key)));

            } else {
                result.add(new Data(key, Status.UPDATED, file1.get(key), file2.get(key)));
            }
        }
        return result;
    }
}
