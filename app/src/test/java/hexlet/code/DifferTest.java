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
        Path validPathStylish = Reader.getPath(getPathToFixture("EXPECTED_STYLISH"));
        expectedStylish = Reader.readFile(validPathStylish);
        Path validPathPlain = Reader.getPath(getPathToFixture("EXPECTED_PLAIN"));
        expectedPlain = Reader.readFile(validPathPlain);
        Path validPathJson = Reader.getPath(getPathToFixture("EXPECTED_JSON"));
        expectedJson = Reader.readFile(validPathJson);
    }

    @Test
    @DisplayName("'generate' method using two arguments works correctly")
    void testGenerateWithTwoArgs() throws Exception {
        String actualStylish = Differ.generate(
                getPathToFixture("fileNested1.json"),
                getPathToFixture("fileNested3.yml")
        );

        assertEquals(expectedStylish, actualStylish);
    }


    @Test
    @DisplayName("'generate' method using three arguments works correctly")
    void testGenerateWithThreeArgs() throws Exception {
        String actualStylish = Differ.generate(
                getPathToFixture("fileNested1.json"),
                getPathToFixture("fileNested3.yml"),
                "stylish"
        );
        assertEquals(expectedStylish, actualStylish);
    }

    @Test
    @DisplayName("'generate' method with JSON files works correctly")
    void testGenerateWithJson() throws Exception {
        String actualStylish = Differ.generate(
                getPathToFixture("fileNested1.json"),
                getPathToFixture("fileNested2.json"),
                "stylish"
        );
        assertEquals(expectedStylish, actualStylish);
        String actualPlain = Differ.generate(
                getPathToFixture("fileNested1.json"),
                getPathToFixture("fileNested2.json"),
                "plain"
        );
        assertEquals(expectedPlain, actualPlain);
        String actualJson = Differ.generate(
                getPathToFixture("fileNested1.json"),
                getPathToFixture("fileNested2.json"),
                "json"
        );
        JSONAssert.assertEquals(expectedJson, actualJson, JSONCompareMode.STRICT);
    }

    @Test
    @DisplayName("'generate' method with YAML files works correctly")
    void testGenerateWithYaml() throws Exception {
        String actualStylish = Differ.generate(
                getPathToFixture("fileNested3.yml"),
                getPathToFixture("fileNested4.yml"),
                "stylish"
        );
        assertEquals(expectedStylish, actualStylish);
        String actualPlain = Differ.generate(
                getPathToFixture("fileNested3.yml"),
                getPathToFixture("fileNested4.yml"),
                "plain"
        );
        assertEquals(expectedPlain, actualPlain);
        String actualJson = Differ.generate(
                getPathToFixture("fileNested3.yml"),
                getPathToFixture("fileNested4.yml"),
                "json"
        );
        JSONAssert.assertEquals(expectedJson, actualJson, JSONCompareMode.STRICT);
    }

    private String getPathToFixture(String file) {
        return "./src/test/resources/" + file;
    }
}