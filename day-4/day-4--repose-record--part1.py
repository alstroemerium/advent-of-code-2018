#! /usr/bin/env python

from collections import defaultdict
import operator

def extractMinute(str):
    return int(str.split("] ")[0][-2:])

def extractGuard(str):
    return str.split('#')[1][:4].strip()    #super quick and dirty way

def getSleepiestGuard(dict):
    return sorted(guardNaps.items(), key=operator.itemgetter(1))[-1:][0][0]

def getSleepiestMinute(dict, ele):
    maxVal = 0
    maxIndex = 0
    for i in range(60):
        currentOccurrences = dict[i].count(ele)
        if currentOccurrences > maxVal:
            maxIndex = i
            maxVal = currentOccurrences

    return maxIndex


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

sleepiestGuard = getSleepiestGuard(guardNaps)
sleepiestMinute = getSleepiestMinute(napDictionary, sleepiestGuard)

print sleepiestGuard
print sleepiestMinute

print int(sleepiestGuard) * sleepiestMinute
