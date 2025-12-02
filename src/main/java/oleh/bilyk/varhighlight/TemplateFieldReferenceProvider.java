package oleh.bilyk.varhighlight;

import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Provides references for field names inside {{}} patterns.
 */
public class TemplateFieldReferenceProvider extends PsiReferenceProvider {
    private static final Pattern TEMPLATE_PATTERN = Pattern.compile("\\{\\{([^}]+)}}");

    @NotNull
    @Override
    public PsiReference[] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {
        String text = element.getText();
        if (text == null || text.isEmpty()) {
            return PsiReference.EMPTY_ARRAY;
        }

        List<PsiReference> references = new ArrayList<>();
        Matcher matcher = TEMPLATE_PATTERN.matcher(text);

        while (matcher.find()) {
            String fieldName = matcher.group(1).trim();
            references.add(new TemplateFieldReference(element, fieldName, matcher.start(), matcher.end()));
        }

        return references.toArray(PsiReference.EMPTY_ARRAY);
    }
}

