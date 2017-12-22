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
public class GetOwnedAttributes implements IMultiFeatureHelper {

	final ComrelFactory factory = ComrelFactory.eINSTANCE;
	private SingleInputPort class_;
	private MultiOutputPort property_s;

	/**
	 * Default constructor.
	 * @generated
	 */
	public GetOwnedAttributes() {
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
		property_s = factory.createMultiOutputPort();
		property_s.setName("property_s");
		property_s.setType(org.eclipse.uml2.uml.Property.class);
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
		return property_s;
	}
	
	/**
	 * @generated
	 */
	@Override
	public void run() {
		org.eclipse.uml2.uml.Class in = (org.eclipse.uml2.uml.Class) class_.getValue();
		EList<org.eclipse.uml2.uml.Property> out = new BasicEList<org.eclipse.uml2.uml.Property>();
		
		// TODO implement logic (fill 'out')
		out = in.getOwnedAttributes();
		
		property_s.getValue().addAll(out);
	}

}
	