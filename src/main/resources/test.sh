#!/usr/local/bin/bash
#s="src/main"
s="src/main/java/com/example/demo/DemoApplication.java"
#src/main/java/sqlFile.sql

#if [[ "$s" =~ "^src".* ]] ; then
#  echo "It's there."
#fi

#!/bin/bash
#arr=(src/main/java/com/example/demo/DemoApplication.java src/main/java/sqlFile.sql)
#arr2=()
#for i in ${arr[*]}
#do
#  #echo "$i"
#  if [[ "$i" =~ ^src.* ]]
#   then
#    echo "yes"
#    arr2+=("$i")
#  fi
#done
#
#echo "${arr2[*]}"
#list=('schema/airflow/test' 'seed-data/airflow/test' 'schema/reon/user' 'seed-data/mongo/client')
##declare -A associative_array=(["one"]="Baeldung" ["two"]="is" ["three"]="cool")
#declare -A schemaList
#delcare -A seedDataList
##hm[hello]=1
##hm[world]=1
##hm[hello]=
##if [ "${hm[hello]}" ] ; then echo "${hm[@]}" ; fi
#
#IFS='/'
#for i in ${list[@]}
# do
#   if [[ "$i" =~ ^schema.* ]]; then
#     read -ra arr <<< "$i"
#     echo "${arr[*]}
#     schemaList["${arr[1]}"]="$i"
#
# done
#echo "${schemaList[@]}"

#schema=('list' 'bindu')
##seedData= ${{ steps.arrayId.outputs.seed-data }}
#echo {{ "${schema[*]}" }}




declare -A hashmap
echo "Enter the user name: "
read bindu
echo "printing input"
echo "$bindu"
hashmap["key"]="value"
hashmap["key2"]="value2"
echo "${hashmap[@]}"
#for key in "${!hashmap[@]}"; do echo $key; done