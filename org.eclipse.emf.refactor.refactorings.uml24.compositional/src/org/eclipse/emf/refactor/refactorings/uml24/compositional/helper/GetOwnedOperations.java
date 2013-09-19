/**
 * <copyright>
 * </copyright>
 */
package org.eclipse.emf.refactor.refactorings.uml24.compositional.helper;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import comrel.extensions.IMultiFeatureHelper;
import comrel.ComrelFactory;
import comrel.SingleInputPort;
import comrel.MultiOutputPort;

/**
 * Helper class for synchronizing ports in 
 * composite model refactorings specified in CoMReL.
 * @generated
 */
public class GetOwnedOperations implements IMultiFeatureHelper {

	final ComrelFactory factory = ComrelFactory.eINSTANCE;
	private SingleInputPort class_;
	private MultiOutputPort operation_s;

	/**
	 * Default constructor.
	 * @generated
	 */
	public GetOwnedOperations() {
		initInputPort();
		initOutputPort();
	}
	
	/**
	 * Method for setting the input port.
	 * @generated
	 */
	private void initInputPort() {
		class_ = factory.createSingleInputPort();
		class_.setName("class_");
		class_.setType(org.eclipse.uml2.uml.Class.class);
	}
	
	/**
	 * Method for setting the output port.
	 * @generated
	 */
	private void initOutputPort(){
		operation_s = factory.createMultiOutputPort();
		operation_s.setName("operation_s");
		operation_s.setType(org.eclipse.uml2.uml.Operation.class);
	}
	
	/**
	 * @generated
	 */
	@Override
	public SingleInputPort getInputPort() {
		return class_;
	}

	/**
	 * @generated
	 */
	@Override
	public MultiOutputPort getOutputPort() {
		return operation_s;
	}
	
	/**
	 * @generated
	 */
	@Override
	public void run() {
		org.eclipse.uml2.uml.Class in = (org.eclipse.uml2.uml.Class) class_.getValue();
		EList<org.eclipse.uml2.uml.Operation> out = new BasicEList<org.eclipse.uml2.uml.Operation>();

		// TODO implement logic (fill 'out')
		out = in.getOwnedOperations();
		
		operation_s.getValue().addAll(out);
	}

}
	