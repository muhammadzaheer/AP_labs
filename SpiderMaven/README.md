Local Spider v3 - (MAVEN - Regex - File Attributes)

Introduction:
This simple program recursively indexes the local filesystem and allows 
the user to search for a particular keyword.

It also allows user to search on the basis of attributes of a file i.e. file
size, number of subitems, last modified.

JAVADOCS:
Javadocs can be found in the subfolder /target/site.

Spider's API:

s.index (File root)
    Builts an in memory index for root.
    Accepts a file handler of your root directory

s.search (String keyword)
     Searches for a keyword in the index and prints out 
     absolute paths of files/directories with the given keyword

s.searchRegex(String pattern)
    Search for a given pattern in the index 

s.searchSize(double low, double high)
    Search for a file with size within the given range

s.searchSubItems(int low, int high)
    Search for a file with no of subitems within the given range

s.searchLastModified(long low, long high)
    Search for a file with lastModified attribute within the given range

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

For each attribute, a separate list of Pair is maintained.

Author: M. Zaheer
Github URL: https://github.com/muhammadzaheer/AP_labs/tree/master/SpiderMaven 

How to run:
Import the project in your favourite IDE (e.g. Netbeans) and click on the run button to execute the test cases.
