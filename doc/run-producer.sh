#!/bin/bash

# Send messages to Kafka topic $1
# example sent messages from product.json to product-dto-topic:
# ./run-producer.sh product_ext_dto_topic < ./doc/product.json BAD

# OK
# echo  "{'n': 1, 'name': 'NAME_1', 'groupDtoN':100}" | ./doc/run-producer.sh product_ext_dto_topic
# echo  "{'n': 1, 'name': 'NAME_1', 'groupDtoN':100}\n{'n': 2, 'name': 'NAME_2', 'groupDtoN':200}" | ./doc/run-producer.sh product_ext_dto_topic

# Example product.json:
#   {"n":10,"name":"NAME_10","groupDtoN":100}

#$1 - topic for send
/opt/kafka/bin/kafka-console-producer.sh --broker-list 192.168.1.20:9092 --topic $1