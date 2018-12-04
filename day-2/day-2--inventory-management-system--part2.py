#! /usr/bin/env python

import collections
from difflib import SequenceMatcher

f = open("day-2--input", "r")

box_ids = []

for input in f:
    for id in box_ids:
        string_distance = SequenceMatcher(a=input, b=id).ratio()
        if string_distance > 0.9615384:
            print id + "\n" + input
            quit
    box_ids.append(input)
