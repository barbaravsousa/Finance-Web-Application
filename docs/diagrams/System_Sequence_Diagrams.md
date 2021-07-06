# System Sequence Diagrams #
___
## Level 1 ##
___

### HTML Request. DELETE ###

```puml
@startuml
'settings'
'..........................................'
skinparam linetype ortho
hide empty members
hide circles
skinparam monochrome true
skinparam sequenceMessageAlign center
skinparam maxMessageSize 3500
skinparam nodesep 40
title HTML Request. DELETE

'Participants'
'..........................................'
Actor "Actor" as actor
participant ": FFMApplication" as app

'Interactions'
'..........................................'
activate actor
actor -> app : DELETE/ delete variable
activate app
app --> actor: HTTPStatus.OK, \ninforms success
deactivate app
deactivate actor

@enduml
```

### HTML Request. OPTIONS ###

```puml
@startuml
'settings'
'..........................................'
skinparam linetype ortho
hide empty members
hide circles
skinparam monochrome true
skinparam sequenceMessageAlign center
skinparam maxMessageSize 3500
skinparam nodesep 40
title HTML Request. DELETE

'Participants'
'..........................................'
Actor "Actor" as actor
participant ": FFMApplication" as app

'Interactions'
'..........................................'
activate actor
actor -> app : OPTIONS/ ask options
activate app
app --> actor: HTTPStatus.OK, \ninforms allowed options
deactivate app
deactivate actor

@enduml
```


### HTML Request. POST & PUT ###

```puml
@startuml
'settings'
'..........................................'
skinparam linetype ortho
hide empty members
hide circles
skinparam monochrome true
skinparam sequenceMessageAlign center
skinparam maxMessageSize 3500
skinparam nodesep 40
title HTML Request. POST & PUT

'Participants'
'..........................................'
Actor "Actor" as actor
participant ": FFMApplication" as app

'Interactions'
'..........................................'
actor -> app : start action
activate app
activate actor
app --> actor : request data
deactivate app
actor -> app : POST, PUT/ add information (requestedData)
activate app

app --> actor: HTTPStatus.CREATED, \ninforms success
deactivate app
deactivate actor

@enduml
```

### HTML Request. GET ###

```puml
@startuml
'settings'
'..........................................'
skinparam linetype ortho
hide empty members
hide circles
skinparam monochrome true
skinparam sequenceMessageAlign center
skinparam maxMessageSize 3500
skinparam nodesep 40
title HTML Request. GET

'Participants'
'..........................................'
Actor "Actor" as actor
participant ": FFMApplication" as app

'Interactions'
'..........................................'
activate actor
actor -> app : GET/ get information
activate app
app --> actor: HTTPStatus.OK, \ninforms success
deactivate app
deactivate actor

@enduml
```

___
## Level 2 ##
___

### HTML Request. POST & PUT ###

```puml
@startuml
'settings'
'..........................................'
skinparam linetype ortho
hide empty members
hide circles
skinparam monochrome true
skinparam sequenceMessageAlign center
skinparam maxMessageSize 3500
skinparam nodesep 40
title HTML Request. POST & PUT

'Participants'
'..........................................'
Actor "Actor" as actor
participant ": UI" as ui
participant ": FFMApplication" as app

'Interactions'
'..........................................'
actor -> ui : start action
activate actor
activate ui
ui --> actor : request data
deactivate ui
actor -> ui : input requested data
activate ui
ui -> app : POST, PUT/ addInformation()
activate app
app --> ui : httpStatus.ok
deactivate app
ui --> actor : httpStatus.ok, \ninforms success
deactivate ui
deactivate actor    
@enduml
```

### HTML Request. GET ###

```puml
@startuml
'settings'
'..........................................'
skinparam linetype ortho
hide empty members
hide circles
skinparam monochrome true
skinparam sequenceMessageAlign center
skinparam maxMessageSize 3500
skinparam nodesep 40
title HTML Request. GET

'Participants'
'..........................................'
Actor "Actor" as actor
participant ": UI" as ui
participant ": FFMApplication" as app

'Interactions'
'..........................................'
actor -> ui : GET/ start action
activate actor
activate ui
ui -> app : GET/ getInformation()
activate app
app --> ui : httpStatus.OK, info
deactivate app
ui --> actor : httpStatus.OK, info
deactivate ui
deactivate actor    
@enduml
```

### HTML Request. DELETE ###

```puml
@startuml
'settings'
'..........................................'
skinparam linetype ortho
hide empty members
hide circles
skinparam monochrome true
skinparam sequenceMessageAlign center
skinparam maxMessageSize 3500
skinparam nodesep 40
title HTML Request. DELETE

'Participants'
'..........................................'
Actor "Actor" as actor
participant ": UI" as ui
participant ": FFMApplication" as app

'Interactions'
'..........................................'
actor -> ui : DELETE/ start action
activate actor
activate ui
ui -> app : DELETE/ deleteVariable(variable)
activate app
app --> ui : httpStatus.OK
deactivate app
ui --> actor : httpStatus.OK, \ninforms success
deactivate ui
deactivate actor    
@enduml
```

### HTML Request. OPTIONS ###

```puml
@startuml
'settings'
'..........................................'
skinparam linetype ortho
hide empty members
hide circles
skinparam monochrome true
skinparam sequenceMessageAlign center
skinparam maxMessageSize 3500
skinparam nodesep 40
title HTML Request. OPTIONS

'Participants'
'..........................................'
Actor "Actor" as actor
participant ": UI" as ui
participant ": FFMApplication" as app

'Interactions'
'..........................................'
actor -> ui : OPTIONS/ start action
activate actor
activate ui
ui -> app : OPTIONS/ askOptions()
activate app
app --> ui : httpStatus.OK
deactivate app
ui --> actor : httpStatus.OK, \ninforms allowed options
deactivate ui
deactivate actor    
@enduml
```