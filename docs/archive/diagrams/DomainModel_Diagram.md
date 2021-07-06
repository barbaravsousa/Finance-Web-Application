```puml 
skinparam monochrome true
skinparam sequenceMessageAlign center

skinparam linetype polyline
skinparam linetype ortho
hide empty members
hide circles

skinparam component {
  ArrowFontSize 10
}

title Domain Model Diagram SP03

class "Family Relation" as FR{
 -Relation Type
}

class Person {
    -Name
    -Birth Date
    -VAT
    -Address
}

class Family {
 -Name
 -Registration Date
}

class Category {
}


class "Telephone Number" as TN{
}

class "Email Account" as EA{
 
}
class Ledger{
}
class "Financial Transaction" as FT{
    -description
    -date
    -entryDate
    -balance
}
class "Financial Service Provider" as FSP {
}

class "Cash Account" as CA{
    -amount
}
class "Bank Account" as BA {
    -balance
}

class "Debit Card Account" as DCA {
    - balance
}

class "Credit Card Account" as CCA {
    -balance
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
    -amount
}
class Transfer{
}
class Payment{
}

class "Service Provider" as SP{
}
class Invoice {
    -Family members VAT
    -Seller VAT
    -Date
    -Value
    -description
}
class Contract {
    -Number
    -Other important information
}
class "Recurrent Future Transaction" as RF {
    -initial date
    -end date
}
class "Future Transaction" as Future{
    -value
}
class "Unique Future Transaction" as UF {
    -date
}


Family "1"-> "0..1" CA : has >
Family "1"--> "member 1..*  " Person : has >
Family "   1" --> "1 admin" Person : has >
Family "1" --> "0..*"CustomCategory : customizes >

CA--|>Account
BA ---|>Account
DCA ---|>Account
CCA ---|>Account
Person "0..*"---> "    1"Account : owns >
Movement "0..*" --> "1" Account : linked to >

Transfer "1"---> "1" Movement : debit >
Transfer "1"---> "1" Movement : credit >
Transfer ---|> FT

Payment "1" ----> "1" Movement : debit >
Payment ---|> FT
Invoice "1"---> "0..*"Payment : involves >

Invoice "0..*"--->"0..1" Contract :has >
SP "0..1"--> "0..*" Invoice : provides >

Contract "1" ---> "0..1" RF : creates >
RF ---|> Future
UF ---|> Future

FT "0..*" ---> "1" Category : has >

CustomCategory ---|> Category
CustomCategory "0..*"--->"0..1" Category : child of >
StandardCategory ---|> Category
StandardCategory "0..*" ---> "0..1" StandardCategory : is child >
StandardCategory "1"---> "0..*" Invoice :has >

Ledger "0..1" ---> "1" FT : registers >
FSP "0..1" ---> "0..*" FT : provides >

Person "1" ---> "0..*" FR : has >
Person "1" ---> "0..* other emails" EA : has >
Person "1" --> "1 main email" EA : has >
Person "1" ---> "0..*" TN : has > 
Person "1" ---> "1" Ledger : has >
Person "0..1" ---> "0..*" FT : registers >
Person "1" ---> "0..*" Future : creates >
Person "0..1" ---> "0..*" Invoice : registers >
```