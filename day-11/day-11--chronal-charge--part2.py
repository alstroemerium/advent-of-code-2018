#! /usr/bin/env python

import sys

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


max_power_level_board = list(power_level_board)
solutions = []

for row in range(board_size):
    for col in range(board_size):
        max_square_size = 0
        max_sum = - sys.maxint - 1

        for square_size in range(1, board_size + 1 - max(row,col)):
            current_sum = sum(max_power_level_board[row][col:col+square_size])
            for row_on_board in range(1, square_size):
                current_sum += sum(max_power_level_board[row+row_on_board][col:col+square_size])
            if max_sum < current_sum:
                max_sum = current_sum
                max_square_size = square_size
        if max_square_size > 0:
            solutions.append( [ max_sum, row, col, max_square_size  ] )

print max(solutions)
