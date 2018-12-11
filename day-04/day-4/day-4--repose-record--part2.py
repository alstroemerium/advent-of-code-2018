#! /usr/bin/env python

from collections import defaultdict
from collections import Counter
import operator
import re

def extractMinute(str):
    return int(str.split("] ")[0][-2:])

def extractGuard(str):
    pattern = ("(?<=#)(\d)*")
    return re.search(pattern, str).group(0)

def getTheSleepiest(dict):
    mostCommonGuard = 0
    mostCommonMinute = 0
    maxOccurrences = 0
    for i in range(60):
        dataForThisMinute = dict[i]
        if dataForThisMinute:
            mostCommonThisMinute = Counter(dataForThisMinute).most_common(1)[0]

            if mostCommonThisMinute[1] > maxOccurrences:
                mostCommonGuard = mostCommonThisMinute[0]
                mostCommonMinute = i
                maxOccurrences = mostCommonThisMinute[1]

    return [ int(mostCommonGuard), mostCommonMinute ]


currentGuard = ""
napStart = 0
napDictionary = defaultdict(list)   # (max) 60 element dictionary containing what guards slept at which minute
guardNaps = defaultdict(int)        # keeps track of the total minutes slept by each guard

f = open("day-4--input--sorted", "r")

for line in f:
    currentMinute = extractMinute(line)

    # assume all sleeping occurs in the same hour
    if "falls asleep" in line:
        napStart = currentMinute
    elif "wakes up" in line:
        for i in range(napStart, currentMinute):
            napDictionary[i].append(currentGuard)
        guardNaps[currentGuard] = guardNaps[currentGuard] + (currentMinute - napStart)

    if "begins shift" in line:
        currentGuard = extractGuard(line)

answer = getTheSleepiest(napDictionary)
print answer
print answer[0]*answer[1]
