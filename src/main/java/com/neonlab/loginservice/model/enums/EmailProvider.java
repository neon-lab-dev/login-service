package com.neonlab.loginservice.model.enums;

import lombok.Getter;

@Getter
public enum EmailProvider {

    DEFAULT,
    JAVA_MAIL_SENDER;

    public static EmailProvider fromString(String text) {
        for (EmailProvider provider : EmailProvider.values()) {
            if (provider.name().equalsIgnoreCase(text)) {
                return provider;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found in EmailProvider enum");
    }
}
