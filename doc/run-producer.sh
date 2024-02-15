#!/bin/bash

# Send messages to Kafka topic $1
# example sent messages from product.json to product-dto-topic:
# ./run-producer.sh product_ext_dto_topic < ./doc/product.json

# Example product.json:
#   {"n":10,"name":"NAME_10","groupDtoN":100}

#$1 - topic for send
/opt/kafka/bin/kafka-console-producer.sh --broker-list 192.168.1.20:9092 --topic $1