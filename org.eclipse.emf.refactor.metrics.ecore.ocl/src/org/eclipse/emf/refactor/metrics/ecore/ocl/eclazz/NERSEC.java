package org.eclipse.emf.refactor.metrics.ecore.ocl.eclazz;

import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.metrics.interfaces.IMetricCalculator;
import org.eclipse.emf.refactor.metrics.ocl.managers.OCLManager;


public final class NERSEC implements IMetricCalculator {

	private final String expression = 
		"self.eReferences -> select(ref:EReference|ref.eReferenceType=self) -> size()";	
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