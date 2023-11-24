package com.example.spring_jobs.company;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum IndustryEnum {
    SERVICE(IndustryEnum.Kind.SERVICE),
    MANUFACTURING(IndustryEnum.Kind.MANUFACTURING),
    IT(IndustryEnum.Kind.IT);

    private final String kind;

    IndustryEnum(String kind) {
        this.kind = kind;
    }

    @JsonValue
    public String getKind() {
        return this.kind;
    }

    public static class Kind {
        public static final String SERVICE = "서비스업";
        public static final String MANUFACTURING = "제조업";
        public static final String IT = "IT/웹/통신";
    }

    @JsonCreator
    public static IndustryEnum from(String value) {
        for (IndustryEnum industry : IndustryEnum.values()) {
            if (industry.getKind().equals(value)) {
                return industry;
            }
        }
        return null;
    }
}
