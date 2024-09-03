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

    @Option(names = {"-f", "--format"}, paramLabel = "format", description = "output format [default: stylish]")
    String format = "stylish";
    @Parameters(index = "0", paramLabel = "filepath1", description = "Path to first file")
    String filepath1;
    @Parameters(index = "1", paramLabel = "filepath2", description = "Path to second file")
    String filepath2;

    @Override
    public Integer call() {
        try {
            String result = Differ.generate(filepath1, filepath2, format);
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
