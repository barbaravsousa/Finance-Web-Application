package switchtwentytwenty.project.interfaceadaptor.icontroller.relation;

import org.springframework.http.ResponseEntity;

public interface IGetSystemRelationsListController {

    /**
     * Get family relation's from a person given his/her ID
     *
     * @return a family relation list
     */
    ResponseEntity<Object> getSystemRelationsList();
}
