package com.example.bookstoreapp.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.Objects;

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
            Object firstObj = getFieldValue(o, firstFieldName);
            Object secondObj = getFieldValue(o, secondFieldName);

            return (Objects.equals(firstObj, secondObj));
        } catch (ReflectiveOperationException | NullPointerException e) {
            throw new RuntimeException("Annotation does not "
                    + "work well. Check annotations properties.", e);
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong in FieldMatchValidator", e);
        }
    }

    private Object getFieldValue(Object o, String fieldName) throws ReflectiveOperationException {
        Field field = o.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(o);
    }
}
