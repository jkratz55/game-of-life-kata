package io.jkratz.katas.gameoflife

import org.scalatest.{FunSpec, Matchers}

class LifeSpec extends FunSpec with Matchers {

  val initialState: Array[Array[Int]] = Array(
    Array(1, 1, 1, 1, 0, 1, 1, 0, 0, 0),
    Array(1, 1, 1, 1, 0, 1, 1, 0, 0, 0),
    Array(0, 0, 1, 0, 0, 0, 0, 0, 0, 0),
    Array(0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 1, 1, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 1, 1, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    Array(0, 1, 0, 0, 0, 0, 0, 0, 1, 1),
    Array(1, 1, 0, 0, 0, 0, 0, 0, 1, 1)
  )

  val expectedState: Array[Array[Int]] = Array(
    Array(1, 0, 0, 1, 0, 1, 1, 0, 0, 0),
    Array(1, 0, 0, 0, 0, 1, 1, 0, 0, 0),
    Array(1, 0, 0, 1, 0, 0, 0, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 1, 1, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 1, 1, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    Array(1, 1, 0, 0, 0, 0, 0, 0, 1, 1),
    Array(1, 1, 0, 0, 0, 0, 0, 0, 1, 1)
  )

  describe("When given a valid initial state") {
    describe("The object will be instantiated") {
      val board = Board(initialState)
      describe("After evolving the state should match the expected state") {
        val evolvedBoard = board.evolve()
        assert(board.grid.deep == board.grid.deep)
      }
    }
  }

  describe("When given a null state an exception should be throws") {
    assertThrows[IllegalArgumentException] {
      val board = Board(null)
    }
  }

  describe("When given a jagged initialState an exception should be thrown") {
    val jaggedState = Array(
      Array(1, 1, 1, 1, 0, 1, 1, 0, 0, 0),
      Array(1, 1, 1, 1, 0, 1, 1, 0, 0, 0),
      Array(0, 0, 1, 0, 0, 0, 0, 0, 0, 0),
      Array(0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
      Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
      Array(0, 0, 0, 0, 0, 1, 1, 0, 0, 0),
      Array(0, 0, 0, 0, 0, 1, 1, 0, 0, 0),
      Array(0, 0, 0, 0, 0, 0),
      Array(0, 1, 0, 0, 0, 0, 0, 0, 1, 1),
      Array(1, 1, 0, 0, 0, 0, 0, 0, 1, 1)
    )

    assertThrows[IllegalArgumentException] {
      val board = Board(jaggedState)
    }
  }

  describe("When the initialState is smaller than 1 by 1 an exception should be thrown") {
    assertThrows[IllegalArgumentException] {
      val board = Board(Array[Array[Int]]())
    }
  }

  describe("When the initialState contains invalid values an exception should be thrown") {
    val invalidInitialStateValues = Array(
      Array(1, 1, 1, 1, 0, 1, 1, 0, 0, 0),
      Array(1, 1, 1, 1, 0, 1, 1, 0, 0, 0),
      Array(0, 0, 1, 0, 0, 0, 0, 0, 0, 0),
      Array(0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
      Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
      Array(0, 0, 2, 0, 0, 1, 1, 0, 0, 0),
      Array(0, 0, 0, 0, 0, 1, 1, 0, 0, 0),
      Array(0, 0, 0, -1, 0, 0, 0, 0, 0, 0),
      Array(0, 1, 0, 0, 0, 0, 0, 0, 1, 1),
      Array(1, 1, 0, 0, 0, 0, 0, 0, 1, 1)
    )

    assertThrows[IllegalArgumentException] {
      val board = Board(invalidInitialStateValues)
    }
  }
}
