package g.h.i;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.util.Properties;


public class Clitu {

	public static final Properties qq4 = new Properties();

	static {
		try {
			qq4.load(Qlitu.class.getResourceAsStream("../config/config.properties"));
		} catch (Exception e) {
			
		}
	}
}
