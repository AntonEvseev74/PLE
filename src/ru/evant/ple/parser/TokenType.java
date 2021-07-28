package ru.evant.ple.parser;

public enum TokenType {

    NUMBER,     // десятичное число
    HEX_NUMBER,  // шестнадцатеричное число
    WORD,        // слово

    PLUS,       // плюс
    MINUS,      // минус
    MULTIPLY,   // умножить
    DIVIDE,     // разделить

    LPAREN,     // открывающая скобка  (
    RPAREN,     // закрывающая скобка  )

    EOF
}
