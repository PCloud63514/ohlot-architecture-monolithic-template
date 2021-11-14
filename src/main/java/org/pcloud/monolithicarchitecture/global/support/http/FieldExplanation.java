package org.pcloud.monolithicarchitecture.global.support.http;

public class FieldExplanation {
    private final String fieldName;
    private final Object explanation;

    public FieldExplanation(String fieldName, Object explanation) {
        this.fieldName = fieldName;
        this.explanation = explanation;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getExplanation() {
        return explanation;
    }
}
