#!/bin/sh
DATE=`date +%Y%m%d`
DATE2=`date +%Y-%m-%d`
USER=`whoami`

MODE=SIT2
TOMCAT_HOME="/usr/local/apache-tomcat-9.0.31"
APP_HOME=/home/obs/app
SRC_HOME=/home/obs/src/obs
LOG_HOME=/home/obs/logs
INSTAL_PATH=""
LIB_FROM_PATH=""
LIB_TO_PATH=""
APP_NAME=""
RUN_JARNAME=""
APP_PORT=""
LOGFILENAME=""



printOperation ()
{
tput clear
cat <<EOF


__________________________________________________________________________________________

用户：$USER			   网银部署			日期：$DATE
__________________________________________________________________________________________




				1 : 备份并部署
				2 : 启动服务
				3 : 停止服务
				4 : 重启服务
				5 : 启动Tomcat
				6 : 停止Tomcat
				7 : 重启Tomcat
			      999 : 使用备份回退(未实现)
				0 : 退出



__________________________________________________________________________________________



EOF
echo -n "请选择操作："
}

printList ()
{
tput clear
cat <<EOF


__________________________________________________________________________________________

用户：$USER		      请选择需要部署的应用			日期：$DATE2
__________________________________________________________________________________________




				1 : mca            (独立部署)
				2 : mcm            (独立部署)
				3 : eweb           (Tomcat部署)
				4 : mweb           (Tomcat部署)			
				5 : router         (独立部署)
				6 : gateway        (Tomcat部署)
				7 : esbaccess      (Tomcat部署)
				8 : batch          (独立部署)
				0 : 退出



__________________________________________________________________________________________



EOF
echo -n "请输入（如1或1234）："
}

printInfo ()
{
echo "<INFO> $1"
}

printError ()
{
echo "<ERROR> $1"
}

println()
{
echo ------------------------------------------------------------------------
}

printm()
{
println
wlen=`echo $* | awk '{print length($0)}'`
slen=$(( 36+$wlen/2 ))
printf "%${slen}s\n" "$*"
println
}

waitForWord() {
	try=0
	printf "Waiting for $SERVER_NAME startup..."
	while [ $try -lt 60 ]; do
		try=`expr $try + 1`
		printf .
		sleep 10
		grep -i "$1" $LOGFILENAME > /dev/null 2>&1
		if [ $? == '0' ]; then
			try='99'
			break;
		fi
		done
	echo ""
	if [ $try ==  '99' ]; then
		echo "$SERVER_NAME start success."
	else
		echo "$SERVER_NAME start failed."
	fi
}


stopServer() {
PID=`ps -ef | grep java | grep $APP_NAME-`
if [ "$PID" != "" ]; then
    printInfo "服务已存在，正在停止[$APP_NAME]" 
    PID=`echo $PID | awk '{print $2}'`
    kill -9 $PID
fi
}

startServer() {
    PID=`ps -ef | grep java | grep $APP_NAME-`
    if [ "$PID" != "" ]; then
	printInfo "服务已存在,将停止后重新启动" 
	stopServer
    fi
    printm "Exceting $RUN_JARNAME"
    java -Xms256M -Xmx512M -DCurrentMode=$MODE -jar $RUN_JARNAME > /dev/null 2>&1 &
}

xarInstall() {
    cd $APP_HOME/$1
    mvn clean install '-Dmaven.test.skip=true'
}





checkoperater ()
{
	if [[ "$USER" != "obs" ]]
	then
	    printError  "网银war包更新需使用obs用户，当前用户是 ${USER}。请切换用户后再执行此脚本!"
	    exit 1
	fi
}




resolveName() {
    	APP_NAME=`echo $1 | sed 's/1/mca/g;s/2/mcm/g;s/3/eweb/g;s/4/mweb/g;s/5/router/g;s/6/access/g;s/7/esbaccess/g;s/8/batch/g;'`
}

resolveRunName() {
    	RUN_JARNAME=`echo $1 | sed 's/1/mca-bootstrap-1.0.1-SNAPSHOT.jar/g;s/2/mcm-bootstrap-1.0.1-SNAPSHOT.jar/g;s/5/router-bootstrap-1.0.1-SNAPSHOT.jar/g;s/8/batch/g;'`
    	RUN_JARNAME="$LIB_TO_PATH/lib/$RUN_JARNAME"
}

resolveInlPath() {
    	dirname=`echo $1 | sed 's/1/mca_pom/g;s/2/mcm_pom/g;s/3/eweb_pom/g;s/4/mweb_pom/g;s/5/router_pom/g;s/6/gateway_pom/g;s/7/esbaccess_pom/g;s/8/batch_pom/g;'`
    	INSTAL_PATH=${SRC_HOME}"/"${dirname}
}

resolveFromPath() {
    resolveInlPath $1
    dirname=`echo $1 | sed 's/1/mca-bootstrap\/target\/lib/g;s/2/mcm-bootstrap\/target\/lib/g;s/3/eweb-web\/target\/eweb.war/g;s/4/mweb-web\/target\/mweb.war/g;s/5/router-bootstrap\/target\/lib/g;s/6/gateway-access\/target\/access.war/g;s/7/esbaccess-web\/target\/esbaccess.war/g;s/8/batch-bootstrap\/target\/lib/g;'`
    LIB_FROM_PATH=$INSTAL_PATH"/"$dirname
}

resolveToPath() {
    if [[ $(isWebApp) = "0" ]]; then
	LIB_TO_PATH=`echo $1 | sed 's/1/mca/g;s/2/mcm/g;s/5/router/g;s/8/batch/g;'`
	LIB_TO_PATH=$APP_HOME"/"$LIB_TO_PATH
    else
	LIB_TO_PATH=$TOMCAT_HOME"/webapps"
    fi
}

resolveLogPath() {
#ls -l | tail -1 | awk '{print $NF}'
    LOGFILENAME="$LOG_HOME/$APP_NAME/$DATE2"
    fileName=`ls -l $LOGFILENAME | tail -1 | awk '{print $NF}'`
    LOGFILENAME="$LOGFILENAME/$fileName"
}

resolvePort() {
    APP_PORT=`echo $1 | sed 's/1/18001/g;s/2/38301/g;s/5/18002/g;'`
}

checkServerStatus() {
    resolveName $1
    resolveLogPath $1
    printInfo "请检查日志查看服务状态是否正常： $LOGFILENAME"
    if [[ $(isWebApp) = "1" ]]; then return 1; fi
    PID=`ps -ef | grep java | grep $APP_NAME`
    if [ "$PID" != "" ]; then
	printInfo "$APP_NAME 服务已启动"
    else
	printInfo "$APP_NAME 服务启动失败"
    fi
    
}

checkTomcatStatus() {
    PID=`ps -ef | grep java | grep apache-tomcat`
    if [ "$PID" != "" ]; then
	printInfo "$APP_NAME Tomcat服务已启动"
    else
	printInfo "$APP_NAME Tomcat服务启动失败"
    fi
}

config() {
    resolveName $1
    resolveRunName $1
    resolveInlPath $1
    resolveFromPath $1
    resolveToPath $1
    resolveRunName $1
    resolveLogPath $1
}

isWebApp() {
  if [[ "$APP_NAME" = "mca" ||  "$APP_NAME" = "mcm" ||  "$APP_NAME" = "router" ||  "$APP_NAME" = "batch" ]]; then
	echo "0"
    else
	echo "1"
    fi
}

updateGit() {
    printm "git更新"
    cd $SRC_HOME
    git pull
    if [[ "$?" != "0" ]]; then
	printError "git更新出现问题，请解决"
	exit 1
    fi
}

mvnInstall() {
    printm "COMPILE $APP_NAME"
    cd $INSTAL_PATH
    mvn install '-Dmaven.test.skip=true'
    if [[ "$?" != "0" ]]; then
	printError "maven编译失败，请解决"
	exit 1
    fi
}

backup() {
    printm "备份: $LIB_TO_PATH"
    cd $LIB_TO_PATH
    if [[ $(isWebApp) = "0" ]]; then
	if [ ! -d "$LIB_TO_PATH/lib" ]; then return 0; fi	
	printInfo "备份: $LIB_TO_PATH/lib >>> $LIB_TO_PATH/lib.$DATE.tar"
	tar -cvf "lib.$DATE.tar" lib > /dev/null 2>&1
	rm -rf lib
    else
	if [ ! -f "$LIB_TO_PATH/$APP_NAME.war" ]; then return 0; fi
	printInfo "备份: $LIB_TO_PATH/$APP_NAME.war >>> $LIB_TO_PATH/bak/$APP_NAME.war$DATE"
	if [[ ! -d bak ]]; then
	    mkdir bak
	fi
	mv $APP_NAME.war bak/$APP_NAME.war$DATE
	rm -rf $LIB_TO_PATH/$APP_NAME > /dev/null 2>&1
    fi
}

copyToDeploy() {
    printInfo "复制: $LIB_FROM_PATH >>> $LIB_TO_PATH"
    cp -rf $LIB_FROM_PATH $LIB_TO_PATH
}

stopTomcat() {
    printm "STOP TOMCAT"
    $TOMCAT_HOME/bin/shutdown.sh
}

startTomcat() {
    printm "START TOMCAT"
    $TOMCAT_HOME/bin/startup.sh
}

executeOne() {
    config $1
    mvnInstall
    if [[ $(isWebApp) = "0" ]]; then
	stopServer
    fi
    backup
    copyToDeploy
    if [[ $(isWebApp) = "0" ]]; then
	startServer
    fi
}

deploy() {
    printList
    read OP
    if [[ -z "`echo $OP | awk '/^[1-8]+$/'`" ]]
    then
	printInfo '已选择退出'
	exit 0
    fi
    printm "部署开始"
    updateGit
    stopTomcat
    OP=`echo $OP | sed 's/[1-9]/& /g'`
    for var in $OP
    do 
	executeOne $var
    done
    startTomcat
    printm "检查部署结果"
    for var in $OP
    do 
	checkServerStatus $var
    done
} 



handleServer() {
    printList
    read OP
    if [[ -z "`echo $OP | awk '/^[1-8]+$/'`" ]]
    then
	printInfo '已选择退出'
	exit 0
    fi
    if [[ -z "`echo $OP | awk '/^[1,2,5,8]+$/'`" ]]
    then
	printError "请选择重启Tomcat"
	exit 0
    fi
    CM=$1
    OP=`echo $OP | sed 's/[1-9]/& /g'`
    for var in $OP
    do 
	config $var
	
	if [[ "$CM" = "2" ]]; then #启动服务
	    startServer
	elif [[ "$CM" = "3" ]]; then #停止服务
	    stopServer
	else #重启服务
	    stopServer
	    startServer
	fi
	printInfo "日志路径： $LOGFILENAME"
    done
    if [ $CM != "3" ]; then
	tail -f $LOGFILENAME
    fi
}

main() {
    checkoperater
    printOperation
    read OP
    if [[ -z "`echo $OP | awk '/^[1-7]$/'`" ]]
    then
	printInfo '已选择退出'
	exit 0
    fi
    if [[ "$OP" = "1" ]]; then
	deploy
    elif [ "$OP" = "2" -o "$OP" = "3" -o "$OP" = "4" ]; then
	handleServer $OP
    elif [[ "$OP" = "5" ]];then
	startTomcat
    elif [[ "$OP" = "6" ]];then
	stopTomcat
    else
	stopTomcat
	startTomcat
    fi
}


#BUILD SUCCESS

#config $1
#startServer
#mvnInstall
#backup
#copyToDeploy
#startServer
#deploy
main
