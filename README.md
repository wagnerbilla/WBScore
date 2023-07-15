# WBScore

This project concerns data files used in the experiment described in the article "WB Score: A Novel Methodology for Visual Classifier Selection in Increasingly Noisy Datasets."
Below is described the folders content:

JAVA: Contains the class "Tampering.java," which inputs the data part of a dataset file (the lines below the mark @data in an arff file) and spawns it to 20 tampered datasets with increasing noise levels ranging from 1% to 20%, using a step of 1%. There are three types of generated noise: multiplicative, additive, and a mix of both.

Original Datasets: Contains the five used public datasets (iris, glass, ionosphere, image segmentation and seeds), which can be found in the UC Irvine Machine Learning Repository (https://archive.ics.uci.edu/). These datasets are ported in ARFF file format (WEKA file).

Tampered Datasets: All tampered datasets generated using the Tampering.java class are here. These are the noisy files tested in the experiment.

Custom Floodings Dataset: A dataset of flooding events in 2015 and 2016 in São Paulo, Brazil. It consists of six numeric fields representing partial accumulated precipitation in the last 12 hours before an event and a class labeled binary value (1 or 0) indicating whether flooding has occurred or not, respectively. More information about this dataset can be found in the article named "FROM RAINFALL DATA TO A TWO-DIMENSIONAL DATA-SPACE SEPARATION FOR FLOOD OCCURRENCE," which can be accessed via the following link: https://doi.org/10.29327/154013.24-44

I hope you enjoy the contents and can easily reproduce the experiment. All my generated files and Java code are intended not to have copyright constraints; use them freely.
