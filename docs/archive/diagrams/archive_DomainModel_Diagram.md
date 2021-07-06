```puml 
skinparam monochrome true
skinparam sequenceMessageAlign center
hide empty members
hide circles

skinparam component {
  ArrowFontSize 10
}

title Domain Model Diagram SP02

class FamilyRelation {
 -kin
 -relationType
}

class Person {
    -name
    -birthDate
}

class Family {
 -name
 -registrationDate
}

class Category {
}

class CashTypeAccount{
    -balance
    -categoryDesignation
}

class Telephone {
 -number
}

class Email {
 -emailAdress
}

class VAT {
 -number
}

class CC {
 -number
}

class Address {
 -street
 -city
 -zipCode
 -number
 -country
}

class BankTypeAccount {
    -balance
    -categoryDesignation
}

class Account {
}

class StandardCategory {
    -categoryDesignation
}

class CustomCategory {
    -categoryDesignation
}


class Movement {
    -date
    -systemDateEntry
    -amount
    -description
    -balance
}


skinparam linetype ortho
left to right direction
skinparam nodesep 80
skinparam ranksep 80

Family "1" -- "0..*" Person : has >
Family "1" --- "0..1" Person : has admin >
Family "1" - "0..1" CashTypeAccount : has >
Category "0..*" -left- "0..*" Family

Category -- StandardCategory : is <
Category -- CustomCategory : is <

Account -left- CashTypeAccount : is <
Account - BankTypeAccount : is <
Account "1" -- "0..*" Movement : has >

Person "1..*" -left- "0..*" BankTypeAccount : has >
Person "1" -left- "0..1" CashTypeAccount : has >
Person "1" -- "0..*" Email
Person "1" -- "1" Address
Person "1" -- "1" VAT
Person "1" -- "1" CC
Person "1" -- "0..*" Telephone
Person "1" -left- "0..*" FamilyRelation : has >
Person "1" -left- "1" FamilyRelation : is kin <

Movement "0..*" -left- "1" Category : has >

```