package org.eclipse.emf.refactor.refactorings.ecore;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.impl.EcorePackageImpl;

public abstract class RefactoringHelper {

	public static String getReasonForInvalidEPackageName(String ePackageName) {
		return checkLowerCaseString(ePackageName);
	}

	public static String getReasonForInvalidEReferenceName(String eReferenceName) {
		return checkLowerCaseString(eReferenceName);
	}

	public static String getReasonForInvalidEParameterName(String eParameterName) {
		return checkLowerCaseString(eParameterName);
	}

	public static String getReasonForInvalidEOperationName(String eOperationName) {
		return checkLowerCaseString(eOperationName);
	}

	public static String getReasonForInvalidEEnumLiteralName(String eEnumLiteralString) {
		return checkCompletelyUpperCaseString(eEnumLiteralString);
	}

	public static String getReasonForInvalidEDataTypeName(String eDataTypeString) {
		return checkUpperCaseString(eDataTypeString);
	}

	public static String getReasonForInvalidEClassName(String eClassName) {
		return checkUpperCaseString(eClassName);
	}

	public static String getReasonForInvalidEAttributeName(String eAttributeName) {
		return checkLowerCaseString(eAttributeName);
	}

	private static boolean isValidUpperCaseString(String str) {
		if (hasWhiteSpaces(str)) {
			return false;
		}
		if (! startsWithLetter(str)) {
			return false;
		}
		if (! startsWithUpperCase(str)) {
			return false;
		}	
		return true;
	}

	private static String checkUpperCaseString(String str) {
		if (hasWhiteSpaces(str)) {
			return "Invalid name: It contains whitespaces!";
		}
		if (! startsWithLetter(str)) {
			return "Invalid name: It doesn't start with a letter!";
		}
		if (! startsWithUpperCase(str)) {
			return "Invalid name: It starts with an lower case letter!";
		}
		return "";
	}

	private static boolean isValidLowerCaseString(String str) {
		if (hasWhiteSpaces(str)) {
			return false;
		}
		if (! startsWithLetter(str)) {
			return false;
		}
		if (! startsWithLowerCase(str)) {
			return false;
		}	
		return true;
	}
	
	private static String checkLowerCaseString(String str) {
		if (hasWhiteSpaces(str)) {
			return "Invalid name: It contains whitespaces!";
		}
		if (! startsWithLetter(str)) {
			return "Invalid name: It doesn't start with a letter!";
		}
		if (! startsWithLowerCase(str)) {
			return "Invalid name: It starts with an upper case letter!";
		}	
		return "";
	}

	private static boolean isValidCompletelyUpperCaseString(String str) {
		if (hasWhiteSpaces(str)) {
			return false;
		}
		if (! startsWithLetter(str)) {
			return false;
		}
		if (! isCompletelyUpperCase(str)) {
			return false;
		}	
		return true;
	}

	private static String checkCompletelyUpperCaseString(String str) {
		if (hasWhiteSpaces(str)) {
			return "Invalid name: It contains whitespaces!";
		}
		if (! startsWithLetter(str)) {
			return "Invalid name: It doesn't start with a letter!";
		}
		if (! isCompletelyUpperCase(str)) {
			return "Invalid name: It contains lower case letters!";
		}
		return "";
	}

	private static boolean startsWithLetter(String str) {
		return Character.isLetter(str.charAt(0));
	}

	private static boolean isCompletelyUpperCase(String str) {
		String str_2 =  str.toUpperCase();		
		return str.equals(str_2);
	}	

	private static boolean startsWithLowerCase(String str) {
		String str_1 = str.substring(0, 1);
		String str_2 =  str.substring(0, 1).toLowerCase();
		return str_1.equals(str_2);
	}

	private static boolean startsWithUpperCase(String str) {
		String str_1 = str.substring(0, 1);
		String str_2 =  str.substring(0, 1).toUpperCase();
		return str_1.equals(str_2);
	}

	private static boolean hasWhiteSpaces(String str) {
		return str.contains(" ");
	}
	
	public static boolean isValidEPackageName(String ePackageName) {
		return isValidLowerCaseString(ePackageName);
	}

	public static boolean isValidEReferenceName(String eReferenceName) {
		return isValidLowerCaseString(eReferenceName);
	}

	public static boolean isValidEParameterName(String eParameterName) {
		return isValidLowerCaseString(eParameterName);
	}

	public static boolean isValidEOperationName(String eOperationName) {
		return isValidLowerCaseString(eOperationName);
	}

	public static boolean isValidEEnumLiteralName(String eEnumLiteralName) {
		return isValidCompletelyUpperCaseString(eEnumLiteralName);
	}

	public static boolean isValidEDataTypeName(String eDataTypeName) {
		return isValidUpperCaseString(eDataTypeName);
	}

	public static boolean isValidEClassName(String eClassName) {
		return isValidUpperCaseString(eClassName);
	}

	public static boolean isValidEAttributeName(String eAttributeName) {
		return isValidLowerCaseString(eAttributeName);
	}

	public static boolean containsEPackage
								(EPackage eSuperPackage, String ePackageName) {
		for (EPackage ePackage : eSuperPackage.getESubpackages()) {
			if (ePackage.getName().equals(ePackageName)) {
				return true;
			}
		}
		return false;
	}

	public static boolean containsEClassifier
									(EPackage ePackage, String eClassifierName) {
		for (EClassifier eClassifier : ePackage.getEClassifiers()) {
			if (eClassifier.getName().equals(eClassifierName)) {
				return true;
			}
		}
		return false;
	}

	public static boolean containsEEnumLiteral
										(EEnum eEnum, String eEnumLiteralName) {
		for (EEnumLiteral eEnumLiteral : eEnum.getELiterals()) {
			if (eEnumLiteral.getName().equals(eEnumLiteralName)) {
				return true;
			}
		}
		return false;
	}

	public static boolean containsEStructuralFeature
						(EClass eContainingClass, String eStructuralFeatureName) {
		for (EStructuralFeature eStructuralFeature : 
							eContainingClass.getEAllStructuralFeatures()) {
			if (eStructuralFeature.getName().equals(eStructuralFeatureName)) {
				return true;
			}
		}
		return false;
	}

	public static boolean containsEOperation(EClass eContainingClass, 
									EOperation eOperation, String eOperationName) {
		for (EOperation eOperationTemp : eContainingClass.getEAllOperations()) {
			if (eOperationTemp.getName().equals(eOperationName)) {
				if (haveSameEParameterLists(eOperation, eOperationTemp)) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean haveSameSignatures
						(EOperation eOperation_1, EOperation eOperation_2) {
		if (! eOperation_1.getName().equals(eOperation_2.getName())) {
			return false;
		}
		if (! haveSameEParameterLists(eOperation_1, eOperation_2)) {
			return false;
		}
		return true;
	}
	
	public static boolean haveSameSignaturesAndETypes
						(EOperation eOperation_1, EOperation eOperation_2) {
		if (! haveSameSignatures(eOperation_1, eOperation_2)) {
			return false;
		}
		if (! haveSameETypes(eOperation_1, eOperation_2)) {
			return false;
		}		
		return true;
	}

	private static boolean haveSameETypes
							(EOperation eOperation_1, EOperation eOperation_2) {
		if (eOperation_1.getEType().equals(eOperation_2.getEType())) {
			return true;
		}
		return false;
	}

	private static boolean haveSameEParameterLists
								(EOperation eOperation_1, EOperation eOperation_2) {
		int numberOfParameters_1 = eOperation_1.getEParameters().size();
		int numberOfParameters_2 = eOperation_2.getEParameters().size();
		if (numberOfParameters_1 == numberOfParameters_2) {
			for (int i=0; i < numberOfParameters_1; i++) {
				EParameter eParameter_1 = eOperation_1.getEParameters().get(i);
				EParameter eParameter_2 = eOperation_2.getEParameters().get(i);
				if (! eParameter_1.getEType().equals(eParameter_2.getEType())) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}

	public static boolean containsEParameter
						(EOperation eOperation, String eParameterName) {
		for (EParameter eParameter : eOperation.getEParameters()) {
			if (eParameter.getName().equals(eParameterName)) {
				return true;
			}
		}
		return false;
	}

	public static boolean containsEOperation(EOperation eOperation,
			String eParameterName, EClassifier eClassifier) {
		for (EOperation eOperationTemp : 
			eOperation.getEContainingClass().getEAllOperations()) {
			if (! eOperation.equals(eOperationTemp)) {
				EParameter newEParameter =
									EcoreFactory.eINSTANCE.createEParameter();
				newEParameter.setName(eParameterName);
				eOperation.getEParameters().add(newEParameter);
				newEParameter.setEType(eClassifier);
				if (haveSameSignatures(eOperationTemp, eOperation)) {
					eOperation.getEParameters().remove(newEParameter);
					return true;
				} else {
					eOperation.getEParameters().remove(newEParameter);
				}
			}
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	public static boolean isEmptyEClass(EClass eClass) {
		if (! eClass.getEStructuralFeatures().isEmpty()) return false;
		if (! eClass.getEOperations().isEmpty()) return false;
		if (! eClass.getESuperTypes().isEmpty()) return false;
		for (TreeIterator iter = eClass.eResource().getAllContents(); 
																					iter.hasNext(); ) {
			EObject eObject = (EObject) iter.next();
			if (eObject instanceof ETypedElement) {
				ETypedElement eTypedElement = (ETypedElement) eObject;
				if (eTypedElement.getEType() != null 
										&& eTypedElement.getEType().equals(eClass)) {
					return false;
				}
			}
		}
		return true;
	}

	@SuppressWarnings("rawtypes")
	public static String getReasonForNonEmptyEClass(EClass eClass) {
		if (! eClass.getEStructuralFeatures().isEmpty())  {
			return "The EClass contains an EStructuralFeature.";
		}
		if (! eClass.getEOperations().isEmpty()) {
			return "The EClass contains an EOperation.";
		}
		if (! eClass.getESuperTypes().isEmpty()) {
			return "The EClass has an eSuperType.";
		}
		for (TreeIterator iter = eClass.eResource().getAllContents(); iter.hasNext(); ) {
			EObject eObject = (EObject) iter.next();
			if (eObject instanceof ETypedElement) {
				ETypedElement eTypedElement = (ETypedElement) eObject;
				if (eTypedElement.getEType() != null 
										&& eTypedElement.getEType().equals(eClass)) {
					return "The EClass is an eType of an ETypedElement.";
				}
			}
		}
		return "";
	}

	public static boolean containsEOperation
				(EOperation eOperation, EParameter eParameter) {
		for (EOperation eOperationTemp : 
			eOperation.getEContainingClass().getEAllOperations()) {
			if (! eOperation.equals(eOperationTemp)) {
				int position = eOperation.getEParameters().indexOf(eParameter);
				eOperation.getEParameters().remove(eParameter);
				if (haveSameSignatures(eOperationTemp, eOperation)) {
					eOperation.getEParameters().add(position, eParameter);
					return true;
				} else {
					eOperation.getEParameters().add(position, eParameter);
				}
			}
		}
		return false;	
	}

	@SuppressWarnings("rawtypes")
	public static EList<EClass> getESubClasses(EClass eSuperClass) {
		EList <EClass> eSubClasses = new BasicEList<EClass>();
		for (TreeIterator iter = eSuperClass.eResource().getAllContents(); iter.hasNext(); ) {
			EObject eObject = (EObject) iter.next();
			if (eObject instanceof EClass) {
				EClass eClass = (EClass) eObject;
				if (eClass.getESuperTypes().contains(eSuperClass)) {
					eSubClasses.add(eClass);
				}
			}
		}	
		return eSubClasses;
	}

	public static EClass getESuperClass
								(EClass subEClass, String eClassName) {
		for (EClass eClass : subEClass.getESuperTypes()) {
			if (eClass.getName().endsWith(eClassName)) {
				return eClass;
			}
		}
		return null;
	}

	public static boolean childEClassContainsEStructuralFeature
								(EClass eClass, String eClassName) {
		EList <EClass> eSubClasses = getESubClasses(eClass);
		for (EClass subEClass : eSubClasses) {
			for (EStructuralFeature eStrFeat : subEClass.getEStructuralFeatures()) {
				if (eStrFeat.getName().equals(eClassName)) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isPrimitiveDataType(String eType) {
		if (eType.equals("EInt")) return true;
		if (eType.equals("EBoolean")) return true;
		if (eType.equals("EString")) return true;
		if (eType.equals("EFloat")) return true;
		if (eType.equals("EChar")) return true;
		return false;
	}
	
	public static EDataType getPrimitiveDataType(String eType) {
		if (eType.equals("EInt")) return EcorePackageImpl.init().getEInt();
		if (eType.equals("EBoolean")) return EcorePackageImpl.init().getEBoolean();
		if (eType.equals("EString")) return EcorePackageImpl.init().getEString();
		if (eType.equals("EFloat")) return EcorePackageImpl.init().getEFloat();
		if (eType.equals("EChar")) return EcorePackageImpl.init().getEChar();
		return null;
	}
	 
}
