declare -a arr
arr=$1
echo "printing the list in script file"
echo "${arr[@]}"
declare -A schemaList
declare -A seedDataList
for i in ${arr[*]}
do
if [[ "$i" =~ ^src.* ]]; then
  key=$(echo $i | cut -d'/' -f 2-)
  schemaList["${key}"]=$i
elif [[ "$i" =~ ^seed-data.*  ]]; then
  key=$(echo $i | cut -d'/' -f 2-)
  seedDataList["${key}"]=$i
  if [[ ! -v schemaList["${key}"] ]]; then
    schemaList["${key}"]="schema/""$i"
  fi
fi
done
echo "::set-output name=schema::${schemaList[@]}"
echo "::set-output name=seed-data::${seedDataList[@]}"
echo ${schemaList[@]}