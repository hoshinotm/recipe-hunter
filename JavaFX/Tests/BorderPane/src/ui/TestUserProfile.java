package ui;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import core.CoreSettings;
import database.UserDataAccess;
import database.UserRecord;

import java.sql.Connection;

/**
 * Created by takayukihoshino on 2/27/17.
 */
public class TestUserProfile extends UserProfile {

    private static final String TEST_USER_NAME = "test-user";
    private static final String TEST_USER_EMAIL = "hoshinot@gmail.com";
    private static final String TEST_USER_PHONE = "+1-773-425-2824";

    public TestUserProfile( ) {
        super( new UserName( TEST_USER_NAME ), TEST_USER_EMAIL, TEST_USER_PHONE );
    }
}
