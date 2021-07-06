#Archive: [US105](../../user_stories/sp1/US105.md)

***

##\#1 System sequence diagram

```puml
skinparam monochrome true
autonumber
title SSD
actor "Family Administrator"

"Family Administrator" -> "System" : change relation between two family members

"Family Administrator" <-- "System" : select family member

"Family Administrator" -> "System" : input required data

"Family Administrator" <-- "System" : select relation to be updated

"Family Administrator" -> "System" : input required data

"Family Administrator" <-- "System" : select new relation type

"Family Administrator" -> "System" : input required data

"Family Administrator" <-- "System" : result
```

*date: 2021-04-07*

**Needed improvements:**
- Implementation Domain-Driven-Design principles;
- Onion Layered Architecture

***

##\#2 Sequence diagram

```puml

skinparam monochrome true
title SD Part1
actor "Family Administrator"

"Family Administrator" -> ":UI" : change relation between two family members

"Family Administrator" <-- ":UI" : select family member

"Family Administrator" -> ":UI" : input required data

"Family Administrator" <-- ":UI" : select relation to be updated

"Family Administrator" -> ":UI" : input required data

"Family Administrator" <-- ":UI" : select new relation type

"Family Administrator" -> ":UI" : input required data

    ":UI" -> ":UpdateRelationController" : updateRelation()

        ref over ":UpdateRelationController"
            US105
        end

    ":UI" <-- ":UpdateRelationController" : informs success

"Family Administrator" <-- ":UI" : informs success

```

*date: 2021-04-07*

**Needed improvements:**
- Implementation Domain-Driven-Design principles;
- Onion Layered Architecture

***

##\#3 Class diagram

```puml
skinparam linetype ortho
skinparam monochrome true
left to right direction

class UpdateRelationController {
    + getListOfFamilyMembers(familyId)
    + getListOfFamilyRelationsAndNameMembersFromAPerson(personId, familyId)
    + getListOfPossibleRelations()
    + updateRelation(personId1, personId2, relationType)
}

class FFMApplication {
    + getInstance()
    + getPersonService()
    + getFamilyService()
    + getCategoryService()
}

class PersonService {
    - relations : List<RelationType>
    - isValidRelation(relationType)
    + getOpposite(relationType)
    + getListOfPossibleRelations()
    + createRelation(personId, kinId, relationType, familyId)
    + findPerson(personId)
    - initialiseListOfRelations();
}

class FamilyService {
    + getListOfFamilyMembers(familyId)
    + findFamily(familyId) 
}

class Family {
    - name : String
    - registration  : Date
    - administrator : Person   
    + getListOfFamilyMembers(familyId)
    + findPerson(personId) 
    + changeRelation(personId, relationType)
    + createRelation(personId, relationType)
    + addRelation(relation)
}

class FamilyRelation {
    - kinId : String
    - relationType : RelationType
}

class Person {
    - id : integer
    - name : String
    - birthDate : Date
    - vat : VAT
    - address : PostalAddress
    - phoneNumbers : List<PhoneNumber>
    - emails : List<Email>
    - relations : List<FamilyRelation> 
    + changeRelation(personId, relationType)
}

class RelationType {
    - type: String
    - oppositeType
}

UpdateRelationController --> FFMApplication : > controlls
UpdateRelationController -- PersonService

FFMApplication -> PersonService
FFMApplication -> FamilyService
FamilyService "1" *-right- "0..*" Family

Person "0..*"--* "1" Family
Person "1" *-- "0..*" FamilyRelation 
PersonService - RelationType
PersonService -- Person

```

*date: 2021-04-07*

**Needed improvements:**
- Implementation Domain-Driven-Design principles;
- Onion Layered Architecture

***
