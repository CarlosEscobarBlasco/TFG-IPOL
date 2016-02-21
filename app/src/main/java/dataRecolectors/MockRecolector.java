package dataRecolectors;

import java.util.ArrayList;

import model.SubTopicData;
import model.TopicData;

/**
 * Created by Carlos on 13/02/2016.
 */
public class MockRecolector  {

    private static MockRecolector instance;
    private ArrayList<TopicData> data = new ArrayList<>();
    private TopicData itemSelected;

    protected MockRecolector (){
        ArrayList<SubTopicData> array3D = new ArrayList<>();
        array3D.add(new SubTopicData("An Implementation and Parallelization of the Scale Space Meshing Algorithm"));
        array3D.add(new SubTopicData("An Analysis and Implementation of a Parallel Ball Pivoting Algorithm"));
        array3D.add(new SubTopicData("Farman Institute 3D Point Sets - High Precision 3D Data Sets"));
        data.add(new TopicData("3D",array3D));

        ArrayList<SubTopicData> Blur = new ArrayList<>();
        Blur.add(new SubTopicData("Computing an Exact Gaussian Scale-Space"));
        Blur.add(new SubTopicData("A Survey of Gaussian Convolution Algorithms"));
        Blur.add(new SubTopicData("Total Variation Deconvolution using Split Bregman"));
        data.add(new TopicData("Blur",Blur));

        ArrayList<SubTopicData> calibration = new ArrayList<>();
        calibration.add(new SubTopicData("Automatic Lens Distortion Correction Using One-Parameter Division Models"));
        calibration.add(new SubTopicData("Recovering the Subpixel PSF from Two Photographs at Different Distances"));
        calibration.add(new SubTopicData("Non-parametric Sub-pixel Local Point Spread Function Estimation"));
        calibration.add(new SubTopicData("Algebraic Lens Distortion Model Estimation"));
        calibration.add(new SubTopicData("An Iterative Optimization Algorithm for Lens Distortion Correction Using Two-Parameter Models"));
        data.add(new TopicData("Calibration",calibration));

        ArrayList<SubTopicData> colorContrast = new ArrayList<>();
        colorContrast.add(new SubTopicData("An Algorithmic Analysis of Variational Models for Perceptual Local Contrast Enhancement"));
        colorContrast.add(new SubTopicData("Multiscale Retinex"));
        colorContrast.add(new SubTopicData("Screened Poisson Equation for Image Contrast Enhancement"));
        data.add(new TopicData("Color and Contrast",colorContrast));

    }

    public static MockRecolector getInstance(){
        if(instance==null)instance= new MockRecolector();
        return instance;
    }

    public ArrayList<TopicData> getData() {return data;}

    public SubTopicData getSubDataFromName(String name){
        for (TopicData topicData:data ){
            for (SubTopicData subTopicData: topicData.getData()){
                if (subTopicData.getText().equals(name))return subTopicData;
            }
        }
        return null;
    }

    public ArrayList<SubTopicData> getSubTopicList(){
        for (TopicData topicData:data ){
            if (topicData.getTopic().equals(itemSelected.getTopic()))return topicData.getData();
        }
        return null;
    }

    public void topicSelected(TopicData item) {
        itemSelected=item;
    }
}
