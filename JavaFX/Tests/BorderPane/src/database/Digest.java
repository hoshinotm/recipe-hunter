
/**
 * Created by takayukihoshino on 2/1/17.
 */

package database;

import java.net.PasswordAuthentication;

public class Digest {

    PasswordAuthentication pwAuth;

    public Digest( String userName, String password ) {
        this.pwAuth = new PasswordAuthentication( userName, password.toCharArray() );
    }

    @Override
    public String toString() {
        return pwAuth.getUserName() + "/" + pwAuth.getPassword();
    }

}
