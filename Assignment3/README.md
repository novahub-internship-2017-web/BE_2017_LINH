Web application of managing books, writen in Spring Framework
=============================================================

## Summary

This is a Spring MVC web application. This app have some main features:
- Allow user create new accounts
- User registered can create their own books with title, author and description and can modify the information of their books.
- User also can modify their information like first name, last name and password
- User can see all books created by the others. However they can not modify any thing.
- Account had ADMIN role have all privillege of a regular user. Besides it, admin can manage all user registered.

## Clone or download 

Clone all three projects in this repository with command

git clone https://github.com/thanqminh/BE_2017_LINH.git

This assignment in to Assignment3 repository

##Change database configuration
Change database configuration in file resources/db.properties

## Building 

Via commandline:

- Build a war file
  mvn compile war:war

## Deploy
- Change war file name to ROOT.war
- Copy war file in to <catalina_home>/webapps
- Import database in file /resources/sql/BooksManagement.sql
- Start tomcat


