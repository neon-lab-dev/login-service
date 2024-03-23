package com.neonlab.loginservice.model.enums;

import lombok.Getter;

@Getter
public enum SMSProvider {


    DEFAULT,
    INTERAKT;

    public static SMSProvider fromString(String text) {
        for (SMSProvider provider : SMSProvider.values()) {
            if (provider.name().equalsIgnoreCase(text)) {
                return provider;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found in EmailProvider enum");
    }

}
