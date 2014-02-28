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

public final class EquallyNamedClasses implements IModelSmellFinder {

	private final String transformationPath = "transformations/";
	private final String henshinFileName = "equallynamedclasses.henshin";

	@Override
	public LinkedList<LinkedList<EObject>> findSmell(EObject root) {
		String fullPath = getFullPath(transformationPath + henshinFileName);
		LinkedList<LinkedList<EObject>> results = HenshinRuntimeManager.run(root, fullPath);
		return removeRedundantResults(results);
	}
	
	private LinkedList<LinkedList<EObject>> removeRedundantResults(
			LinkedList<LinkedList<EObject>> results) {
		LinkedList<LinkedList<EObject>> redundantResults = new LinkedList<LinkedList<EObject>>();
		for (int i = 0; i < results.size(); i++) {
			LinkedList<EObject> result1 = results.get(i);
			for (int j = i+1; j < results.size(); j++) {
				LinkedList<EObject> result2 = results.get(j);
				if (result1.getFirst() == result2.getLast() && 
						result2.getFirst() == result1.getLast()) {
					redundantResults.add(result2);
				}
			}
		}
		results.removeAll(redundantResults);
		return results;
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