# Overview

This is an example of how to use pure aspectj to enhance existing Java classes via AOP. Doing so may be useful in many scenarios when you don't have control over source code of a class but its functionality needs to be enhanced nonetheless.

In our case we will be adding logging to invocations of `java.sql.Connection#prepareStatement` methods, so that text of the compiled SQL statements will become visible to us.


# Steps

Following steps need to be taken to apply aspectj aspects in an existing Java application.

1. Define aspects in @Aspect-annotated class(es). See `aspects.PreparedStatementLogger` class in this project.

2. Compile the @Aspect-annotated classes and put them to your application's classpath.

3. Place `META-INF/aop.xml` file on your classpath:
````
<aspectj>
    <aspects>
        <!-- fully qualified names of @Aspect-annotated classes -->
        <aspect name="aspects.PreparedStatementLogger"/>
    </aspects>

    <weaver options="-verbose -debug">
        <!-- don't forget to include the packages with @Aspect-annotated classes -->
        <include within="aspects.*"/>

        <!-- restrict classes to apply the aspects to (for faster application startup) -->
        <include within="net.sourceforge.jtds.jdbc.*"/>
    </weaver>
</aspectj>
````

4. Download `aspectjweaver.jar` of appropriate version and start the target Java application with `-javaagent:/path_to_aspectjweaver.jar` argument.

5. See stdout for aspectj logging and outputs written by your aspects.


Note: For Tomcat-hosted web applications, directory `webapps/<web-app>/WEB-INF/classes` is always part of the classpath and this directory can be used in Steps 2 and 3.
