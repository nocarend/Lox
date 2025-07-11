package com.example.lox

import java.io.BufferedInputStream
import java.nio.file.{Files, Paths}
import java.util.Scanner
import scala.annotation.tailrec
import scala.io.Source
import scala.sys.exit

object Lox {

  private var hadError = false

  def main(args: Array[String]): Unit = {
    val len = args.length
    len match
      case 0 => {
        runPrompt()
      }
      case 1 => {
        runFile(args(0))
      }
      case _ => {
        println("Usage: jlox [script]")
        exit(64)
      }
  }

  def runFile(path: String): Unit = {
    // TODO: refactor for using scala api
    val bytes = Files.readAllBytes(Paths.get(path))
    run(new String(bytes))

    if (hadError) {
      exit(65)
    }
  }

  def runPrompt(): Unit = {
    val input = new BufferedInputStream(System.in)
    val reader = Source.fromInputStream(input).bufferedReader()

    @tailrec
    def readLines(): Unit = {
      print("> ")
      val line = reader.readLine()
      if (line != null) {
        run(line)
        hadError = false
        readLines()
      }
    }

    readLines()
  }

  def run(source: String): Unit = {
    val scanner = new Scanner(source)
    val tokens = scanner.tokens()

    tokens.forEach(println(_))
  }

  def error(line: Int, message: String): Unit = {
    report(line, "", message)
  }

  def report(line: Int, where: String, message: String): Unit = {
    Console.err.println(s"[$line] Error $where: $message")
  }
}
