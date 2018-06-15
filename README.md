# sonar-stackoverflow-enhancer
Sonar StackOverflow Enhancer is an application created with springboot, using REST forthe api calls, jackson for data conversions,redis for storage, and [stackexchange](https://stackexchange.com/) for the API calls to [stackoverflow](https://stackoverflow.com/)
##### Easy start 
If you have docker installed, you can use the docker compose file and skip all the needed setup. Just be in the same directory where the docker-compose.yml file is located, then type 
```
docker compose up -d 
```
or if you want the see the output of the program running, don't use the -d flag 

##### Requirements for application
* Java JDK 1.8.0+ 
* Gradle 4.5+
* Redis server (Default port: 6379)
* Sonar cube server with postgres (Docker compose file included)

