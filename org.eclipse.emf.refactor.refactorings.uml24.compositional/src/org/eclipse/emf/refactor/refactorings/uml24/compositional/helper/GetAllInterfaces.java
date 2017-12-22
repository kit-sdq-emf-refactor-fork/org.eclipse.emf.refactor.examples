/**
 * <copyright>
 * </copyright>
 */
package org.eclipse.emf.refactor.refactorings.uml24.compositional.helper;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.PackageableElement;

import comrel.ComrelFactory;
import comrel.MultiOutputPort;
import comrel.SingleInputPort;
import comrel.extensions.IMultiFeatureHelper;

/**
 * Helper class for synchronizing ports in 
 * composite model refactorings specified in CoMReL.
 * @generated
 */
public class GetAllInterfaces implements IMultiFeatureHelper {

	final ComrelFactory factory = ComrelFactory.eINSTANCE;
	private SingleInputPort package_;
	private MultiOutputPort interface_s;

	/**
	 * Default constructor.
	 * @generated
	 */
	public GetAllInterfaces() {
		initInputPort();
		initOutputPort();
	}
	
	/**
	 * Method for setting the input port.
	 * @generated
	 */
	private void initInputPort() {
		package_ = factory.createSingleInputPort();
		package_.setName("package_");
		package_.setType(org.eclipse.uml2.uml.Package.class);
	}
	
	/**
	 * Method for setting the output port.
	 * @generated
	 */
	private void initOutputPort(){
		interface_s = factory.createMultiOutputPort();
		interface_s.setName("interface_s");
		interface_s.setType(org.eclipse.uml2.uml.Interface.class);
	}
	
	/**
	 * @generated
	 */
	@Override
	public SingleInputPort getInputPort() {
		return package_;
	}

	/**
	 * @generated
	 */
	@Override
	public MultiOutputPort getOutputPort() {
		return interface_s;
	}
	
	/**
	 * @generated
	 */
	@Override
	public void run() {
		org.eclipse.uml2.uml.Package in = (org.eclipse.uml2.uml.Package) package_.getValue();
		EList<org.eclipse.uml2.uml.Interface> out = new BasicEList<org.eclipse.uml2.uml.Interface>();
		
		// TODO implement logic (fill 'out')
		for (PackageableElement pe : in.getPackagedElements()) {
			if (pe instanceof org.eclipse.uml2.uml.Interface) {
				out.add((Interface) pe);
			}
		}
		
		interface_s.getValue().addAll(out);
	}

}
	