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
package jalds.alds.al.sorting;

import jalds.alds.SortableObject;

/**
 * All of the Sorting Classes implement this Interface. This way the user can
 * easily replace one implementation with another and compare performances of
 * various algorithms.
 * 
 * @author Devender Gollapally
 * 
 */
public interface Sort {

	SortableObject[] sort(SortableObject[] unSortedList);
}
