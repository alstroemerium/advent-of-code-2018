#! /usr/bin/env python

stack = ['']

with open("day-5--input") as f:
    while True:
        c = f.read(1)
        if not c:
            break   #EOF

        stack_top = stack[-1]
        if c.swapcase() != stack_top:
            stack.append(c)
        else:
            stack.pop()

print (len(stack)-2)    # -1 for the initial empty str and -1 for the newline character at the end
