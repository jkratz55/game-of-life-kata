package io.jkratz.katas.gameoflife

import scala.util.Random

case class Board(grid: Array[Array[Int]]) {

  require(grid != null, "grid cannot be null")
  require(!isJagged(grid), "grid cannot be jagged")
  require(isValid(grid), "grid contains invalid values, 0 and 1 are the only valid values")

  val rows: Int = {
    grid.length
  }

  val columns: Int = {
    grid(0).length
  }

  def evolve(): Board = {

    val newGrid = Array.ofDim[Int](rows, columns)
    for (i <- grid.indices) {
      for (j <- grid(0).indices) {
        newGrid(i)(j) = getNextCellState(i,j)
      }
    }

    Board(newGrid)
  }

  private def getNextCellState(i:Int, j: Int): Int = {
    var liveCount = 0
    val cellValue = grid(i)(j)
    for (x <- -1 to 1; y <- -1 to 1) {
      if (i + x < 0 || i + x > (this.rows - 1) || y + j < 0 || y + j > (this.columns - 1)) {
        // do nothing, out of bounds
      } else {
        liveCount += grid(i + x)(j + y)
      }
    }
    liveCount -= cellValue

    if (cellValue.equals(Board.CELL_ALIVE) && (liveCount < 2 || liveCount > 3)) {
      Board.CELL_DEAD
    } else if (cellValue.equals(Board.CELL_DEAD) && liveCount == 3) {
      Board.CELL_ALIVE
    } else {
      cellValue
    }
  }

  private def isJagged(grid: Array[Array[Int]]): Boolean = {
    var valid = true
    val size = grid(0).length
    grid.foreach(row => if (row.length.equals(size)) valid = false)
    valid
  }

  private def isValid(grid: Array[Array[Int]]): Boolean = {
    var valid = true
    for (i <- grid.indices; j <- grid(0).indices) {
      val x = grid(i)(j)
      if (x != 0 && x != 1) {
        valid = false
      }
    }
    valid
  }
}

object Board {

  val CELL_DEAD = 0
  val CELL_ALIVE = 1
  val DEFAULT_ROWS = 10
  val DEFAULT_COLUMNS = 10

  def random(rows: Int = DEFAULT_ROWS, columns: Int = DEFAULT_COLUMNS): Board = {
    val grid = Array.ofDim[Int](rows, columns)
    for (i <- grid.indices) {
      for (j <- grid(0).indices) {
        grid(i)(j) = Random.nextInt(2)
      }
    }
    Board(grid=grid)
  }

  def prettyPrint(board: Board): Unit = {
    val grid = board.grid
    for (i <- grid.indices) {
      for (j <- grid(0).indices) {
        if (grid(i)(j) == 0) print(" - ") else print(" * ")
      }
      println()
    }
  }
}