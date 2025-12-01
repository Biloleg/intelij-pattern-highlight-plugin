package oleh.bilyk.varhighlight;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Annotator that highlights text matching the pattern {{.*}} in Java files.
 */
public class TemplatePatternAnnotator implements Annotator {
    private static final Pattern TEMPLATE_PATTERN = Pattern.compile("\\{\\{.*?}}", Pattern.DOTALL);

    public static final TextAttributesKey TEMPLATE_KEY = TemplatePatternColors.TEMPLATE_PATTERN;

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        String text = element.getText();
        if (text == null || text.isEmpty()) {
            return;
        }

        Matcher matcher = TEMPLATE_PATTERN.matcher(text);

        while (matcher.find()) {
            TextRange range = new TextRange(
                element.getTextRange().getStartOffset() + matcher.start(),
                element.getTextRange().getStartOffset() + matcher.end()
            );

            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(range)
                .textAttributes(TEMPLATE_KEY)
                .create();
        }
    }
}
