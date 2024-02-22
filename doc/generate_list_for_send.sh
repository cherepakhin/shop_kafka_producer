# Generate list for send to /echo/array_message and etc...

max=100
json_file="test_list_products.txt"
# uncomment the next line if you want to generate a new file with the array json
#echo -ne "[\n" > $json_file
for i in $(seq 1 $max)
do
    echo "{\"n\": $i, \"name\": \"NAME_$i\", \"groupDtoN\":100}" >> $json_file
# uncomment the next line if you want to generate a new file with the array json
#   echo -ne ",\n" >> $json_file
done
# uncomment the next line if you want to generate a new file with the array json
#echo "{\"n\":" $((max+1)), "\"name\": \"NAME_$((max+1))\", \"groupDtoN\":100}" >> $json_file
#echo "]" >> $json_file