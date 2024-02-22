#!/bin/bash

# This script is used to send a file to a remote kafka topic

# cat list_products.txt:
#{"n": 1, "name": "NAME_1", "groupDtoN":100}
#{"n": 2, "name": "NAME_2", "groupDtoN":100}
#{"n": 3, "name": "NAME_3", "groupDtoN":100}
#{"n": 4, "name": "NAME_4", "groupDtoN":100}
#{"n": 101, "name": "NAME_101", "groupDtoN":100}

# run from directory: doc/send_file/

cat ./list_products.txt | /opt/kafka/bin/kafka-console-producer.sh --broker-list 192.168.1.20:9092 --topic product_ext_dto_topic