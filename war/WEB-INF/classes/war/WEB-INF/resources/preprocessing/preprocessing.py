def read(path):
    with open(path, 'r') as f:
        tokens = []

        lines = f.readlines()
        lines = [_.strip() for _ in lines]

        for line in lines:
            tokens.append(line.lower())

        return tokens




#englisch
githubEN = read('tabu/github/en')
wiktionaryEN = read('tabu/wiktionary/English_swear.txt')
mergedEN = set(githubEN +  wiktionaryEN)
outputEN = open('tabu_EN.txt', 'w')
for w in mergedEN:
    outputEN.write(w)
    outputEN.write("\n")
outputEN.close()

#chinesisch
githubZH = read('tabu/github/zh')
wiktionaryZH = read('tabu/wiktionary/Chinese_swear.txt')
mergedZH = set(githubZH +  wiktionaryZH)
outputZH = open('tabu_ZH.txt', 'w')
for w in mergedZH:
    outputZH.write(w)
    outputZH.write("\n")
outputZH.close()


#spanisch
githubES = read('tabu/github/es')
wiktionaryES = read('tabu/wiktionary/Spanish_swear.txt')
mergedES = set(githubES +  wiktionaryES)
outputES = open('tabu_ES.txt', 'w')
for w in mergedES:
    outputES.write(w)
    outputES.write("\n")
outputES.close()


#deutsch
githubDE = read('tabu/github/de')
wiktionaryDE = read('tabu/wiktionary/German_swear.txt')
mergedDE = set(githubDE +  wiktionaryDE)
outputDE = open('tabu_DE.txt', 'w')
for w in mergedDE:
    outputDE.write(w)
    outputDE.write("\n")
outputDE.close()

#japanisch
githubJA = read('tabu/github/ja')
wiktionaryJA = read('tabu/wiktionary/Japanese_swear.txt')
mergedJA = set(githubJA +  wiktionaryJA)
outputJA = open('tabu_JA.txt', 'w')
for w in mergedJA:
    outputJA.write(w)
    outputJA.write("\n")
outputJA.close()

#französisch
githubFR = read('tabu/github/fr')
wiktionaryFR = read('tabu/wiktionary/French_swear.txt')
mergedFR = set(githubFR +  wiktionaryFR)
outputFR = open('tabu_FR.txt', 'w')
for w in mergedFR:
    outputFR.write(w)
    outputFR.write("\n")
outputFR.close()


#arabisch
githubAR = read('tabu/github/ar')
wiktionaryAR = read('tabu/wiktionary/Arabic_swear.txt')
mergedAR = set(githubAR +  wiktionaryAR)
outputAR = open('tabu_AR.txt', 'w')
for w in mergedAR:
    outputAR.write(w)
    outputAR.write("\n")
outputAR.close()

#portugiesisch
githubPT = read('tabu/github/pt')
wiktionaryPT = read('tabu/wiktionary/Portugese_swear.txt')
mergedPT = set(githubPT +  wiktionaryPT)
outputPT = open('tabu_PT.txt', 'w')
for w in mergedPT:
    outputPT.write(w)
    outputPT.write("\n")
outputPT.close()

#italienisch
githubIT = read('tabu/github/pt')
wiktionaryIT = read('tabu/wiktionary/Italian_swear.txt')
mergedIT = set(githubIT +  wiktionaryIT)
outputIT = open('tabu_IT.txt', 'w')
for w in mergedIT:
    outputIT.write(w)
    outputIT.write("\n")
outputIT.close()

#russisch
githubRU = read('tabu/github/ru')
wiktionaryRU = read('tabu/wiktionary/Russian_swear.txt')
mergedRU = set(githubRU +  wiktionaryRU)
outputRU = open('tabu_RU.txt', 'w')
for w in mergedRU:
    outputRU.write(w)
    outputRU.write("\n")
outputRU.close()

#koreanisch
githubKO = read('tabu/github/ko')
wiktionaryKO = read('tabu/wiktionary/Korean_swear.txt')
mergedKO = set(githubKO +  wiktionaryKO)
outputKO = open('tabu_KO.txt', 'w')
for w in mergedKO:
    outputKO.write(w)
    outputKO.write("\n")
outputKO.close()

#indonesisch
#gibts nicht auf github liste

#dutch
githubNL = read('tabu/github/nl')
wiktionaryNL = read('tabu/wiktionary/Dutch_swear.txt')
mergedNL = set(githubNL +  wiktionaryNL)
outputNL = open('tabu_NL.txt', 'w')
for w in mergedNL:
    outputNL.write(w)
    outputNL.write("\n")
outputNL.close()

#hindi
githubHI = read('tabu/github/hi')
wiktionaryHI = read('tabu/wiktionary/Hindi_swear.txt')
mergedHI = set(githubHI +  wiktionaryHI)
outputHI = open('tabu_HI.txt', 'w')
for w in mergedHI:
    outputHI.write(w)
    outputHI.write("\n")
outputHI.close()

#türkisch
githubTR = read('tabu/github/tr')
wiktionaryTR = read('tabu/wiktionary/Turkish_swear.txt')
mergedTR = set(githubTR +  wiktionaryTR)
outputTR = open('tabu_TR.txt', 'w')
for w in mergedTR:
    outputTR.write(w)
    outputTR.write("\n")
outputTR.close()

#urdu
#gibts nicht auf github

#swedisch
githubSV = read('tabu/github/sv')
wiktionarySV = read('tabu/wiktionary/Swedish_swear.txt')
mergedSV = set(githubSV +  wiktionarySV)
outputSV = open('tabu_SV.txt', 'w')
for w in mergedSV:
    outputSV.write(w)
    outputSV.write("\n")
outputSV.close()

#polnisch
githubPL = read('tabu/github/pl')
wiktionaryPL = read('tabu/wiktionary/Polish_swear.txt')
mergedPL = set(githubPL +  wiktionaryPL)
outputPL = open('tabu_PL.txt', 'w')
for w in mergedPL:
    outputPL.write(w)
    outputPL.write("\n")
outputPL.close()

#persisch
githubFA = read('tabu/github/fa')
wiktionaryFA = read('tabu/wiktionary/Persian_swear.txt')
mergedFA = set(githubFA +  wiktionaryFA)
outputFA = open('tabu_FA.txt', 'w')
for w in mergedFA:
    outputFA.write(w)
    outputFA.write("\n")
outputFA.close()

#thai
githubTH = read('tabu/github/th')
wiktionaryTH = read('tabu/wiktionary/Thai_swear.txt')
mergedTH = set(githubTH +  wiktionaryTH)
outputTH = open('tabu_TH.txt', 'w')
for w in mergedTH:
    outputTH.write(w)
    outputTH.write("\n")
outputTH.close()




