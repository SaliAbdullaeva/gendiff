package hexlet.code;

import java.nio.file.Path;

import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONAssert;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public final class DifferTest {
    private String expectedStylish;
    private String expectedPlain;
    private String expectedJson;

    @BeforeEach
    void setUp() throws Exception {
        Path validPathStylish = Differ.getPath(getPathToFixture("EXPECTED_STYLISH"));
        expectedStylish = Differ.readFile(validPathStylish);
        Path validPathPlain = Differ.getPath(getPathToFixture("EXPECTED_PLAIN"));
        expectedPlain = Differ.readFile(validPathPlain);
        Path validPathJson = Differ.getPath(getPathToFixture("EXPECTED_JSON"));
        expectedJson = Differ.readFile(validPathJson);
    }

    @Test
    void testGenerateWithTwoArgs() throws Exception {
        String actualStylish = Differ.generate(
                getPathToFixture("fileNested1.json"),
                getPathToFixture("fileNested4.yml")
        );

        assertEquals(expectedStylish, actualStylish);
    }

    @Test
    void testGenerateWithThreeArgs() throws Exception {
        String actualStylish = Differ.generate(
                getPathToFixture("fileNested1.json"),
                getPathToFixture("fileNested4.yml"),
                "stylish"
        );
        assertEquals(expectedStylish, actualStylish);
    }

    //проверка generate с файлами json
    @Test
    void testGenerateWithJson() throws Exception {
        String actualStylish = Differ.generate(
                getPathToFixture("fileNested1.json"),
                getPathToFixture("fileNested2.json"),
                "stylish"
        );
        assertEquals(expectedStylish, actualStylish);
    }

    @Test
    void testGenerateWithJsonPlain() throws Exception {
        String actualPlain = Differ.generate(
                getPathToFixture("fileNested1.json"),
                getPathToFixture("fileNested2.json"),
                "plain"
        );
        assertEquals(expectedPlain, actualPlain);
    }

    @Test
    void testGenerateWithJsonJson() throws Exception {
        String actualJson = Differ.generate(
                getPathToFixture("fileNested1.json"),
                getPathToFixture("fileNested2.json"),
                "json"
        );
        JSONAssert.assertEquals(expectedJson, actualJson, JSONCompareMode.STRICT);
    }

    //проверка generate с файлами YAML (работает корректно)
    @Test
    void testGenerateWithYaml() throws Exception {
        String actualStylish = Differ.generate(
                getPathToFixture("fileNested3.yml"),
                getPathToFixture("fileNested4.yml"),
                "stylish"
        );
        assertEquals(expectedStylish, actualStylish);
    }

    @Test
    void testGenerateWithYamlPlain() throws Exception {
        String actualPlain = Differ.generate(
                getPathToFixture("fileNested3.yml"),
                getPathToFixture("fileNested4.yml"),
                "plain"
        );
        assertEquals(expectedPlain, actualPlain);
    }

    @Test
    void testGenerateWithYamlJson() throws Exception {
        String actualJson = Differ.generate(
                getPathToFixture("fileNested3.yml"),
                getPathToFixture("fileNested4.yml"),
                "json"
        );
        JSONAssert.assertEquals(expectedJson, actualJson, JSONCompareMode.STRICT);
    }

    public String getPathToFixture(String file) {
        return "./src/test/resources/" + file;
    }
}
