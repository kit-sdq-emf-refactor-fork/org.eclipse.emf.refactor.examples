package org.eclipse.emf.refactor.metrics.uml24.classuml;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.metrics.interfaces.IMetricCalculator;
import org.eclipse.uml2.uml.PackageableElement;

public final class NACP implements IMetricCalculator {
		
	private List<EObject> context; 
		
	@Override
	public void setContext(List<EObject> context) {
		this.context=context;
	}	
		
	@Override
	public double calculate() {	
		org.eclipse.uml2.uml.Package in = (org.eclipse.uml2.uml.Package) context.get(0);
		double ret = 0.0;
		// custom code - begin
		for (PackageableElement pe : in.getPackagedElements()) {
			if (pe instanceof org.eclipse.uml2.uml.Class) {
				if (((org.eclipse.uml2.uml.Class) pe).isAbstract()) {
					ret++;
				}
			}
		}
		// custom code - end
		return ret;
	}
}