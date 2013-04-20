package org.eclipse.emf.refactor.smells.uml24;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.smells.interfaces.IModelSmellFinder;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;

public final class UnnamedAttribute implements IModelSmellFinder {

	@Override
	public LinkedList<LinkedList<EObject>> findSmell(EObject root) {
		LinkedList<LinkedList<EObject>> results = new LinkedList<LinkedList<EObject>>();
		// begin custom code
		List<Property> properties = getAllProperties(root);
		for (Property prop : properties) {
			if (prop.getName() == null || prop.getName().equals("")) {
				LinkedList<EObject> result = new LinkedList<EObject>();
				result.add(prop);
				results.add(result);
			}
		}
		// end custom code
		return results;
	}

	private List<Property> getAllProperties(EObject root) {
		List<Property> properties = new ArrayList<Property>();
		TreeIterator<EObject> iter = root.eAllContents();
		while (iter.hasNext()) {
			EObject eObject = iter.next();
			if (eObject instanceof Classifier) {
				Classifier cl = (Classifier) eObject;
				for (Property prop : cl.getAttributes()) {
					properties.add(prop);
				}
			}
		}
		return properties;
	}
	
}