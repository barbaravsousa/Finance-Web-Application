#!/bin/bash

URL_HTTP="http://localhost:8080"

#-----------------------------------------------------

curl --location --request POST $URL_HTTP'/auth/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username" : "sm",
    "password" : "sm"
}' > token.temp

TOKEN="$(cat token.temp | cut -d":" -f2 | cut -d"," -f1 | sed 's/"//g')"
rm token.temp

#-----------------------------------------------------

#Create standard categories
curl --header "Content-Type: application/json" \
  --header "Authorization: Bearer $TOKEN" \
  --request POST \
  --data '{"designation": "Groceries", "parentID": null}' \
  $URL_HTTP/categories/standard > temp.temp

#Save category identifier
CATEGORY_ID1="$(cat temp.temp | cut -d":" -f3 | cut -d"," -f1 | sed 's/"//g')"

#Create standard categories
curl --header "Content-Type: application/json" \
  --header "Authorization: Bearer $TOKEN" \
  --request POST \
  --data '{"designation": "Vegetables", "parentID": "'"$CATEGORY_ID1"'"}' \
  $URL_HTTP/categories/standard

#Create standard categories
curl --header "Content-Type: application/json" \
  --header "Authorization: Bearer $TOKEN" \
  --request POST \
  --data '{"designation": "Fruits", "parentID": "'"$CATEGORY_ID1"'"}' \
  $URL_HTTP/categories/standard

#Create standard categories
curl --header "Content-Type: application/json" \
  --header "Authorization: Bearer $TOKEN" \
  --request POST \
  --data '{"designation": "Sausages", "parentID": "'"$CATEGORY_ID1"'"}' \
  $URL_HTTP/categories/standard

#Create standard categories
curl --header "Content-Type: application/json" \
  --header "Authorization: Bearer $TOKEN" \
  --request POST \
  --data '{"designation": "Transportation", "parentID": null}' \
  $URL_HTTP/categories/standard > temp.temp

#Save category identifier
CATEGORY_ID2="$(cat temp.temp | cut -d":" -f3 | cut -d"," -f1 | sed 's/"//g')"

#Create standard categories
curl --header "Content-Type: application/json" \
  --header "Authorization: Bearer $TOKEN" \
  --request POST \
  --data '{"designation": "Bus", "parentID": "'"$CATEGORY_ID2"'"}' \
  $URL_HTTP/categories/standard

#Create standard categories
curl --header "Content-Type: application/json" \
  --header "Authorization: Bearer $TOKEN" \
  --request POST \
  --data '{"designation": "Train", "parentID": "'"$CATEGORY_ID2"'"}' \
  $URL_HTTP/categories/standard

#Create standard categories
curl --header "Content-Type: application/json" \
  --header "Authorization: Bearer $TOKEN" \
  --request POST \
  --data '{"designation": "Education", "parentID": null}' \
  $URL_HTTP/categories/standard > temp.temp

#Save category identifier
CATEGORY_ID3="$(cat temp.temp | cut -d":" -f3 | cut -d"," -f1 | sed 's/"//g')"

#Create standard categories
curl --header "Content-Type: application/json" \
  --header "Authorization: Bearer $TOKEN" \
  --request POST \
  --data '{"designation": "School", "parentID": "'"$CATEGORY_ID3"'"}' \
  $URL_HTTP/categories/standard

#Create standard categories
curl --header "Content-Type: application/json" \
  --header "Authorization: Bearer $TOKEN" \
  --request POST \
  --data '{"designation": "Books", "parentID": "'"$CATEGORY_ID3"'"}' \
  $URL_HTTP/categories/standard

#Create standard categories
curl --header "Content-Type: application/json" \
  --header "Authorization: Bearer $TOKEN" \
  --request POST \
  --data '{"designation": "Sports", "parentID": "'"$CATEGORY_ID3"'"}' \
  $URL_HTTP/categories/standard

#Remove temporary files
rm temp.temp
