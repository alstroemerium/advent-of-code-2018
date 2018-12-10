#!/usr/bin/env python

from collections import deque

def marble_game(players, last):
    game = deque()
    game.append(0)
    game.append(1)
    scores = [0] * players
    current_player = 2

    for current_marble in range(2, last):
        if current_marble % 23 == 0:
            scores[current_player] += current_marble
            game.rotate(7)
            marble_to_remove = game.pop()
            scores[current_player] += marble_to_remove
            game.rotate(-1)
        else:
            game.rotate(-1)
            game.append(current_marble)

        current_player += 1
        if current_player == num_players:
            current_player = 0

    return max(scores)

input_line = ""
with open("day-9--input") as f:
    input_line = f.readline()

split_input = input_line.split(' ')
num_players = int(split_input[0])
last_marble = int(split_input[-2])
last_marble = last_marble * 100

winner = marble_game(num_players, last_marble)

print winner
