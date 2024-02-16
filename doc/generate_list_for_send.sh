# Generate list for send to /echo/array_message and etc...

max=100
json_file="list_10_products.json"
echo -ne "[\n" > $json_file
for i in $(seq 1 $max)
do
    echo "{\"n\": $i, \"name\": \"NAME_$i\", \"groupDtoN\":100}, " >> $json_file
done
echo "{\"n\":" $((max+1)), "\"name\": \"NAME_$((max+1))\", \"groupDtoN\":100}" >> $json_file
echo "]" >> $json_file