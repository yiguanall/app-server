package com.crabar.common.startup;

import com.jfinal.plugin.activerecord.IContainerFactory;

import java.util.*;

@SuppressWarnings({"rawtypes", "unchecked"})
public class HumpContainerFactory implements IContainerFactory {

	public Map<String, Object> getAttrsMap() {
		return new HumpMap();
	}
	
	public Map<String, Object> getColumnsMap() {
		return new HumpMap();
	}
	
	public Set<String> getModifyFlagSet() {
		return new HumpSet();
	}
	
	private static Object humpConvert(Object key) {
		if (key instanceof String) {
            String fieldName = (String)key;
            char[] chars = fieldName.toCharArray();

            StringBuilder sb = new StringBuilder(fieldName.length());
            for(int i = 0; i < chars.length ; i++){
                if(chars[i] == '_'){
                    sb.append(Character.toUpperCase(chars[++i]));
                }else{
                    sb.append(chars[i]);
                }
            }
            return sb.toString();
        }
		return key;
	}
	
	/*
	 * 1：非静态内部类拥有对外部类的所有成员的完全访问权限，包括实例字段和方法，
	 *    为实现这一行为，非静态内部类存储着对外部类的实例的一个隐式引用
	 * 2：序列化时要求所有的成员变量是Serializable 包括上面谈到的引式引用
	 * 3：外部类CaseInsensitiveContainerFactory 需要 implements Serializable 才能被序列化
	 * 4：可以使用静态内部类来实现内部类的序列化，而非让外部类实现 implements Serializable 
	 */
	public static class HumpSet extends HashSet {
		
		private static final long serialVersionUID = 102410961064096233L;
		
		public boolean add(Object e) {
			return super.add(humpConvert(e));
		}
		
		public boolean remove(Object e) {
			return super.remove(humpConvert(e));
		}
		
		public boolean contains(Object e) {
			return super.contains(humpConvert(e));
		}
		
		public boolean addAll(Collection c) {
			boolean modified = false;
			for (Object o : c)
				if (super.add(humpConvert(o)))
					modified = true;
			return modified;
		}
	}
	
	public static class HumpMap extends HashMap {
		
		private static final long serialVersionUID = 6843981594457576677L;
		
		public Object get(Object key) {
			return super.get(humpConvert(key));
		}
		
		public boolean containsKey(Object key) {
			return super.containsKey(humpConvert(key));
		}
		
		public Object put(Object key, Object value) {
			return super.put(humpConvert(key), value);
		}
		
		public void putAll(Map m) {
			for (Map.Entry e : (Set<Map.Entry>)(m.entrySet()))
				super.put(humpConvert(e.getKey()), e.getValue());
		}
		
		public Object remove(Object key) {
			return super.remove(humpConvert(key));
		}
	}
}

