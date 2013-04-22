package org.eclipse.emf.refactor.metrics.uml24.umlop;

import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.metrics.interfaces.IMetricCalculator;
import org.eclipse.emf.refactor.metrics.ocl.managers.OCLManager;

public final class NINPPO implements IMetricCalculator {

	private final String expression = 
		"self.ownedParameter -> " +
		"select(direction <> uml::ParameterDirectionKind::return " +
				"and direction <> uml::ParameterDirectionKind::out) " +
		"-> size()";	
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