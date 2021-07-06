``` puml 
@startuml
skinparam monochrome true

() "UI"
component "Family Finance Management System" <<Component>> as ffm{
}
interface "UI" as UI

UI - ffm

@enduml
```


``` puml 
@startuml
skinparam monochrome true

DataAccess --# [FFM_UI]



component "Family_Finance_Management_System" <<Component>>{
    component  "FFM_UI" <<Component>>{
    }

    component  "FFM_BusinessLogic" <<Component>>{
    }
}

left to right direction 

FFM_UI  #-(0- FFM_BusinessLogic

note bottom of FFM_UI: By now, it is not \nbeing developed

note bottom of FFM_BusinessLogic: It is the one being developed \non SP01, SP02 and SP03 

@enduml
```

 ``` puml 
 @startuml
skinparam monochrome true
 circle "BL_API" as BL_API
 
 component FFM_BusinessLogic as FFM_BL <<component>> {
    component Controllers <<component>> {
    }
    circle Model_API
    circle Service_API
    circle DTO_API
    component Model <<component>> {
    }
    component Services <<component>> {
    }
    component DTO <<component>> {
    }
 }
 
 BL_API -# FFM_BL
 
 FFM_BL - Controllers
 Controllers -( Model_API
 Controllers -up-( Service_API
 Controllers -down-( DTO_API
 Service_API )-right- Services
 Model_API -right- Model
 DTO_API )-right- DTO
 Services -( Model_API
 DTO -up-( Model_API
 
 @enduml
 ```