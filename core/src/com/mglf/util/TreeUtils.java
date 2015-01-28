package com.mglf.util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;

import com.mglf.util.TreeUtils;

import com.mglf.util.TreeUtils;

/**
 * TREE 对象 ：主要功能生成对象初始化所生成的JSON
 * 
 * @author: zhongzhuohan
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class TreeUtils {

	private final static Log log = LogFactory.getLog(TreeUtils.class);
	
	public static String getTreeJsonForJqgrid() {
		return null;
	}

	public static String getTree2String(List treeData, Map map) {
		StringBuffer treeJson = new StringBuffer("");
		LinkedMap rootMap = buildTree(treeData, map);
		treeJson.append("{");
		for (int i = 0; i < rootMap.size(); i++) {
			JSONObject jsonObj = new JSONObject();
			String nodeKey = (String) rootMap.get(i);
			jsonObj.put(nodeKey, rootMap.get(nodeKey));

			if (i != 0) {
				treeJson.append(",");
			}
			String json = jsonObj.toJSONString();
			json = json.substring(1, json.length() - 1);
			treeJson.append(json);
		}
		treeJson.append("}");

		if (map.get("hideRoot") != null) {
			return treeJson.toString();
		} else {
			String rsStr = "{\"all\" : {\"name\": \"全部\", \"type\": \"folder\",\"additionalParameters\":{\"children\":"
					+ treeJson.toString() + "}}}";
			return rsStr;
		}
	}

	/**
	 * List中的数据要求，先将所有有子节点的数据放在最上面，然后按级别排序，最后按所要显示的数据排序
	 * @param treeData
	 * @param map
	 * @return
	 */
	private static LinkedMap buildTree(List<Object> treeData, Map<String, String> map) {
		/*
		 * 1、将所有有子节点的分别放入各级别的MAP中，其实最高级节点放在List中 2、将子节点放入父节点中。
		 */
		LinkedMap rootMap = new LinkedMap();
		Map<String, Map> allChild = new HashMap<String, Map>();
		for (int i = 0; i < treeData.size(); i++) {
			Object obj = treeData.get(i);
			Map nodeMap = buildNode(obj, map);
			if ("1".equals(nodeMap.get("level")) || nodeMap.get("superId") == null) {
				rootMap.put(nodeMap.get("name"), nodeMap);
			} else {
				// 1、先将每个节点分别放入Map中所对应的对象中
				Map levelMap = allChild.get(nodeMap.get("level"));
				if (levelMap == null) {
					levelMap = new HashMap();
					allChild.put((String) nodeMap.get("level"), levelMap);
				}
				levelMap.put(nodeMap.get("id"), nodeMap);
				String superLevel = String.valueOf((Integer.valueOf((String) nodeMap.get("level")) - 1));
				// 2、将2级节点加入头节点中
				Set<String> rootKeySet = rootMap.keySet();
				if (!"0".equals(superLevel)) {
					if ("1".equals(superLevel)) {
						for (String key : rootKeySet) {
							Map superMap = (Map) rootMap.get(key);
							if (superMap.get("id").equals(nodeMap.get("superId"))) {
								Map addMap = (Map) superMap.get("additionalParameters");
								LinkedMap link = (LinkedMap) addMap.get("children");
								link.put(nodeMap.get("name"), nodeMap);
							}
						}
					} else {
						Map<String, Map> superLevelMap = allChild.get(superLevel);
						Map superNodeMap = (Map) superLevelMap.get(nodeMap.get("superId"));
						Map addMap = (Map) superNodeMap.get("additionalParameters");
						LinkedMap link = (LinkedMap) addMap.get("children");
						link.put(nodeMap.get("name"), nodeMap);
					}
				}
			}
		}

		return rootMap;
	}

	private static Map buildNode(Object obj, Map<String, String> map) {
		Map node = new HashMap();
		Set<String> keySet = map.keySet();
		for (String key : keySet) {
			if ("hideRoot".equals(key)) {
				continue;
			}
			node.put(key, getValue(obj, map.get(key)));
		}
		Map child = new HashMap();
		child.put("children", new LinkedMap());
		node.put("additionalParameters", child);
		if ("1".equals(node.get("type"))) {
			node.put("type", "folder");
		} else {
			node.put("type", "item");
		}
		return node;
	}

	private static String getValue(Object obj, String property) {
		Class c = obj.getClass();
		Method m;
		try {
			m = c.getMethod("get" + property, new Class[] {});
			String rsValue = (String) m.invoke(obj, new Object[] {});
			return rsValue;
		} catch (Exception e) {
			log.error("getValue", e);
			return null;
		}
	}

}
