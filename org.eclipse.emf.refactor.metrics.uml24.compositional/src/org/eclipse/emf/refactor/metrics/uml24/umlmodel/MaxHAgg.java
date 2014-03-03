package org.eclipse.emf.refactor.metrics.uml24.umlmodel;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.metrics.core.Metric;
import org.eclipse.emf.refactor.metrics.interfaces.IMetricCalculator;
import org.eclipse.uml2.uml.Class;

public class MaxHAgg implements IMetricCalculator {

	private List<EObject> context; 
	private String metricId = "org.eclipse.emf.refactor.metrics.uml24.hagg";
		
	@Override
	public void setContext(List<EObject> context) {
		this.context=context;
	}	
		
	@Override
	public double calculate() {	
		EList<Double> haggs = new BasicEList<Double>();
		Metric metric = Metric.getMetricInstanceFromId(metricId);
		IMetricCalculator calc = metric.getCalculateClass();
		TreeIterator<EObject> iter = context.get(0).eResource().getAllContents();
		while (iter.hasNext()) {
			EObject eObject = iter.next();
			if (eObject instanceof Class) {
				ArrayList<EObject> context = new ArrayList<EObject>();
				context.add(eObject);
				calc.setContext(context);
				haggs.add(calc.calculate());
			}			
		}
		return maxHAgg(haggs);
	}

	private double maxHAgg(EList<Double> doubles) {
		Double result = 0.0;
		for (Double d : doubles) {
			if (d > result) {
				result = d;
			}
		}
		return result;
	}

}
