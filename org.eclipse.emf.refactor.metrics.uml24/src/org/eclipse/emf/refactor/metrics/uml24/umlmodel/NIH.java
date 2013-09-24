package org.eclipse.emf.refactor.metrics.uml24.umlmodel;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.metrics.interfaces.IMetricCalculator;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Model;

public final class NIH implements IMetricCalculator {
		
	private List<EObject> context; 
		
	@Override
	public void setContext(List<EObject> context) {
		this.context=context;
	}	
		
	@Override
	public double calculate() {	
		org.eclipse.uml2.uml.Model in = (org.eclipse.uml2.uml.Model) context.get(0);
		double ret = 0.0;
		// custom code - begin
		List<Class> classes = getAllClasses(in);		
		for (Class cl : classes) {
			if (isSuperclass(cl) && cl.getGeneralizations().isEmpty()) {
				ret++;
			}
		}
		// custom code - end
		return ret;
	}
	
	private boolean isSuperclass(Class clazz) {
		List<Class> classes = getAllClasses(clazz.getModel());		
		for (Class cl : classes) {
			if (cl.getSuperClasses().contains(clazz)) return true;
		}
		return false;
	}

	private ArrayList<Class> getAllClasses(Model model) {
		ArrayList<Class> classes = new ArrayList<Class>();
		TreeIterator<EObject> iter = model.eResource().getAllContents();
		while (iter.hasNext()) {
			EObject eObject = iter.next();
			if (eObject instanceof Class) {
				classes.add((Class) eObject);
			}			
		}		
		return classes;
	}
}