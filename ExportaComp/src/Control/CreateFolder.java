
package Control;

import java.io.File;

/**
 *
 * @author PHAIMBNOT003
 */
public class CreateFolder {

    public void createFolder() {

        File fli = new File("C:\\xml");

        if (!fli.exists()) {

            fli.mkdirs();

        }

        
    }

}
