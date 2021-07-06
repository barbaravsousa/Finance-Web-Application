# US110
=======================================


# 1. Requirements

>__"As a family administrator, I want to get the list of the categories on the family’s category tree."__

## 1.1 Description
The family administrator has the possibility to get a list of the categories on the family's category tree.
That includes the standard categories and the custom categories that where create for this family in particular.

**Demo1** The family administrator can obtain a list of all the categories related to the family he is part of.
- Demo1.1 The standard categories define by the system manager will always be parte of the list.
- Demo1.2 To the list will be added all the existing custom categories related to this family.

# 2. Analysis

## 2.1 Product Owner 
Some answers of the product owner (PO) are important in some design decisions.
> It's not possible to add a category that already exists.

> To this date, there is no requirement describing an order or sorting for this list.


## 2.2 Decisions

- The categories on the list are represented by its categoryDesignation, meaning the list will be filled we Strings and not references to the instance Category.

## 2.3 Dependent US
The [US001](US001.md) and the [US002](US002.md) influence the implementation of this US.
The [US002](US002.md) defines the type of data structure that will be handled to obtain the list of family categories, e.g. a tree usualy needs a recursive function, however with a simple array we could simply make a copy.

# 3. Design

## 3.1. Functionalities Flow

```puml
skinparam monochrome true

title SD Part1
actor "Family Administrator"

"Family Administrator" -> ":UI" : Get list of categories on \nmy family's category tree
activate ":UI"
    ":UI" -> ":GetListOfFamily\nCategoriesController" : getListOfFamilyCategories(familyId)
    activate ":GetListOfFamily\nCategoriesController"

        ":GetListOfFamily\nCategoriesController" -> ":FFMApplication" : getFamilyService()
        activate ":FFMApplication"
        ":GetListOfFamily\nCategoriesController" <-- ":FFMApplication" : familyService
        deactivate ":FFMApplication"

        ":GetListOfFamily\nCategoriesController" -> "familyService\n:FamilyService" : getListOfFamilyCategories(familyId)
        activate "familyService\n:FamilyService"
             ref over "familyService\n:FamilyService"
                    US110 getListOfFamilyCategories(familyId)
             end ref

        ":GetListOfFamily\nCategoriesController" <-- "familyService\n:FamilyService" : List of categories
        deactivate "familyService\n:FamilyService"

    ":UI" <-- ":GetListOfFamily\nCategoriesController" : List of categories
    deactivate ":GetListOfFamily\nCategoriesController"
"Family Administrator" <-- ":UI" : List of categories
deactivate ":UI"
```

```puml
skinparam monochrome true

title getListOfFamilyCategories()

[-> ":FamilyService" : getListOfFamilyCategories(familyId)
activate ":FamilyService"
    ":FamilyService" -> ":FamilyService" : findFamily(familyId)
    ":FamilyService" -> "family\n:Family" : getListOfFamilyCategories()
    activate "family\n:Family"
        "family\n:Family" -> "categories:List<Category>" : categories.size()
        activate "categories:List<Category>"
        "family\n:Family" <-- "categories:List<Category>" : listSize
        deactivate "categories:List<Category>"
        "family\n:Family" -> "newList:List<String>" : new ArrayList(listSize)
        activate "newList:List<String>"
        "family\n:Family" <-- "newList:List<String>" : newList

        participant "newList:List<String>"

        participant "category:Category"
        loop for each Category in categories
            "family\n:Family" -> "category:Category" : getDesignation()
            activate "category:Category"
            "family\n:Family" <-- "category:Category" : categoryDesignation
            deactivate "category:Category"
            "family\n:Family" -> "newList:List<String>" : add(categoryDesignation)
        deactivate "newList:List<String>"
        end

    
    ":FamilyService" <-- "family\n:Family" : List of categories
    deactivate "family\n:Family"
[<-- ":FamilyService" : List of categories
deactivate ":FamilyService"
```

## 3.2. Class Diagram
```puml
skinparam monochrome true

class Category {
    - categoryDesignation : String
    + getDesignation()
}

class Family {
    - categories : List<Category>
    + getListOfFamilyCategories()
}

class FamilyService {
    - familes : List<Family>
    + getListOfFamilyCategories(String familyId)
    + findFamily(familyId)
}

class GetListOfFamilyCategories {
    + getListOfFamilyCategories(String familyId)
}

class FFMApplication {
    - app : FFMApplication
    - personService : PersonService
    - familyService : FamilyServicee
    - categoryService : CategoryService
    + getInstance()
    + getPersonService()
    + getFamilyService()
    + getCategoryService()
}

GetListOfFamilyCategories - FFMApplication
FFMApplication "1" *-- "1" FamilyService
FamilyService "1" *-- "0..*" Family
Family "0..*" *-- "1..*" Category

```

## 3.3. Applied Design Patterns

From GRASP pattern:
Controller,
Information Expert,
Low Coupling

From SOLID:
Single Responsibility Principle

## 3.4. Tests 

**Teste 1:** Returns the expected list of categories.

        static GetListOfFamilyCategoriesController getListOfFamilyCategoriesController;
        static CategoryService categoryService;
        static String familyId;
    
        @BeforeAll
        public static void init() {
            getListOfFamilyCategoriesController = new GetListOfFamilyCategoriesController();
            CreateCategoryController createCategoryController = new CreateCategoryController();
            createCategoryController.createCategory("Energy");
            createCategoryController.createCategory("Food");
            createCategoryController.createCategory("Bank");
            CreateFamilyController createfamilyController = new CreateFamilyController();
            createfamilyController.createFamily("Turing");
            AddFamilyAdministratorController addFamilyAdministratorController = new AddFamilyAdministratorController();
            familyId = addFamilyAdministratorController.getListOfFamiliesWithoutAdministrator().get(0);
            categoryService = new CategoryService();
        }
    
        @Test
        public void returnListOfCategories() {
            int resultSize, expectedSize;
            List<String> result;
            List<String> expected = new ArrayList<>();
            expected.add("Energy");
            expected.add("Food");
            expected.add("Bank");
            expectedSize = expected.size();
            result = getListOfFamilyCategoriesController.getListOfFamilyCategories(familyId);
            resultSize = result.size();
            assertArrayEquals(result.toArray(), expected.toArray());
            assertEquals(resultSize, expectedSize);
        }

# 4. Implementation

* The simplicity of this US granted by the way the tree is stored, makes the *Test 1* an example of the implementation of this functionality.

# 5. Integration/Demo

* Given the implementation of [US001](US001.md) and [US002](US002.md) the only thing necessary to implement the US was the creation of a list o Strings that could store the categoryDesignation of all the instances of the class Category stored in a Family attribute called categories.
Handling Strings instead of instances of the Category class assures more encapsulation of important data. A String being immutable secures a correct handling of the data to the UI.

# 6. Observations

* The data structure that we used in this US to store the tree, i.e List<Category>, could be substituted by another structure.
A data structure like a binaryTree, but with more branches per Node, is a possible alternative.
All the methods that support the US must then be converted to a recursive one. 
A class CategoryTree must also be created and stored where the actual List is stored.
