**Technical test task.**

On the launch - app is trying to get data from json, that contains match data and saves it into database.

To change file name of file for fetch json, please refer to `application.properties` 

In total - application covers one endpoint for getting and filtering match data.
 

**Running the app**


_Note: to launch the app successfully - your 8080 and 5432 ports must be available. 

1. Install and run PostgreSQL server
2. Create new database
3. In `application.properties` set url, password and username what you need.
4. Run `mvn clean package`
5. Run `java -jar target/tech-test-1.0-SNAPSHOT.jar`

**API Usage**

Name: **/api/record**

Description: **Return match data based on provided filters** 

Example: 

**/api/matchdata/?statusType=finished&liveStatus=FT&page=1&size=3&sort=time,asc** 

Method: **GET**

Params:

**liveStatus** (optional) : Describes current live match status
 
 Can be FT, HT, -, Cancelled, or minute (example 60+) etc.
 
 **statusType** (optional) : Describes general match status
  
 Can be inprogress, notstarted, finished, canceled 
 
 **page** (optional)  : Specify page that you want to fetch (to reduce latency)
 
 **size** (optional) (default = 1000) : Specify amount of data on one page
 
 **sort** (optional) : Specify matchdata variable, based on which you result will be sorted. `asc` for ascending, `desc` for descending    

