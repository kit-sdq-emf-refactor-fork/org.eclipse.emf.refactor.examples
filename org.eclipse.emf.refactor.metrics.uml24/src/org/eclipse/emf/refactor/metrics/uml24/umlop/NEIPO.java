package org.eclipse.emf.refactor.metrics.uml24.umlop;

import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.metrics.interfaces.IMetricCalculator;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;

public final class NEIPO implements IMetricCalculator {
		
	private List<EObject> context; 
		
	@Override
	public void setContext(List<EObject> context) {
		this.context=context;
	}	
		
	@Override
	public double calculate() {	
		Operation op = (Operation) context.get(0);
		if (op.getClass_() != null) {
			return getNumberOfEqualParametersInSiblingOperations(op);
		} else {
			return 0.0;
		}
	}
	
	private double getNumberOfEqualParametersInSiblingOperations(Operation op) {
		double result = 0.0;
		Class owningClass = op.getClass_();
		EList<Operation> siblingOperations = getSiblingOperations(owningClass, op);
		if (siblingOperations.isEmpty()) return result;
		Double[] maxEqualParameters = new Double[siblingOperations.size()];
		for (int i=0; i < maxEqualParameters.length; i++) maxEqualParameters[i] = 0.0;
		for (Parameter param : op.getOwnedParameters()) {
			if (param.getDirection().getValue() == ParameterDirectionKind.IN ||
					param.getDirection().getValue() == ParameterDirectionKind.INOUT) {
				for (int i=0; i < siblingOperations.size(); i++) {
					if (containsEqualParameter(siblingOperations.get(i), param)) {
						maxEqualParameters[i]++;
					}
				}
			}
		}
		result = max(maxEqualParameters);
		return result;
	}

	private double max(Double[] doubleArray) {
		Double result = 0.0;
		for (int i=0; i < doubleArray.length; i++) {
			if (doubleArray[i] > result) {
				result = doubleArray[i];
			}
		}
		return result;
	}

	private boolean containsEqualParameter(Operation op, Parameter param) {
		boolean contains = false;
		for (Parameter p : op.getOwnedParameters()) {
			if (haveEqualDirections(p, param)
				&& haveEqualNames(p, param) 
				&& haveEqualTypes(p, param) 
				&& haveEqualVisibility(p, param) 
				&& haveEqualMultiplicities(p, param) ) {
					contains = true;
					break;
				}
		}
		return contains;
	}

	private boolean haveEqualMultiplicities(Parameter p, Parameter param) {
		return ((p.getLower() == param.getLower()) && (p.getUpper() == param.getUpper()));
	}

	private boolean haveEqualVisibility(Parameter p, Parameter param) {
		return p.getVisibility().equals(param.getVisibility());
	}

	private boolean haveEqualTypes(Parameter p, Parameter param) {
		return ((p.getType() == null) && (param.getType() == null)) || 
				((p.getType() != null) && (param.getType() != null)) && (p.getType().equals(param.getType()));
	}

	private boolean haveEqualNames(Parameter p, Parameter param) {
		return p.getName().equals(param.getName());
	}

	private boolean haveEqualDirections(Parameter p, Parameter param) {
		return (p.getDirection().getValue() == param.getDirection().getValue());
	}

	private EList<Operation> getSiblingOperations(Class owningClass, Operation op) {
		EList<Operation> siblingOperations = new BasicEList<Operation>();
		for (Operation oper : owningClass.getOwnedOperations()) {
			if (! oper.equals(op)) {
				siblingOperations.add(oper);
			}
		}
		return siblingOperations;
	}
}