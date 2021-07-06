```puml
skinparam monochrome true

"System Managerr" as sm
"Family Administrator" as Admin
"System User" as us

"US003- I want the list of standard categories to include those loaded (whenever needed) from a complementary system defined by configuration" as (Use3)
"US111v2 - I want to add a custom category to the family’s category tree "extended" from either external or internal standard categories" as (Use111)
"US172 - I want to login into the application in order to use it" as (Use80)

left to right direction
sm --> (Use3)
Admin --> (Use111)
us --> (Use80)
```