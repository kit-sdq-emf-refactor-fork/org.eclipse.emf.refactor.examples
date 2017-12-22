package org.eclipse.emf.refactor.metrics.uml24.umlcl;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.metrics.interfaces.IMetricCalculator;
import org.eclipse.emf.refactor.metrics.ocl.managers.OCLManager;

public class ECPar implements IMetricCalculator {

	private final String expression = 
			"Class.allInstances() -> reject(c|c=self) "
			+ "-> collect(ownedOperation) -> collect(ownedParameter) "
			+ "-> select(type=self) -> size()";	
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
