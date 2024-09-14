package hexlet.code.formatters;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface StringFormatter {
    String format(List<Map<String, Object>> differEntryList) throws IOException;
}
