package com.example.lox

import com.example.lox.TokenType.*

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class Scanner(val source: String) {
  private val tokens: mutable.ArrayBuffer[Token] = ArrayBuffer()
  private var start: Int = 0
  private var current: Int = 0
  private var line: Int = 1

  def scanTokens(): ArrayBuffer[Token] = {
    while (!isAtEnd()) {
      start = current
      scanToken()
    }

    tokens.addOne(Token(EOF, "", null, line))
  }

  private def scanToken(): Unit = {
    val c: Char = advance()
    val tokenType: TokenType = c match {
      case '(' => LEFT_PAREN
      case ')' => RIGHT_PAREN
      case '{' => LEFT_BRACE
      case '}' => RIGHT_BRACE
      case ',' => COMMA
      case '.' => DOT
      case '-' => MINUS
      case '+' => PLUS
      case ';' => SEMICOLON
      case '*' => STAR
    }

    addToken(tokenType)
  }

  private def advance(): Char = {
    val char = source.charAt(current)
    current += 1
    char
  }

  private def addToken(tokenType: TokenType): Unit = {
    addToken(tokenType, null)
  }

  private def addToken(tokenType: TokenType, literal: Object): Unit = {
    val text: String = source.substring(start, current)
    tokens.addOne(Token(tokenType, text, literal, line))
  }

  private def isAtEnd(): Boolean = {
    current >= source.length
  }
}
