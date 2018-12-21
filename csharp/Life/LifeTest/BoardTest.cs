using System;
using Life;
using Xunit;

namespace LifeTest
{
    public class BoardTest
    {
        [Fact]
        public void TestRandom()
        {
            Board board = Board.Random();
            Assert.True(board.Rows == 10);
            Assert.True(board.Columns == 10);
            
            Board customSizedBoard = Board.Random(5, 5);
            Assert.True(customSizedBoard.Rows == 5);
            Assert.True(customSizedBoard.Columns == 5);
        }

        [Fact]
        public void TestRandomWithZeroSize()
        {
            Assert.Throws<ArgumentException>(() => Board.Random(0, 0));
        }

        [Fact]
        public void TestConstructorWithNullInitialState()
        {
            Assert.Throws<ArgumentNullException>(() => new Board(null));
        }

        [Fact]
        public void TestConstructorWithInvalidValues()
        {
            int[,] arr = { { 0, 0, 0, 0, 1 }, {2, 0, 1, -1, 3} };
            Assert.Throws<ArgumentException>(() => new Board(arr));
        }

        [Fact]
        public void TestConstructorWithInvalidSize()
        {
            int[,] state = new int[0,0];
            Assert.Throws<ArgumentException>(() => new Board(state));
        }

        [Fact]
        public void TestEvolve()
        {
            
        }
    }
}