package org.eclipse.emf.refactor.smells.uml24;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.smells.interfaces.IModelSmellFinder;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;

public final class UnnamedParameter implements IModelSmellFinder {

	@Override
	public LinkedList<LinkedList<EObject>> findSmell(EObject root) {
		LinkedList<LinkedList<EObject>> results = new LinkedList<LinkedList<EObject>>();
		// begin custom code
		List<Parameter> parameters = getAllParameters(root);
		for (Parameter par : parameters) {
			if (par.getDirection() != ParameterDirectionKind.RETURN_LITERAL)
			if (par.getName() == null || par.getName().equals("")) {
				LinkedList<EObject> result = new LinkedList<EObject>();
				result.add(par);
				results.add(result);
			}
		}
		// end custom code
		return results;
	}

	private List<Parameter> getAllParameters(EObject root) {
		List<Parameter> parameters = new ArrayList<Parameter>();
		TreeIterator<EObject> iter = root.eAllContents();
		while (iter.hasNext()) {
			EObject eObject = iter.next();
			if (eObject instanceof Parameter) {
				Parameter par = (Parameter) eObject;
				parameters.add(par);
			}
		}
		return parameters;
	}
	
}