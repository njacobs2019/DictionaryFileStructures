# This is the final project for UMaine's COS 226 (Data Structures and Algorithms).

![alt text](https://raw.githubusercontent.com/njacobs2019/DictionaryFileStructures/main/Other/TableSize.png)



For this project you’ll be comparing the process time of two different styles of data storage. Hash tables and B+ Trees.

Your goal is to store an undetermined number of words with their dictionary definitions into the data structure so that the user can quickly and efficiently get access to them through a word search. There are online text dumps of dictionaries that you can use for this project, you’ll just
need to be able to parse them.

Each “word” might have several definitions, so make sure the structure holding the word and the definitions can hold multiple definitions (and print them out when called on).

For both the hash table and B+ tree, it needs to be able to add, search, remove, full print, and partial print. Partial print would be searching for a word, then printing the next N number of words that are alphabetically after that word.

Set up your program so that it keeps track of how long the operations take so that you can compare the two.