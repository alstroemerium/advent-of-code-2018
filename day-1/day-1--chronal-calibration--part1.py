#! /usr/bin/env python

f = open("day-1--input", "r")
resulting_frequency = 0
for input in f:
    resulting_frequency += int(input)

print resulting_frequency
