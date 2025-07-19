package com.example.lox

import com.example.lox.TokenType.EOF

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class Scanner(val source: String) {
  private val tokens: mutable.ArrayBuffer[Token] = ArrayBuffer()
  private var start:   Int = 0
  private var current: Int = 0
  private var line:    Int = 1
  
  def scanTokens(): ArrayBuffer[Token] = {
    while (!isAtEnd()) {
      start = current
      scanToken()
    }
    
    tokens.addOne(Token(EOF, "", null, line))
  }
  
  private def isAtEnd(): Boolean = {
    current >= source.length
  }
}
