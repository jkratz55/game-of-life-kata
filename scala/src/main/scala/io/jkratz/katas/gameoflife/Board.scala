package io.jkratz.katas.gameoflife

import scala.util.Random

case class Board(grid: Array[Array[Int]]) {

  require(grid != null, "grid cannot be null")
  require(!isJagged(grid), "grid cannot be jagged")
  require(isValid(grid), "grid must be at least 1X1 and contain only 0s and 1s for values")

  val rows: Int = {
    grid.length
  }

  val columns: Int = {
    grid(0).length
  }

  def evolve(): Board = Board(Array.tabulate(rows,columns)(getNextCellState))

  private def getNextCellState(i:Int, j: Int): Int = {

    val cellValue = grid(i)(j)
    val liveCount = (for {
      x <- (0 max i-1) to (i+1 min rows-1)
      y <- (0 max j-1) to (j+1 min columns-1)
    } yield grid(x)(y)).sum - cellValue

    if (cellValue.equals(Board.CELL_ALIVE) && (liveCount < 2 || liveCount > 3)) {
      Board.CELL_DEAD
    } else if (cellValue.equals(Board.CELL_DEAD) && liveCount == 3) {
      Board.CELL_ALIVE
    } else {
      cellValue
    }
  }

  private def isJagged(grid: Array[Array[Int]]): Boolean =
    grid.exists(_.length != grid.head.length)

  private def isValid(grid: Array[Array[Int]]): Boolean =
    grid.forall(_.forall(n => (n & -2)==0)) && grid.length > 0
}

object Board {

  val CELL_DEAD = 0
  val CELL_ALIVE = 1
  val DEFAULT_ROWS = 10
  val DEFAULT_COLUMNS = 10

  def random(rows:Int = DEFAULT_ROWS, columns:Int = DEFAULT_COLUMNS): Board =
    Board(Array.fill(rows,columns)(Random.nextInt(2)))

  def prettyPrint(board: Board): Unit =
    board.grid
      .map(_.map(c => if (c == CELL_DEAD) " - " else " * ").mkString)
      .foreach(println)
}