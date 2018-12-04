#! /usr/bin/env python

import collections

f = open("day-2--input", "r")

contains_two = 0
contains_three = 0

for input in f:
    frequency_dictionary = collections.Counter(input)
    dictionary_values = frequency_dictionary.values()
    if 2 in dictionary_values:
        contains_two += 1
    if 3 in dictionary_values:
        contains_three += 1

print contains_two * contains_three
