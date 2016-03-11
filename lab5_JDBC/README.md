Introduction:
This lab uses JDBC to load a CSV file of City Data into a MYSQL database.

1. A table is created into MySQL database
2. The records are inserted into the database
3. User can search into the records using latitude and longitude values
   to find a city name
4. User can also provide a city name to find nearby cities using box 
   approximation.

API:
1. CityManager.createTable()
2. CityManager.readCSV(csFileName)
3. CityManager.searchCity(latitude, longitude)
4. CityManager.searchNearby(city)

Test:
lab5.Test.java: Tests all corresponding functions

Author: M.Zaheer
Github URL: https://github.com/muhammadzaheer/AP_labs/tree/master/lab5_JDBC 




