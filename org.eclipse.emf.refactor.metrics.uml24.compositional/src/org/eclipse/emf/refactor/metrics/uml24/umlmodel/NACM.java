package org.eclipse.emf.refactor.metrics.uml24.umlmodel;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.metrics.core.Metric;
import org.eclipse.emf.refactor.metrics.interfaces.IMetricCalculator;
import org.eclipse.uml2.uml.Class;


public final class NACM implements IMetricCalculator {

		
	private List<EObject> context; 
	private String metricId = "org.eclipse.emf.refactor.metrics.uml24.nsupc2";
		
	@Override
	public void setContext(List<EObject> context) {
		this.context=context;
	}	
		
	@Override
	public double calculate() {	
		Double result = 0.0;
		Metric metric = Metric.getMetricInstanceFromId(metricId);
		IMetricCalculator calc = metric.getCalculateClass();
		TreeIterator<EObject> iter = context.get(0).eResource().getAllContents();
		while (iter.hasNext()) {
			EObject eObject = iter.next();
			if (eObject instanceof Class) {
				ArrayList<EObject> context = new ArrayList<EObject>();
				context.add(eObject);
				calc.setContext(context);
				result += calc.calculate();
			}			
		}
		return result;
	}
}