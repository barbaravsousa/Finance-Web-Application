#Archive: [US186](../../user_stories/sp2/US186.md)

***

##\#1 Class diagram

```puml
skinparam monochrome true
skinparam linetype ortho
top to bottom direction

class AccountService {
    - accounts : List<Account>
    - familyService : FamilyService
    + getListOfPersonAccounts(personId)
    + getListOfMovementsBetweenDates(accountId, initialDate, finalDate) 
    - findAccount(accountId)
}

interface Account{
    + getBalance()
    + withdraw(amount)
    + deposit(amount)
    + changeAccountName(newAccountName)
    + isMyAccount(personId)
    + createDTO()
    + isSameDesignation(categoryDesignation)
    + isCashAccount(accountId)
    + getListOfMovementsBetweenDates(initialDate, finalDate);
}

class Movement {
    + isBetweenDates(initialDate, finalDate)
}

class RootAccount {
    + getListOfMovementsBetweenDates(initialDate, finalDate)
}

class CurrentAccount {
    + getListOfMovementsBetweenDates()
}

class BankSavingsAccount {
    + getListOfMovementsBetweenDates()
}

class CreditAccount {
    + getListOfMovementsBetweenDates()
}

class CashAccount {
    + getListOfMovementsBetweenDates()
}

class FFMApplication {
    + getAccountService()
}

class GetListOfMovementsBetweenDatesController {
    - app : FFMApplication
    + getListOfPersonAccounts(personId)
    + getListOfMovementsBetweenDates(accountId, initialDate, finalDate) 
}

abstract CurrentAccount {
    + getListOfMovementsBetweenDates()
}

GetListOfMovementsBetweenDatesController - FFMApplication
GetListOfMovementsBetweenDatesController -- AccountService

AccountService --* FFMApplication

AccountService *--- Account

Account <|.. BankAccount
Account <|.. CashAccount

BankAccount *-- RootAccount
CashAccount *-- RootAccount

CurrentAccount --|> BankAccount
BankSavingsAccount --|> BankAccount
CreditAccount --|> BankAccount

Movement -right-* RootAccount
```

*date: 2021-03-15*

**Needed improvements:**
- Creation of Transfer and Payment class that will store instances of the class Movement;
- The record of the transaction will be made in an instance of the class ledger;
- New knowledge about the representation of the different associations in UML;

***

##\#2 Sequence diagram

```puml
skinparam monochrome true
autonumber
title SD Part 1
actor "Family Member"

"Family Member" -> ":UI" : get list of movements on one of my accounts between two dates
activate ":UI"

    ":UI" -> ":GetListOfMovements\nBetweenDatesController" : getListOfPersonAccounts(personId)
    activate ":GetListOfMovements\nBetweenDatesController"
    
        ":GetListOfMovements\nBetweenDatesController" -> ":FFMApplication" : getAccountService()
        activate ":FFMApplication"
        ":GetListOfMovements\nBetweenDatesController" <-- ":FFMApplication" : accountService
        deactivate ":FFMApplication"
        
        ":GetListOfMovements\nBetweenDatesController" -> "accountService:\nAccontService" : getListOfPersonAccounts(personId)
        activate "accountService:\nAccontService"
            
            "accountService:\nAccontService" -> "listOfPersonAccounts\n:List<AccountDTO>" *: create
            
            loop for each account in system 
                "accountService:\nAccontService" -> "accountDTO:AccountDTO" *: create
                "accountService:\nAccontService" -> "accountService:\nAccontService" : listOfPersonAccounts.add(accountDTO)
            end
        
        ":GetListOfMovements\nBetweenDatesController" <-- "accountService:\nAccontService" : list of accounts
        deactivate "accountService:\nAccontService"

    ":UI" <-- ":GetListOfMovements\nBetweenDatesController" : list of accounts
    deactivate ":GetListOfMovements\nBetweenDatesController"
   
"Family Member" <-- ":UI" : show list of accounts

"Family Member" -> ":UI" : select account

"Family Member" <-- ":UI" : ask for time frame

"Family Member" -> ":UI" : insert required data
    
    ":UI" -> ":GetListOfMovements\nBetweenDatesController" : getListOfMovementsBetween\nDates(accountId, initialDate, finalDate)
    activate ":GetListOfMovements\nBetweenDatesController"
    
        ":GetListOfMovements\nBetweenDatesController" -> ":FFMApplication" : getAccountService()
        activate ":FFMApplication"
        ":GetListOfMovements\nBetweenDatesController" <-- ":FFMApplication" : accountService
        deactivate ":FFMApplication"
        
        ":GetListOfMovements\nBetweenDatesController" -> "accountService:\nAccontService" : getListOfMovementsBetween\nDates(accountId, initialDate, finalDate)
        activate "accountService:\nAccontService"
            ref over "accountService:\nAccontService"
                SD Part 2
            end
        
        ":GetListOfMovements\nBetweenDatesController" <-- "accountService:\nAccontService" : list of movements between the defined dates
        deactivate "accountService:\nAccontService"
        
    ":UI" <-- ":GetListOfMovements\nBetweenDatesController" : list of movements between the defined dates
    deactivate ":GetListOfMovements\nBetweenDatesController"
  
"Family Member" <-- ":UI" : list of movements between the defined dates
deactivate ":UI"

```

```puml
skinparam monochrome true
autonumber
title SD Part 2
    
[-> "accountService:\nAccontService" : getListOfMovementsBetween\nDates(accountId, initialDate, finalDate)
activate "accountService:\nAccontService"

    "accountService:\nAccontService" -> ":Account" : getListOfMovementsBetween\nDates(initialDate, finalDate)
    activate ":Account"
        
        participant "movements\n:List<Transation>"
            
            ":Account" -> "listOfMovements\n:List<MovementDTO>" * : create()
            
            loop for each movement in movements
            
                ":Account" -> ":Movement" : getListOfMovements\nBetweenDates(initialDate, finalDate)
                activate ":Movement"
                
                ":Movement" -> ":Movement" : isBetweenDates(initialDate, finalDate)
            
                participant "listOfMovements\n:List<MovementDTO>"
                
                alt boolean
                
                    ":Account" <-- ":Movement" : false
                    
                else
                
                    ":Account" <-- ":Movement" : true
                    deactivate ":Movement"
                    
                    ":Account" -> "listOfMovements\n:List<MovementDTO>" : add(movementDTO)
                    activate "listOfMovements\n:List<MovementDTO>"
                    deactivate "listOfMovements\n:List<MovementDTO>"
                    
                end
                
            end
    
    "accountService:\nAccontService" <-- ":Account" : list of movements between the defined dates
    deactivate ":Account"
    
[<-- "accountService:\nAccontService" : list of movements between the defined dates
deactivate "accountService:\nAccontService"

```

*date: 2021-04-16*

**Needed improvements:**
- Implementation Domain-Driven-Design principles;

***