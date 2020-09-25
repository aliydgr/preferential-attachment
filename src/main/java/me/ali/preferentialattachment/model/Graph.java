package me.ali.preferentialattachment.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

	private List<Vertex> vertices;
	private Map<Vertex, Integer> indegrees;

	public Graph() {
		this.vertices = new ArrayList<Vertex>();
		this.indegrees = new HashMap<Vertex, Integer>();
	}

	public Collection<Integer> getIndegrees() {
		return indegrees.values();
	}

	public List<Vertex> getVertices() {
		return vertices;
	}
	
	public int getDegree(Vertex vertex) {
		return indegrees.get(vertex);
	}

	public Vertex addVertex() {
		Vertex vertex = new Vertex(indegrees.size());
		vertices.add(vertex);
		indegrees.put(vertex, 0);
		return vertex;
	}

	public void addEdge(Vertex src, Vertex dst) {
		Integer dstDegree = indegrees.get(dst);
		if (!indegrees.containsKey(src) || dstDegree == null)
			throw new IllegalArgumentException();
		indegrees.replace(dst, dstDegree, dstDegree + 1);
	}

}
