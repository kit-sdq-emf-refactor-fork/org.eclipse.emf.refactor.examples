package org.eclipse.emf.refactor.metrics.uml24.umlpack;

import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.metrics.interfaces.IMetricCalculator;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TypedElement;

public class Ca implements IMetricCalculator {

private List<EObject> context; 
	
	@Override
	public void setContext(List<EObject> context) {
		this.context=context;
	}	
		
	@Override
	public double calculate() {
		double ret = 0.0;
		// custom code - begin
		for (Class otherclass : getOtherClasses()) {
			if (dependsOn(otherclass, getOwnedClasses())) {
				ret++;
			}
		}
		// custom code - end
		return ret;
	}
	
	private boolean dependsOn(Class otherClass, EList<Class> ownedClasses) {
		boolean depends = false;
		for (Property attribute : otherClass.getOwnedAttributes()) {
			depends |= hasType(attribute, ownedClasses);
		}
		for (Operation operation : otherClass.getOwnedOperations()) {
			for (Parameter parameter : operation.getOwnedParameters()) {
				depends |= hasType(parameter, ownedClasses);
			}
		}
		for (Class superclass : otherClass.getSuperClasses()) {
			depends |= ownedClasses.contains(superclass);
		}
		return depends;
	}

	private boolean hasType(TypedElement te, EList<Class> classes) {
		if (te.getType() != null && te.getType() instanceof Class) {
			return (classes.contains(te.getType()));
		}
		return false;
	}

	private EList<Class> getAllClasses() {
		EList<Class> classes = new BasicEList<Class>();
		TreeIterator<EObject> iter = context.get(0).eResource().getAllContents();
		while (iter.hasNext()) {
			EObject eObject = iter.next();
			if (eObject instanceof Class) {
				classes.add((Class) eObject);
			}
		}
		return classes;
	}
	
	private EList<Class> getOwnedClasses() {
		EList<Class> classes = new BasicEList<Class>();
		for (PackageableElement pe : ((Package) context.get(0)).getPackagedElements()) {
			if (pe instanceof Class) {
				classes.add((Class) pe);
			}
		}
		return classes;
	}
	
	private EList<Class> getOtherClasses() {
		EList<Class> classes = getAllClasses();
		classes.removeAll(getOwnedClasses());
		return classes;
	}

}
