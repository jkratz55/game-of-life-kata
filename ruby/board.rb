class Board

  attr_reader :height, :state, :width

  # Creates a new board with the given width and height with a random
  # initial state
  #
  # Params
  # - height: height or number of rows for the board, must be greater than 0
  # - width: width or number of columns for the board, must be greater than 0
  #
  # Returns a new board
  def self.random(width, height)

    if width.nil? || width < 0
      raise ArgumentError, 'width must be a positive integer > 0'
    end

    if height.nil? || height < 0
      raise ArgumentError, 'height must be a positive integer > 0'
    end

    state = Array.new(height) {Array.new(width)}

    (0...height).each do |row|
      (0...width).each do |cell|
        state[row][cell] = Random.rand(2)
      end
    end
    Board.new(state)
  end

  # Initializes the Board with a provided initial state. The provided initial
  # state should be a 2D array with a minimum height (row) of 1, and width (column)
  # of 1. The 2D array must also not be jagged. The board uses 0 to represent a dead
  # cell and 1 to represent a live cell. Those are the only valid values that can be
  # provided in the initial state. If the initial state is not valid an ArgumentException
  # will be raised.
  #
  # Params:
  # - initial_state: the initial state of the board, cannot be nil
  #
  # Returns: new Board with the provided initial state
  def initialize(initial_state)

    # Validates the initial state is valid, if not an error will be raised
    validate_state(initial_state)

    # If we reach this point the initial state is valid, init instance variables
    @state = initial_state
    @width = initial_state.length
    @height = initial_state[0].length
  end

  # Evolves the board to the next state
  def evolve

    new_state = Array.new(height) {Array.new(width)}

    (0...@height).each do |row|
      (0...@width).each do |cell|
        neighbors_alive = calculate_living_neighbors(row, cell)
        cell_value = @state[row][cell]

        if cell_value == 1 and (neighbors_alive < 2 or neighbors_alive > 3)
          new_state[row][cell] = 0
        elsif cell_value == 0 and neighbors_alive == 3
          new_state[row][cell] = 1
        else
          new_state[row][cell] = cell_value
        end
      end
    end
    @state = new_state
  end

  private
  def validate_state(state)

    if state.nil?
      raise ArgumentError, 'initial_state cannot be nil'
    end
  end

  def calculate_living_neighbors(i, j)

    live_count = 0
    (-1...2).each do |x|
      (-1...2).each do |y|
        if i + x < 0 or i + x > (@height - 1) or y + j < 0 or y + j > (@width - 1)
          # do nothing
        else
          live_count += @state[i + x][y + j]
        end
      end
    end
    live_count -= @state[i][j]
  end
end