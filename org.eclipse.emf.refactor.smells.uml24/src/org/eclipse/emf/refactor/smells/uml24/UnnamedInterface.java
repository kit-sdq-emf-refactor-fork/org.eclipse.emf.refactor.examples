package org.eclipse.emf.refactor.smells.uml24;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.smells.interfaces.IModelSmellFinder;
import org.eclipse.uml2.uml.Interface;

public final class UnnamedInterface implements IModelSmellFinder {

	@Override
	public LinkedList<LinkedList<EObject>> findSmell(EObject root) {
		LinkedList<LinkedList<EObject>> results = new LinkedList<LinkedList<EObject>>();
		// begin custom code
		List<Interface> interfaces = getAllInterfaces(root);
		for (Interface interf : interfaces) {
			if (interf.getName() == null || interf.getName().equals("")) {
				LinkedList<EObject> result = new LinkedList<EObject>();
				result.add(interf);
				results.add(result);
			}
		}
		// end custom code
		return results;
	}

	private List<Interface> getAllInterfaces(EObject root) {
		List<Interface> interfaces = new ArrayList<Interface>();
		TreeIterator<EObject> iter = root.eAllContents();
		while (iter.hasNext()) {
			EObject eObject = iter.next();
			if (eObject instanceof Interface) {
				Interface cl = (Interface) eObject;
				interfaces.add(cl);
			}
		}
		return interfaces;
	}
	
}