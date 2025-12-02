# Template Pattern Highlighter Plugin

An IntelliJ IDEA plugin that highlights text matching the pattern `{{.*}}` in Java files.

## Features

- Automatically highlights template variables like `{{name}}`, `{{value}}`, etc. in Java string literals and comments
- Customizable highlight color through IDE settings
- Works with all Java files in your project

## Building the Plugin

1. Make sure you have Java 11 or higher installed
2. Open the project in IntelliJ IDEA
3. Run the Gradle task to build the plugin:
   ```bash
   ./gradlew buildPlugin
   ```
4. The plugin will be built in `build/distributions/`

## Running the Plugin in Development Mode

To test the plugin in a sandbox IDE:
```bash
./gradlew runIde
```

This will start a new IntelliJ IDEA instance with the plugin installed.

## Installing the Plugin

1. Build the plugin using `./gradlew buildPlugin`
2. In IntelliJ IDEA, go to **Settings/Preferences → Plugins**
3. Click the gear icon ⚙️ and select **Install Plugin from Disk...**
4. Navigate to `build/distributions/` and select the `.zip` file
5. Restart IntelliJ IDEA

## Customizing the Highlight Color

1. Go to **Settings/Preferences → Editor → Color Scheme → Template Pattern Highlighter**
2. Adjust the color and text attributes for "Template Pattern ({{...}})"
3. Click **Apply** and **OK**

## Example

When you write Java code like this:
```java
String template = "Hello {{name}}, welcome to {{place}}!";
System.out.println("Processing {{count}} items");
```

The text `{{name}}`, `{{place}}`, and `{{count}}` will be highlighted with your chosen color.

## Development

### Project Structure
- `src/main/java/oleh/bilyk/varhighlight/TemplatePatternAnnotator.java` - Main annotator that detects and highlights the pattern
- `src/main/java/oleh/bilyk/varhighlight/TemplateColorSettingsPage.java` - Color settings configuration page
- `src/main/resources/META-INF/plugin.xml` - Plugin configuration

### Pattern Details
The plugin uses the regex pattern `\{\{.*?\}\}` to match any text enclosed in double curly braces.


