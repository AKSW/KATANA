package org.aksw.simba.katana.NLUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.aksw.simba.katana.KBUtils.SparqlHandler;
import org.aksw.simba.katana.model.RDFProperty;
import org.aksw.simba.katana.model.RDFResource;

import edu.stanford.nlp.ie.util.RelationTriple;

public class ComparisonUtils {
	SparqlHandler queryHandler;
	NLUtils nlHandler;
	public Map<RDFProperty, ArrayList<RDFResource>> kbPropResourceMap;

	public ComparisonUtils() {
		this.queryHandler = new SparqlHandler();
		this.nlHandler = new NLUtils();
		//this.kbPropResourceMap = queryHandler.getPropertyResourceMap();
	}

	public void addLabels(List<RelationTriple> triplesFromNL, Map<RDFProperty, ArrayList<RDFResource>> map) {
		for (Map.Entry<RDFProperty, ArrayList<RDFResource>> entry : map.entrySet()) {
			for (RelationTriple triple : triplesFromNL) {
				if (entry.getKey().getLabel().contains(triple.relationLemmaGloss())) {
					List<RDFResource> res = entry.getValue();
					System.out.println("Property match Found !! \n" + entry.getKey().getLabel());
					String object = triple.objectLemmaGloss();
					String subject = triple.subjectLemmaGloss();
					for (RDFResource resource : res) {
						if (resource.getKbLabel().toLowerCase().contains(subject.toLowerCase())
								|| resource.getKbLabel().toLowerCase().contains(object.toLowerCase())) {

							System.out.println("Got Resource match");
							System.out
									.println("Resource : " + resource.getKbLabel() + "  Potential Label : " + subject);

						}
					}
				}
			}
		}

	}

	public void psuedoaddLabels(List<RelationTriple> triplesFromNL, Map<RDFProperty, ArrayList<RDFResource>> map) {

		for (Map.Entry<RDFProperty, ArrayList<RDFResource>> entry : map.entrySet()) {
			for (RelationTriple triple : triplesFromNL) {
				if (entry.getKey().getLabel().contains(triple.relationLemmaGloss())) {
					System.out.println("Property match Found !! \n" + entry.getKey().getLabel());
					List<RDFResource> res = entry.getValue();
					System.out.println(
							"Potential Labels are \n " + triple.subjectGloss() + " :: " + triple.objectLemmaGloss());
					System.out.println("Associated resources are :");
					for (RDFResource resource : res) {
						System.out.println(resource.getKbLabel());
					}
				}
			}
		}
	}

	public void searchElement(String[] arr, List<RDFResource> res) {
		for (String nlEle : arr) {
			for (RDFResource resource : res) {
				if (resource.getLabels().contains(nlEle)) {
					System.out.println(resource.getKbLabel() + ":" + nlEle);
				}
			}
		}
	}


}