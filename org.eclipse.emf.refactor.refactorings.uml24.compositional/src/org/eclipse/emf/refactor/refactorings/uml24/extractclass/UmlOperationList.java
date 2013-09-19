package org.eclipse.emf.refactor.refactorings.uml24.extractclass;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Operation;

import comrel.interpreter.IEObjectGetter;

public class UmlOperationList implements IEObjectGetter{
	
	public UmlOperationList(ArrayList<Operation> umlOperations) {
		super();
		this.umlOperations = umlOperations;
	}

	private ArrayList<Operation> umlOperations = new ArrayList<Operation>();
	
	public ArrayList<Operation> getUmlOperations() {
		return umlOperations;
	}

	public void setUmlOperations(ArrayList<Operation> umlOperations) {
		this.umlOperations = umlOperations;
	}

	@Override
	public EObject getEObject() {
		return umlOperations.get(0);
	}

}
