package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.util.concurrent.Callable;

@Command(name = "gendiff",
        mixinStandardHelpOptions = true,
        description = "Calculates the differences between files.")

public class App implements Callable<Integer> {

    @Parameters(index = "0", paramLabel = "filepath1", description = "Path to first file")
    private String filePath1;

    @Parameters(index = "1", paramLabel = "filepath2", description = "Path to second file")
    private String filePath2;

    @Option(names = {"-f", "--format"},
            paramLabel = "format",
            description = "output format [default: stylish]",
            defaultValue = "stylish")
    private String format;

    @Override
    public Integer call() throws Exception {
        try {
            String result = Differ.generate(filePath1, filePath2);
            System.out.println(result);
            return 0; // Успешное завершение
        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
            return 2; // Код ошибки
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            return 1; // Код ошибки
        }
    }


    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
