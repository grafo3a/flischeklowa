# About FlischeKlowa

Presentation
-------------
FlischeKlowa stands for Flight Scheduler of Klow Airport.

Klow Airport is the main airport of Klow.
Klow is the capital city of Syldavia, an imaginary country in the Adventures of Tintin.
Although it exists only in the fiction world, the airport is probably located somewhere around Warsaw in Poland.

Using FlischeKlowa gives you an opportunity to have the power and responsibilities of a flight scheduling administrator in an airport.
Come on, have fun and schedule flights by exploring FlischeKlowa.

Technical aspects
-----------------
As a programming project, FlischeKlowa is an application based on Java EE 8.
The author of the project is the programmer Joseph B. Apasa.

The following technologies are used in the project:
- Servlet 4 with Filter;
- JSP with JSTL;
- G11N (globalization including I18n);
- JDBC via DriverManager and DataSource (connection pool);
- JPA via Hibernate (for ORM data persistence);
- JSF 2.3 via Mojarra;
- CDI via Weld (for dependency injections);
- JAX-RS via Jersey (for exposing REST web services);
- JavaScript (plain aka Vanilla);
- HTML, CSS, Bootstrap;
- Maven, JUnit 5, tinylog;
- Databases: H2 (in-memory) and PosgreSQL (in cloud).

The entry point to the application is the root URL mapping "/" in the Servlet "ServletHoraireVols".

By default, the app uses the in-memory H2 database.
To use a different database, you'll need to update the 2 following files correctly:
* paquetBDD.properties (located in src/net.apasajb.flischeklowa.ressourcesG11n, used by the connection pool)
* persistence.xml (located in src/META-INF, used by the entity manager factory).

To run the app, export the project as a WAR file then deploy the file on Tomcat [9] or any other application server supporting Java EE 8. You can also run the app through an IDE.

Screenshots
-----------

![flischeklowa_screenshot_01.png](/WebContent/common/images/flischeklowa_screenshot_01.png?raw=true)
