import java.io.IOException;
import java.util.Properties;

/**
  * 获取.properties文件的属性值
  */
public class PropertiesUtil {
	private static Properties p = new Properties();
	private static String f;

	public Tools(String file) {
		f = file;
		try {
			p.load(Tools.class.getClassLoader().getResourceAsStream(f));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据key得到value的值
	 */
	public String getValue(String key) {
		return p.getProperty(key);
	}
}
