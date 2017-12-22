package org.eclipse.emf.refactor.smells.uml24;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.smells.interfaces.IModelSmellFinder;
import org.eclipse.uml2.uml.DataType;

public final class UnnamedDataType implements IModelSmellFinder {

	@Override
	public LinkedList<LinkedList<EObject>> findSmell(EObject root) {
		LinkedList<LinkedList<EObject>> results = new LinkedList<LinkedList<EObject>>();
		// begin custom code
		List<DataType> dataTypes = getAllDataTypes(root);
		for (DataType dataType : dataTypes) {
			if (dataType.getName() == null || dataType.getName().equals("")) {
				LinkedList<EObject> result = new LinkedList<EObject>();
				result.add(dataType);
				results.add(result);
			}
		}
		// end custom code
		return results;
	}

	private List<DataType> getAllDataTypes(EObject root) {
		List<DataType> dataTypes = new ArrayList<DataType>();
		TreeIterator<EObject> iter = root.eAllContents();
		while (iter.hasNext()) {
			EObject eObject = iter.next();
			if (eObject instanceof DataType) {
				DataType dataType = (DataType) eObject;
				dataTypes.add(dataType);
			}
		}
		return dataTypes;
	}
	
}