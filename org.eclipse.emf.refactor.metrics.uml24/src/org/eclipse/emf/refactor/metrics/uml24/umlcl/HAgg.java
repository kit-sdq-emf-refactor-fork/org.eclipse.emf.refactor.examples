package org.eclipse.emf.refactor.metrics.uml24.umlcl;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.metrics.interfaces.IMetricCalculator;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Property;

public class HAgg implements IMetricCalculator {

	private List<EObject> context; 
	
	@Override
	public void setContext(List<EObject> context) {
		this.context=context;
	}	
		
	@Override
	public double calculate() {	
		Class c = (Class) context.get(0);
		return getHAgg(c);
	}

	private int getHAgg(Class c) {
		if (c.getOwnedAttributes() == null || c.getOwnedAttributes().isEmpty()) {
			return 0;
		}
		int[] haggs = new int[c.getOwnedAttributes().size()];
		for (int i=0; i < haggs.length; i++) {
			haggs[i] = 0;
			Property att = c.getOwnedAttributes().get(i); 
			if (! att.getAggregation().equals(AggregationKind.NONE_LITERAL)) {
				System.out.println("Attribute: " + att.getQualifiedName());
				haggs[i] = 1 + getHAgg((Class) att.getType());
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
