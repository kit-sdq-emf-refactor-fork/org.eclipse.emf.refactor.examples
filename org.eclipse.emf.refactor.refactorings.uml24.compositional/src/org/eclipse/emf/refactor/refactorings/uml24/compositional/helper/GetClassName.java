/**
 * <copyright>
 * </copyright>
 */
package org.eclipse.emf.refactor.refactorings.uml24.compositional.helper;

import comrel.extensions.ISingleFeatureHelper;
import comrel.ComrelFactory;
import comrel.SingleInputPort;
import comrel.SingleOutputPort;

/**
 * Helper class for synchronizing ports in 
 * composite model refactorings specified in CoMReL.
 * @generated
 */
public class GetClassName implements ISingleFeatureHelper {

	final ComrelFactory factory = ComrelFactory.eINSTANCE;
	private SingleInputPort class_in;
	private SingleOutputPort string_out;

	/**
	 * Default constructor.
	 * @generated
	 */
	public GetClassName() {
		initInputPort();
		initOutputPort();
	}
	
	/**
	 * Method for setting the input port.
	 * @generated
	 */
	private void initInputPort() {
		class_in = factory.createSingleInputPort();
		class_in.setName("class_in");
		class_in.setType(org.eclipse.uml2.uml.Class.class);
	}
	
	/**
	 * Method for setting the output port.
	 * @generated
	 */
	private void initOutputPort(){
		string_out = factory.createSingleOutputPort();
		string_out.setName("string_out");
		string_out.setType(String.class);
	}
	
	/**
	 * @generated
	 */
	@Override
	public SingleInputPort getInputPort() {
		return class_in;
	}

	/**
	 * @generated
	 */
	@Override
	public SingleOutputPort getOutputPort() {
		return string_out;
	}
	
	/**
	 * @generated
	 */
	@Override
	public void run() {
		org.eclipse.uml2.uml.Class in = (org.eclipse.uml2.uml.Class) class_in.getValue();
		String out = null;
		
		out = in.getName();
		
		string_out.setValue(out);
	}

}
	