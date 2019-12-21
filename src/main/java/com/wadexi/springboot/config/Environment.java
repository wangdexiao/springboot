package com.wadexi.springboot.config;

import lombok.Getter;

public enum Environment {

    DEVOLOPER("dev"),
    PRODUCTION("pro");

    @Getter
    private final String simpleName;

    Environment(String simpleName) {
        this.simpleName = simpleName;
    }
}
