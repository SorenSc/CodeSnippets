#!/bin/bash

# I've started set up some configurations (user, groups, etc.) on a Windows machine. When I switched to a Linux-based machine, 
# the same server was not listing the previously created configurations. I asked a question on the camunda forum
# (https://forum.camunda.org/t/user-list-on-tomcat-server-is-differs-depending-on-operating-system/5816/2) which was kindly 
# answered by thorben. So I wrote a small shell script for Linux machines, so that the same path is used by the apache tomcat 
# server and users + groups are available ;).

# Be aware that you have to make this file and the start-camunda.sh executable via
# chmod +x /path/to/file
# Otherwise this will not work.

# Variables
ORIGIN="jdbc:h2:./camunda-h2-dbs/process-engine;MVCC=TRUE;TRACE_LEVEL_FILE=0;DB_CLOSE_ON_EXIT=FALSE"
AIM="jdbc:h2:./server/apache-tomcat-8.0.24/bin/camunda-h2-dbs/process-engine;MVCC=TRUE;TRACE_LEVEL_FILE=0;DB_CLOSE_ON_EXIT=FALSE"

# Replace path of apache-tomcat server
sed -i "s@$ORIGIN@$AIM@" ./server/apache-tomcat-8.0.24/conf/server.xml

# Call script to start the camunda engine
sh ./start-camunda.sh