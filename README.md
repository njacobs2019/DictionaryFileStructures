
# Dictionary File Structures

This was the final project for UMaine's COS 226, Data Structures and Algorithms, class spring 2022.<br />
Nicholas Jacobs and Nate Roberts

## Running the Program
To run the program, download the files and compile them with the Java Development Kit.  Run **Main.java** to demo the functionalities of the program.  All scripts \*_Tester.java were used for testing the program's functionality during development and are not needed to run the program.  Tune.java was used to tune the size of the B+ tree's buckets, size of the hashmap's array, and hashing algorithm.


## Tuning the Data Structures
There is a trade-off with the B+ tree's bucket size.  The bigger the bucket, the shorter the tree which helps with searching and adding.  It helps with adding as there need to be fewer split operations. The downside is that the bigger the bucket, the longer the tree takes to linearly search through each bucket which hurts performance.  The graph below shows the bucket size and the time it took to fill the tree.  The optimal bucket size for this problem is around 20.


![Bucket Size vs Fill Time Graph](https://raw.githubusercontent.com/njacobs2019/DictionaryFileStructures/main/Other/BucketSize.png)


The hashmap has a tradeoff when choosing the array size.  The larger the array, the more space in memory it takes up as more indices might be empty, but operations are faster.  For example, when searching, the buckets at each array index are smaller, leading to faster searches.  The array was varied from 100 to 2,000 elements and the time to fill it was measured.  This test showed that after an array size of 300, improvement in fill time was negligible.  Additionally with a size of 300, none of the array's indices had empty buckets.
![Table Size vs Fill Time Graph](https://raw.githubusercontent.com/njacobs2019/DictionaryFileStructures/main/Other/TableSize.png)

The ideal hash function for a hashmap should evenly distribute the data among the array's indices.  If a function were to map all words to a single index, the data structure would turn into a linked list and would have very slow performance.  Standard deviation of bucket sizes was used to measure the performance of the hash function.  The ideal hash function would have a standard deviation of zero.  The table below show the standard deviation for the SHA-1, SHA-256, and MD5 hash algorithms with hash array lengths of 100, 1000, and 5000.  The standard deviations for each array size were normalized by dividing them by the average standard deviation for that array size. Summing the normalized standard deviation for each algorithm shows that SHA-1 has the lowest standard deviation across a range of array sizes and was chosen.
![Chart of Hashing Algorithm Performance](https://raw.githubusercontent.com/njacobs2019/DictionaryFileStructures/main/Other/HashFunctionTune.png)



## Project Goals/Requirements
For this project you’ll be comparing the process time of two different styles of data storage. Hash tables and B+ Trees.

Your goal is to store an undetermined number of words with their dictionary definitions into the data structure so that the user can quickly and efficiently get access to them through a word search. There are online text dumps of dictionaries that you can use for this project, you’ll just
need to be able to parse them.

Each “word” might have several definitions, so make sure the structure holding the word and the definitions can hold multiple definitions (and print them out when called on).

For both the hash table and B+ tree, it needs to be able to add, search, full print, and partial print. Partial print would be searching for a word, then printing the next N number of words that are alphabetically after that word.

Set up your program so that it keeps track of how long the operations take so that you can compare the two.
