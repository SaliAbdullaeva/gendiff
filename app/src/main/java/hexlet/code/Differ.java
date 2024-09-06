package hexlet.code;

import java.io.IOException;
import java.nio.file.Path;

import java.util.Map;
import java.util.List;

public final class Differ {

    public static String generate(String filePath1, String filePath2, String format) throws IOException {
        Path path1 = Reader.getPath(filePath1);
        Path path2 = Reader.getPath(filePath2);

        Map<String, Object> data1 = Parser.parseString(
                Reader.readFile(path1),
                Reader.getFileExtension(path1)
        );
        Map<String, Object> data2 = Parser.parseString(
                Reader.readFile(path2),
                Reader.getFileExtension(path2)
        );

        List<Map<String, Object>> differDifferNodeList = DifferListComposer.composeList(data1, data2);
        return Formatter.formatString(differDifferNodeList, format);
    }

    public static String generate(String filePath1, String filePath2) throws IOException {
        return generate(filePath1, filePath2, "stylish");
    }

}