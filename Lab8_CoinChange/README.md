Introduction:
Change-making problem addresses the following question: how can a given amount 
of money be made with the least number of coins of given denominations? In 
simple words, it is the problem of representing a given value with the fewest 
coins possible.

API:

GreedyCC and DynamicCC have same APIs.

Construct an object by passing an int of denominations e.g:
  GreedyCC gcc = new GreedyCC (new int [] {1, 2, 5, 10});

Get change by using the call:
  ArrayList <Integer> change = gcc.getChange(total);

Elements of list represent the actual coins for change whereas the size
of list represents the number of coins for optimal solution.
  int n = change.size();

Analysis:

Total: 62215
Greedy: 2490
Dynamic: 2490

Total: 72744
Greedy: 2915
Dynamic: 2915

Total: 54929
Greedy: 2201
Dynamic: 2201

Total: 27144
Greedy: 1091
Dynamic: 1091

Total: 72437
Greedy: 2900
Dynamic: 2900

Total: 23010
Greedy: 921
Dynamic: 921

Total: 16350
Greedy: 654
Dynamic: 654

Total: 6670
Greedy: 268
Dynamic: 268

Total: 1093
Greedy: 48
Dynamic: 48

Total: 85771
Greedy: 3433
Dynamic: 3433

How to run your application:
Import the project into Netbeans and execute Test.java

Github URL:
https://github.com/muhammadzaheer/AP_labs/tree/master/Lab8_CoinChange

