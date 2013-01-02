package org.eclipse.emf.refactor.metrics.ecore.eclazz;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.metrics.interfaces.IMetricCalculator;

public class MAXDITEC implements IMetricCalculator {

	private List<EObject> context; 
	
	@Override
	public void setContext(List<EObject> context) {
		this.context = context;
	}	
		
	@Override
	public double calculate() {	
		EClass c = (EClass) context.get(0);
		return getMaxDit(c);
	}

	private int getMaxDit(EClass c) {
		if (c.getESuperTypes().isEmpty()) {
			return 0;
		}
		int[] dits = new int[c.getESuperTypes().size()];
		for (int i=0; i < dits.length; i++) {
			dits[i] = 1 + getMaxDit((EClass) c.getESuperTypes().get(i));
		}
		return max(dits);
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
