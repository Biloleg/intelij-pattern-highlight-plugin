package oleh.bilyk.varhighlight;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import java.awt.Color;
import java.awt.Font;

public final class TemplatePatternColors {
    public static final TextAttributesKey TEMPLATE_PATTERN =
        TextAttributesKey.createTextAttributesKey(
            "TEMPLATE_PATTERN",
            TextAttributesKey.createTextAttributesKey(
                "TEMPLATE_PATTERN_DEFAULT",
                new TextAttributes(Color.LIGHT_GRAY, null, null, null, Font.PLAIN)
            )
        );

    private TemplatePatternColors() {}
}
