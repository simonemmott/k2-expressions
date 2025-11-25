package com.k2.expressions.reader;

import com.k2.expressions.model.K2Expression;
import com.k2.expressions.parser.K2ExpressionCharacterStreamer;

public interface K2ExpressionReader {
    void accept(K2ExpressionCharacterStreamer.K2ExpressionCharacter k2ExpressionCharacter);
    boolean isComplete();
    K2Expression get();
}
