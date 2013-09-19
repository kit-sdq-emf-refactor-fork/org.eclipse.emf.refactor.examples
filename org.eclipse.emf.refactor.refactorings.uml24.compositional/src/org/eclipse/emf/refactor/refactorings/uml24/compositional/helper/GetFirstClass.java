/**
 * <copyright>
 * </copyright>
 */
package org.eclipse.emf.refactor.refactorings.uml24.compositional.helper;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import comrel.extensions.ISingleFilterHelper;
import comrel.MultiInputPort;
import comrel.ComrelFactory;
import comrel.SingleOutputPort;

/**
 * Helper class for synchronizing ports in 
 * composite model refactorings specified in CoMReL.
 * @generated
 */
public class GetFirstClass implements ISingleFilterHelper {

	final ComrelFactory factory = ComrelFactory.eINSTANCE;
	private MultiInputPort class_s;
	private SingleOutputPort class_;

	/**
	 * Default constructor.
	 * @generated
	 */
	public GetFirstClass() {
		initInputPort();
		initOutputPort();
	}
	
	/**
	 * Method for setting the input port.
	 * @generated
	 */
	private void initInputPort() {
		class_s = factory.createMultiInputPort();
		class_s.setName("class_s");
		class_s.setType(org.eclipse.uml2.uml.Class.class);
	}
	
	/**
	 * Method for setting the output port.
	 * @generated
	 */
	private void initOutputPort(){
		class_ = factory.createSingleOutputPort();
		class_.setName("class_");
		class_.setType(org.eclipse.uml2.uml.Class.class);
	}
	
	/**
	 * @generated
	 */
	@Override
	public MultiInputPort getInputPort() {
		return class_s;
	}

	/**
	 * @generated
	 */
	@Override
	public SingleOutputPort getOutputPort() {
		return class_;
	}
	
	/**
	 * @generated
	 */
	@Override
	public void run() {
		EList<org.eclipse.uml2.uml.Class> in = setInput();
		org.eclipse.uml2.uml.Class out = null;
		
		// TODO implement logic (replace 'null')
		out = in.get(0);
		
		class_.setValue(out);
	}

	/**
	 * Helper method for extracting input to Java objects. 
	 * @generated
	 */
	private EList<org.eclipse.uml2.uml.Class> setInput() {
		EList<Object> origin = class_s.getValue();
		EList<org.eclipse.uml2.uml.Class> in = new BasicEList<org.eclipse.uml2.uml.Class>();
		for (Object o : origin) {
			in.add((org.eclipse.uml2.uml.Class) o);
		}
		return in;
	}

}
	