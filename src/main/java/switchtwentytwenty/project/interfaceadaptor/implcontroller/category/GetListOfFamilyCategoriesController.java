package switchtwentytwenty.project.interfaceadaptor.implcontroller.category;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.ICategoryService;
import switchtwentytwenty.project.dto.outdto.CategoryOutDTO;
import switchtwentytwenty.project.exception.ElementNotFoundException;
import switchtwentytwenty.project.interfaceadaptor.icontroller.category.IGetListOfFamilyCategoriesController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"}, maxAge = 3600)
public class GetListOfFamilyCategoriesController implements IGetListOfFamilyCategoriesController {

    //Attributes

    @Autowired
    private final ICategoryService categoryService;


    //Business Methods

    /**
     * Get list of the categories on the family’s category tree.
     *
     * @param familyID - family identifier
     * @return list of categories
     */
    @GetMapping(value = "/families/{familyID}/categories")
    public ResponseEntity<Object> getListOfFamilyCategoriesController(@PathVariable(value = "familyID") String familyID) throws ElementNotFoundException {
        try {
            List<CategoryOutDTO> dtoList = categoryService.getListOfFamilyCategories(familyID);

            if (dtoList.isEmpty()) {
                return new ResponseEntity<>(dtoList, HttpStatus.NO_CONTENT);
            }

            Link allStandardLink = linkTo(methodOn(GetAllStandardCategoriesController.class).getAllStandardCategories()).withRel("all standard categories");

            for (CategoryOutDTO categoryOutDTO : dtoList) {
                categoryOutDTO.add(allStandardLink);
            }

            return new ResponseEntity<>(dtoList, HttpStatus.OK);

        } catch (NullPointerException exception) {
            String msg = exception.getMessage();
            return new ResponseEntity<>(msg, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    //Equals and HashCode
}
