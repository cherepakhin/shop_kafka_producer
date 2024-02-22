max=1
json_file="list_products.json"
echo -ne "[\n" > $json_file
for i in $(seq 1 $max)
do
    echo "{\"n\": $i, \"name\": \"NAME_$i\", \"groupDtoN\":100}, " >> $json_file
done
echo "{\"n\":" $((max+1)), "\"name\": \"NAME_$((max+1))\", \"groupDtoN\":100}" >> $json_file
echo "]" >> $json_file

http POST http://127.0.0.1:8990/shop_kafka_producer/api/send_product/send_list < $json_file
