#!/usr/bin/env python

from collections import deque

def marble_game(players, last):
    game = deque()
    game.append(0)
    scores = [0] * players

    for current_marble in range(1, last):
        if current_marble % 23 == 0:
            current_player = current_marble % players
            scores[current_player] += current_marble
            game.rotate(7)
            scores[current_player] += game.pop()
            game.rotate(-1)
        else:
            game.rotate(-1)
            game.append(current_marble)

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
