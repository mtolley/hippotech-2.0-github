mkdir -p build
cd hippotech-react
npm install
npm run build
cd ..
cd java-api
mvn clean package
cd ..
cd approval
mvn clean package
cd ..
cd blog
npm ci
cd ..
cd credit
mvn clean package
cd ..
cd fraud
mvn clean package
cd ..
