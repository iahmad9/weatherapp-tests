# Automated Tests For Backend APIs
These are the tests to verify the functionality of RESTful API endpoint weather-app-api for weather app exposed at

https://< BASEURL >/api

where BASEURL is a server address where weather-app-api service is hosted


These tests are mostly data driven, so that these can be extended by simply adding more test data sets to verify different permutations of post code and error messages etc.

To build and run these tests from IDE or Commandline, please follow the instructions given below

Please make sure environment is setup as per the instructions in README under parent folder of this project.

## How to Build and Run from Command Line
* Clone the project and cd into the backend folder
	```
	$ git clone git@github.com:iahmad9/weatherapp-tests.git
  	$ cd weatherapp-tests/backend
  	```

* To build and execute the tests using Maven binary
  	
  	```
  	$ mvn test
  	```

* Results can be found backend/target folder
  
## How to Build and Run from Eclipse IDE
* Launch Eclipse and choose **File -> Open Projects from File System**

* Browse to weatherapp-tests/backend folder and press **Finish**

* Once project is loaded to the workspace, wait for dependencies to be loaded.

* Expand the project tree in Package Explorer and right click on pom.xml

* From menu, choose **Run As -> Maven test**

* Once execution is finished, results will be available under /target folder
