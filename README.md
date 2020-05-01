# Review Microservice

Listening on port: 9000

### Get all reviews
curl --location --request GET 'http://localhost:8080/review-service/reviews'

### Get a review
curl --location --request GET 'http://localhost:8080/review-service/reviews/1'

### Add a review
curl --location --request POST 'http://localhost:8080/review-service/reviews' \
--header 'Content-Type: application/json' \
--data-raw '{
	"review_text":"Reminds me of evenings in Barcelona. The pulpo is awesome!",
	"rating": 3, 
	"restaurant_id": 3 
}'

### Get all reviews for restaurant
curl --location --request GET 'http://localhost:8080/review-service/reviews/restaurant/1'
