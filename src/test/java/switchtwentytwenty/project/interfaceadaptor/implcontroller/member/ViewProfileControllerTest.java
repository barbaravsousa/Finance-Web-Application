package switchtwentytwenty.project.interfaceadaptor.implcontroller.member;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.applicationservice.appservice.implappservice.PersonService;
import switchtwentytwenty.project.dto.outdto.UserProfileOutDTO;
import switchtwentytwenty.project.exception.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.when;

    @ExtendWith(MockitoExtension.class)
    public class ViewProfileControllerTest {

        @InjectMocks
        ViewProfileController controller;
        @Mock
        PersonService service;

        @Test
        @DisplayName("Gets user profile")
        void getProfileSuccessfully() throws Exception {
            //arrange
            String personId = "dragon@gmail.com";
            String name = "Dragon";
            String street = "Rua Nova,25";
            String zipCode = "4234-886";
            String city = "Valongo";
            String country = "Portugal";
            String houseNumber = "20";
            String birthDate = "22/04/1997";
            List<String> telephone = new ArrayList<>();
            telephone.add("234567890");
            String vat = "123456789";
            List<String> otherEmail = new ArrayList<>();
            otherEmail.add("otheremail@gmail.com");
            UserProfileOutDTO profile = new UserProfileOutDTO.UserProfileDTOBuilder()
                      .withName(name)
                      .withBirthDate(birthDate)
                      .withCity(city)
                      .withHouseNumber(houseNumber)
                      .withCountry(country)
                      .withStreet(street)
                      .withZipCode(zipCode)
                      .withVat(vat)
                      .withTelephoneNumbers(telephone)
                      .withMainEmail(personId)
                      .withOtherEmails(otherEmail)
                      .build();
            //act
            when(service.getUserProfile(personId)).thenReturn(profile);

           ResponseEntity<Object> result = controller.getUserProfile(personId);
           int expected = 200;

            //assert
            assertEquals(expected, result.getStatusCodeValue());
        }

        @Test
        @DisplayName("Gets user profile: failure")
        void getProfileFailure() throws Exception{
            //arrange
            String personId = "noname@gmail.com";

            //act
            willThrow(new ElementNotFoundException("Person Not Found")).given(service).getUserProfile(personId);
            int expected = 422;

            ResponseEntity<Object> result = controller.getUserProfile(personId);

            //assert
            assertEquals(expected,result.getStatusCodeValue());

        }
}