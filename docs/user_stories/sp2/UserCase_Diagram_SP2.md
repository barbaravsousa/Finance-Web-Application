``` puml 
@startuml
skinparam monochrome true

"Family Member" as User
"Family Administrator" as Admin
"Parent" as Parent

"US170 - I want to create a personal cash account" as (Use10)
"US171 - I want to add a bank account I have" as (Use11)
"US172 - I want to add a bank savings account I have" as (Use12)
"US173 - I want to add a credit card account I have" as (Use13)
"US180 - I want to transfer money from my cash account to another family member’s cash account" as (Use14)
"US181 - I want to register a payment that I have made using one of my cash accounts" as (Use15)
"US185 - I want to check the balance of one of my accounts" as (Use16)
"US186 - I want to get the list of movements on one of my accounts between to dates" as (Use17)

left to right direction
User --> (Use10)
User --> (Use11)
User --> (Use12)
User --> (Use13)
User --> (Use14)
User --> (Use15)
User --> (Use16)
User --> (Use17)

"US106 - I want to change the relation between two family members" as (Use1)
"US111 - I want to add a category to the family’s category tree" as (Use2)
"US130 - I want to transfer money from the family’s cash account to another family member’s cash account" as (Use3)
"US135 - I want to check the balance of the family’s cash account or of a given family member" as (Use4)

left to right direction
Admin --> (Use1)
Admin --> (Use2)
Admin --> (Use3)
Admin --> (Use4)

"US188 - I want to check the balance of one of my children’s cash account" as (Use18)

left to right direction
Parent --> (Use18)

@enduml 
```