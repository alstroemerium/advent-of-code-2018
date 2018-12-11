#! /usr/bin/env python

import string
import sys


def react_polymer_ignoring_letter(letter):
    stack = ['']
    with open("day-5--input") as f:
        while True:
            c = f.read(1)
            if not c:
                break   #EOF

            if c != letter and c.swapcase() != letter:
                stack_top = stack[-1]
                if c.swapcase() != stack_top:
                    stack.append(c)
                else:
                    stack.pop()

    return (len(stack)-2)    # -1 for the initial empty str and -1 for the newline character at the end


min_length = sys.maxint
letters = list(string.ascii_lowercase)
for l in letters:
    current_length = react_polymer_ignoring_letter(l)
    if min_length > current_length:
        min_length = current_length

    #print l + " : " + str(current_length)


print min_length
