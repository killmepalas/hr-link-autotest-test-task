package user;

import utils.PropertyReader;

public class UserFactory {

    public static User withAdminPermission(){
        return new User(PropertyReader.getProperty("hr-link.login.email.as.admin"),
                PropertyReader.getProperty("hr-link.user.password"));
    }

    public static User withHRPermissionWithoutLegalEntityRight(){
        return new User(PropertyReader.getProperty("hr-link.email.as.employeeWorkInTeplo"),
                PropertyReader.getProperty("hr-link.user.password"));
    }


}
