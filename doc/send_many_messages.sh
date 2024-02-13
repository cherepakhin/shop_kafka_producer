# run from ./doc
max=10
for i in $(seq 1 $max)
do
    ./doc/run-producer.sh product_ext_dto_topic < ./doc/product.json
done