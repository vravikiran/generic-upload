This project allows users to upload a csv file to create different types of entities based on the uploadType. Here uploadType refers to a specific entity.
This is a library module which can be re used in other applications. In order to use it as a library please follow the below commands:
clone the repository using the link https://github.com/vravikiran/generic-upload.git
navigate to the directory and build the project using command "gradle build".
Once the build is completed publish the repositorty to maven using command "gradle publishMavenToLocal".It publishes the repository to .m2 folder.
To import it as a library in another application. Please follow the below commands:
create a new spring boot application. In build.gradle file add the dependency in the dependencies section as below:
implementation 'com.generic:uploadservice:0.0.1'
After adding build the project and run the application. You can call the "REST" endpoints of library project created above in the current application.
Please add the required properties in the current application too.
