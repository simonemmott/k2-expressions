package com.k2.expressions.parser;

import lombok.Getter;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class K2ExpressionCharacterStreamer {
    @Getter
    public static final class K2ExpressionCharacter {
        private final Character character;
        private final Character peek;
        private final int row;
        private final int column;

        K2ExpressionCharacter(Character character, Character peek, int row, int column) {
            this.character = character;
            this.peek = peek;
            this.row = row;
            this.column = column;
        }

    }

    private static class K2ExpressionCharacterIterator implements Iterator<K2ExpressionCharacter> {

        private final String expression;
        private int pos = 0;
        private int row = 0;
        private int column = 0;

        private K2ExpressionCharacterIterator(String expression) {
            this.expression = expression;
        }


        @Override
        public boolean hasNext() {
            return (pos < expression.length());
        }

        @Override
        public K2ExpressionCharacter next() {
            if (pos >= expression.length()) {
                return null;
            }
            K2ExpressionCharacter k2ExpressionCharacter = new K2ExpressionCharacter(
                    expression.charAt(pos),
                    pos + 1 < expression.length() ? expression.charAt(pos + 1) : null,
                    row,
                    column
            );
            if ('\n' == k2ExpressionCharacter.character) {
                row++;
                column = 0;
            } else {
                column++;
            }
            pos++;
            return k2ExpressionCharacter;
        }
    }

    public K2ExpressionCharacterStreamer() {
        throw new IllegalArgumentException("Cannot construct K2ExpressionCharacterStreamer instance");
    }

    public static Stream<K2ExpressionCharacter> stream(String expression) {
        K2ExpressionCharacterIterator iterator = new K2ExpressionCharacterIterator(expression);
        Spliterator<K2ExpressionCharacter> spliterator = Spliterators.spliteratorUnknownSize(
                iterator,
                Spliterator.IMMUTABLE
        );
        return StreamSupport.stream(spliterator, false);
    }


}
