package org.eclipse.emf.refactor.metrics.ecore.eclazz;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.refactor.metrics.interfaces.IMetricCalculator;

public class HAGGEC implements IMetricCalculator {
	
	private List<EObject> context; 

	@Override
	public void setContext(List<EObject> context) {
		this.context=context;
	}

	@Override
	public double calculate() {
		EClass c = (EClass) context.get(0);
		return getHAgg(c);
	}

	private double getHAgg(EClass c) {
		if (c.getEReferences() == null || c.getEReferences().isEmpty()) {
			return 0;
		}
		int[] haggs = new int[c.getEReferences().size()];
		for (int i=0; i < haggs.length; i++) {
			haggs[i] = 0;
			EReference ref = c.getEReferences().get(i); 
			if (ref.isContainment() && ref.getEReferenceType() != c) {
				haggs[i] = (int) (1 + getHAgg((EClass) ref.getEReferenceType()));
			}
		}
		return max(haggs);
	}
	
	private int max(int[] intArray) {
		int result = 0;
		for (int i=0; i < intArray.length; i++) {
			if (intArray[i] > result) {
				result = intArray[i];
			}
		}
		return result;
	}

}
