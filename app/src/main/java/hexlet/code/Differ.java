package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.List;

public final class Differ {

    public static String generate(String filePath1, String filePath2, String format) throws IOException {
        Path path1 = Differ.getPath(filePath1);
        Path path2 = Differ.getPath(filePath2);

        Map<String, Object> data1 = Parser.parseString(
                Differ.readFile(path1),
                Differ.getFileExtension(path1)
        );
        Map<String, Object> data2 = Parser.parseString(
                Differ.readFile(path2),
                Differ.getFileExtension(path2)
        );

        List<Map<String, Object>> differDifferNodeList = DifferListComposer.composeList(data1, data2);
        return Formatter.formatString(differDifferNodeList, format);
    }

    public static String generate(String filePath1, String filePath2) throws IOException {
        return generate(filePath1, filePath2, "stylish");
    }
    public static String readFile(Path filePath) throws IOException {
        return Files.readString(filePath);
    }

    public static String getFileExtension(Path filePath) {
        return filePath.getName(filePath.getNameCount() - 1)
                .toString().split("\\.")[1].trim().toLowerCase();
    }

    public static Path getPath(String filePath) {
        return Paths.get(filePath).toAbsolutePath().normalize();
    }

}
