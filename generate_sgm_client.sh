#!/bin/sh

export JAVA_HOME=$(/usr/libexec/java_home)
WSDL2JAVA="/Applications/axis2-1.6.2/bin/wsdl2java.sh"
BASE_URI="http://sigaim.saprm.cesga.es:8080/SIGAIM-SGM-WS/services"

rm src/org/sigaim/siie/interfaces/terminologies/sigaim/sgm/ws/*.java
"$WSDL2JAVA" -uri $BASE_URI/INT003SGMImpl?wsdl  -p org.sigaim.siie.interfaces.terminologies.sigaim.sgm.ws -d adb 
