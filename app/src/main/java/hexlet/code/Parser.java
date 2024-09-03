package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.Map;

public final class Parser {

    public static Map<String, Object> parseString(String serializedData, String fileExtension) throws IOException {
        ObjectMapper objectMapper = getObjectMapper(fileExtension);
        return objectMapper.readValue(serializedData, new TypeReference<>() {
        });
    }

    private static ObjectMapper getObjectMapper(String fileExtension) throws IOException {
        return switch (fileExtension) {
            case "json" -> new ObjectMapper();
            case "yml", "yaml" -> new YAMLMapper();
            default ->
                    throw new IOException("File should have .json or .yml (.yaml) extension! (was '.%s')"
                            .formatted(fileExtension));
        };
    }
}