package oleh.bilyk.varhighlight;

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;

public final class TemplatePatternColors {
    public static final TextAttributesKey TEMPLATE_PATTERN =
        TextAttributesKey.createTextAttributesKey(
            "TEMPLATE_PATTERN",
            DefaultLanguageHighlighterColors.LOCAL_VARIABLE
        );

    private TemplatePatternColors() {}
}
