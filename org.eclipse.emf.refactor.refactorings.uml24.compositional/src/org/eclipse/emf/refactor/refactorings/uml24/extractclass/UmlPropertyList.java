package org.eclipse.emf.refactor.refactorings.uml24.extractclass;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Property;

import comrel.interpreter.IEObjectGetter;

public class UmlPropertyList implements IEObjectGetter{
	
	public UmlPropertyList(ArrayList<Property> umlProperties) {
		super();
		this.umlProperties = umlProperties;
	}

	private ArrayList<Property> umlProperties = new ArrayList<Property>();
	
	public ArrayList<Property> getUmlProperties() {
		return umlProperties;
	}

	public void setUmlProperties(ArrayList<Property> umlProperties) {
		this.umlProperties = umlProperties;
	}

	@Override
	public EObject getEObject() {
		return umlProperties.get(0);
	}

}
