require_relative 'board'

def print_board(board)
  board.each do |row|
    row.each { |cell|
      if cell == 0
        print ' - '
      else
        print ' * '
      end
    }
    puts
  end
end

b = Board.random(10 , 10)
puts 'State 0'
print_board(b.state)

b.evolve

puts
puts 'State 1'
print_board(b.state)
