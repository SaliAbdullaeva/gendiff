package hexlet.code;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.TreeSet;

public final class DifferListComposer {

    public static List<Map<String, Object>> composeList(
            Map<String, Object> data1,
            Map<String, Object> data2
    ) {
        List<Map<String, Object>> differNodeList = new ArrayList<>();

        Set<String> keys = new TreeSet<>(data1.keySet());
        keys.addAll(data2.keySet());
        for (String key : keys) {
            differNodeList.add(makeDifferNode(key, data1, data2));
        }

        return differNodeList;
    }

    private static Map<String, Object> makeDifferNode(
            String key,
            Map<String, Object> data1,
            Map<String, Object> data2
    ) {
        Map<String, Object> differNode = new LinkedHashMap<>();
        Optional<Object> value1 = Optional.ofNullable(data1.get(key));
        Optional<Object> value2 = Optional.ofNullable(data2.get(key));

        differNode.put("key", key);
        if (!data1.containsKey(key)) {
            differNode.put("condition", "ADDED");
            differNode.put("value", value2.orElse(null));
        } else if (!data2.containsKey(key)) {
            differNode.put("condition", "DELETED");
            differNode.put("value", value1.orElse(null));
        } else if (value1.equals(value2)) {
            differNode.put("condition", "UNCHANGED");
            differNode.put("value", value1.orElse(null));
        } else {
            differNode.put("condition", "CHANGED");
            differNode.put("value1", value1.orElse(null));
            differNode.put("value2", value2.orElse(null));
        }
        return differNode;
    }
}
