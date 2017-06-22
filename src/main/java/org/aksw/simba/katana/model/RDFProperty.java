package org.aksw.simba.katana.model;

public class RDFProperty {
	String uri;
	String label;

	public RDFProperty(String uri, String label) {
		this.uri = uri;
		this.label = label;

	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
