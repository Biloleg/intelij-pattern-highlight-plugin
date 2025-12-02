package oleh.bilyk.varhighlight;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Annotator that highlights text matching the pattern {{.*}} in Java string literals.
 * Validates that field names inside {{}} exist in the containing class.
 */
public class TemplatePatternAnnotator implements Annotator {
    private static final Pattern TEMPLATE_PATTERN = Pattern.compile("\\{\\{([^}]+)}}", Pattern.DOTALL);

    public static final TextAttributesKey TEMPLATE_KEY = TemplatePatternColors.TEMPLATE_PATTERN;

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        // Only process string literals
        if (!(element instanceof PsiLiteralExpression)) {
            return;
        }

        String text = element.getText();
        if (text == null || text.isEmpty()) {
            return;
        }

        Matcher matcher = TEMPLATE_PATTERN.matcher(text);

        while (matcher.find()) {
            String fieldName = matcher.group(1).trim();
            int startOffset = element.getTextRange().getStartOffset() + matcher.start();
            int endOffset = element.getTextRange().getStartOffset() + matcher.end();
            TextRange range = new TextRange(startOffset, endOffset);

            // Find the containing class and check if the field exists
            PsiClass containingClass = PsiTreeUtil.getParentOfType(element, PsiClass.class);
            PsiField field = containingClass != null ? containingClass.findFieldByName(fieldName, true) : null;

            if (field == null) {
                // Field doesn't exist - highlight in red with error
                holder.newAnnotation(HighlightSeverity.ERROR, "Field '" + fieldName + "' not found in class")
                    .range(range)
                    .create();
            } else {
                // Field exists - apply normal template highlighting
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(range)
                    .textAttributes(TEMPLATE_KEY)
                    .create();
            }
        }
    }
}
