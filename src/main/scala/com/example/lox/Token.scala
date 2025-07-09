package com.example.lox

case class Token(tokenType: TokenType,
                 lexeme: String,
                 literal: Object,
                 line: Int) {
  override def toString: String = f"$tokenType $lexeme $literal"
}
