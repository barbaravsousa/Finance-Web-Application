```plantuml
skinparam linetype polyline
skinparam linetype ortho
skinparam monochrome true
skinparam sequenceMessageAlign center

hide circle 
hide methods
package "Person Aggregate" {
class Person <<Aggregate Root>>, <<Entity>>
class PersonName <<Value Object>>
class VAT <<Value Object>>
class Address <<Value Object>>
class BirthDate <<Value Object>>
class TelephoneNumber <<Value Object>>
}
package "Family Aggregate" {
class Family <<Aggregate Root>>,  <<Entity>>
class RegistrationDate <<Value Object>>
class FamilyName <<Value Object>>
}
package "Catagory Aggregate" {
class Category <<Aggregate Root>>, <<Entity>>
class Designation <<Value Object>>
class "Custom Category" as Custom <<Entity>>
class "Standard Category" as Standard <<Entity>>
}
package "FamilyRelations Aggregate" {
class FamilyRelationList <<Aggregate Root>>, <<Entity>>
class FamilyRelation <<Value Object>>
}

class RelationType <<Value Object>>
class "FamilyRelation Repository" as FamilyRelationRepository
class "Category Repository" as CategoryRepository
class "Person Repository" as PersonRepository
class "Family Repository" as FamilyRepository
class "   App   " as App
class Email <<Value Object>>, <<ID>>
class "Category ID" as CategoryID <<Value Object>>, <<ID>>
Class "Family ID" as FamilyID <<Value Object>>, <<ID>>

FamilyRelationList "1" --> "0..*" FamilyRelation
FamilyRelationList <-l- FamilyRelationRepository
FamilyRelationList --> "1" FamilyID
FamilyRelation "0..*" --> "1" RelationType

Family-->"1  "RegistrationDate
Family-->"1 "FamilyName
FamilyRepository-->Family
Family-->"members  0..*   "Email 
Family-->"admin 1" Email
Family-->"custom\ncategories\n0..* " CategoryID
Family--> "id 1"FamilyID

Category-->"id       " CategoryID
Category-->"1 "Designation
Custom--|>Category
Standard--|>Category
CategoryRepository-->Category

Person-->"1 " PersonName
Person "1" -->"1" VAT
Person "1"-->"1 " Address
Person"1"-->" 1 " BirthDate
Person "1..*"-->"0..*"TelephoneNumber
PersonRepository--->Person
Person---> "id 1"  Email
Person -->"emails\n0..*" Email

App....>FamilyRelationRepository
App....>Family
App....>FamilyRepository
App..>CategoryRepository
App...>Category
App.....>Person
App...l>PersonRepository
```