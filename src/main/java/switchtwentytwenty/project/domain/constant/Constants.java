package switchtwentytwenty.project.domain.constant;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Constants {

    //Constructor Method

    /**
     * Private sole constructor.
     */
    private Constants() {}

    //Exception messages
    public static final String UNSUPPORTED_OPERATION = "This operation is not supported in this account.";
    public static final String FAMILY_NOT_FOUND = "Family not found.";

    //Relation types system denomination
    public static final String RELATION_TYPE_CONFIG_FILE = "./RelationTypes.properties";
    public static final String ACCOUNT_TYPE_CONFIG_FILE = "./Accounts.properties";

    public static final String PARENT = "parent";
    public static final String CHILD = "child";
    public static final String SPOUSE = "spouse";
    public static final String SIBLING = "sibling";
    public static final String UNCLE = "uncle";
    public static final String NEPHEW = "nephew";
    public static final String GRANDPARENT = "grandparent";
    public static final String GRANDCHILD = "grandchild";
    public static final String COUSIN = "cousin";
    public static final String FRIEND = "friend";
    public static final String PARTNER = "partner";
    public static final String NOT_DEFINED = "not defined";

    //Movement type
    public static final String DEBIT = "debit";
    public static final String CREDIT = "credit";
    public static final List<String> MOVEMENTS_TYPE = Collections.unmodifiableList(Arrays.asList(DEBIT,CREDIT));

    //Date format
    public static final String REGISTRATION_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String SYSTEM_ENTRY_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String MOVEMENT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String BIRTH_DATE_FORMAT = "yyyy-MM-dd";


    public static final String CREDIT_ACCOUNT_TYPE = "credit";
    public static final String CURRENT_ACCOUNT_TYPE = "current";
    public static final String BANK_SAVINGS_ACCOUNT_TYPE = "savings";
    public static final String CASH_ACCOUNT_TYPE = "cash";

    //URI's
    public static final String URI_USERS = "/users";

    //URL's
    public static final String URL_CATEGORIES_GROUP_I = "http://vs249.dei.isep.ipp.pt:8080/categories/standard/list";
    public static final String URL_CATEGORIES_GROUP_III = "http://vs116.dei.isep.ipp.pt:8080/categories";
    public static final String URL_EXTERNAL_CATEGORIES_CONFIGURATION = "src/main/resources/ExternalCategoryConfiguration.xml";

    //Prefix Group's
    public static final String GROUP_I = "G1-";
    public static final String GROUP_III = "G3-";


}
