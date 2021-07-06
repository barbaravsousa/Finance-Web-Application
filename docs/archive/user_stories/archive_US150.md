# US150 - Get Profile Information
=======================================


# 1. Requirements

>__"As a family member, I want to get my profile’s information."__

## 1.1. Description 

* Any family member has the possibility to access their profile information, that has their basic information.
  
**Demo1** The family member wants to see their profile information.
- Demo1.1. The family member is also a family administrator, in that case it will appear in the profile information.
- Demo1.2. The family member doesn't have an email or phone number, those fields will appear empty on the profile page. 


# 2. Analysis

##2.1.Product Owner 

* The product owner requested that the only information visible to the family member is basic information such as the name,birth-date, telephone,email,citizen number,Vat number and address.
## 2.2. US Dependencies

* This User Storie doesn't have other user stories depending on it but has some dependency with [US010](US010.md) (to create a family) and [US101](US101.md) (add family members), since it's necessary to have a family first in order to add the family members and only then these members are able to see their profile information.

## 2.3. US Dependencies


# 3. Design

## 3.1. Functionalities Flow
```puml
skinparam monochrome true

autonumber
title Get Profile Information
actor "Family Member" as FM
participant UI
participant ":viewProfileInfoController" as VPC
participant ":FMApplication" as App
participant ":FamilyService" as fs
participant ":FamilyList" as fl
participant ":Person" as P
participant ":PersonList" as pl

FM -> UI: request profile infomation
activate UI
UI -> VPC : getUserProfile(personID,familyID)
activate VPC
VPC -> App : getFamilyService()
activate App
VPC <-- fs : familyService
deactivate App
VPC -> fs :getUserProfile(personID,familyID)
activate fs
deactivate VPC

deactivate App

loop for each FamilyID in the list of families
fs -> fl: findFamily(familyID)
activate fl
   alt Family in list of families
   fl --> fs: Family 
   deactivate fs
   deactivate fl
   end
end
loop for each member on the Family 
P -> pl: getPerson(PersonId)
activate P
activate pl
  alt if person!=null
    pl -> P:getProfile(personId);
    deactivate pl
  end
end

P -> fs : PersonProfile(name,vatNumber,\nphoneNumber,email,address,\nbirthDate,family)
deactivate P

   

fs --> VPC :PersonProfile(name,vatNumber,\nphoneNumber,email,address,\nbirthDate,family)
deactivate fs
activate VPC
VPC --> UI :PersonProfile(name,vatNumber,\nphoneNumber,email,address,\nbirthDate,family)
deactivate VPC
 deactivate UI  

UI --> FM :PersonProfile(name,vatNumber,\nphoneNumber,email,address,\nbirthDate,family)
```
The ViewProfileController will invoke the FamilyService, that contains the list of families, in which is possible to access all the users(in the list of members) and their personal ID. 
<p>To be able to access the person's information, we must go through the list of family members, and check to see which one has the same ID as the person who did the request to see the profile information. When the ID's are the same, it will be shown the person profile with the following information:</p>

- Name
- Birth Date
- Phone Number(s)
- Email(s)
- VAT number
- Citizen Number
- Address

## 3.2. Class Diagram
```puml
skinparam monochrome true

class "ViewProfileController"
class "FFMApplication"
class "Person"
class "FamilyService"

class ViewProfileController {
 
    + getUserProfile(String personId, String familyId)
}

class FamilyService{
    + getUserProfile(personId, familyId)
    + findFamily(familyId) 
    
}
class Family{
    +findPerson(personId)
}

class FFMApplication {
 
}

class Person {
    - id : String
    - cc : String
    - name : String
    - birthDate : Date
    - vat : VAT
    - address : PostalAddress
    - phoneNumbers : PhoneNumberList
    - emails : EmailList
    - isAdministrator : boolean
    +getProfile()
}

ViewProfileController -- FFMApplication :familyService
FFMApplication -- FamilyService
FamilyService "1"--* "1..*" Family :familyList
Family "1..*" --  "1..*" Person :personList

```
As shown in the sequence diagram there is the class Controller that makes the connection between the UI and the business logic.
The classes Vat, Address, Email, PhoneNumber and Family are attributes to the class Person.
The main functionality of the FFMApplication is to delegate the incoming requests to the appropriate Services,in this  case,PersonService which contains the FamilyService.
## 3.3. Applied Design Patterns
From GRASP pattern:
Controller and Information Expert

## 3.4. Tests 

**Test 1:** Get family member profile

	@Test
    @DisplayName("Get family member profile")
    public void testGetProfile() {

        String cc = "17883679";
        String name = "Raquel";
        String birthDate = "31/01/1997";
        String vat = "506931137";
        String houseNumber = "15";
        String street = "Rua Da Estação";
        String city = "Paredes";
        String country = "Portugal";
        String zipCode = "4589-701";
        String Address = "Street:" + street + ", " + "House Number:" + houseNumber + ", " + "ZipCode:" + zipCode + ", " + "City:" + city + ", " + "Country:" + country;

        ArrayList<String> emails = new ArrayList<String>();
        emails.add("1120717@isep.ipp.pt");

        ArrayList<String> phoneNumbers = new ArrayList<String>();
        phoneNumbers.add("919104587");
        phoneNumbers.add("939652158");

        String famId = this.app.getFamilyService().createFamily("Santos");

        this.app.getFamilyService().addFamilyMember(cc, name, birthDate, vat, houseNumber, street, city, country, zipCode,
                phoneNumbers, emails, famId);
        String personId = this.app.getFamilyService().findFamily(famId).getPersonIdbyCc(cc);
        ViewProfileInfoController viewProfileInfoController = new ViewProfileInfoController(app);
        HashMap<String, String> personProfile = viewProfileInfoController.getUserProfile(personId, famId);
        viewProfileInfoController.displayUserProfile(personProfile);
        Assertions.assertNotNull(personProfile);
        Assertions.assertEquals(cc, personProfile.get("CC"));
        Assertions.assertEquals(name, personProfile.get("Name"));
        Assertions.assertEquals(birthDate, personProfile.get("BirthDate"));
        Assertions.assertEquals(vat, personProfile.get("VAT"));
        Assertions.assertEquals(Utils.stringBuilder(phoneNumbers).toString(), personProfile.get("PhoneNumbers"));
        Assertions.assertEquals(Utils.stringBuilder(emails).toString(), personProfile.get("Emails"));
        Assertions.assertEquals(Address, personProfile.get("Address"));
    }

# 4. Implementation

* The implementation of this US, was quite simple because of the user stories that were implemented before, such as creating a family and adding a family member.
  
  *Test 1*, is a good example of that.

# 5. Integration/Demo
* Given the implementation of [US001](US010.md) and [US001](US101.md), it wasn't necessary to implement more methods.
