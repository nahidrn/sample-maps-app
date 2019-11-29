# sample-maps-app

# Summary:

The project shows a simple map application with user-management funtionality.

Location Map

One event location and multiple point of interests can be selected and they are all shown together in a mup showing the path and distances.

Location Management

There are two types of locations: Events and Point of Interests. Authorized user can create, edit and delete locations. A location is linked to one address showing One to One relation.

User Management

One can create a new user, edit or delete an existing user, and list all the users. User can be associated with one or more UserProfile, showing many-to-many relationship. URL’s of the applications are secured using Spring Security. That means, based on the roles of logged in user, access to certain URL’s will be granted or prohibited. On the view layer, user will see only the content he/she is allowed to based on the roles assigned to him/her, thanks to Spring Security tags for view layer.

# Following technologies being used:

    Spring Framework 4.3.0.RELEASE
    Spring Security 4.0.2
    Hibernate 4.3.5.FINAL
    hibernate-validator 5.1.3.Final
    PostGres 12.1
    Maven 3.6.0
    JDK 1.8
    Tomcat 9.0.27
    Eclipse Neon 


# Steps for running

1. Clone repository by this command- git clone https://github.com/nahidrn/sample-maps-app.git

2. Import project in Eclipse: 
Click 'File->Open projects from file system' and then select project from directory. After that click Finish button to complete importing.

3. Configure sample-maps-app/src/main/resources/application.properties
  - provide your postgres dbname, url
  - Change the hibernate.hbm2ddl.auto = update to hibernate.hbm2ddl.auto = create-drop
  (This will drop the current tables and crete new one in db of your public schema. PLEASE CHANGE IT TO THE INITIAL UPDATE VALUE AFTER THE FIRST RUN)
  
4.
  - Go to project directory by this command - 
  ```
  cd sample-maps-app__ 
  ```
  - Then run this command for creating war file within target directory - 
  ```
  mvn clean package
  ```
  - Then run your tomcat and put this war file to webapps directory. As a example 
  ```
  cp -r sample-maps-app/target/sample-maps-app.war to webapps/sample-maps-app.war
  ```
  - Now you can go to your browser and type http://localhost:8080/sample-maps-app . Project will successfully run.
  
5. Run the following sql queries to create first admin user and seed locations.
```
INSERT INTO USER_PROFILE(type)
VALUES ('USER');
  
INSERT INTO USER_PROFILE(type)
VALUES ('ADMIN');
  
INSERT INTO USER_PROFILE(type)
VALUES ('DBA');  
INSERT INTO sample_maps_db.public.USER(name, password, is_active)
VALUES ('admin','$2a$10$D9O5DheIuJ1N5Hh6JOgIOOY3KSSnNh5PQM5U6FexmlhNpA7X2euOe', true);
 
INSERT INTO USER_USER_PROFILE (user_id, user_profile_id)
  SELECT sample_maps_db.public.user.id, profile.id FROM sample_maps_db.public.user, user_profile profile
  where sample_maps_db.public.user.name='admin' and profile.type='ADMIN';
  
```
Now you can login to the application using following id and password
```
User Name: __admin__
Password: __ThisIsASecuredPassword :)__
```
# Project Structure

The project was created using Spring's annotation based structure. I chose annotations over xml configurations because of their simplicity and better readability. The project is divided in the following layers.
1. Entity
  - All the entiy classes have been annotated with @Entity which tells Hibernate that this class represents an object that we can persist. The @Table(name = "...") annotation tells Hibernate which table to map properties in this class to. The first property in this class is our object ID which will be unique for all events persisted. This is why we’ve annotated it with @Id.The @GeneratedValue annotation says that this value will be determined by the datasource, not by the code.@Column(name = "...") annotation is used to map the property name column in the related table. 
  (e.g. __src/main/java/com/mm/app/model/Location.java__)

2. DAO  Layer
  - @Repository annotation was provided in all the classes in DAO layer. It is yet another stereotype that was introduced inSpring 2.0. This annotation is used to indicate that a class functions as arepository and needs to have exception translation applied transparently on it. Thebenefit of exception translation is that the service layer only has to deal withexceptions from Spring’s DataAccessException hierarchy, even when using plain JPA in the DAO classes. All DAO classes are interfaces that are implemented in impl folder under same directory.
  (e.g. __src/main/java/com/mm/app/dao/LocationDao.java__)
  
3. Service Layer
  - This Service Layer act as a bridge between the DAO (Persistence) layer and thePresentation (Web) layer. Even in service layer similar to DAO layer we have theinterface and its implementation. In the ServiceImpl class, we are using mainly three Spring annotations:
 @Service, @Transactional and @Autowired
 @Service: Indicates that the annotated class is a "Service". A stereotype of @Component for persistence layer
 @Transactional: Enables Spring's transactional behaviour.Can be applied to a class, an interface or a method. This annotation is enabled by setting in the context configuration file.The attribute readOnly = true which sets the transaction to read only mode sothat it cannot modify data in any case.
 @Autowired: Marks the field as to be autowired by Spring's dependency injection facilities. The field is injected right after construction of the bean, before any config methods are invoked. Autowires the bean by matching the data type in the configuration metadata.
 (e.g. __src/main/java/com/mm/app/service/impl/LocationServiceImpl__)
 
 4. Presentation Layer
   - A controller that handles the web requests dispatched via.DispatcherServlet. @Controller:Indicates that the annotated PhoneController class serves the role of a "Controller". A stereotype of @Component for web layer.
 (e.g. __src/main/java/com/mm/app/controller/impl/LocationController__)
 
 # Future Plans
 
 1. Adding Docker for ci/cd pipeline implementation.
 2. Adding JQuery for UI side.
 3. Adding sitemesh for templating in jsp.
 4. Maybe shifting the googe maps api implementation to server side.
 5. Implementation frond-end validations for all forms
 
 # Current Concerns
 
 __1. The map is currently only showing directions/distance for one event location to one point of interest using DirectionsService. This needs to support multiple point of interest to one event location distances and routes.__
 
 __2. Currrent test suit covers 100% of the services, 80% of the models and 0% of the conrollers and dao classes.__

 __3. Code needs to be refactored.__
