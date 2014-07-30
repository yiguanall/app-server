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
	 * 1���Ǿ�̬�ڲ���ӵ�ж��ⲿ������г�Ա����ȫ����Ȩ�ޣ�����ʵ���ֶκͷ�����
	 *    Ϊʵ����һ��Ϊ���Ǿ�̬�ڲ���洢�Ŷ��ⲿ���ʵ����һ����ʽ����
	 * 2�����л�ʱҪ�����еĳ�Ա������Serializable ��������̸������ʽ����
	 * 3���ⲿ��CaseInsensitiveContainerFactory ��Ҫ implements Serializable ���ܱ����л�
	 * 4������ʹ�þ�̬�ڲ�����ʵ���ڲ�������л����������ⲿ��ʵ�� implements Serializable 
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

