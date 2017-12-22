package org.eclipse.emf.refactor.metrics.uml24.umlpack;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.metrics.core.Metric;
import org.eclipse.emf.refactor.metrics.interfaces.IMetricCalculator;
import org.eclipse.emf.refactor.metrics.interfaces.IOperation;
import org.eclipse.emf.refactor.metrics.operations.Operations;

public class A implements IMetricCalculator {
	
	private List<EObject> context;
	private String metricID1 = "org.eclipse.emf.refactor.metrics.uml24.nacp";
	private String metricID2 = "org.eclipse.emf.refactor.metrics.uml24.tncp";
	
	IOperation operation = Operations.getOperation("Division");

	@Override
	public void setContext(List<EObject> context) {
		this.context = context;	
	}

	@Override
	public double calculate() {
		Metric metric1 = Metric.getMetricInstanceFromId(metricID1);
		Metric metric2 = Metric.getMetricInstanceFromId(metricID2);
		
		IMetricCalculator calc1 = metric1.getCalculateClass();
		IMetricCalculator calc2 = metric2.getCalculateClass();
			
		calc1.setContext(this.context);
		calc2.setContext(this.context);
		return operation.calculate(calc1.calculate(),calc2.calculate());
	}

}
