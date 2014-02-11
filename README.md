
mule-module-jpa-osiv
=======
An intercepting mule-module that implements the open-session-in-view pattern 
for jpa across components in a mule flow. It manages obtaining, exposing, and
 closing a jpa EntityManager for the mule processor chain it precedes in a 
 mule flow.

The intended use case is one where we call one or more java components/dao services from a mule flow that use JPA. Some of the entities in the JPA access may have lazily initialized relationships and we use jackson mixins to vary the parts of the entities to serialize on the way out of a REST api implemented in Mule. We need to keep the entities attached to an EntityManager while serialization is happening but we also need to ensure that the EntityManager is closed after the view is rendered so we dont leak connections and dont read dirty stuff from JPA 1st level cache.

This module implements an intercepting processor in mule nearly identical to how the org.mule.interceptor.AbstractEnvelopeInterceptor behaves. When placed at the beginning of a flow (or the beginning of a processor-chain) it will be invoked before, after, and finally on that flow/processor-chain.

In the before phase it will place an EntityManager in a flowVar to be used as a parm in calls to custom java components that implement data services/daos.

In the after phase it does nothing.

In the finally phase, it will close the EntityManager and remove the flowVariable it was placed in. The finally phase will run even if the surrounding processor-chain throws and exception.


Current state
=======
Functional but poorly documented, lacking serious tests, and only beginning to be used in any serious way in some of my dev projects. Seems promising enough to place here and perhaps that will motivate me to clean it up and make available to community.


./pom.xml:
A maven project descriptor that describes how to build this module.

./LICENSE.md:
The open source license text for this project.

BUILDING
=======

Maven is your friend. 

mvn clean package -Ddevkit.studio.package.skip=false
mvn install

For Mulsoft details on dealing with DevKit modules see: 
http://www.mulesoft.org/documentation/display/current/Installing+and+Testing+Your+Connector



TESTING
=======

This  project also contains test classes that can be run as part of a test
suite...but they barely do anything atm.

ADDITIONAL RESOURCES
====================
Everything you need to know about getting started with Mule can be found here:
http://www.mulesoft.org/documentation/display/MULE3INTRO/Home

There further useful information about extending Mule here:
http://www.mulesoft.org/documentation/display/DEVKIT/Home

Remember if you get stuck you can try getting help on the Mule user list:
http://www.mulesoft.org/email-lists

Also, MuleSoft, the company behind Mule, offers 24x7 support options:
http://www.mulesoft.com/enterprise-subscriptions-and-support

Enjoy your Mule ride!

The Mule Team
