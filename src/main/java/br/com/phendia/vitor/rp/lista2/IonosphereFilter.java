package br.com.phendia.vitor.rp.lista2;

import weka.attributeSelection.ChiSquaredAttributeEval;
import weka.attributeSelection.Ranker;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.supervised.attribute.AttributeSelection;

public class IonosphereFilter 
{
	private Instances data;
	public IonosphereFilter() throws Exception {
		DataSource dataSource = new DataSource("resources/ionosphere.arff");
		data = dataSource.getDataSet();
		if (data.classIndex() == -1)
			data.setClassIndex(data.numAttributes() -1);
	}
	
	public Instances chiSquareFilter() throws Exception {
		AttributeSelection filter = new AttributeSelection();
		ChiSquaredAttributeEval evaluator = new ChiSquaredAttributeEval();
		Ranker search = new Ranker();
		filter.setEvaluator(evaluator);
		filter.setSearch(search);
		filter.setInputFormat(data);
		return Filter.useFilter(data, filter);
	}
	
	public static void printAttributes(Instances data) {
		for (int i = 0; i < data.numAttributes(); i++) {
			System.out.println(data.attribute(i).name());
		}
	}

	public Instances getData() {
		return data;
	}
	
    public static void main( String[] args )
    {
        try {
			IonosphereFilter lista2 = new IonosphereFilter();
			IonosphereFilter.printAttributes(lista2.getData());
			Instances data = lista2.chiSquareFilter();
			IonosphereFilter.printAttributes(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
}
