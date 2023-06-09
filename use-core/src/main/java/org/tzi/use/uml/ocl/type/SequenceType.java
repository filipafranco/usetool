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

package org.tzi.use.uml.ocl.type;

import org.tzi.use.uml.ocl.value.CollectionValue;
import org.tzi.use.uml.ocl.value.SequenceValue;
import org.tzi.use.uml.ocl.value.Value;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * The OCL Sequence type.
 *
 * @author  Mark Richters
 * @see     SetType
 * @see     BagType
 */
public final class SequenceType extends CollectionType {

    public SequenceType(Type elemType) {
        super(elemType);
    }
    
    @Override
    public boolean isInstantiableCollection() {
    	return true;
    }

    @Override
    public boolean isTypeOfCollection() {
    	return false;
    }
    
    @Override
    public boolean isTypeOfSequence() {
    	return true;
    }
        
    @Override
	public boolean isKindOfSequence(VoidHandling h) {
		return true;
	}

	public String shortName() {
        if (elemType().isKindOfCollection(VoidHandling.EXCLUDE_VOID) )
            return "Sequence(...)";
        else 
            return "Sequence(" + elemType() + ")";
    }

    public Type getLeastCommonSupertype(Type type)
    {
    	if (!type.isKindOfCollection(VoidHandling.INCLUDE_VOID))
    		return null;
    	
    	if (type.isTypeOfVoidType())
    		return this;
    	
    	CollectionType cType = (CollectionType)type;
    	Type commonElementType = this.elemType().getLeastCommonSupertype(cType.elemType());
    	
    	if (commonElementType == null) 
    		return null;
    	
    	if (type.isTypeOfSequence())
    		return TypeFactory.mkSequence(commonElementType);
    	else
    		return TypeFactory.mkCollection(commonElementType);
    }
    
    /** 
     * Returns true if this type is a subtype of <code>t</code>. 
     */
    public boolean conformsTo(Type t) {
        if (!t.isTypeOfCollection() && !t.isTypeOfSequence())
            return false;

        CollectionType t2 = (CollectionType) t;
        if (elemType().conformsTo(t2.elemType()) )
            return true;
        
        return false;
    }

    /** 
     * Returns the set of all supertypes (including this type).  If
     * this collection has type Sequence(T) the result is the set of
     * all types Sequence(T') and Collection(T') where T' &lt;= T.
     */
    public Set<Type> allSupertypes() {
        Set<Type> res = new HashSet<Type>();
        res.addAll(super.allSupertypes());
        Set<? extends Type> elemSuper = elemType().allSupertypes();
        Iterator<? extends Type> typeIter = elemSuper.iterator();
        
        while (typeIter.hasNext() ) {
            Type t = typeIter.next();
            res.add(TypeFactory.mkSequence(t));
        }
        return res;
    }
    
    @Override
	public Type createCollectionType(Type t) {
	 	return TypeFactory.mkSequence(t);
	}

    @Override
    public CollectionValue createCollectionValue(List<Value> values) {
    	return new SequenceValue(elemType(), values);
    }
    
    @Override
    public CollectionValue createCollectionValue(Value[] values) {
    	return new SequenceValue(elemType(), values);
    }
    
    @Override
    public StringBuilder toString(StringBuilder sb) {
        sb.append("Sequence(");
        elemType().toString(sb);
        return sb.append(")");
    }
}
