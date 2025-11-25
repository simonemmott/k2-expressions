package com.k2.expressions.model;

import lombok.Getter;

@Getter
public class K2LiteralBoolean extends K2Expression{
    private final Boolean value;

    public K2LiteralBoolean(Boolean value) {
        this.value = value;
    }
}
