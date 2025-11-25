package com.k2.expressions.reader;

import com.k2.expressions.exception.K2InvalidExpressionException;
import com.k2.expressions.model.K2LiteralBoolean;
import com.k2.expressions.parser.K2ExpressionCharacterStreamer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class LiteralBooleanReaderTest {

    private final LiteralBooleanReader reader = new LiteralBooleanReader();

    @ParameterizedTest
    @ValueSource(strings = {"true", " true"})
    public void shouldReturnTRUE(String test) {
        // Given
        K2ExpressionCharacterStreamer.stream(test)
                .forEach(reader::accept);

        // When
        K2LiteralBoolean k2LiteralBoolean = reader.get();

        // Then
        assertTrue(k2LiteralBoolean.getValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {"false", " false"})
    public void shouldReturnFALSE(String test) {
        // Given
        K2ExpressionCharacterStreamer.stream(test)
                .forEach(reader::accept);

        // When
        K2LiteralBoolean k2LiteralBoolean = reader.get();

        // Then
        assertFalse(k2LiteralBoolean.getValue());
    }

    @Test
    public void XXX_shouldTHROW() {
        // Then When
        assertThrows(
                K2InvalidExpressionException.class,
                () -> K2ExpressionCharacterStreamer.stream("XXX")
                        .forEach(reader::accept),
                "Expected Invalid Expression Exception"
        );
    }

    @Test
    public void TRUE_shouldTHROW() {
        // Then When
        assertThrows(
                K2InvalidExpressionException.class,
                () -> K2ExpressionCharacterStreamer.stream("TRUE")
                        .forEach(reader::accept),
                "Expected Invalid Expression Exception"
        );
    }

    @Test
    public void FALSE_shouldTHROW() {
        // Then When
        assertThrows(
                K2InvalidExpressionException.class,
                () -> K2ExpressionCharacterStreamer.stream("FALSE")
                        .forEach(reader::accept),
                "Expected Invalid Expression Exception"
        );
    }

    @Test
    public void shouldExtractFirstBooleanLiteral() {
        // Given
        K2ExpressionCharacterStreamer.stream("true && false")
                .forEach(k2ExpressionChar -> {
                    if (!reader.isComplete()) {
                        reader.accept(k2ExpressionChar);
                    }
                });

        // When
        K2LiteralBoolean k2LiteralBoolean = reader.get();

        // Then
        assertTrue(k2LiteralBoolean.getValue());
    }

}
