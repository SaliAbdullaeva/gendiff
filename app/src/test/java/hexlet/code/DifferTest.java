package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONAssert;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

public final class DifferTest {
    private String expectedStylish;
    private String expectedPlain;
    private String expectedJson;

    @BeforeEach
    void setUp() throws Exception {
           // Чтение ожидаемых результатов из файлов перед каждым тестом
        Path validPathStylish = Differ.getPath(getPathToFixture("EXPECTED_STYLISH"));
        expectedStylish = Differ.readFile(validPathStylish);
        Path validPathPlain = Differ.getPath(getPathToFixture("EXPECTED_PLAIN"));
        expectedPlain = Differ.readFile(validPathPlain);
        Path validPathJson = Differ.getPath(getPathToFixture("EXPECTED_JSON"));
        expectedJson = Differ.readFile(validPathJson);
    }

    @Test
            // Тест для метода с двумя аргументами
    void testGenerateWithTwoArgs() throws Exception {
        String actualStylish = Differ.generate(
                getPathToFixture("fileNested1.json"),
                getPathToFixture("fileNested3.yml")
        );

        assertEquals(expectedStylish, actualStylish);
    }

    @Test
           // Тест для метода с тремя аргументами
    void testGenerateWithThreeArgs() throws Exception {
        String actualStylish = Differ.generate(
                getPathToFixture("fileNested1.json"),
                getPathToFixture("fileNested3.yml"),
                "stylish"
        );
        assertEquals(expectedStylish, actualStylish);
    }

    @Test
           // Тест для метода с JSON файлами в формате stylish
    void testGenerateWithJson() throws Exception {
        String actualStylish = Differ.generate(
                getPathToFixture("fileNested1.json"),
                getPathToFixture("fileNested2.json"),
                "stylish"
        );
        assertEquals(expectedStylish, actualStylish);
    }

    @Test
           //Тест для метода с JSON файлами в формате plain
    void testGenerateWithJsonPlain() throws Exception {
        String actualPlain = Differ.generate(
                getPathToFixture("fileNested1.json"),
                getPathToFixture("fileNested2.json"),
                "plain"
        );
        assertEquals(expectedPlain, actualPlain);
    }

    @Test
            // Тест для метода с JSON файлами в формате json
    void testGenerateWithJsonJson() throws Exception {
        String actualJson = Differ.generate(
                getPathToFixture("fileNested1.json"),
                getPathToFixture("fileNested2.json"),
                "json"
        );
        JSONAssert.assertEquals(expectedJson, actualJson, JSONCompareMode.STRICT);
    }

    @Test
           // Тест для метода с YAML файлами в формате stylish
    void testGenerateWithYaml() throws Exception {
        String actualStylish = Differ.generate(
                getPathToFixture("fileNested3.yml"),
                getPathToFixture("fileNested4.yml"),
                "stylish"
        );
        assertEquals(expectedStylish, actualStylish);
    }

    @Test
           // Тест для метода с YAML файлами в формате plain
    void testGenerateWithYamlPlain() throws Exception {
        String actualPlain = Differ.generate(
                getPathToFixture("fileNested3.yml"),
                getPathToFixture("fileNested4.yml"),
                "plain"
        );
        assertEquals(expectedPlain, actualPlain);
    }

    @Test
           // Тест для метода с YAML файлами в формате json
    void testGenerateWithYamlJson() throws Exception {
        String actualJson = Differ.generate(
                getPathToFixture("fileNested3.yml"),
                getPathToFixture("fileNested4.yml"),
                "json"
        );
        JSONAssert.assertEquals(expectedJson, actualJson, JSONCompareMode.STRICT);
    }

      // Вспомогательный метод для чтения файлов с ожидаемыми результатами
    private String readFixture(String fileName) throws Exception {
        Path path = Differ.getPath(getPathToFixture(fileName));
        return Differ.readFile(path);
    }

      // Вспомогательный метод для получения пути к тестовым файлам
    public String getPathToFixture(String file) {
        return "./src/test/resources/" + file;
    }
}