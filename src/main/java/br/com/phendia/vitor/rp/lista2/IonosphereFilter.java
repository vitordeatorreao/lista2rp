package br.com.phendia.vitor.rp.lista2;

import weka.attributeSelection.BestFirst;
import weka.attributeSelection.CfsSubsetEval;
import weka.attributeSelection.ChiSquaredAttributeEval;
import weka.attributeSelection.InfoGainAttributeEval;
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
	
	public Instances infoGainFilter() throws Exception {
		AttributeSelection filter = new AttributeSelection();
		Ranker search = new Ranker();
		filter.setSearch(search);
		filter.setInputFormat(data);
		InfoGainAttributeEval evaluator = new InfoGainAttributeEval();
		filter.setEvaluator(evaluator);
		return Filter.useFilter(data, filter);
	}
	
	public Instances correlationFilter() throws Exception {
		AttributeSelection filter = new AttributeSelection();
		BestFirst search = new BestFirst();
		filter.setSearch(search);
		filter.setInputFormat(data);
		CfsSubsetEval evaluator = new CfsSubsetEval();
		filter.setEvaluator(evaluator);
		return Filter.useFilter(data, filter);
	}
	
	public static void printAttributes(Instances data, int rank) {
		for (int i = 0; i < rank; i++) {
			System.out.print(data.attribute(i).name());
		}
		System.out.print("\n");
	}

	public Instances getData() {
		return data;
	}
	
    public static void main( String[] args )
    {
        try {
			IonosphereFilter lista2 = new IonosphereFilter();
			System.out.println("Todos os atributos:");
			IonosphereFilter.printAttributes(lista2.getData(), lista2.getData().numAttributes());
			Instances data = lista2.chiSquareFilter();
			System.out.println("Chi Quadrado:");
			IonosphereFilter.printAttributes(data, 5);
			Instances data2 = lista2.correlationFilter();
			System.out.println("Baseado em correlação:");
			IonosphereFilter.printAttributes(data2, 5);
			Instances data3 = lista2.infoGainFilter();
			System.out.println("Baseado em Ganho de Informação:");
			IonosphereFilter.printAttributes(data3, 5);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
}
