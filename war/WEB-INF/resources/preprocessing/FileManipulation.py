import csv

#creates a new file with the missing elements
def checkWordAvailability(path1, path2, outputFileName):
    with open(path1, 'r', encoding='utf-8') as firstFile, open(path2, 'r', encoding='utf-8') as secondFile, open(outputFileName, 'w') as outputFile:
        output = csv.writer(outputFile)

        elementsToCheck = set()
        linesFirst = firstFile.readlines()
        for line1 in linesFirst:
            if line1.strip():
                #print(line)
                elementsToCheck.add(line1.strip())

        elementsToCheckAgainst = set()
        linesSecond = secondFile.readlines()
        for line2 in linesSecond:
            if line2.strip():
                #print(line)
                elementsToCheckAgainst.add(line2.strip())

        for element in elementsToCheckAgainst:
            if element not in elementsToCheck:
                output.writerow(element)
                #print(element)
            #else:
                #print(element)

        print('done')



checkWordAvailability('IPASymbols.txt', 'UsedIPA.txt', 'missingIPA.txt')