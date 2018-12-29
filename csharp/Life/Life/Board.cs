using System;

namespace Life
{
    public class Board
    {
        private const int CellDead = 0;
        private const int CellAlive = 1;
        private const int DefaultRows = 10;
        private const int DefaultColumns = 10;
        
        public int[,] State { get; private set; }
        public int Rows { get; }
        public int Columns { get; }

        public Board(int[,] initialState)
        {
            if (initialState == null)
            {
                throw new ArgumentNullException(nameof(initialState), "Value cannot be NULL");
            }

            if (!IsValid(initialState))
            {
                throw new ArgumentException(
                    "initialState must be a 2D array with at least 1 row and 1 column with values of 0 and 1",
                    nameof(initialState));
            }
            
            State = initialState;
            Rows = initialState.GetLength(0);
            Columns = initialState.GetLength(1);
        }

        public void Evolve()
        {
            int[,] newState = new int[Rows,Columns];
            for (var i = 0; i < Rows; i++)
            {
                for (var j = 0; j < Columns; j++)
                {
                    newState[i, j] = NextStateForCell(i, j);
                }
            }

            State = newState;
        }

        private int NextStateForCell(int i, int j)
        {
            int neighborsAlive = CalculateLivingNeighbors(i, j);
            int cellValue = State[i, j];
            switch (cellValue)
            {
                case CellDead when neighborsAlive == 3:
                    return CellAlive;
                case CellAlive when (neighborsAlive < 2 || neighborsAlive > 3):
                    return CellDead;
                default:
                    return cellValue;
            }
        }

        private int CalculateLivingNeighbors(int i, int j)
        {
            int neighborsAlive = 0;
            for (int x=-1; x<=1; x++)
            {
                for (int y=-1; y<=1; y++)
                {
                    if (i + x < 0 || i + x > (Rows - 1) || y + j < 0 || y + j > (Columns - 1)) {
                        continue;
                    }
                    neighborsAlive += State[i + x, y + j];
                }
            }
            neighborsAlive -= State[i,j];
            return neighborsAlive;
        }

        private bool IsValid(int[,] arr)
        {
            if (arr.GetLength(0) < 1 || arr.GetLength(1) < 1)
            {
                return false;
            }
            for (int i = 0; i < arr.GetLength(0); i++)
            {
                for (int j = 0; j < arr.GetLength(1); j++)
                {
                    int value = arr[i, j];
                    if (value < 0 || value > 1)
                    {
                        return false;
                    }
                }
            }

            return true;
        }

        protected bool Equals(Board other)
        {
            return State.Equals(other.State) && Rows == other.Rows && Columns == other.Columns;
        }

        public override bool Equals(object obj)
        {
            if (ReferenceEquals(null, obj)) return false;
            if (ReferenceEquals(this, obj)) return true;
            if (obj.GetType() != GetType()) return false;
            return Equals((Board) obj);
        }

        public override int GetHashCode()
        {
            unchecked
            {
                var hashCode = State.GetHashCode();
                hashCode = (hashCode * 397) ^ Rows;
                hashCode = (hashCode * 397) ^ Columns;
                return hashCode;
            }
        }

        public override string ToString()
        {
            return $"{nameof(State)}: {State}, {nameof(Rows)}: {Rows}, {nameof(Columns)}: {Columns}";
        }

        /// <summary>
        /// Creates a Board with a random state.
        /// </summary>
        /// <param name="rows"></param>
        /// <param name="columns"></param>
        /// <returns></returns>
        public static Board Random(int rows=DefaultRows, int columns=DefaultColumns)
        {
            Random random = new Random(Guid.NewGuid().GetHashCode());
            int[,] state = new int[rows,columns];
            for (var i = 0; i < rows; i++)
            {
                for (var j = 0; j < columns; j++)
                {
                    state[i, j] = random.Next(0, 2);
                }
            }
            return new Board(state);
        }
    }

    public static class BoardExtensions
    {
        public static void Print(this Board board)
        {
            int[,] state = board.State;
            for (int i = 0; i < board.Rows; i++)
            {
                for (int j = 0; j < board.Columns; j++)
                {
                    if (state[i,j] == 0) Console.Write(" - ");
                    else Console.Write(" * ");
                }
                Console.WriteLine();
            }
        }
    }
}