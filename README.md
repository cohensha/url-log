
 I decided to use nested maps, since a TreeMap would allow me easily print the dates
 in chronological order, and data was not deeply nested, so 2 maps would be sufficient.

 ### Space complexity:
  Each entry from the input file is stored in the map once.
  URL's may appear in the map multiple times if accessed on
  different days, but overall space complexity is O(n)

 ### Time complexity:
   Processing the input file:
  For every entry, we format the date ( O(1)) and call putInMap,
  which makes constant containsKey/get/put calls to HashMap ( O(1)) and/or TreeMap (O(logn)).
  The log n access in TreeMap is over number of dates in the key set, but in the worst case
  this would be the same as number of entries in input. So, the runtime for putInMap is O(log n)
  and the total runtime for processing is O(nlogn).
          
  ### Printing the log:
  To print the results, we iterate through the TreeMap, and for each date iterate through its
  URL set in the order of count value. I am not completely of the runtime of this sorting using the
  lambda expressions. But in the worst case runtime, where each entry in the input is a unique date,
  we will print once for every entry, giving a runtime of O(n).

 Overall, the runtime is then O(n logn) for number of entries."# url-log"  git init git add README.md git commit -m "first commit" git remote add origin https://github.com/cohensha/url-log.git git push -u origin master
"# url-log" 
