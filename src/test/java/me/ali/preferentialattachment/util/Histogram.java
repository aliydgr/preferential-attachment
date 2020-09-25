package me.ali.preferentialattachment.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.Map.Entry;

public class Histogram<K> extends TreeMap<K, Integer> {
	private static final long serialVersionUID = 1L;

	public void add(K key) {
		Integer nev = this.get(key);
		if (nev == null)
			nev = 0;
		this.put(key, nev+1);
	}
	
	public void add(K key, int cnt) {
		Integer nev = this.get(key);
		if (nev == null)
			nev = 0;
		this.put(key, nev+cnt);
	}
	
	public void print(String title) {
		System.out.println(title);
		for (Entry<K, Integer> entry : this.entrySet()) {
			System.out.println(entry.getKey()+": "+entry.getValue());
		}
		System.out.println("================");
	}

	public void print(String fileName, String title) {
		try (PrintWriter writer = new PrintWriter(fileName, "UTF-8")) {
			writer.append(title);
			for (Entry<K, Integer> entry : this.entrySet()) {
				writer.println(entry.getKey()+": "+entry.getValue());
			}
			writer.println("================");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void printSortByValue(String title) {
		List<Map.Entry<K, Integer>> list = new LinkedList<Map.Entry<K, Integer>>(this.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, Integer>>() {
			public int compare(Map.Entry<K, Integer> o1, Map.Entry<K, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});
		
		System.out.println(title);
		for (Map.Entry<K, Integer> entry : list) {
			System.out.println(entry.getKey()+": "+entry.getValue());
		}
		System.out.println("================");
	}
}

