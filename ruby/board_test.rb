require_relative 'board'
require 'test/unit'

class BoardTest < Test::Unit::TestCase

  def test_random
    b = Board.random(5,5)
    assert(b.height == 5)
    assert(b.width == 5)
    assert(b.state != nil)
  end

  def test_random_with_invalid_height
    assert_raise ArgumentError do
      Board.random(-1,10)
    end
    assert_raise ArgumentError do
      Board.random(nil ,10)
    end
  end

  def test_random_with_invalid_width
    assert_raise ArgumentError do
      Board.random(10,-1)
    end
    assert_raise ArgumentError do
      Board.random(10 ,nil)
    end
  end

  def test_initialize_with_nil_inital_state
    assert_raise ArgumentError do
      Board.new(nil)
    end
  end

  def test_initialize_with_invalid_dimensions
    invalid_state_1 = Array.new(5) {Array.new(0)}
    invalid_state_2 = Array.new(0) {Array.new(5)}
    assert_raise ArgumentError do
      Board.new(invalid_state_1)
    end
    assert_raise ArgumentError do
      Board.new(invalid_state_2)
    end
  end

  def test_initialize_with_invalid_values
    invalid_state = [
        [0,1,1,1,0],
        [1,1,1,1,1],
        [-1,-2,0,1,2],
        [0,1,1,1,0],
        [0,1,1,1,0]
    ]

    assert_raise ArgumentError do
      Board.new(invalid_state)
    end
  end

  def test_initialize_with_jagged_initial_state
    invalid_state = [
        [0,1,1,1,0],
        [1,1,1,1,1],
        [-1],
        [0,1,1,0],
        [0,1,1,1,0]
    ]

    assert_raise ArgumentError do
      Board.new(invalid_state)
    end
  end

  def test_evolve
    
    init_state = [
        [1, 1, 1, 1, 0, 1, 1, 0, 0, 0],
        [1, 1, 1, 1, 0, 1, 1, 0, 0, 0],
        [0, 0, 1, 0, 0, 0, 0, 0, 0, 0],
        [0, 1, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 1, 1, 0, 0, 0],
        [0, 0, 0, 0, 0, 1, 1, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 1, 0, 0, 0, 0, 0, 0, 1, 1],
        [1, 1, 0, 0, 0, 0, 0, 0, 1, 1]
    ]

    expected_state = [
        [1, 0, 0, 1, 0, 1, 1, 0, 0, 0],
        [1, 0, 0, 0, 0, 1, 1, 0, 0, 0],
        [1, 0, 0, 1, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 1, 1, 0, 0, 0],
        [0, 0, 0, 0, 0, 1, 1, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [1, 1, 0, 0, 0, 0, 0, 0, 1, 1],
        [1, 1, 0, 0, 0, 0, 0, 0, 1, 1]
    ]

    b = Board.new(init_state)
    assert(b.state == init_state)

    b.evolve
    assert(b.state == expected_state)
    
  end

end