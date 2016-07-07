package com.venkat.annotations.processors;

import java.io.Serializable;
import java.util.List;

public class BeanInfoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	String packageName;
	String className;
	String qualifiedName;
	String beanInfoClassName;
	String beanInfoQualifiedName;
	String description;
	boolean expert;
	boolean hidden;
	boolean preferred;
	String name;
	String qualifiedType;
	List<BeanInfoDTO> parameters;
	
	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getQualifiedName() {
		return qualifiedName;
	}

	public void setQualifiedName(String qualifiedName) {
		this.qualifiedName = qualifiedName;
	}

	public String getBeanInfoClassName() {
		return beanInfoClassName;
	}

	public void setBeanInfoClassName(String beanInfoClassName) {
		this.beanInfoClassName = beanInfoClassName;
	}

	public String getBeanInfoQualifiedName() {
		return beanInfoQualifiedName;
	}

	public void setBeanInfoQualifiedName(String beanInfoQualifiedName) {
		this.beanInfoQualifiedName = beanInfoQualifiedName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isExpert() {
		return expert;
	}

	public void setExpert(boolean expert) {
		this.expert = expert;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public boolean isPreferred() {
		return preferred;
	}

	public void setPreferred(boolean preferred) {
		this.preferred = preferred;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQualifiedType() {
		return qualifiedType;
	}

	public void setQualifiedType(String qualifiedType) {
		this.qualifiedType = qualifiedType;
	}

}
