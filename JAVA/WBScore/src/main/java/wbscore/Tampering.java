package main.java.wbscore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Tampering {

	private static Random rand;
	private static long seed = 88888888;
	private static int repetitions = 100;

	public static void main(String[] args) throws IOException {

		//IRIS
		String datasetArffPath = ".\\iris.data";
		String tamperedDatasetArffPath = ".\\tampered_data\\iris_";
		int[] columnsToTamper = new int[]{ 0,1,2,3 };
		double[] meanValues = new double[]{ 5.843, 3.054, 3.759, 1.199 }; 

		//GLASS
//		String datasetArffPath = ".\\glass.data";
//		String tamperedDatasetArffPath = ".\\tampered_data\\glass_";
//		int[] columnsToTamper = new int[]{ 0,1,2,3,4,5,6,7,8 };
//		double[] meanValues = new double[]{ 1.518, 13.408, 2.685, 1.445, 72.651, 0.497, 8.957, 0.175, 0.057 }; 
		
		//IONOSPHERE
//		String datasetArffPath = ".\\ionosphere.data";
//		String tamperedDatasetArffPath = ".\\tampered_data\\ionosphere_";
//		int[] columnsToTamper = new int[]{ 0,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33 };
//		double[] meanValues = new double[]{ 0.892, 0, 0.641, 0.044, 0.601, 0.116, 0.55, 0.119, 0.512, 0.181, 0.476, 0.155, 0.401, 0.093, 0.344, 0.071, 0.382, -0.004, 0.359, -0.024, 0.337, 0.008, 0.362, -0.057, 0.396, -0.071, 0.542, -0.07, 0.378, -0.028, 0.353, -0.004, 0.349, 0.014 }; 
		
		//IMAGE SEGMENTATION
//		String datasetArffPath = ".\\segmentation.data";
//		String tamperedDatasetArffPath = ".\\tampered_data\\segmentation_";
//		int[] columnsToTamper = new int[]{ 1,2,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19 };
//		double[] meanValues = new double[]{ 0, 124.648, 122.757, 0, 0.008, 0.006, 1.926, 5.72, 2.604, 11.638, 37.091, 32.968, 44.011, 34.294, -12.37, 20.76, -8.39, 44.888, 0.423, -1.34 }; 

		//SEEDS
//		String datasetArffPath = ".\\seeds.data";
//		String tamperedDatasetArffPath = ".\\tampered_data\\seeds_";
//		int[] columnsToTamper = new int[]{ 0,1,2,3,4,5,6 };
//		double[] meanValues = new double[]{ 14.848, 14.559, 0.871, 5.629, 3.259, 3.7, 5.408 }; 
		
		rand = new Random(seed);
		
        String line;
        String csvSplitBy = ",";

        //Testing up to 20% noise level
        for (int intensity=1; intensity<21; intensity++) {
        
        
    		//Preparing writing file
    		FileWriter fileWriterOutput = new FileWriter(tamperedDatasetArffPath + intensity + ".arff");
    		PrintWriter printWriterOutput = new PrintWriter(fileWriterOutput);

	        BufferedReader br = new BufferedReader(new FileReader(datasetArffPath));
	        
	            while ((line = br.readLine()) != null) {
	       
	            	for (int count=0; count < repetitions; count++) {
	            	
		                String[] multiplicativeNoiseValues = line.split(csvSplitBy);
		                String[] additiveNoiseValues = line.split(csvSplitBy);
		                String[] mixedNoiseValues = line.split(csvSplitBy);
		
		                multiplicativeNoiseValues = createMultiplicativeNoise(multiplicativeNoiseValues, columnsToTamper, intensity);
		                additiveNoiseValues = createAdditiveNoise(additiveNoiseValues, meanValues, columnsToTamper, intensity);
		                mixedNoiseValues = createMixedNoise(mixedNoiseValues, multiplicativeNoiseValues, additiveNoiseValues, columnsToTamper, intensity);
		                
		                printWriterOutput.print(joinStringArrayWithComma(multiplicativeNoiseValues) + "\n");
		                printWriterOutput.print(joinStringArrayWithComma(additiveNoiseValues) + "\n");
		                printWriterOutput.print(joinStringArrayWithComma(mixedNoiseValues) + "\n");
		                
	            	}
	                
	            }

	            printWriterOutput.close();
	            fileWriterOutput.close();
	            br.close();
	        
        }
        
        
        System.out.println("End processing.");
        
	}
	
	
	
	private static String[] createMultiplicativeNoise(String[] multiplicativeNoiseValues, int[] columnsToTamper, int intensity) {
		
        for (int index : columnsToTamper) {
        	if (("".equals(multiplicativeNoiseValues[index].trim())) || ("?".equals(multiplicativeNoiseValues[index].trim()))) continue;
        	multiplicativeNoiseValues[index] = generateMultiplicativeNoise(Double.parseDouble(multiplicativeNoiseValues[index]), intensity) + "";
        }
		
        return multiplicativeNoiseValues;
	}

	
	private static String[] createAdditiveNoise(String[] additiveNoiseValues, double[] meanValues, int[] columnsToTamper, int intensity) {
		
        for (int index : columnsToTamper) {
        	if (("".equals(additiveNoiseValues[index].trim())) || ("?".equals(additiveNoiseValues[index].trim()))) continue;
        	additiveNoiseValues[index] = generateAdditiveNoise(Double.parseDouble(additiveNoiseValues[index]), meanValues[index], intensity) + "";
        }
		
        return additiveNoiseValues;
	}
	
	
	private static String[] createMixedNoise(String[] mixedNoiseValues, String[] multiplicativeNoiseValues, String[] additiveNoiseValues, int[] columnsToTamper, int intensity) {
		
        for (int index : columnsToTamper) {
        	if ( ("".equals(additiveNoiseValues[index].trim())) || ("?".equals(additiveNoiseValues[index].trim())) || ("".equals(multiplicativeNoiseValues[index].trim())) || ("?".equals(multiplicativeNoiseValues[index].trim())) ) continue;
        	mixedNoiseValues[index] = generateMixedNoise(Double.parseDouble(multiplicativeNoiseValues[index]), Double.parseDouble(additiveNoiseValues[index])) + "";
        }
		
		return mixedNoiseValues;
	}
	
	
	
	private static String joinStringArrayWithComma(String[] stringArray) {

		StringBuffer sb=new StringBuffer();
		
		for (int x=0; x < stringArray.length; x++) {

			if (x==0) {
				sb.append(stringArray[x]);	
			} else {
				sb.append("," + stringArray[x]);
			}
			
		}
		
		return sb.toString();
		
	}

	
	public static double truncateToFourDecimalPlaces(double value) {
		
        double truncatedValue = Math.floor(value * 10000) / 10000;
        return truncatedValue;
        
    }	
	
	
	private static double generateMultiplicativeNoise(double value, int intensity) {
		
    	int percentual = rand.nextInt(101);
		double calculatedPercentual = (0.01 * intensity * percentual) - (intensity/2);
		double noiseValue = 0.01 * calculatedPercentual * value;
		double tamperedValue = value + noiseValue; 

		return truncateToFourDecimalPlaces(tamperedValue);
		
	}
	

	private static double generateAdditiveNoise(double value, double meanValue, int intensity) {
		
    	int percentual = rand.nextInt(101);
		double calculatedPercentual = (0.01 * intensity * percentual) - (intensity/2);
		double noiseValue = 0.01 * calculatedPercentual * meanValue;
		double tamperedValue = value + noiseValue; 

		return truncateToFourDecimalPlaces(tamperedValue);
		
	}
	
	
	private static double generateMixedNoise(double multiplicativeNoise, double additiveNoise) {
		
		double tamperedValue = (multiplicativeNoise + additiveNoise)/2;
		
		return truncateToFourDecimalPlaces(tamperedValue);
		
	}
	
	
}
