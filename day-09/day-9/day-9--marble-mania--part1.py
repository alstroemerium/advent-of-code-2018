#!/usr/bin/env python

input_line = ""
with open("day-9--input") as f:
    input_line = f.readline()

split_input = input_line.split(' ')
num_players = int(split_input[0])
last_marble = int(split_input[-2])

game = [0]
scores = [0] * num_players
current_marble = 1
current_player = 1
current_index = 0

while current_marble <= last_marble:
    if current_marble % 23 == 0:
        scores[current_player] += current_marble
        current_index -= 7
        marble_to_remove = game[current_index]
        game.remove(marble_to_remove)
        scores[current_player] += marble_to_remove
        if current_index < 0:
            current_index = len(game) + 1 + current_index
    else:
        if current_index == len(game) - 1:
            current_index = 1
        else:
            current_index += 2

        game.insert(current_index, current_marble)

    current_marble += 1
    current_player += 1
    if current_player == num_players:
        current_player = 0

print max(scores)
