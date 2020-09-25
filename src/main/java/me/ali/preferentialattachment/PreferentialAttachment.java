package me.ali.preferentialattachment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.ali.preferentialattachment.model.Graph;
import me.ali.preferentialattachment.model.Vertex;

public class PreferentialAttachment {

	private Random rand;
	private final double prob;
	private Graph graph;

	public PreferentialAttachment(double prob) {
		if (prob > 1 || prob < 0)
			throw new IllegalArgumentException();
		this.rand = new Random();
		this.prob = prob;
		this.graph = new Graph();
	}

	public Graph getGraph() {
		return graph;
	}

	public void initCircularGraph(int n) {
		Vertex[] vertices = new Vertex[n];
		for (int i = 0; i < vertices.length; i++)
			vertices[i] = graph.addVertex();
		for (int i = 0; i < vertices.length - 1; i++)
			graph.addEdge(vertices[i], vertices[i + 1]);
	}

	public void initCompleteGraph(int n) {
		Vertex[] vertices = new Vertex[n];
		for (int i = 0; i < vertices.length; i++)
			vertices[i] = graph.addVertex();
		for (int i = 0; i < vertices.length; i++)
			for (int j = 0; j < vertices.length; j++)
				if (i != j)
					graph.addEdge(vertices[i], vertices[j]);
	}

	public Vertex addVertex() {
		return graph.addVertex();
	}

	public void addEdgeFrom(Vertex vertex) {
		Vertex dst = null;
		if (rand.nextDouble() < prob) {
			// uniform
			do {
				List<Vertex> list = graph.getVertices();
				dst = list.get(rand.nextInt(list.size()));
			} while (dst == vertex);

		} else {
			// based on degree
			List<Vertex> list = graph.getVertices();
			List<Vertex> probList = new ArrayList<Vertex>();
			for (Vertex v : list) {
				for (int i = 0; i < graph.getDegree(v); i++)
					probList.add(v);
			}
			dst = probList.get(rand.nextInt(probList.size()));
		}

		graph.addEdge(vertex, dst);
	}

}
