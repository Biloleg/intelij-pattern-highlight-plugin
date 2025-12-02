package oleh.bilyk.varhighlight;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Custom reference that resolves field names inside {{}} patterns to their declarations.
 * Enables Cmd/Ctrl+Click navigation to field definitions.
 */
public class TemplateFieldReference extends PsiReferenceBase<PsiElement> {
    private final String fieldName;

    public TemplateFieldReference(@NotNull PsiElement element, String fieldName, int start, int end) {
        super(element, new TextRange(start, end));
        this.fieldName = fieldName;
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        // Find the containing class
        PsiClass containingClass = PsiTreeUtil.getParentOfType(getElement(), PsiClass.class);
        if (containingClass == null) {
            return null;
        }

        // Look for the field by name (including inherited fields)
        return containingClass.findFieldByName(fieldName, true);
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        // Provide completion variants - all fields in the class
        PsiClass containingClass = PsiTreeUtil.getParentOfType(getElement(), PsiClass.class);
        if (containingClass == null) {
            return PsiField.EMPTY_ARRAY;
        }

        // Return all fields (including inherited ones) as completion options
        return containingClass.getAllFields();
    }
}

