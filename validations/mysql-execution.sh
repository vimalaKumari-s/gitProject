#!/bin/sh
ret=0
sudo /etc/init.d/mysql start
mysql -e 'CREATE DATABASE test;' -uroot -proot
mysql -e 'SHOW DATABASES;' -uroot -proot
mysql -e 'use test;' -uroot -proot
for i in $1
   do
   echo "$i"
   mysql -uroot -proot test < $i || ret=1
   done
for i in $2
   do
   echo "$i"
   mysql -uroot -proot test < $i || ret=1
   done

exit $ret