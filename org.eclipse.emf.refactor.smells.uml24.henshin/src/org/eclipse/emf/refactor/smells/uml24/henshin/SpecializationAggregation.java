package org.eclipse.emf.refactor.smells.uml24.henshin;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.smells.henshin.managers.HenshinRuntimeManager;
import org.eclipse.emf.refactor.smells.interfaces.IModelSmellFinder;

public final class SpecializationAggregation implements IModelSmellFinder {

	private final String transformationPath = "transformations/";
	private final String henshinFileName = "specializationaggregation.henshin";

	@Override
	public LinkedList<LinkedList<EObject>> findSmell(EObject root) {
		String fullPath = getFullPath(transformationPath + henshinFileName);
		return HenshinRuntimeManager.run(root, fullPath);
	}
	
	private String getFullPath(String transformationPath){
		URL url = FileLocator.find(org.eclipse.emf.refactor.smells.uml24.henshin.Activator.getDefault().getBundle(), new Path(transformationPath), Collections.EMPTY_MAP);
		URL fileUrl = null;
		try {
		fileUrl = FileLocator.toFileURL(url);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return 	fileUrl.getPath();
	}
	
}