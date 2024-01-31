# run from ./doc
max=10
for i in $(seq 1 $max)
do
    ./run-producer.sh product_ext_dto_topic < ./product.json
done