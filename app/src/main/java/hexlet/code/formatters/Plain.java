package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public final class Plain implements StringFormatter {

    @Override
    public String format(List<Map<String, Object>> differNodeList) {
        List<Map<String, Object>> filteredList = differNodeList.stream()
                .filter(node -> !node.get("condition").equals("UNCHANGED"))
                .toList();

        StringBuilder sb = new StringBuilder();

        for (Map<String, Object> node : filteredList) {
            String condition = (String) node.get("condition");
            switch (condition) {
                case "ADDED" -> {
                    String value = formatComplexValue(node.get("value"));
                    sb.append("Property '%s' was added with value: %s%n"
                            .formatted(node.get("key"), value));
                }
                case "DELETED" -> sb.append("Property '%s' was removed%n"
                        .formatted(node.get("key")));
                case "CHANGED" -> {
                    String value1 = formatComplexValue(node.get("value1"));
                    String value2 = formatComplexValue(node.get("value2"));
                    sb.append("Property '%s' was updated. From %s to %s%n"
                            .formatted(node.get("key"), value1, value2));
                }
                default -> throw new IllegalStateException("Unexpected value: " + condition);
            }
        }

        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    private static String formatComplexValue(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof List<?> || value instanceof Map<?, ?>) {
            return "[complex value]";
        }
        if (value instanceof String) {
            return String.format("'%s'", value);
        }
        return value.toString();
    }
}