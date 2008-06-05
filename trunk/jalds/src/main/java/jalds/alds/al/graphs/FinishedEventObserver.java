package jalds.alds.al.graphs;

import jalds.alds.ds.graphs.Vertex;

public interface FinishedEventObserver {
	void update(Vertex vertex, int discoveredTime, int finishedTime);
}
