
cd eureka-server/
mvn clean && mvn package -DskipTests
docker build . -t eureka-server
cd ..

cd config-server/
sh make.sh
cd ..

cd movie-service/
mvn clean && mvn package -DskipTests
docker build . -t movie-service
cd ..

cd catalog-service/
mvn clean && mvn package -DskipTests
docker build . -t catalog-service
cd ..

cd gateway-server/
sh make.sh
cd ..

cd series-service/
sh make.sh
cd ..

docker-compose up
