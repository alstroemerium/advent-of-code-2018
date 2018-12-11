#! /usr/bin/env python

def calculate_power_level(x, y, sn):
    rack_id = x + 10
    power_level = rack_id * y
    power_level += sn
    power_level *= rack_id
    power_level = (power_level // 100) % 10
    return power_level - 5

serial_number = 4172
board_size = 300
power_level_board = [ [0] * board_size ] * board_size

power_level_board = [[calculate_power_level(i,j,serial_number) for j in range(board_size)] for i in range(board_size)]

max_i = 0
max_j = 0
max_power = 0

max_power_level_board_size = board_size - 3
max_power_level_board = [ [0] * max_power_level_board_size ] * max_power_level_board_size

for i in range(max_power_level_board_size):
    for j in range(max_power_level_board_size):
        power_block_val = power_level_board[i][j] + power_level_board[i][j+1] + power_level_board[i][j+2] + \
                            power_level_board[i+1][j] + power_level_board[i+1][j+1] + power_level_board[i+1][j+2] + \
                            power_level_board[i+2][j] + power_level_board[i+2][j+1] + power_level_board[i+2][j+2]
        if power_block_val > max_power:
            max_power = power_block_val
            max_i = i
            max_j = j
        max_power_level_board[i][j] = power_block_val

print max_power, max_i, max_j

#print max(map(max,max_power_level_board))

#for i in range(board_size):
#    s = ''
#    for j in range(board_size):
#        s += str(power_level_board[i][j]) + '  '
#    print s
