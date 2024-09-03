package hexlet.code;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

class ReadFile {
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
