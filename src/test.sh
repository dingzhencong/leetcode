#!/bin/bash
if [ $(ps -ef | grep -c "ssh") -eq 1 ]; then echo "true"; fi

for str in This is a string
do
    echo $str
done

int=1
while(( $int<=5 ))
do
    echo $int
    let int++
done

a=0

until [ ! $a -lt 10 ];
do
   echo $a
   let a++
done

site="runoob"

case "$site" in
   "runoob") echo "菜鸟教程"
   ;;
   "google") echo "Google 搜索"
   ;;
   "taobao") echo "淘宝网"
   ;;
esac

if [ -e /home/dzc/application ]
then
    echo '文件已存在!'
else
    echo '文件不存在!'
fi