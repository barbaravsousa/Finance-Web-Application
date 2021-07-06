```puml
skinparam monochrome true
autonumber
skinparam SequenceMessageAlign left
title Bootstrap Sequence Diagram


activate ":Bootstrap"
":Bootstrap" --> "repoX1 : RepoX" : : 
":Bootstrap" --> "appServiceX1 : AppServiceX" : :create(repoX1)
":Bootstrap" --> "repoY1 : RepoY" : 
":Bootstrap" --> "appServiceY1 : AppServiceY" : :create(repoY1)
":Bootstrap" --> "ctrlX1 : ControllerX" : :
":Bootstrap" --> "routeX : RouteX" : :create("/X", ctrlX1)
":Bootstrap" --> "<<Singleton>> Container" : :
":Bootstrap" -> "<<Singleton>> Container" : :set("AppServiceX", appServiceX1)
":Bootstrap" -> "<<Singleton>> Container" : :set("AppServiceY", appServiceY1)
":Bootstrap" -> "<<Singleton>> Container" : :set("RepoX, repoX1)
":Bootstrap" -> "<<Singleton>> Container" : :set("RepoY", repoY1)


```