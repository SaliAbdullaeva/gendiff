package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

public final class AppTest {
    private static final int SUCCESS_EXIT_CODE = 0;
    private static final int ERROR_EXIT_CODE = 15;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    private String expectedStylish;
    private String expectedPlain;
    private String expectedJson;

    @BeforeEach
    void setUp() throws Exception {
        System.setOut(new PrintStream(output));
        Path pathStylish = ReadFile.getPath(getPathToFixture("EXPECTED_STYLISH"));
        expectedStylish = ReadFile.readFile(pathStylish);
        Path pathPlain = ReadFile.getPath(getPathToFixture("EXPECTED_PLAIN"));
        expectedPlain = ReadFile.readFile(pathPlain);
        Path pathJson = ReadFile.getPath(getPathToFixture("EXPECTED_JSON"));
        expectedJson = ReadFile.readFile(pathJson);
    }

    @Test
    @DisplayName("App works correctly")
    void testSuccessExitCode() throws Exception {
        String[] argsYamlStylish = new String[]{
                "-f=stylish",
                getPathToFixture("fileNested1.json"),
                getPathToFixture("fileNested2.json")
        };
        int exitCode1 = new CommandLine(new App()).execute(argsYamlStylish);
        assertEquals(expectedStylish, output.toString(StandardCharsets.UTF_8).trim());
        assertEquals(SUCCESS_EXIT_CODE, exitCode1);

        output.reset();

        String[] argsYamlPlain = new String[]{
                "-f=plain",
                getPathToFixture("fileNested1.yml"),
                getPathToFixture("fileNested2.yaml")
        };
        int exitCode2 = new CommandLine(new App()).execute(argsYamlPlain);
        assertEquals(expectedPlain, output.toString(StandardCharsets.UTF_8).trim());
        assertEquals(SUCCESS_EXIT_CODE, exitCode2);

        output.reset();

        String[] argsYamlJson = new String[]{
                "-f=json",
                getPathToFixture("fileNested1.yml"),
                getPathToFixture("fileNested2.json")
        };
        int exitCode3 = new CommandLine(new App()).execute(argsYamlJson);
        String actualJson = output.toString(StandardCharsets.UTF_8).trim();
        JSONAssert.assertEquals(expectedJson, actualJson, JSONCompareMode.STRICT);
        assertEquals(SUCCESS_EXIT_CODE, exitCode3);
    }

    @Test
    @DisplayName("App returns error exit code when provided with incorrect args")
    void testErrorExitCode() {
        String[] argsWrongFormat = new String[]{
                "-f=stailish",
                getPathToFixture("fileNested1.json"),
                getPathToFixture("fileNested2.json")
        };
        int exitCode2 = new CommandLine(new App()).execute(argsWrongFormat);
        assertEquals(ERROR_EXIT_CODE, exitCode2);

        String[] argsFileNotExist = new String[]{
                "-f=stylish",
                getPathToFixture("fil.yml"),
                getPathToFixture("fileNested2.yaml")
        };
        int exitCode3 = new CommandLine(new App()).execute(argsFileNotExist);
        assertEquals(ERROR_EXIT_CODE, exitCode3);
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }

    private String getPathToFixture(String file) {
        return "./src/test/resources/" + file;
    }
}