Web application of managing books, written in Spring Boot
=============================================================

## Summary

This is a Spring Boot web application. This app have some main features:
- Allow user create new accounts
- User registered can create their own books with title, author and description and can modify the information of their books.
- User also can modify their information like first name, last name and password
- User can see all books enabled and their disabled books.
- Anonymous user can see all enabled books
- Account had ADMIN role have all privilege of a regular user. Besides it, admin can manage all user registered.

## Clone or download 

Clone all three projects in this repository with command
````
git clone https://github.com/novahub-internship-2017-web/BE_2017_LINH.git
````

This assignment in to Assignment4 repository

##Change database configuration
Change database configuration in file resources/application.properties
Import database from file resources/booksmanagements-springboot.sql

## Building and running via command line

````
  mvn spring-boot:run
````

##Test account
````
ADMIN
email: admin@gmail.com
password: password

REGULAR USER
email: user@gmail.com
password: password
````


