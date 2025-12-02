package oleh.bilyk.varhighlight;

import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;

/**
 * Registers reference providers for template field references to enable navigation.
 */
public class TemplateFieldReferenceContributor extends PsiReferenceContributor {
    @Override
    public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar) {
        // Register for string literals
        registrar.registerReferenceProvider(
            PlatformPatterns.psiElement(PsiLiteralExpression.class),
            new TemplateFieldReferenceProvider()
        );

        // Register for comments
        registrar.registerReferenceProvider(
            PlatformPatterns.psiElement(PsiComment.class),
            new TemplateFieldReferenceProvider()
        );
    }
}

