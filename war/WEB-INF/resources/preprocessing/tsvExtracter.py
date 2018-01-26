#for extracting specific content from tsv files

import csv

#cols is a list of numbers which specify which columns should be extracted
def extractColumns(path, cols, name):
    with open(path, 'r') as tsvin, open(name, 'w') as t:
        tsvin = csv.reader(tsvin, delimiter='\t')
        tsvout = csv.writer(t, delimiter='\t')

        for row in tsvin:
            rowNew = []
            for col in cols:
                rowNew.append(row[col])
            tsvout.writerow(rowNew)






extractColumns('zh-wiki-preprocessed-20180110.tsv', [0,2], 'zh-kanji.tsv')