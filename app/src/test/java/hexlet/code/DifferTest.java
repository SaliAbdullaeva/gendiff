package hexlet.code;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

public class DifferTest {

    private static String readFileFromResources(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get("src/test/resources/" + fileName)));
    }

    @Test
    public void testGenerateJsonWithDifferences() throws Exception {
        // Используем файлы из ресурсов
        String filePath1 = "testFile1.json";
        String filePath2 = "testFile2.json";

        // Ожидаемый результат
        String expectedJson = "{\n" +
                "  - key2: \"value2\"\n" +
                "  + key3: \"value3\"\n" +
                "    key1: \"value1\"\n" +
                "}";

        // Выполняем метод и проверяем результат
        String result = Differ.generate(readFileFromResources(filePath1), readFileFromResources(filePath2));
        assertEquals(expectedJson, result);
    }

    @Test
    public void testGenerateJsonNoDifferences() throws Exception {
        // Используем файлы из ресурсов
        String filePath1 = "testFile1.json";
        String filePath2 = "testFile2.json";

        // Ожидаемый результат
        String expectedJson = "{\n" +
                "    key1: \"value1\"\n" +
                "    key2: \"value2\"\n" +
                "}";

        // Выполняем метод и проверяем результат
        String result = Differ.generate(readFileFromResources(filePath1), readFileFromResources(filePath2));
        assertEquals(expectedJson, result);
    }

    @Test
    public void testGenerateJsonEmptyFiles() throws Exception {
        // Используем файлы из ресурсов
        String filePath1 = "emptyFile.json";
    }
}
