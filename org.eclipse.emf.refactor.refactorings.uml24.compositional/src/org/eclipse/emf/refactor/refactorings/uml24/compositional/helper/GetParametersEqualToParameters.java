package org.eclipse.emf.refactor.refactorings.uml24.compositional.helper;

import java.util.ArrayList;

import org.eclipse.emf.refactor.refactorings.uml24.compositional.UmlUtils;
import org.eclipse.emf.refactor.refactorings.uml24.createclasswithattributesfromparameterlist.UmlParameterList;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;

import comrel.ComrelFactory;
import comrel.MultiOutputPort;
import comrel.SingleInputPort;
import comrel.extensions.IMultiFeatureHelper;


public class GetParametersEqualToParameters implements IMultiFeatureHelper {
	
	final ComrelFactory factory = ComrelFactory.eINSTANCE;
	private SingleInputPort parameterList;
	private MultiOutputPort parameters;

	public GetParametersEqualToParameters() {
		initInputPort();
		initOutputPort();
	}
	
	private void initInputPort() {
		parameterList = factory.createSingleInputPort();
		parameterList.setName("parameterList");
		parameterList.setType(UmlParameterList.class);
	}

	private void initOutputPort(){
		parameters = factory.createMultiOutputPort();
		parameters.setName("parameters");
		parameters.setType(org.eclipse.uml2.uml.Parameter.class);
	}
	
	@Override
	public SingleInputPort getInputPort() {
		return parameterList;
	}

	@Override
	public MultiOutputPort getOutputPort() {
		return parameters;
	}

	@Override
	public void run() {
		ArrayList<Parameter> parametersList = 
				((UmlParameterList) parameterList.getValue()).getUmlParameters();
		Operation owningOperation = parametersList.get(0).getOperation();
		Class owningClass = owningOperation.getClass_();
		ArrayList<Operation> operations = new ArrayList<Operation>();
		for (Operation op : owningClass.getOwnedOperations()) {
			if (UmlUtils.operationHasParameters(op, parametersList)) {
				operations.add(op);
			}
		}
		for (Operation op : operations) {
			for (Parameter param : parametersList) {
				Parameter equalParameter = UmlUtils.getEqualParameterInOperation(op, param);
				parameters.getValue().add(equalParameter);
			}
		}
	}

}
