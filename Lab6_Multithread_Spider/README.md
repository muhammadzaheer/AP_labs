Multithreaded Spider

This simple program recursively indexes the local filesystem and allows 
the user to search for a particular keyword.

It uses three threads to crawl, three threads to index and two threads to search.


Spider's API:

s.index (File root)
    Builts an in memory index for root.
    Accepts a file handler of your root directory

s.search (String keyword)
     Searches for a keyword in the index and prints out 
     absolute paths of files/directories with the given keyword

Use case example:
If you want to search in /Users/smzaheerabbas/test directory,
you could first build an index as:
s.index( new File ("/Users/smzaheerabbas/test");

To search a particular keyword within the index:
s.search("foo");

Implementation of Index:
Index is implented with a HashMap of keywords against List of paths 
where the keyword is found. 
Before outputting search results, list is converted to a set for 
removing duplicates.

Author: M. Zaheer
Github URL: https://github.com/muhammadzaheer/AP_labs/tree/master/Lab6_Spider 
