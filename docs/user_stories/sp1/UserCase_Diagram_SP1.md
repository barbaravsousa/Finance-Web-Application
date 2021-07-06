``` puml 
@startuml
skinparam monochrome true

"Family Member" as User

"US150 - To Consult My Profile" as (Use10)
"US151 - To Add an Email Account To My Profile" as (Use11)

left to right direction
User --> (Use10)
User --> (Use11)


"Family Administrator" as Admin

"US001 - To Create Standard Category" as (Use)
"US002 - To Consult Standard Category" as (Use2)
"US010 - To Create a Family" as (Use3)
"US011 - To Add a Family Administrator" as (Use4)

left to right direction
:System_Manager: --> (Use)
:System_Manager: --> (Use2)
:System_Manager: --> (Use3)
:System_Manager: --> (Use4)

"US101 - To add family members" as (Use5)
"US104 - To List Family Members" as (Use6)
"US105 - To Create a Relation Between \n Two Family Members" as (Use7)
"US110 - To List Family Categories Tree" as (Use8)
"US120 - To Create a Family Cash Account" as (Use9)

left to right direction
Admin --> (Use5)
Admin --> (Use6)
Admin --> (Use7)
Admin --> (Use8)
Admin --> (Use9)


@enduml 
```