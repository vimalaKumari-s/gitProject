execute=$1
if ( $execute ); then
  sudo /etc/init.d/mysql start
  mysql -e 'CREATE DATABASE test;' -uroot -proot
  mysql -e 'SHOW DATABASES;' -uroot -proot
  mysql -e 'use test;' -uroot -proot
  for i in $2
     do
     mysql -uroot -proot test < $i
     done
  for i in $3
     do
     mysql -uroot -proot test < $i
     done
fi

echo "checking whether it prints the code"
echo "$?" "status of previous command in file"
if [[ ! ("$?" = "0") ]]; then
  echo "it is returning the exit code other than 0"
  exit 1
fi
exit 0