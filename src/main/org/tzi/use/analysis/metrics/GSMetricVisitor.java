/*
 * USE - UML based specification environment
 * Copyright (C) 1999-2010 Mark Richters, University of Bremen
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

// $Id$

package org.tzi.use.analysis.metrics;

import jline.internal.Log;

import org.tzi.use.uml.ocl.expr.ExpForAll;

/**
 * TODO
 * @author ms
 *
 */
public class GSMetricVisitor extends AbstractMetricVisitor {

	/**
	 * @param expandOperations
	 */
	public GSMetricVisitor(boolean expandOperations) {
		super(expandOperations);
	}

	@Override
	public void visitForAll(ExpForAll exp) {
		Log.info(exp);
				
		// visitQuery(exp);
	}
}