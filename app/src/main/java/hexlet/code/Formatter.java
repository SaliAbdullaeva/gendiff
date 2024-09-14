package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.StringFormatter;
import hexlet.code.formatters.Stylish;
import hexlet.code.formatters.Plain;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public final class Formatter {
    public static String formatString(List<Map<String, Object>> differEntryList, String format) throws IOException {
        StringFormatter formatter = getFormatter(format);
        return formatter.format(differEntryList);
    }

    private static StringFormatter getFormatter(String format) throws IOException {
        return switch (format) {
            case "stylish" -> new Stylish();
            case "plain" -> new Plain();
            case "json" -> new Json();
            default ->
                    throw new IOException("File should have 'stylish', 'plain' or 'json' format! (was '%s')"
                            .formatted(format));
        };
    }
}
