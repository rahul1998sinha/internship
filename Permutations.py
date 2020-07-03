from itertools import permutations  # importing the itertools module
import csv
import sys

pathOfFile = sys.argv[1]  # taking the file name as path

with open(pathOfFile, "rt") as file:
    dataOfFile = list(csv.reader(file))  # create a list of the words
result = []


def printer(data):
    for i in data:
        for j in i:
            x = list(permutations(j))
            for k in range(len(x)):
                s = "".join(x[k])
                x[k] = s
            result.append(x)
    for i in result:
        for j in i:
            print(j)


printer(dataOfFile)
