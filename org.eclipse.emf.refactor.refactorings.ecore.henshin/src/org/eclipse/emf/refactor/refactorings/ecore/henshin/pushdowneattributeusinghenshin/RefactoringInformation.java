/**
 * <copyright>
 * </copyright>
 *
 * $Id: RefactoringInformation.java,v 1.1 2011/01/19 12:09:32 tarendt Exp $
 */
 package org.eclipse.emf.refactor.refactorings.ecore.henshin.pushdowneattributeusinghenshin;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.refactor.henshin.runtime.HenshinInformationAdapter;
import org.eclipse.emf.refactor.henshin.core.IHenshinDataManagement;
import org.osgi.framework.Bundle;

import org.eclipse.emf.refactor.refactorings.ecore.henshin.Activator;

/**
 * Class used for specifying a Henshin file that shall be used by
 * a specific EMF model refactoring.
 * @generated
 */
public class RefactoringInformation extends HenshinInformationAdapter {
	
	/**
	 * HenshinDataManagement object of the specific EMF model refactoring.
	 * @generated
	 */
	private RefactoringDataManagement dataManagement;
	
	/**
	 * Default constructor using a RefactoringDataManagement object.
	 * @param dataManagement HenshinDataManagement object of the specific 
	 * EMF model refactoring.
	 * @generated
	 */
	public RefactoringInformation
				(RefactoringDataManagement dataManagement) {
		super();
		this.dataManagement = dataManagement;
	}

	/**
	 * @see org.eclipse.emf.refactor.henshin.core.IHenshinInformation#
	 * getHenshinDataManagement()
	 * @generated
	 */
	@Override
	public IHenshinDataManagement getHenshinDataManagement() {
		return this.dataManagement;
	}
	
	/**
	 * @see org.eclipse.emf.refactor.henshin.runtime.
	 * HenshinInformationAdapter#getTransformationFileName()
	 * @generated
	 */
	@Override
	public String getTransformationFileName() {
		String emtPath = "";
		final Bundle bundle = Platform.getBundle(Activator.PLUGIN_ID);
		try {
			emtPath = FileLocator.toFileURL
						(bundle.getEntry("transformation")).getFile();
			emtPath += this.transformationFileName;
		} catch (Exception e) {
			e.printStackTrace();
		}		
		System.out.println(emtPath);
		return emtPath;
	}

}