/**
 * <copyright>
 * </copyright>
 *
 * $Id: RefactoringInformation.java,v 1.1 2011/01/19 12:09:31 tarendt Exp $
 */
 package org.eclipse.emf.refactor.refactorings.ecore.henshin.renameeclassusinghenshin;

import java.io.File;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.refactor.refactoring.henshin.interfaces.IHenshinDataManagement;
import org.eclipse.emf.refactor.refactoring.henshin.runtime.HenshinInformationAdapter;
import org.eclipse.emf.refactor.refactorings.ecore.henshin.Activator;
import org.osgi.framework.Bundle;

/**
 * Class used for specifying a Henshin file that shall be used by
 * a specific EMF model refactoring.
 * @generated
 */
public class RefactoringInformation extends HenshinInformationAdapter {
	
	private final String transformationPathName = "transformation";
	
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
	 * @generated NOT
	 */
	@Override
	public String getTransformationFileName() {
		String emtPath = "";
		final Bundle bundle = Platform.getBundle(Activator.PLUGIN_ID);
		try {
			if (bundle == null) { // does not run in an eclipse instance; used for JUnit tests
				emtPath = transformationPathName + File.separator + this.transformationFileName;
			} else {
				emtPath = FileLocator.toFileURL
							(bundle.getEntry(transformationPathName)).getFile();
				emtPath += this.transformationFileName;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		

		System.out.println(emtPath);
		return emtPath;
	}

}