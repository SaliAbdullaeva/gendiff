package hexlet.code.formatters;

import java.util.List;
import java.util.Map;


public final class Stylish implements StringFormatter {

    @Override
    public String format(List<Map<String, Object>> differDifferNodeList) {
        StringBuilder sb = new StringBuilder("{\n");
        final int indentation = 2;

        for (Map<String, Object> node : differDifferNodeList) {
            String condition = (String) node.get("condition");
            switch (condition) {
                case "ADDED" -> sb.append(" ".repeat(indentation))
                        .append("+ %s: %s%n".formatted(node.get("key"), node.get("value")));
                case "DELETED" -> sb.append(" ".repeat(indentation))
                        .append("- %s: %s%n".formatted(node.get("key"), node.get("value")));
                case "CHANGED" -> {
                    sb.append(" ".repeat(indentation))
                            .append("- %s: %s%n".formatted(node.get("key"), node.get("value1")));
                    sb.append(" ".repeat(indentation))
                            .append("+ %s: %s%n".formatted(node.get("key"), node.get("value2")));
                }
                case "UNCHANGED" -> sb.append(" ".repeat(indentation))
                        .append("  %s: %s%n".formatted(node.get("key"), node.get("value")));
                default -> throw new IllegalStateException("Unexpected value: " + condition);
            }
        }
        sb.append("}");

        return sb.toString();
    }
}
