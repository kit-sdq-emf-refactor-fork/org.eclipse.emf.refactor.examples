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

public class GetOperationsHavingParameters implements IMultiFeatureHelper {
	
	final ComrelFactory factory = ComrelFactory.eINSTANCE;
	private SingleInputPort parameterList;
	private MultiOutputPort operations;

	public GetOperationsHavingParameters() {
		initInputPort();
		initOutputPort();
	}
	
	private void initInputPort() {
		parameterList = factory.createSingleInputPort();
		parameterList.setName("parameterList");
		parameterList.setType(UmlParameterList.class);
	}

	private void initOutputPort(){
		operations = factory.createMultiOutputPort();
		operations.setName("operations");
		operations.setType(org.eclipse.uml2.uml.Operation.class);
	}
	
	@Override
	public SingleInputPort getInputPort() {
		return parameterList;
	}

	@Override
	public MultiOutputPort getOutputPort() {
		return operations;
	}

	@Override
	public void run() {
		ArrayList<Parameter> parameters = 
				((UmlParameterList) parameterList.getValue()).getUmlParameters();
		Operation owningOperation = parameters.get(0).getOperation();
		Class owningClass = owningOperation.getClass_();
		for (Operation op : owningClass.getOwnedOperations()) {
			if (UmlUtils.operationHasParameters(op, parameters)) {
				operations.getValue().add(op);
			}
		}
	}

}
