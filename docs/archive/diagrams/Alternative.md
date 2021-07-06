```plantuml
skinparam linetype ortho
skinparam monochrome true
hide empty members
title Alternative Approach

'Classes'

class CreditAccount extends BasicBankAccount {
}

class BankAccount extends BasicBankAccount {
}

class BankSavingsAccount extends BasicBankAccount {
}

class BasicBankAccount implements Account{
}

class CashAccount implements Account{
}

'Class Relations'
Controller - AccountService
Controller -up- FFMApplication
FFMApplication *- AccountService
FFMApplication *-- FamilyService
AccountService - FamilyService
FamilyService *- Family
Family *- Person
Person *-- Account
Movement -* BasicAccount
Movement *-left- MoneyValue

CashAccount *-- BasicAccount
BasicAccount -* BasicBankAccount

```

```plantuml
skinparam linetype ortho
skinparam monochrome true
hide empty members
title Approach

'Classes'

class CreditAccount extends BasicBankAccount {
}

class BankAccount extends BasicBankAccount {
}

class BankSavingsAccount extends BasicBankAccount {
}

class BasicBankAccount implements Account{
}

class CashAccount implements Account{
}

'Class Relations'
Controller - AccountService
Controller -up- FFMApplication
FFMApplication *- AccountService
AccountService *- Account
Movement -* BasicAccount
Movement *-left- MoneyValue

CashAccount *-- BasicAccount
BasicAccount -* BasicBankAccount

```