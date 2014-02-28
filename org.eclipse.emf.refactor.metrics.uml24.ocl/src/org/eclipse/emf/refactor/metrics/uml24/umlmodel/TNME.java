package org.eclipse.emf.refactor.metrics.uml24.umlmodel;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.metrics.interfaces.IMetricCalculator;
import org.eclipse.emf.refactor.metrics.ocl.managers.OCLManager;

public class TNME implements IMetricCalculator {

	private final String expression = 
			"(Package.allInstances() -> reject(oclIsTypeOf(Model)) -> size()) + "
				+ "(Class.allInstances() -> reject(oclIsTypeOf(StateMachine)) -> size()) + "
				+ "(Interface.allInstances() -> size()) + "
				+ "(Class.allInstances() -> collect(ownedAttribute) -> size()) + "
				+ "(Operation.allInstances() -> size()) + "
				+ "(Parameter.allInstances() -> size()) + "
				+ "(Association.allInstances() -> reject(oclIsTypeOf(AssociationClass)) -> size()) + "
				+ "(Association.allInstances() -> reject(oclIsTypeOf(AssociationClass)) -> collect(ownedEnd) -> size()) + "
				+ "(Generalization.allInstances() -> size()) + "
				+ "(InterfaceRealization.allInstances() -> size()) + "
				+ "(Enumeration.allInstances() -> size()) + "
				+ "(EnumerationLiteral.allInstances() -> size())";	
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
