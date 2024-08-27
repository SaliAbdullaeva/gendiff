package hexlet.code;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

class ReadFile {
    public static String readFile(String file) throws Exception {
        Path path = Paths.get(file).toAbsolutePath().normalize();

        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }

        return Files.readString(path);
    }
}
