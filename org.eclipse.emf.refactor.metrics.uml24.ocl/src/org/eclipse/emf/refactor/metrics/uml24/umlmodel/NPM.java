package org.eclipse.emf.refactor.metrics.uml24.umlmodel;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.metrics.interfaces.IMetricCalculator;
import org.eclipse.emf.refactor.metrics.ocl.managers.OCLManager;

public class NPM implements IMetricCalculator {

	private final String expression = 
			"Package.allInstances() -> reject(oclIsTypeOf(Model)) -> size()";	
	private List<EObject> context; 
			
	@Override
	public void setContext(List<EObject> context) {
		this.context = context;
	}	
			
	@Override
	public double calculate() {	
		EObject contextObject = context.get(0);
		return OCLManager.evaluateOCLOnContextObject(contextObject, expression);
	}

}
