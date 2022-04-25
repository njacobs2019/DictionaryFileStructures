## This code reads in and parses A.txt and B.txt then writes them out as a tsv

import csv

def import_file(f_name, dataset):
    with open(f_name,'r') as f:
        lines = f.readlines()
    
    for line in lines:
        line = line.strip()

        if(line==""):
            continue

        word, pos = line.split("(",maxsplit=1)
        pos, defn = pos.split(")",maxsplit=1)

        word = remove_tabs(word.strip())
        pos = remove_tabs(pos.strip())
        defn = remove_tabs(defn.strip())

        dataset.append((word,pos,defn))

def remove_tabs(string):
    string_list = string.split("\t")
    return "".join(string_list)

## Read in the files
dataset = []
import_file("A.txt",dataset)
import_file("B.txt",dataset)

## Write the data to csv
header = ['Word name','Part of speech','Definition']

with open('Dataset.tsv','w',newline='') as f:
    writer = csv.writer(f,delimiter='\t')
    
    writer.writerow(header)
#     writer.writerow("Test")
    for line in dataset:
        writer.writerow([line[0],line[1],line[2]])