Make sure you have java 17 or above installed on your computer 
make sure you have git installed on your computer 
clone the source code by command
#git clone https://github.com/SibomanaEdouard/blog_backend
The go in directory called blog_backend by command 
#cd blog_backend
Then install dependencies
#mvn clean install

make sure you have postgresql installed on your pc 
Login in your postgressql
CREATE DATABASE called blogdb 
or other database by using command 
#CREATE DATABASE db_name 
Example 
#CREATE DATABASE blogdb
Import Test  databa by navigating to backup folder which is in resources 
then run this command 
#psql -U username -d db_name -f outputfile.sql
Example
#psql -U postges -d blogdb -f outputfile.sql

Go in application.properties and change database configuration with  your own
I mean database_name
username and userpassword
then Run application 
#mvn spring-boot:run

After running backend then you will run also frontend as it is show in frontend readme file 
Test credentials  are 

{
    "email":"edd@gmail.com",
    "password":"sibo1234"
}

