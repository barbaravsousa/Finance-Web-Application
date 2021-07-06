``` puml 
@startuml
skinparam monochrome true

note top of FFMSystem: Correlated with "FFMSystem" component of the SystemOverview (L1)
note bottom of UI: Correlates with "FFM_UIO"\ncomponent of LogicView (L2)
note "Correlates with FFM_UIO\ncomponent of LogicView (L2)" as N1

package FFMSystem  {
    
    package domain {
    }
    package controller {
    }
    package UI {
    }
    package constant{
    }
    package utils{
    }
    package exception{
    }
    
}   

UI .right.> controller
controller .right.> domain

domain <.left. constant
domain <.left. utils
domain <.left. exception
controller .. N1
N1 .. domain

@enduml 
```

``` puml 
@startuml
skinparam monochrome true

note bottom of controller: Correlates with "Controllers"\ncomponent of L3_BL_LogicView
note bottom of service: Correlates with "Services"\ncomponent of L3_BL_LogicView

package controller {}

package domain  {
    
    package category{
    }

    package factory{
    }
    
    package family{
    }
    
    package familyrelation{
    }
    
     package person{
    }
    
     package service {
    }
    
    package repository {
    }
    
    package share{
    }
 
 
 }
 
controller .right.> domain
person .right.> service
category .right.> service    
family .right.> service
service .right.> repository
repository .right.>factory       
familyrelation .right.>family
   
@enduml 
```