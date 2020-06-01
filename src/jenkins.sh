#!/bin/sh
# 一键启动WAR服务和备份WAR服务
# 使用说明 : 需要配置jenkins传送war包的路径，启动war包的tomcat

# create : 2020-04-30
# update : 2020-04-30
# author : wuduoqiang

echo "=============================================================="
# war存放的路径
warPath=/usr/local/jenkins/imp

if [ -e $warPath ];
then
  echo "$warPath存在"
  cd $warPath
else
  echo "$warPath不存在"
  exit
fi

# 刷新环境变量,防止个别服务器远程命令出错
source /etc/profile

echo "=============================================================="
# 动态参数修改,tomcat根路径,目录里存放启动war包的tomcat，下级目录名要跟war名一样，如/usr/local/imp/application-service-1.1-SNAPSHOT/webapps/，/usr/local/imp/hardware-service-1.1-SNAPSHOT/webapps/
tomcatPath="/usr/local/imp/"
echo "tomcat根目录:"
echo $tomcatPath

echo "=============================================================="
# 当前目录
paths=`pwd`
echo "当前目录："
echo $paths

echo "=============================================================="
# 找出当前目录下所有的服务
allService=`find $paths -name '*.war'`
echo "当前目录下所有服务："
echo "$allService"

echo "=============================================================="
# 当前目录下服务的个数
serviceNum=`find $paths -name '*.war' | wc -l`
echo "当前目录下所有服务个数：$serviceNum"

echo "=============================================================="
# 备份找到的服务
echo "执行备份命令："
for oneService in `find $allService -name '*.war'`
do
  # 当前时间
  currentTime=`date '+%Y%m%d%H%M%S'`
  echo "$oneService >>>>> $oneService.$currentTime.bak"
  cp $oneService $oneService.$currentTime.bak
done

echo "=============================================================="
# 重启找到的服务
echo "重启找到的服务："
for oneService in `find $allService -name '*.war'`
do

  echo ""

  # 去掉目录和后缀的文件名
  serviceName=$(basename $oneService .war)
  # 判断服务是否正在运行
  running=`ps -ef | grep "$serviceName" | grep -v grep | wc -l`
  if [ "$running" -eq 1 ]
  then
    # 停止正在运行的服务
    echo "服务$serviceName正在运行"
    pid=`ps -ef | grep "$serviceName" | grep -v grep | awk '{print $2}'`
    echo "停止服务$serviceName的进程号为$pid"
    kill -9 $pid
  else
    echo "服务$serviceName不在运行"
  fi

  # 判断启动命令是否存在
  if [ -e $tomcatPath$serviceName/bin/startup.sh ];
  then
    # 删除旧服务,启动新服务
    echo "删除$tomcatPath$serviceName/webapps/$serviceName"
    rm -rf $tomcatPath$serviceName/webapps/$serviceName
    echo "删除$tomcatPath$serviceName/webapps/$serviceName.war"
    rm -rf $tomcatPath$serviceName/webapps/$serviceName.war
    # 复制新的war包
    echo "复制$serviceName.war到$tomcatPath$serviceName/webapps"
    cp $serviceName.war $tomcatPath$serviceName/webapps
    echo "启动$serviceName服务"
    sh $tomcatPath$serviceName/bin/startup.sh
    sleep 3
    pid=`ps -ef | grep "$serviceName" | grep -v grep | awk '{print $2}'`
    if [ -z $pid ];
    then
      echo "启动失败，请检查环境配置"
    else
      echo "$serviceName已启动,进程号为$pid"
    fi
  else
    echo "不存在$tomcatPath$serviceName/bin/startup.sh,启动失败，请检查!"
  fi

done

echo "=============================================================="
# 找出当前目录下所有备份的服务
allBackup=`find $paths -name '*.bak'`
echo "当前目录下所有备份的服务："
echo "$allBackup"

echo "=============================================================="
# 当前目录下服务备份的个数
backupNum=`find $paths -name '*.bak' | wc -l`
echo "当前目录下所有服务备份个数：$backupNum"

echo "=============================================================="
# 备份的版本数
versionNum=3
echo "需要备份的版本数为：$versionNum"

echo "=============================================================="
echo "所有服务数：$serviceNum"

echo "=============================================================="
# 需要备份的个数
needBackupNum=`expr $versionNum \* $serviceNum`
echo "需要备份的个数：$needBackupNum"

echo "=============================================================="
# 当前服务备份，删除旧的备份文件
if [ $backupNum -gt $needBackupNum ];
then

  # 需要删除的备份个数
  deleteNum=`expr $backupNum - $needBackupNum`
  echo "备份个数 $backupNum 大于需要备份的服务个数 $needBackupNum 为 $deleteNum 个"
  echo "=============================================================="
  echo "所有的备份文件排序后为："
  echo "`find $paths -name '*.bak' | xargs ls -lta | grep ^[^d] | awk '{print $9}'`"
  echo "=============================================================="
  echo "需要删除的备份个数为：$deleteNum"

  # 需要删除的备份文件
  deleteName=`find $paths -name '*.bak' | xargs ls -lta | grep ^[^d] | awk '{print $9}' | tail -$deleteNum`
  echo "=============================================================="
  echo "需要删除的备份文件为："
  echo "$deleteName"
  rm -rf $deleteName

fi
麻烦提供一份打war包的一键部署脚本
这个是模板
可以参照也可以自己写