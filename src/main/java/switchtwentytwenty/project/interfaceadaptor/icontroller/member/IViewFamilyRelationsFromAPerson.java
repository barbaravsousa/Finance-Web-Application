package switchtwentytwenty.project.interfaceadaptor.icontroller.member;

import org.springframework.http.ResponseEntity;

public interface IViewFamilyRelationsFromAPerson {

    /**
     * Get family relation's from a person given his/her ID
     *
     * @param personID - person ID
     * @return a family relation list
     */
    ResponseEntity<Object> getFamilyRelationByPersonID(String personID);
}
