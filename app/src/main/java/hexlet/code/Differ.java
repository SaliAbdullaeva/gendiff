package hexlet.code;

import java.nio.file.Path;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        // Чтение содержимого файлов
        String fileContent1 = ReadFile.readFile(Path.of(filePath1));
        String fileContent2 = ReadFile.readFile(Path.of(filePath2));

        // Получение расширения файлов для парсинга
        String extension1 = ReadFile.getFileExtension(Path.of(filePath1));
        String extension2 = ReadFile.getFileExtension(Path.of(filePath2));

        // Парсинг содержимого файлов в Map
        Map<String, Object> map1 = Parser.parseString(fileContent1, extension1);
        Map<String, Object> map2 = Parser.parseString(fileContent2, extension2);

        // Создание отсортированного набора ключей
        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());

        // Формирование результата в зависимости от формата
        return switch (format) {
            case "plain" -> formatPlain(allKeys, map1, map2);
            case "json" -> formatJson(allKeys, map1, map2);
            default -> formatStylish(allKeys, map1, map2); // формат по умолчанию
        };
    }

    private static String formatStylish(Set<String> allKeys, Map<String, Object> map1, Map<String, Object> map2) {
        StringBuilder result = new StringBuilder("{\n");

        for (String key : allKeys) {
            Object value1 = map1.get(key);
            Object value2 = map2.get(key);

            if (value1 == null && value2 != null) {
                result.append("  + ").append(key).append(": ").append(value2).append("\n");
            } else if (value1 != null && value2 == null) {
                result.append("  - ").append(key).append(": ").append(value1).append("\n");
            } else if (value1 != null && value2 != null && !value1.equals(value2)) {
                result.append("  - ").append(key).append(": ").append(value1).append("\n");
                result.append("  + ").append(key).append(": ").append(value2).append("\n");
            } else if (value1 != null && value2 != null && value1.equals(value2)) {
                result.append("    ").append(key).append(": ").append(value1).append("\n");
            }
        }

        result.append("}");
        return result.toString();
    }

    private static String formatPlain(Set<String> allKeys, Map<String, Object> map1, Map<String, Object> map2) {
        StringBuilder result = new StringBuilder();

        for (String key : allKeys) {
            Object value1 = map1.get(key);
            Object value2 = map2.get(key);

            if (value1 == null) {
                result.append("Property '").append(key).append("' was added with value: ").append(value2).append("\n");
            } else if (value2 == null) {
                result.append("Property '").append(key).append("' was removed").append("\n");
            } else if (!value1.equals(value2)) {
                result.append("Property '").append(key).append("' was updated. From ").append(value1).append(" to ").append(value2).append("\n");
            }
        }

        return result.toString().trim();
    }

    private static String formatJson(Set<String> allKeys, Map<String, Object> map1, Map<String, Object> map2) {
        StringBuilder result = new StringBuilder("{");

        for (String key : allKeys) {
            Object value1 = map1.get(key);
            Object value2 = map2.get(key);

            if (value1 == null) {
                result.append("\"").append(key).append("\": ").append(value2).append(", ");
            } else if (value2 == null) {
                result.append("\"").append(key).append("\": ").append(value1).append(", ");
            } else if (!value1.equals(value2)) {
                result.append("\"").append(key).append("\": ").append(value2).append(", ");
            } else {
                result.append("\"").append(key).append("\": ").append(value1).append(", ");
            }
        }

        // Удаляем последнюю запятую и пробел, если они есть
        if (result.length() > 1) {
            result.setLength(result.length() - 2);
        }

        result.append("}");
        return result.toString();
    }
}
