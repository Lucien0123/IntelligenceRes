package tcu.lucien.util;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

public class ParamConfigUtil {
	private Properties properties;

	public ParamConfigUtil() {
		properties = new Properties();
		try {
			properties.load(getClassLoader().getResourceAsStream("conf.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 得到整个参数
	 * @return -配置文件中的值
	 */
	public String getParamStr(String param) {
		return properties.getProperty(param);
	}

	/**
	 * 将配置文件中的值封装成Map
	 * @return map -key：接口文档中参数的字段 vaule:配置文件中所给的值
	 */
	public Map<String, String> buildMap(String typeValue) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		String[] values = typeValue.split(",");
		for (int i = 0; i < values.length; i++) {
			map.put(values[i].split("=")[0], values[i].split("=")[1]);
		}
		return map;
	}

	/**
	 * 得到Map中对应key的值
	 * @param map -配置文件的Map
	 * @param key -配置文件中所给的key
	 * @return -key中所对应的值
	 */
	public String getMapValue(Map<String, String> map,String key) {
		String value = (String) map.get(key);
		if (value == null) {
			return "";
		} else {
			return value;
		}
	}
	public ClassLoader getClassLoader() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader == null) {
			classLoader = ParamConfigUtil.class.getClassLoader();
		}
		return classLoader;
	}

}
