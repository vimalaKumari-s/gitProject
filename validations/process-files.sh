#!/bin/sh

declare -a arr
arr=$1
declare -A schemaList
declare -A seedDataList
for i in ${arr[*]}
do
if [[ "$i" =~ ^schema.* ]]; then
  key=$(echo $i | cut -d'/' -f 2-)
  schemaList["${key}"]=$i
elif [[ "$i" =~ ^seed_data.*  ]]; then
  key=$(echo $i | cut -d'/' -f 2-)
  seedDataList["${key}"]=$i
  if [[ ! -v schemaList["${key}"] ]]; then
    schemaList["${key}"]="schema/""$key"
  fi
fi
done

isChangeInDBFiles=false
if [[ ${#schemaList[@]} -gt 0 || ${#seed-seedDataList[@]} -gt 0 ]]; then
  isChangeInDBFiles=true
fi

echo "::set-output name=schema::"${schemaList[@]}""
echo "::set-output name=seedData::"${seedDataList[@]}""
echo "::set-output name=isDBFileChangeExists::$isChangeInDBFiles"