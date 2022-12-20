package org.bogus.groove.common.enumeration;

public enum FreemarkerTemplateType {
    MAIL("mail-"),
    PUSH("push-");

    String namePrefix;

    FreemarkerTemplateType(String namePrefix) {
        this.namePrefix = namePrefix;
    }
}
