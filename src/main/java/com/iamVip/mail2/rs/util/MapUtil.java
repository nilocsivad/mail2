/**
 * 
 */
package com.iamVip.mail2.rs.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Colin
 */
public class MapUtil {

	/**
	 * 
	 */
	public MapUtil() {
	}

	@SuppressWarnings("unchecked")
	public static <V> Map<String, V> mapThem(String[] keys, V... vals) {
		if (keys == null || keys.length == 0) {
			return new HashMap<String, V>(4);
		}
		Map<String, V> map = new HashMap<String, V>(keys.length + 1);
		for (int i = 0; i < keys.length; i++) {
			if (vals.length > i) {
				map.put(keys[i], vals[i]);
			}
			else {
				map.put(keys[i], null);
			}
		}
		return map;
	}

	public static Map<String, Object> map() {
		return new HashMap<String, Object>(4);
	}

	/**
	 * @param cols "name,address,telephone"
	 */
	public static <T> Map<String, Object> toMap1(int from, T obj, String cols) throws SecurityException, IllegalArgumentException, IllegalAccessException {
		return toMap2(from, obj, cols.split(","));
	}

	public static <T> Map<String, Object> toMap2(int from, T obj, String... fields) throws SecurityException, IllegalArgumentException, IllegalAccessException {
		Map<String, Object> map = new HashMap<String, Object>(fields.length + 1);
		Class<?> cls = obj.getClass();
		for (String key : fields) {
			key = key.trim();
			try {
				Field fd = cls.getDeclaredField(key);
				if (fd != null) {
					fd.setAccessible(true);
					Object val = fd.get(obj);
					if (val == null) {
						map.put(key, (from == 9 ? "nil" : null));
					}
					else {
						if (from == 9 && fd.getType().isAssignableFrom(Integer.class)) {
							map.put(key, val + "");
						}
						else {
							map.put(key, val);
						}
					}
					// else {
					// if (val instanceof Integer && from == 9) {
					// Integer ival = (Integer) val;
					// ival = ival == 0 ? -ival : ival;
					// map.put(key, ival);
					// }
					// else {
					// map.put(key, val);
					// }
					// }
					fd.setAccessible(false);
				}
			}
			catch (NoSuchFieldException e) {
				map.put(key, "");
				e.printStackTrace();
			}
		}
		return map;
	}

	/**
	 * @param cols "name,address,telephone"
	 */
	public static <T> List<Map<String, Object>> toMapList1(int from, List<T> obj, String cols) throws SecurityException, IllegalArgumentException, IllegalAccessException {
		return toMapList2(from, obj, cols.split(","));
	}

	public static <T> List<Map<String, Object>> toMapList2(int from, List<T> obj, String... fields) throws SecurityException, IllegalArgumentException, IllegalAccessException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(obj.size() + 1);
		for (T t : obj) {
			list.add(toMap2(from, t, fields));
		}
		return list;
	}

	/**
	 * iOS null->nil 0->-0
	 * @param from 0:Android 9:iOS
	 * @param map
	 * @return
	 */
	public Map<String, Object> mapData(int from, Map<String, Object> map) {
		if (from == 9) {
			for (Map.Entry<String, Object> itm : map.entrySet()) {
				if (itm.getValue() == null) {
					itm.setValue("nil");
				}
				// else {
				// if (itm.getValue() instanceof Integer) {
				// int v = (int) itm.getValue();
				// if (v == 0)
				// map.put(itm.getKey(), -v);
				// }
				// }
			}
		}
		return map;
	}

}
