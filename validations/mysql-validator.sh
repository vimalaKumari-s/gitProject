#!/bin/sh

#checking the schema files
create_with_db_name=$(egrep -ri  "create table.*[a-zA-Z0-9_\`]{1,}\.[a-zA-Z0-9_\`]{1,}"   schema | wc -l )

ret=0
if [  $create_with_db_name -gt 0  ]; then
  echo ""
  echo -e  "** Contains create tables with db name specified : $create_with_db_name **"
  egrep -ri  "create table.*[a-zA-Z0-9_\`]{1,}\.[a-zA-Z0-9_\`]{1,}"   schema
  ret=1
fi



# checking the seed files
seed_with_db_name=$(egrep -ri  "(INSERT|REPLACE)[ ]{1,}INTO[ ]{1,}[a-zA-Z0-9_\`]{1,}\.[a-zA-Z0-9_\`]{1,}" seed_data | wc -l)
if [  $seed_with_db_name -gt 0  ]; then
  echo ""
  echo "** Contains seed_data with db name specified : $seed_with_db_name **"
  egrep -ri  "(INSERT|REPLACE)[ ]{1,}INTO[ ]{1,}[a-zA-Z0-9_\`]{1,}\.[a-zA-Z0-9_\`]{1,}" seed_data
  ret=1
fi


# check if use <db_name> is present
use_db_name=$(egrep -ir "^[ ]{0,}use[ ]{1,}[\`a-zA-Z0-9_]{1,}[;]{0,}$" schema seed_data | wc -l )
if [  $use_db_name -gt 0  ]; then
  echo ""
  echo "** Use database is found : $use_db_name **"
  egrep -ir "^[ ]{0,}use[ ]{1,}[\`a-zA-Z0-9_]{1,}[;]{0,}$" schema seed_data
  ret=1
fi

exit $ret
