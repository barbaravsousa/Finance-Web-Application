```plantuml
skinparam linetype ortho
skinparam monochrome true

hide empty members
left to right direction



class Address {
    - houseNumber : String
    - street : String
    - city : String
    - country : String
    - zipCode : String
}

class CashAccount {
}

class Category {
    - categoryDesignation : String
}

class CC {
    - cc : String
}

class Email {
    - emailAddress : String
}

class Family {
    - id : String
    - name : String
    - registrationDate : Date
}

class FamilyRelation {
    - kinId : String
    - relationType : RelationType
}

class FFMApplication {
}

class Person {
    - id : String
    - name : String
    - birthDate : Date
}

class PhoneNumber {
    - phoneNumber : String
}

class RelationType {
    - relationType : String
}

class VAT {
    - vat : String
}

class CategoryService {
    - categories : List<Category>
}      

class FamilyService {
    - categoryService : CategoryService
 }

class PersonService {
    - relations : List<RelationType>
    - familyService : FamilyService
} 

class AccountService {
    - accounts : List<Account>
    - familyService : FamilyService
} 

class RootAccount {
    - id : String
    - categoryDesignation : String
} 

interface Account {
} 

interface AccountHolder {
} 

class Movement {
    date : Date
    systemDateEntry : Date
    amount : MoneyValue
    description : String
} 

abstract BankAccount {
} 



FFMApplication "1" *-- "1" AccountService 
FFMApplication "1" *-- "1" CategoryService
FFMApplication "1" *-- "1" FamilyService
FFMApplication "1" *-- "1" PersonService 
AccountService -[hidden] CategoryService
FamilyService -[hidden] PersonService

AccountService "1" *-- "0..*" Account

Account <|.. BankAccount
Account <|.. CashAccount
BankAccount -[hidden] CashAccount

BankAccount "1" *- "1" RootAccount
CashAccount "1" *-down- "1" RootAccount

BankAccount <|-- BankSavingAccount
BankAccount <|-- CreditAccount
BankAccount <|-- CurrentAccount

CategoryService "1" o-- "0..*" Category

FamilyService "1" o---- "0..1" Family

Family "0..*" o-up "0..*" Category

Category <|-- CustomCategory
Category "1" --o "0..*" Movement

Movement -down* RootAccount
Movement *- MoneyValue

MoneyValue -down-* CashAccount
MoneyValue -down* BankAccount

PersonService <- Person
PersonService o-down RelationType

RelationType o---- FamilyRelation

Person "1" *-down "0..*" FamilyRelation
Person "1" *-- "1" VAT
Person "1" *-- "1" CC
Person "1" *-- "1" Address
Person "1" *-- "0..*" PhoneNumber
Person "1" *-- "0..*" Email


BankSavingAccount o--- AccountHolder
CreditAccount o--- AccountHolder
CurrentAccount o--- AccountHolder
CashAccount o--- AccountHolder

AccountHolder <|.. Person

Family ..|> AccountHolder
Family o-- Person

````


