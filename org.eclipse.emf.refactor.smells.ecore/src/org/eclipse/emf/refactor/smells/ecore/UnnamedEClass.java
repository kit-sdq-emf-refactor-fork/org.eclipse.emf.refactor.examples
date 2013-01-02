package org.eclipse.emf.refactor.smells.ecore;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.smells.interfaces.IModelSmellFinder;

public final class UnnamedEClass implements IModelSmellFinder {

	@Override
	public LinkedList<LinkedList<EObject>> findSmell(EObject root) {
		LinkedList<LinkedList<EObject>> results = new LinkedList<LinkedList<EObject>>();
		List<EClass> classes = getAllClasses(root);
		for (EClass cl : classes) {
			if (cl.getName() == null || cl.getName().equals("")) {
				LinkedList<EObject> result = new LinkedList<EObject>();
				result.add(cl);
				results.add(result);
			}
		}
		return results;
	}
	
	private List<EClass> getAllClasses(EObject root) {
		List<EClass> classes = new ArrayList<EClass>();
		TreeIterator<EObject> iter = root.eAllContents();
		while (iter.hasNext()) {
			EObject eObject = iter.next();
			if (eObject instanceof EClass) {
				EClass cl = (EClass) eObject;
				classes.add(cl);
			}
		}
		return classes;
	}
	
}