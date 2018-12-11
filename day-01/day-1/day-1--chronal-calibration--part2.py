#! /usr/bin/env python

current_frequency = 0
resulting_frequency = 0
frequency_history = []
end_condition = False

while not end_condition:
    f = open("day-1--input", "r")
    for input in f:
        frequency_history.append(current_frequency)
        frequency_delta = int(input)
        resulting_frequency = current_frequency + frequency_delta
        if resulting_frequency in frequency_history:
            print resulting_frequency
            end_condition = True
            break
        else:
            current_frequency = resulting_frequency
