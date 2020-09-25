package me.ali.preferentialattachment;

import java.util.Date;

import me.ali.preferentialattachment.model.Graph;
import me.ali.preferentialattachment.model.Vertex;
import me.ali.preferentialattachment.util.Histogram;

public class Complete {

	private final static double p = 0.5;
	private final static int initSize = 4;
	private final static int limitSize = 1_000_000;
	
    public static void main(String[] args) {
    	
    	PreferentialAttachment pref = new PreferentialAttachment(p);
    	pref.initCompleteGraph(initSize);
    	
    	int cnt = 0;
    	Graph graph = pref.getGraph();
		while (graph.getVertices().size() < limitSize) {
			Vertex vertex = pref.addVertex();
			pref.addEdgeFrom(vertex);
			if(++cnt % 10_000 == 0)
				System.out.println(new Date() + "\t" + cnt + "/" + limitSize);
		}
    	
    	Histogram<Integer> histo = new Histogram<Integer>();
    	for (Vertex vertex : graph.getVertices()) {
			histo.add(graph.getDegree(vertex));
		}
    	histo.printSortByValue("-------------");
    	
    }
}
