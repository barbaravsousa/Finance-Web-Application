# US088
=======================================
# 1. Requirements

>__"As a System User, I want to login into the application in order to use it"__

## 1.1 Description
# 2. Analysis
## 2.1.Domain Model in the US context
## 2.2 Product Owner
## 2.3 US Dependencies
## 2.4. System Sequence Diagram
# 3. Design
## 3.1. Spring Boot Sequence Diagram in the US context
```puml

'Settings'
skinparam defaultFontSize 10
skinparam sequenceMessageAlign center
skinparam monochrome true
title Main Sequence Diagram
skinparam titleFontSize 20
autonumber

'UserStory Ententies'
actor "User" as user
participant "UI" as ui
participant " : AuthenticationController" as controller
participant " : UserRepository " as userrepos
participant " userJPA : UserJPA " as userjpa

'Entety Relations'
activate user
user -> ui : Sign Up Request
activate ui
ui --> user : Request data
user -> ui : Input data (username, email, password)
ui -> controller : POST/ registerUser(loginRequest)
activate controller
controller -> userrepos : existsByUsername (username)
activate userrepos
userrepos --> controller : false
controller -> userrepos : existsByEmail (email)
userrepos --> controller : false
deactivate userrepos
controller -> userjpa *: create()
note right : the username and \nemail are save \nwithout beeing incriptated, \nthe password is not.
ref over controller : Set up authorization role \n of the user
controller -> userrepos : save (userJPA)
controller -> ui : httpSatus.ok
deactivate controller
ui -> user : "User created Successfully"
```
## 3.2.Class Diagram
## 3.3. Functionalities Flow
## 3.4. Applied Design Patterns
## 3.5. Tests
# 4. Implementation
# 5. Integration/Demo

