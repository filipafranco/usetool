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

/* $ProjectHeader: use 0.393 Wed, 16 May 2007 14:10:28 +0200 opti $ */

package org.tzi.use.uml.ocl.type;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.tzi.use.uml.mm.MClass;

/**
 * Type of objects. Object types are defined by the class of the object.
 *
 * @version     $ProjectVersion: 0.393 $
 * @author  Mark Richters
 */
public final class ObjectType extends Type {
    private MClass fClass;

    ObjectType(MClass cls) {
        fClass = cls;
    }
    
    public MClass cls() {
        return fClass;
    }
    
    /** 
     * Test subtype relation between this and <code>t</code>. 
     */
    public boolean isSubtypeOf(Type t) {
        if (t.isObjectType() ) {
            MClass cls2 = ((ObjectType) t).cls();
            return fClass.isSubClassOf(cls2);
        }
        return t.isOclAny();
    }

    /** 
     * Returns the set of all supertypes (including this type).
     */
    public Set allSupertypes() {
        Set res = new HashSet();
        res.add(this);
        res.add(TypeFactory.mkOclAny());
        Set parents = fClass.allParents();
        Iterator clsIter = parents.iterator();
        while (clsIter.hasNext() ) {
            MClass cls = (MClass) clsIter.next();
            res.add(TypeFactory.mkObjectType(cls));
        }
        return res;
    }

    /** 
     * Return complete printable type name, e.g. 'Set(Bag(Integer))'. 
     */
    public String toString() {
        return fClass.name();
    }

    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this )
            return true;
        if (obj.getClass().equals(getClass()))
            return fClass.equals(((ObjectType) obj).fClass);
        return false;
    }

    public int hashCode() {
        return fClass.hashCode();
    }
    
}