/*
 * Copyright 2008 the original author or authors.
 * 
 * http://www.gnu.org/licenses/gpl.txt
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package jalds.alds.al.graphs;

import jalds.alds.ds.graphs.Vertex;

/**
 * Used if we need to be notified when a vertex has been explored in the Depth
 * First Search.
 * 
 * @see DepthFirstSearch
 * 
 * @author Devender Gollapally
 * 
 */
public interface FinishedEventObserver {
	void update(Vertex vertex, int discoveredTime, int finishedTime);
}
