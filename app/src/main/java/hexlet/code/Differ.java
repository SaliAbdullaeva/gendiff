package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        // Создаем ObjectMapper для парсинга JSON
        ObjectMapper objectMapper = new ObjectMapper();

        String file1 = ReadFile.readFile(filePath1);
        String file2 = ReadFile.readFile(filePath2);

        // Читаем и парсим JSON-файлы
        Map<String, Object> map1 = objectMapper.readValue(file1, new TypeReference<>() { });
        Map<String, Object> map2 = objectMapper.readValue(file2, new TypeReference<>() { });
        //Map<String, Object> map1 = objectMapper.readValue(file1, Map.class);
        //Map<String, Object> map2 = objectMapper.readValue(file2, Map.class);
        // Создаем отсортированные наборы ключей из обоих карт
        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());
        // Строка для хранения результата
        StringBuilder result = new StringBuilder("{\n");
        // Сравниваем ключи и формируем результат
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

}

