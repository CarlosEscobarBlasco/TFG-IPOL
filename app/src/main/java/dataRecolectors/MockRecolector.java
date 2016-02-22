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

        createTopic("3D", "An Implementation and Parallelization of the Scale Space Meshing Algorithm",
                "An Analysis and Implementation of a Parallel Ball Pivoting Algorithm",
                "Farman Institute 3D Point Sets - High Precision 3D Data Sets");

        createTopic("Blur", "Computing an Exact Gaussian Scale-Space",
                "A Survey of Gaussian Convolution Algorithms",
                "Total Variation Deconvolution using Split Bregman");

        createTopic("Calibration", "Automatic Lens Distortion Correction Using One-Parameter Division Models",
                "Recovering the Subpixel PSF from Two Photographs at Different Distances",
                "Non-parametric Sub-pixel Local Point Spread Function Estimation",
                "Algebraic Lens Distortion Model Estimation",
                "An Iterative Optimization Algorithm for Lens Distortion Correction Using Two-Parameter Models");

        createTopic("Color and Contrast","An Algorithmic Analysis of Variational Models for Perceptual Local Contrast Enhancement",
                "Multiscale Retinex",
                "Screened Poisson Equation for Image Contrast Enhancement",
                "Selective Contrast Adjustment by Poisson Equation",
                "Automatic Color Enhancement (ACE) and its Fast Implementation",
                "Color and Contrast Enhancement by Controlled Piecewise Affine Histogram Equalization",
                "Simplest Color Balance",
                "Local Color Correction",
                "Image Color Cube Dimensional Filtering and Visualization",
                "Retinex Poisson Equation: a Model for Color Perception",
                "Midway Image Equalization");

        createTopic("Computational Photography","The Flutter Shutter Code Calculator",
                "Obtaining High Quality Photographs of Paintings by Image Fusion",
                "The Flutter Shutter Camera Simulator");
        createTopic("Demosaicking",
                "A Demosaicking Algorithm with Adaptive Inter-Channel Correlation",
                "Implementation of Nonlocal Pansharpening Image Fusion",
                "Image Demosaicking with Contour Stencils",
                "Gunturk-Altunbasak-Mersereau Alternating Projections Image Demosaicking",
                "Zhang-Wu Directional LMMSE Image Demosaicking",
                "Malvar-He-Cutler Linear Image Demosaicking",
                "Self-similarity Driven Demosaicking");
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

    public void setTopicSelected(TopicData item) {
        itemSelected=item;
    }

    public TopicData getTopicSelected(){return itemSelected;}

    private void createTopic(String title, String... subTopics){
        ArrayList<SubTopicData> aux = new ArrayList<>();
        for (String description: subTopics){
            aux.add(new SubTopicData(description));
        }
        data.add(new TopicData(title,aux));
    }
}
