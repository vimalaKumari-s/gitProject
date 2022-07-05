execute=$1
if ( $execute ); then
  sudo /etc/init.d/mysql start
  mysql -e 'CREATE DATABASE test;' -uroot -proot
  mysql -e 'SHOW DATABASES;' -uroot -proot
  mysql -e 'use test;' -uroot -proot
  for i in ${{steps.arrayId.outputs.schema}}
     do
     mysql -uroot -proot test < $i
     done
  for i in ${{steps.arrayId.outputs.seedData}}
     do
     mysql -uroot -proot test < $i
     done
fi