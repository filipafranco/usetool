/*
 * USE - UML based specification environment
 * Copyright (C) 1999-2004 Mark Richters, University of Bremen
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
package org.tzi.use.parser.use;

import java.util.List;

import org.tzi.use.parser.Context;
import org.tzi.use.parser.MyToken;
import org.tzi.use.parser.SemanticException;
import org.tzi.use.uml.al.ALAction;
import org.tzi.use.uml.al.ALInsert;
import org.tzi.use.uml.mm.MAssociation;

/**
 * @author green
 */
public class ASTALInsert extends ASTALLinkAction {

    public ASTALInsert(List exprList, MyToken assoc) {
        super(exprList, assoc);
    }

    public ALAction gen(Context ctx) throws SemanticException {
        MAssociation assoc = getAssociation(ctx);
        List expressions = getLinkElements(ctx, assoc);
        return new ALInsert(expressions, assoc);        
    }

}