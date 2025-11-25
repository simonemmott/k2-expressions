package com.k2.expressions.reader;

import com.k2.expressions.exception.K2InvalidExpressionException;
import com.k2.expressions.model.K2LiteralBoolean;
import com.k2.expressions.parser.K2ExpressionCharacterStreamer;

public class LiteralBooleanReader implements K2ExpressionReader{
    private static final String TRUE = "true";
    private static final String FALSE = "false";
    private static final String END_TOKEN_CHARS = ")]&|";

    private final StringBuilder tokenBuilder = new StringBuilder();
    private int row = 0;
    private int column = 0;
    private boolean readerException = false;
    private boolean isComplete = false;
    @Override
    public void accept(K2ExpressionCharacterStreamer.K2ExpressionCharacter k2ExpressionCharacter) {
        checkReaderException();
        this.row = k2ExpressionCharacter.getRow();
        this.column = k2ExpressionCharacter.getColumn();
        if (!Character.isWhitespace(k2ExpressionCharacter.getCharacter()) || !tokenBuilder.isEmpty()) {
            tokenBuilder.append(k2ExpressionCharacter.getCharacter());
        }
        if (
                (
                        tokenBuilder.toString().equals(TRUE)
                     || tokenBuilder.toString().equals(FALSE)
                )
                && (
                        (k2ExpressionCharacter.getPeek() == null)
                     || Character.isWhitespace(k2ExpressionCharacter.getPeek())
                     || END_TOKEN_CHARS.contains(String.valueOf(k2ExpressionCharacter.getPeek()))
                )
        ) {
            isComplete = true;
            return;
        }
        if (!(
                (TRUE.startsWith(tokenBuilder.toString()))
            ||  (FALSE.startsWith(tokenBuilder.toString()))
        ))
        {
            throwException(exception());
        }
    }

    @Override
    public boolean isComplete() {
        return isComplete;
    }

    @Override
    public K2LiteralBoolean get() {
        checkReaderException();
        if (TRUE.equals(tokenBuilder.toString().trim())) {
            return new K2LiteralBoolean(true);
        }
        if (FALSE.equals(tokenBuilder.toString().trim())) {
            return new K2LiteralBoolean(false);
        }
        throwException(exception());
        return null;
    }

    private void checkReaderException() {
        if (readerException) {
            throwException(exception());
        }
    }

    private void throwException(K2InvalidExpressionException err) {
        this.readerException = true;
        throw err;
    }

    private K2InvalidExpressionException exception() {
        return new K2InvalidExpressionException(
                tokenBuilder.toString(),
                "is not a literal boolean value",
                row,
                column);
    }
}
