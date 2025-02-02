# This project allows users to upload a csv file to create different types of entities based on the uploadType. Here uploadType refers to a specific entity.
2.  Also inorder to validate field sizes of entity dynamically, declare them in properties file
3.  In the annotations of fields bind the "name of property" from which the value should be fetched.
4.  It reads the value from properties file and validates the fields of entities based on them
5.  This is a library module which can be re used in other applications. In order to use it as a library please follow the below commands:
6.  clone the repository using the link https://github.com/vravikiran/generic-upload.git
7.  navigate to the directory and build the project using command "gradle build".
8.  Once the build is completed publish the repositorty to maven using command "gradle publishMavenToLocal".It publishes the repository to .m2 folder.
9.  To import it as a library in another application. Please follow the below commands:
10.  create a new spring boot application. In build.gradle file add the dependency in the dependencies section as below:
"implementation 'com.generic:uploadservice:0.0.1'"
11.  After adding build the project and run the application. You can call the "REST" endpoints of library project created above in the current application.
12.  Please add the required properties in the current application too
