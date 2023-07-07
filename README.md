# WBScore

This project concerns data files used in the experiment described in the article "WB Score: A Novel Methodology for Visual Classifier Selection in Increasingly Noisy Datasets."
Below has described the folders content:

JAVA: Contains the class "Tampering.java," which inputs the data part of a dataset file (the lines below the mark @data in an arff file) and spawns it to 20 tampered datasets with increasing noise levels ranging from 1% to 20%, using a step of 1%. There are three types of generated noise: multiplicative, additive, and a mix of both.

Original Datasets: Contains the five used public datasets (iris, glass, ionosphere, image segmentation and seeds), which can be found in the UC Irvine Machine Learning Repository (https://archive.ics.uci.edu/). These datasets are ported in ARFF file format (WEKA file).

Tampered Datasets: All tampered datasets generated using the Tampering.java class are here. These are the noisy files tested in the experiment.

I hope you enjoy the contents and can easily reproduce the experiment. All my generated files and Java code are intended not to have copyright constraints; use them freely.
