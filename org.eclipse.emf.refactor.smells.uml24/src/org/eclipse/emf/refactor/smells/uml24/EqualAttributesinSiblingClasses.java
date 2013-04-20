package org.eclipse.emf.refactor.smells.uml24;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.smells.interfaces.IModelSmellFinder;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Property;


public final class EqualAttributesinSiblingClasses implements IModelSmellFinder {

	@Override
	public LinkedList<LinkedList<EObject>> findSmell(EObject root) {
		LinkedList<LinkedList<EObject>> results = new LinkedList<LinkedList<EObject>>();
		List<Property> attributes = getAllClassAttributes(root);
		for (Property attribute : attributes) {
			Class owningClass = attribute.getClass_();
			List<Class> superClasses = owningClass.getSuperClasses();
			for (Class superClass : superClasses) {
				if (eachSubClassContainsAttribute(superClass, attribute)) {
					LinkedList<EObject> result = new LinkedList<EObject>();
					result.add(attribute.getClass_());
					result.add(attribute);
					results.add(result);
				}
			}
		}
		return results;
	}

	private boolean eachSubClassContainsAttribute(Class superClass, Property attribute) {
		List<Class> subClasses = new ArrayList<Class>();
		List<Class> allClasses = getAllClasses(superClass.getModel());
		for (Class modelClass : allClasses) {
			if (modelClass != attribute.getClass_() && modelClass.getSuperClasses().contains(superClass)) {
				subClasses.add(modelClass);
			}
		}
		if (subClasses.isEmpty()) return false;
		for (Class subClass : subClasses) {
			if (! classContainsEqualAttribute(subClass, attribute)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean classContainsEqualAttribute(Class cls, Property attribute) {
		if (attribute.getClass_().equals(cls)) return true;
		boolean classHasEqualAttribute = false;
		for (Property attr : cls.getOwnedAttributes()) {
			if (! haveSameNames(attr, attribute)) continue;
			if (! haveSameType(attr, attribute)) continue;
			if (! haveSameVisibilities(attr, attribute)) continue;
			if (! haveSameMultiplicities(attr, attribute)) continue;
			if (! haveSameAggregations(attr, attribute)) continue;
			if (! haveSameDefaultValues(attr, attribute)) continue;
			if (! haveSameDerivedProperties(attr, attribute)) continue;
			if (! haveSameLeafProperties(attr, attribute)) continue;
			if (! haveSameReadOnlyProperties(attr, attribute)) continue;
			if (! haveSameUniqueProperties(attr, attribute)) continue;
			if (! haveSameOrderedProperties(attr, attribute)) continue;
			if (! haveSameStaticProperties(attr, attribute)) continue;
			if (! haveSameDerivedUnionProperties(attr, attribute)) continue;
			if ((attr.getAssociation() != null) && (attribute.getAssociation() != null)) {
				if (getOtherSideUpper(attr) != getOtherSideUpper(attribute)) continue;
			}
			classHasEqualAttribute = true;
			break;
		}
		return classHasEqualAttribute;
	}
	
	private int getOtherSideUpper(Property attribute) {
		int upper = 0;
		for (Property ae : attribute.getAssociation().getMemberEnds()) {
			if (! ae.equals(attribute)) upper = ae.getUpper();
		}
		return upper;
	}

	private boolean haveSameDerivedUnionProperties(Property att1, Property att2) {
		return (att1.isDerivedUnion() == att2.isDerivedUnion());
	}
	
	private boolean haveSameStaticProperties(Property att1, Property att2) {
		return (att1.isStatic() == att2.isStatic());
	}

	private boolean haveSameOrderedProperties(Property att1, Property att2) {
		return (att1.isOrdered() == att2.isOrdered());
	}
	
	private boolean haveSameUniqueProperties(Property att1, Property att2) {
		return (att1.isUnique() == att2.isUnique());
	}
	
	private boolean haveSameReadOnlyProperties(Property att1, Property att2) {
		return (att1.isReadOnly() == att2.isReadOnly());
	}
	
	private boolean haveSameLeafProperties(Property att1, Property att2) {
		return (att1.isLeaf() == att2.isLeaf());
	}
	
	private boolean haveSameDerivedProperties(Property att1, Property att2) {
		return (att1.isDerived() == att2.isDerived());
	}

	private boolean haveSameDefaultValues(Property att1, Property att2) {
		if ((att1.getDefault() == null) && (att2.getDefault() == null)) return true;
		if ((att1.getDefault() != null) 
				&& (att2.getDefault() != null)
				&& (att1.getDefault().equals(att2.getDefault()))) return true;
		return false;
	}

	private boolean haveSameAggregations(Property att1, Property att2) {
		return att1.getAggregation().equals(att2.getAggregation());
	}

	private boolean haveSameMultiplicities(Property att1, Property att2) {
		return ((att1.getLower() == att2.getLower()) 
				&& (att1.getUpper() == att2.getUpper()));
	}

	private boolean haveSameVisibilities(Property att1, Property att2) {
		return att1.getVisibility().equals(att2.getVisibility());
	}

	private boolean haveSameNames(Property att1, Property att2) {
		return att1.getName().equals(att2.getName());
	}

	private boolean haveSameType(Property att1, Property att2) {
		if ((att1.getType() == null) && (att2.getType() == null)) return true;
		if ((att1.getType() != null) 
				&& (att2.getType() != null)
				&& (att1.getType().equals(att2.getType()))) return true;
		return false;
	}

	private ArrayList<Class> getAllClasses(Model model) {
		ArrayList<Class> classes = new ArrayList<Class>();
		TreeIterator<EObject> iter = model.eResource().getAllContents();
		while (iter.hasNext()) {
			EObject eObject = iter.next();
			if (eObject instanceof Class) {
				classes.add((Class) eObject);
			}			
		}		
		return classes;
	}

	private List<Property> getAllClassAttributes(EObject root) {
		List<Property> attributes = new ArrayList<Property>();
		TreeIterator<EObject> iter = root.eAllContents();
		while (iter.hasNext()) {
			EObject eObject = iter.next();
			if (eObject instanceof Property) {
				Property prop = (Property) eObject;
				if (prop.getClass_() != null) {
					attributes.add(prop);
				}
			}
		}
		return attributes;
	}	
}