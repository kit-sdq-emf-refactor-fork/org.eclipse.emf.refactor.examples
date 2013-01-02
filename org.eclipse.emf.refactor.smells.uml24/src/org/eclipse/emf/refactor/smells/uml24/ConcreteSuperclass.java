package org.eclipse.emf.refactor.smells.uml24;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.smells.interfaces.IModelSmellFinder;
import org.eclipse.uml2.uml.Class;


public final class ConcreteSuperclass implements IModelSmellFinder {

	@Override
	public LinkedList<LinkedList<EObject>> findSmell(EObject root) {
		LinkedList<LinkedList<EObject>> results = new LinkedList<LinkedList<EObject>>();
		// begin custom code
		List<Class> classes = getAllClasses(root);
		for (Class cl : classes) {
			if (cl.isAbstract()) {
				List<Class> superclasses = cl.getSuperClasses();
				for (Class superclass : superclasses) {
					if (! superclass.isAbstract()) {
						LinkedList<EObject> result = new LinkedList<EObject>();
						result.add(cl);
						result.add(superclass);
						results.add(result);
					}
				}
			}
		}
		// end custom code
		return results;
	}

	private List<Class> getAllClasses(EObject root) {
		List<Class> classes = new ArrayList<Class>();
		TreeIterator<EObject> iter = root.eAllContents();
		while (iter.hasNext()) {
			EObject eObject = iter.next();
			if (eObject instanceof Class) {
				Class cl = (Class) eObject;
				classes.add(cl);
			}
		}
		return classes;
	}
	
}