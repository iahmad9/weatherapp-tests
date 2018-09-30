# Front End Automated Tests

These are user acceptance tests, written following a BDD approach. These tests focus on end to end use cases and verify that application is built as per the requirements given by the product owner.

To build and run these tests, make sure environment is setup as per the instructions given in README in parent folder of this project.

## How to Build and Run from command line

* Clone the project and cd into the frontend folder
	```
	$ git clone git@github.com:iahmad9/weatherapp-tests.git
  	$ cd weatherapp-tests/frontend
  	```

* To build and execute the tests using Maven binary
  	
  	```
  	$ mvn test
  	```

* Results can be found under frontend/target folder in different formats including html and json for better integration with CI systems.

 
## How to Build and Run from Eclipse IDE
* Launch Eclipse and choose **File -> Open Projects from File System**

* Browse to weatherapp-tests/frontend folder and press **Finish**

* Once project is loaded to the workspace, wait for dependencies to be loaded.

* Expand the project tree in Package Explorer and right click on pom.xml

* From menu, choose **Run As -> Maven test**

* Once execution is finished, results will be available under /target folder
