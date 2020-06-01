#!/bin/sh
DATE=`date +%Y%m%d`
BAK_DATE=`date +%Y%m%d-%H%M%S`
DATE2=`date +%Y-%m-%d`

# 环境变量
MODE=SIT2

TOMCAT_HOME="/usr/local/apache-tomcat-8.5.54"
APP_HOME=/home/obs/app
LOG_HOME=/home/obs/logs
LIB_TO_PATH=""
APP_NAME=""
WAR_NAME=""
LOGFILENAME=""

config() {
    resolveName
    resolveToPath
    resolveLogPath
}

# 服务名
resolveName() {
    WAR_NAME=`ls $APP_HOME | tail -1 | awk '{print $NF}'`
    APP_NAME=`echo $WAR_NAME | awk -F . '{print $1}'`
}

# 解析tomcat war包的路径
resolveToPath() {
    LIB_TO_PATH=$TOMCAT_HOME"/webapps"
    LIB_FROM_PATH=$APP_HOME/$WAR_NAME
}

resolveLogPath() {
    LOGFILENAME="$LOG_HOME/$APP_NAME/$DATE2"
    fileName=`ls -l $LOGFILENAME | tail -1 | awk '{print $NF}'`
    LOGFILENAME="$LOGFILENAME/$fileName"
    echo "-----$LOGFILENAME"
}

backup() {
    printm "备份: $LIB_TO_PATH"
    cd $LIB_TO_PATH

    if [ ! -f "$LIB_TO_PATH/$APP_NAME.war" ]; then return 0; fi
    printInfo "备份: $LIB_TO_PATH/$APP_NAME.war >>> $LIB_TO_PATH/bak/$APP_NAME.war$DATE"
    if [[ ! -d bak ]]; then
        mkdir bak
    fi
    mv $APP_NAME.war bak/$APP_NAME.war$DATE
    rm -rf $LIB_TO_PATH/$APP_NAME > /dev/null 2>&1
}


# 将新的包文件移动到相应的路径
copyToDeploy() {
    printInfo "复制: $LIB_FROM_PATH >>> $LIB_TO_PATH"
    mv $LIB_FROM_PATH $LIB_TO_PATH
}

stopTomcat() {
    printm "STOP TOMCAT"
    $TOMCAT_HOME/bin/shutdown.sh
}

startTomcat() {
    printm "START TOMCAT"
    $TOMCAT_HOME/bin/startup.sh
}
checkTomcatStatus() {
    PID=`ps -ef | grep java | grep apache-tomcat`
    if [ "$PID" != "" ]; then
	printInfo "$APP_NAME Tomcat服务已启动"
    else
	printInfo "$APP_NAME Tomcat服务启动失败"
    fi
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

main(){
    config

    backup
    copyToDeploy

    stopTomcat
    startTomcat
    checkTomcatStatus

}
main

