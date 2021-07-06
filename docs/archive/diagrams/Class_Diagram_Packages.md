
```puml
skinparam monochrome true

package SistemaOPT {

   package Infrastructure  {
     package Persistence{}
     package Routes{}

   }
   
     package InterfaceAdapters{
     package Repositories{}
     package Controllers{
       package IControllers{}
       package ImplControllers{}
     }
   
   
   package ApplicationServices{
     package AppServices{
       package IAppServices{}
       package ImplAppServices{}
       package IRepositories{}

     }
   }
   
    package Domain{
      package Services{}
      package ValueObjects{}
      package Aggregates{}
   } 

}

Routes .down.> IControllers
Repositories .up.> Persistence 
ImplControllers .left.|> IControllers 
Repositories .down.|> IRepositories
ImplAppServices .down.> ValueObjects
ImplAppServices .left.> Aggregates
ImplAppServices .right> Services
ImplAppServices .right.> IRepositories
ImplControllers .down.> IAppServices
IAppServices <|.right. ImplAppServices

```