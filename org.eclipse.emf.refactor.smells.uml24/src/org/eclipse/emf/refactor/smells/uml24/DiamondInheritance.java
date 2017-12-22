package org.eclipse.emf.refactor.smells.uml24;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.smells.interfaces.IModelSmellFinder;
import org.eclipse.uml2.uml.Class;

public final class DiamondInheritance implements IModelSmellFinder {

	@Override
	public LinkedList<LinkedList<EObject>> findSmell(EObject root) {
		LinkedList<LinkedList<EObject>> results = new LinkedList<LinkedList<EObject>>();
		// begin custom code
		List<Class> classes = getAllClasses(root);
		for (Class cl : classes) {
			if (cl.getSuperClasses().size() > 1) {
				for (int i = 0; i < cl.getSuperClasses().size(); i++) {
					Class cl1 = cl.getSuperClasses().get(i);
//				}
//				for (Class cl1 : cl.getSuperClasses()) {
					for (int j = (i+1); j < cl.getSuperClasses().size(); j++) {
						Class cl2 = cl.getSuperClasses().get(j);
//					}
//					for (Class cl2 : cl.getSuperClasses()) {
//						if (cl1 != cl2) {
							List<Class> superclasses1 = getAllSuperclasses(cl1);
							List<Class> superclasses2 = getAllSuperclasses(cl2);
							for (Class superclass : superclasses1) {
								if (superclasses2.contains(superclass)) {
									LinkedList<EObject> result = new LinkedList<EObject>();
									result.add(cl);
									result.add(cl1);
									result.add(cl2);
									result.add(superclass);
									results.add(result);
								}
							}
//						}
					}
				}
			}
		}
		// end custom code
		return results;
	}

	private List<Class> getAllSuperclasses(Class cl) {
		List<Class> superclasses = new ArrayList<Class>();
		if (cl.getSuperClasses().isEmpty()) return superclasses;
		superclasses.addAll(cl.getSuperClasses());
		for (Class superclass : cl.getSuperClasses()) {
			superclasses.addAll(getAllSuperclasses(superclass));
		}
		return superclasses;
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