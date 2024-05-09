package com.example.bookstoreapp.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        this.firstFieldName = constraintAnnotation.firstFieldName();
        this.secondFieldName = constraintAnnotation.secondFieldName();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        try {
            Field field1 = o.getClass().getDeclaredField(firstFieldName);
            field1.setAccessible(true);
            Object firstObj = field1.get(o);

            Field field2 = o.getClass().getDeclaredField(secondFieldName);
            field2.setAccessible(true);
            Object secondObj = field2.get(o);

            return firstObj == null && secondObj == null
                    || firstObj != null && firstObj.equals(secondObj);
        } catch (ReflectiveOperationException | NullPointerException e) {
            throw new RuntimeException("Annotation does not work well. "
                    + "Check annotations properties.", e);
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong in FieldMatchValidator", e);
        }
    }
}
