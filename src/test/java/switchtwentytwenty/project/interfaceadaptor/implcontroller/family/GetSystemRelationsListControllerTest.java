package switchtwentytwenty.project.interfaceadaptor.implcontroller.family;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.interfaceadaptor.icontroller.relation.IGetSystemRelationsListController;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest()
class GetSystemRelationsListControllerTest {

    @Autowired
    IGetSystemRelationsListController getSystemRelationsController;


    @Test
    void getSystemRelationsList() {

        //act
        ResponseEntity<Object> result = getSystemRelationsController.getSystemRelationsList();
        int expected = 200;

        //assert
        assertEquals(expected, result.getStatusCodeValue());
    }
}