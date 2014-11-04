#!/bin/sh

export JAVA_HOME=$(/usr/libexec/java_home)
WSDL2JAVA="/Applications/axis2-1.6.2/bin/wsdl2java.sh"
BASE_URI="http://sigaim.saprm.cesga.es:8080/SIGAIM-SAPRM-WS/services"

rm src/org/sigaim/siie/interfaces/saprm/ws/*.java
"$WSDL2JAVA" -uri $BASE_URI/INT004SIIESAPRMImpl?wsdl  -p org.sigaim.siie.interfaces.saprm.ws -d adb 
