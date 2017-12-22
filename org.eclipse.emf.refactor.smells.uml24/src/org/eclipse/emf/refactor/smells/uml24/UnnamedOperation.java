package org.eclipse.emf.refactor.smells.uml24;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.smells.interfaces.IModelSmellFinder;
import org.eclipse.uml2.uml.Operation;

public final class UnnamedOperation implements IModelSmellFinder {

	@Override
	public LinkedList<LinkedList<EObject>> findSmell(EObject root) {
		LinkedList<LinkedList<EObject>> results = new LinkedList<LinkedList<EObject>>();
		// begin custom code
		List<Operation> operations = getAllOperations(root);
		for (Operation op : operations) {
			if (op.getName() == null || op.getName().equals("")) {
				LinkedList<EObject> result = new LinkedList<EObject>();
				result.add(op);
				results.add(result);
			}
		}
		// end custom code
		return results;
	}

	private List<Operation> getAllOperations(EObject root) {
		List<Operation> operations = new ArrayList<Operation>();
		TreeIterator<EObject> iter = root.eAllContents();
		while (iter.hasNext()) {
			EObject eObject = iter.next();
			if (eObject instanceof Operation) {
				Operation op = (Operation) eObject;
				operations.add(op);
			}
		}
		return operations;
	}
	
}