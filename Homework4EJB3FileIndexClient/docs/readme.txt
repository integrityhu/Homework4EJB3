Install Interface
-----------------
EJB3FileIndexServiceInterface:
mvn install (~/.m2/repository/hu/infokristaly/homework/EJB3FileIndexServiceInterface/0.0.1-SNAPSHOT

pom.xml -> Dependencies -> remove EJB3FileIndexServiceInterface
pom.xml -> Dependencies -> Enter groupId, ArtifactId ... : EJB3FileIndexServiceInterface

Add an Application User
----------------
This quickstart uses secured management interfaces and requires that you create an application user to access the running application. Instructions to set up the quickstart application user can be found here: [Add an Application User](../README.md#addapplicationuser)
user: quickstartUser
password: quickstartPassword (or quickstartUser)
roles: guest

C:\jboss-as-7.1.1.Final\bin>add-user.bat

What type of user do you wish to add?
 a) Management User (mgmt-users.properties)
 b) Application User (application-users.properties)
(a): b

Enter the details of the new user to add.
Realm (ApplicationRealm) :
Username : quickstartUser
Password :
Re-enter Password :
What roles do you want this user to belong to? (Please enter a comma separated list, or leave blank for none) : guest
About to add user 'quickstartUser' for realm 'ApplicationRealm'
Is this correct yes/no? yes
Added user 'quickstartUser' to file 'C:\jboss-as-7.1.1.Final\standalone\configuration\application-users.properties'
Added user 'quickstartUser' to file 'C:\jboss-as-7.1.1.Final\domain\configuration\application-users.properties'
Added user 'quickstartUser' with roles CONSUME to file 'C:\jboss-as-7.1.1.Final\standalone\configuration\application-roles.properties'
Added user 'quickstartUser' with roles CONSUME to file 'C:\jboss-as-7.1.1.Final\domain\configuration\application-roles.properties'
