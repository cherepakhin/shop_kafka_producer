# send by one message at a time

# run from ./shop_kafka_producer directory
# Example:
# /shop_kafka_producer$ ./doc/send_many_messages.sh

max=100
for i in $(seq 1 $max)
do
#    ./doc/run-producer.sh product_ext_dto_topic < ./doc/product.json
#./doc/run-producer.sh product_ext_dto_topic < echo "\"{\"n\": $i, \"name\": \"NAME_$i\", \"groupDtoN\":100}\""
#  echo "{\"n\": $i, \"name\": \"NAME_$i\", \"groupDtoN\":100}"
#  echo "{\"n\": 1, \"name\": \"NAME_1\", \"groupDtoN\":100}"
#    ./doc/run-producer.sh product_ext_dto_topic < echo "{\"n\": $i, \"name\": \"NAME_$i\", \"groupDtoN\":100}"
  echo "{'n': 1, 'name': 'NAME_1', 'groupDtoN':100}\n{'n': 2, 'name': 'NAME_2', 'groupDtoN':200}" | ./doc/run-producer.sh product_ext_dto_topic
done