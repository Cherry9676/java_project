package common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class HtmlElementReader {

    public static String getTargetByLocator(String locator) {
        ObjectMapper objectMapper = new ObjectMapper();
        String targetValue = null;

        try {
            // Get the current working directory
            String currentDir = System.getProperty("user.dir");
            // Construct the path to the HtmlElement.json file
            File jsonFile = new File(currentDir, "HtmlElement.json");

            // Read the JSON file
            List<HtmlElement> elements = objectMapper.readValue(
                    jsonFile,
                    new TypeReference<List<HtmlElement>>() {}
            );

            // Iterate through the list to find the matching locator
            for (HtmlElement element : elements) {
                if (element.getLocator().equals(locator)) {
                    targetValue = element.getTarget();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Escape double quotes in the targetValue
        if (targetValue != null) {
            targetValue = targetValue.replace("\"", "\\\"");
        }

        return targetValue;
    }

}