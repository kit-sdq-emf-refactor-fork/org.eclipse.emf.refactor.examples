package org.eclipse.emf.refactor.refactorings.uml24.createclasswithattributesfromparameterlist;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Parameter;

import comrel.interpreter.IEObjectGetter;

public class UmlParameterList implements IEObjectGetter {
	
	public UmlParameterList(ArrayList<Parameter> umlParameters) {
		super();
		this.umlParameters = umlParameters;
	}

	private ArrayList<Parameter> umlParameters = new ArrayList<Parameter>();
	
	public ArrayList<Parameter> getUmlParameters() {
		return umlParameters;
	}

	public void setUmlParameters(ArrayList<Parameter> umlParameters) {
		this.umlParameters = umlParameters;
	}

	@Override
	public EObject getEObject() {
		return umlParameters.get(0);
	}

}
