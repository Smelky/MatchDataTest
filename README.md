1. Run `mvn clean package`
2. Run `java -jar target/test-1.0-SNAPSHOT.jar`

API Usage

Name: "/api/matchdata"

Description: return match data based on provided filters

Example: 

/api/matchdata/?statusType=inprogress&page=1&size=2&sort=time,asc

Method: GET

Params:

 liveStatus (optional) : Describes current live match status
  
 statusType (optional) : Describes general match status
     

