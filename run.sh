echo "Building..."
./gradlew clean build
echo "Building Docker image..."
docker build -t  .
echo "Running Docker container..."
docker compose up
