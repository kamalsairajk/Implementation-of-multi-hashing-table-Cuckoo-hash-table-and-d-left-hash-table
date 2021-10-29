# Implementation-of-multi-hashing-table-Cuckoo-hash-table-and-d-left-hash-table

About the code:
The source code includes a mainrun.java file which has classes mainrun which is basically runs the file,
flowcounter java class which contains flowid and counter variables, MultiHashTable class,Cuckoohashtable class,
Dlefthashtable class, conatins s array for the multiple hash functions, numentries is the number of entries into the table,
numhash is the number of hash functions needed to be created, size being the total size of the array and table being the
array to which entries will be inserted into in the cases of Multihashtable and Cuckoohashtable whereas the Dlefthashtable
has the segment array which is a two dimensional array which is divided into number of segments and each being a small part
of the total size. Every class has an insert function for entries to be inserted into the table and Cuckoohashtable has move
function to implement the move functionality.

Input tried:
The code generates flows and entries to be equal.
1) Multi Hashing Table = number of entries 1000, number of hash functions 3
2) Cuckoo Hash Table = number of entries 1000, number of hash functions 3, number of steps 2
3) D Left Hash Table = number of entries 1000, number of hash functions 4, number of segments 4

Output:
Output files attached for the above input.

Steps To Run the project:
1) Import the project into an IDE such as Eclipse or IntelliJ and run the mainrun.java file.
2) Enter 1 for Multi Hashing Table Implementation, 2 for Cuckoo Hash Table implementation and 3 for D Left Hash
Table Implementation.
3) Enter the number of flows/entries and subsequently other parameters when prompted.
4) The output would print the number of flow entries in the hash table and the flow ids of the flows which were inserted
into the hash table.
Also output files are simultaneously created and output is written into the files while printing.
