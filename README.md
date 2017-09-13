# Embedded Tomcat deploying war and using JNDI

The scenario:
- Deploy a war application as a standalone app
- Support JNDI to expose a java.sql.DataSource object

This example demonstrates how to deploy a war file on tomcat.
The war file expect a java.sql.DataSource object to be exposed via JNDI.

There are two projects
- [tomcat](./tomcat) - Contains a [main method](./tomcat/src/main/java/Tomcat.kt) to run the tomcat container
- [war](./war) - A spring MVC application packaged as a war which retrives a java.sql.DataSource from a JNDI source
