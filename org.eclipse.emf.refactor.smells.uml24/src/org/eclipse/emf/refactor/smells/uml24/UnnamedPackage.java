package org.eclipse.emf.refactor.smells.uml24;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.smells.interfaces.IModelSmellFinder;
import org.eclipse.uml2.uml.Package;

public final class UnnamedPackage implements IModelSmellFinder {

	@Override
	public LinkedList<LinkedList<EObject>> findSmell(EObject root) {
		LinkedList<LinkedList<EObject>> results = new LinkedList<LinkedList<EObject>>();
		// begin custom code
		List<Package> packages = getAllPackages(root);
		for (Package p : packages) {
			if (p.getName() == null || p.getName().equals("")) {
				LinkedList<EObject> result = new LinkedList<EObject>();
				result.add(p);
				results.add(result);
			}
		}
		// end custom code
		return results;
	}

	private List<Package> getAllPackages(EObject root) {
		List<Package> packages = new ArrayList<Package>();
		TreeIterator<EObject> iter = root.eAllContents();
		while (iter.hasNext()) {
			EObject eObject = iter.next();
			if (eObject instanceof Package) {
				Package p = (Package) eObject;
				packages.add(p);
			}
		}
		return packages;
	}
	
}