# Rules for URI's

• A singular noun should be used for document names
• http://api.soccer.restapi.org/leagues/seattle/teams/trebuchet/players/claudio

• A plural noun should be used for collection names
• http://api.soccer.restapi.org/leagues/seattle/teams/trebuchet/players

• A plural noun should be used for store names
• http://api.music.restapi.org/artists/mikemassedotcom/playlists

• A verb or verb phrase should be used for controller names
• http://api.college.restapi.org/students/morgan/register

• Variable path segments may be substituted with identity-based values
• http://api.soccer.restapi.org/leagues/%7BleagueId%7D/teams/%7BteamId%7D/players/%7BplayerId%7D

• CRUD function names should not be used in URIs

• DELETE /users/12 (is preferred over) GET /deleteUser/12 (or) DELETE /deleteUser/12

---

# REST CONTROLLER URI

##/families

GET - Get list of families

POST - Create Family & Admin

PUT -

DELETE -

---

##/families/{familyID}/categories

GET - Get list of family categories

POST - 

PUT -

DELETE -

---

##/families/{familyID}/categories/list

GET - Get list of all family categories

POST -

PUT -

DELETE -

---

##/families/{familyID}

GET - Get family profile

POST -

PUT -

DELETE -

---

##/families/relations

GET - Get list of valid relations

POST - 

PUT -

DELETE -

---
##/families/{familyID}/relations

GET - Get list of relations from a person

POST - Create a family relation

PUT -

DELETE -

---

##/users

GET - Get list of family members

POST - Add member

PUT -

DELETE -

---

##/users/{userID}

GET - Get profile information

POST -

PUT -

DELETE -

##/users/{userID}/email

GET -

POST Add email to my profile

PUT -

DELETE -

---

##/accounts/bank

GET -

POST Add bank account

PUT -

DELETE -

---

##/accounts/cash

GET -

POST - Add cash account

PUT -

DELETE -

---

##/accounts/{accountID}

GET - Get account balance

POST -

PUT -

DELETE -

---

##/accounts/{accountID}/{childID}

GET - Get balance of my child accountID

POST -

PUT -

DELETE -

---

##/accounts/{accountID}/movements

GET - Get list of movements between dates

POST -

PUT -

DELETE -

---

###/accounts/{accountID}/transfer/{accountID}

GET -

POST - Transfer between cash accounts

PUT -

DELETE -

---

###/accounts/{accountID}/payment/{accountID}

GET -

POST - Payment between cash accounts

PUT -

DELETE -

---

##/categories/standard/tree

GET - Get standard category tree

POST - 

PUT -

DELETE -

---

##/categories/standard

GET - Get standard category

POST - Create standard category

PUT -

DELETE -

---

##/categories/custom/{familyID}

GET - 

POST - Add a category to the family's tree

PUT -

DELETE -

---

##/categories/standard/list

GET - Get list of all standard categories

POST - 

PUT -

DELETE -
