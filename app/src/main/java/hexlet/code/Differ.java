package hexlet.code;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws IOException {
        // Создаем ObjectMapper для парсинга JSON
        ObjectMapper objectMapper = new ObjectMapper();

        // Читаем и парсим JSON-файлы
        Map<String, Object> map1 = objectMapper.readValue(new File(filePath1), Map.class);
        Map<String, Object> map2 = objectMapper.readValue(new File(filePath2), Map.class);

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
            }
        }

        result.append("}");
        return result.toString();
    }
}