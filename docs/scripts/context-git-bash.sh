#!/bin/bash

ADMIN_ID1="joao_m4r14@hotmail.com"
PERSON_ID1="king_george@gmail.com"
PERSON_ID2="glory@gmail.com"
PERSON_ID3="barbar@gmail.com"
PERSON_ID4="rachel_camacho@hotmail.com"
RELATION="child"
URL_HTTP="http://localhost:8080"

#-----------------------------------------------------

winpty curl --location --request POST $URL_HTTP'/auth/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username" : "sm",
    "password" : "sm"
}' > token.temp

TOKEN="$(cat token.temp | cut -d":" -f2 | cut -d"," -f1 | sed 's/"//g')"
rm token.temp

#-----------------------------------------------------

#Create standard categories
winpty curl --header "Content-Type: application/json" \
  --header "Authorization: Bearer $TOKEN" \
  --request POST \
  --data '{"designation": "Groceries", "parentID": null}' \
  $URL_HTTP/categories/standard > temp.temp

#Save category identifier
CATEGORY_ID1="$(cat temp.temp | cut -d":" -f3 | cut -d"," -f1 | sed 's/"//g')"

#Create standard categories
winpty curl --header "Content-Type: application/json" \
  --header "Authorization: Bearer $TOKEN" \
  --request POST \
  --data '{"designation": "Vegetables", "parentID": "'"$CATEGORY_ID1"'"}' \
  $URL_HTTP/categories/standard

#Create standard categories
winpty curl --header "Content-Type: application/json" \
  --header "Authorization: Bearer $TOKEN" \
  --request POST \
  --data '{"designation": "Fruits", "parentID": "'"$CATEGORY_ID1"'"}' \
  $URL_HTTP/categories/standard

#Create standard categories
winpty curl --header "Content-Type: application/json" \
  --header "Authorization: Bearer $TOKEN" \
  --request POST \
  --data '{"designation": "Sausages", "parentID": "'"$CATEGORY_ID1"'"}' \
  $URL_HTTP/categories/standard

#Create standard categories
winpty curl --header "Content-Type: application/json" \
  --header "Authorization: Bearer $TOKEN" \
  --request POST \
  --data '{"designation": "Transportation", "parentID": null}' \
  $URL_HTTP/categories/standard > temp.temp

#Save category identifier
CATEGORY_ID2="$(cat temp.temp | cut -d":" -f3 | cut -d"," -f1 | sed 's/"//g')"

#Create standard categories
winpty curl --header "Content-Type: application/json" \
  --header "Authorization: Bearer $TOKEN" \
  --request POST \
  --data '{"designation": "Bus", "parentID": "'"$CATEGORY_ID2"'"}' \
  $URL_HTTP/categories/standard

#Create standard categories
winpty curl --header "Content-Type: application/json" \
  --header "Authorization: Bearer $TOKEN" \
  --request POST \
  --data '{"designation": "Train", "parentID": "'"$CATEGORY_ID2"'"}' \
  $URL_HTTP/categories/standard

#Create standard categories
winpty curl --header "Content-Type: application/json" \
  --header "Authorization: Bearer $TOKEN" \s
  --request POST \
  --data '{"designation": "Education", "parentID": null}' \
  $URL_HTTP/categories/standard

#Remove temporary files
rm temp.temp

#-----------------------------------------------------

#Create family(1) and administrator(1)
winpty curl --header "Content-Type: application/json" \
  --header "Authorization: Bearer $TOKEN" \
  --request POST \
  --data '{"personName": "Tomás Maria", "birthDate": "1996-07-18", "vat": "231873417", "houseNumber": "12", "street": "Rua Direita", "city": "Sandim", "country": "Portugal", "zipCode": "1234-234", "email": "'"$ADMIN_ID1"'", "familyName": "Constantino", "phoneNumbers": ["912345612", "915768456"], "description": "The Constantino family", "username": "Tomas", "password": "1234"}' \
  $URL_HTTP/families > temp.temp

#Save family(1) identifier
FAMILY_ID1="$(cat temp.temp | cut -d":" -f4 | cut -d"," -f1 | sed 's/"//g')"

#Add person(1) to family(1)
winpty curl --header "Content-Type: application/json" \
  --header "Authorization: Bearer $TOKEN" \
  --request POST \
  --data '{"email": "'"$PERSON_ID1"'", "name": "Jorge", "birthDate": "2000-12-04", "vat": "123456789", "houseNumber": "25", "street": "Rua Nova", "city": "Valongo", "country": "Portugal", "zipCode": "2156-958", "phoneNumbers": ["912654986"], "familyID": "'"$FAMILY_ID1"'", "username": "Jorge", "password": "1234"}' \
  $URL_HTTP/users

#Add person(2) to family(1)
winpty curl --header "Content-Type: application/json" \
  --header "Authorization: Bearer $TOKEN" \
  --request POST \
  --data '{"email": "'"$PERSON_ID2"'", "name": "Gloria", "birthDate": "1990-10-07", "vat": "203679920", "houseNumber": "13", "street": "Rua Velha", "city": "Guarda", "country": "Portugal", "zipCode": "3356-123", "phoneNumbers": ["911026777"], "familyID": "'"$FAMILY_ID1"'", "username": "Gloria", "password": "1234"}' \
  $URL_HTTP/users

#Add person(3) to family(1)
winpty curl --header "Content-Type: application/json" \
  --header "Authorization: Bearer $TOKEN" \
  --request POST \
  --data '{"email": "'"$PERSON_ID3"'", "name": "Barbara", "birthDate": "1989-09-05", "vat": "265645751", "houseNumber": "1", "street": "Rua Direita", "city": "Rio Tinto", "country": "Portugal", "zipCode": "4356-200", "phoneNumbers": ["911507888"], "familyID": "'"$FAMILY_ID1"'", "username": "Barbs", "password": "1234"}' \
  $URL_HTTP/users

#Add person(4) to family(1)
winpty curl --header "Content-Type: application/json" \
  --header "Authorization: Bearer $TOKEN" \
  --request POST \
  --data '{"email": "'"$PERSON_ID4"'", "name": "Raquel", "birthDate": "1992-07-12", "vat": "257715436", "houseNumber": "99", "street": "Rua do Cubano", "city": "Funchal", "country": "Portugal", "zipCode": "2900-220", "phoneNumbers": ["921547818"], "familyID": "'"$FAMILY_ID1"'", "username": "Raquel", "password": "1234"}' \
  $URL_HTTP/users

#Create cash account for the administrator(1)
winpty curl --header "Content-Type: application/json" \
  --header "Authorization: Bearer $TOKEN" \
  --request POST \
  --data '{"personID": "'"$ADMIN_ID1"'", "initialAmount": "10000.00", "designation": "My admin cash account"}' \
  $URL_HTTP/accounts/cash

#Create cash account for the person(1)
winpty curl --header "Content-Type: application/json" \
  --header "Authorization: Bearer $TOKEN" \
  --request POST \
  --data '{"personID": "'"$PERSON_ID1"'", "initialAmount": "5000.50", "designation": "My person cash account"}' \
  $URL_HTTP/accounts/cash

#Create family relation between administrator(1) and person(1)
winpty curl --header "Content-Type: application/json" \
  --header "Authorization: Bearer $TOKEN" \
  --request POST \
  --data '{"personEmail": "'"$ADMIN_ID1"'", "kinEmail": "'"$PERSON_ID1"'", "relationType": "'"$RELATION"'"}' \
  $URL_HTTP/families/$FAMILY_ID1/relations

#-----------------------------------------------

ADMIN_ID2="alan_turing@hotmail.com"
PERSON_ID5="john_von_newmann@gmail.com"

#Create family(2) and administrator(2)
winpty curl --header "Content-Type: application/json" \
  --header "Authorization: Bearer $TOKEN" \
  --request POST \
  --data '{"personName": "Alan", "birthDate": "1910-07-24", "vat": "233480820", "houseNumber": "5", "street": "Main", "city": "London", "country": "UK", "zipCode": "2000-992", "email": "'"$ADMIN_ID2"'", "familyName": "Turing", "phoneNumbers": ["911056344", "962054111"], "description": "The Turing family", "username": "Alan", "password": "1234"}' \
  $URL_HTTP/families > temp.temp

#Save family(2) identifier
FAMILY_ID2="$(cat temp.temp | cut -d":" -f4 | cut -d"," -f1 | sed 's/"//g')"

#Add person(2) to family(2)
winpty curl --header "Content-Type: application/json" \
  --header "Authorization: Bearer $TOKEN" \
  --request POST \
  --data '{"email": "'"$PERSON_ID5"'", "name": "John", "birthDate": "1945-07-12", "vat": "216249805", "houseNumber": "12", "street": "34th Avenue", "city": "New York", "country": "USA", "zipCode": "1000-951", "phoneNumbers": ["932234556"], "familyID": "'"$FAMILY_ID2"'", "username": "John", "password": "1234"}' \
  $URL_HTTP/users

#Remove temporary files
rm temp.temp

