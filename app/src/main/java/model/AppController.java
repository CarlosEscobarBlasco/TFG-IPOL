package model;

import android.graphics.Bitmap;

/**
 * Created by Carlos on 28/04/2016.
 */
public class AppController {

    private static AppController instance;
    private String key;
    private String demoName;
    private String selectedExampleImage;
    private String param;
    private Bitmap selectedExampleImageBitmap;

    private AppController(){

    }

    public static AppController getInstance(){
        if(instance==null)instance = new AppController();
        return instance;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDemoName() {
        return demoName;
    }

    public void setDemoName(String demoName) {
        this.demoName = demoName;
    }

    public void setSelectedExampleImage(String selectedExampleImage) {
        this.selectedExampleImage = selectedExampleImage;
    }

    public String getSelectedExampleImage() {
        return selectedExampleImage;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getParam() {
        return param;
    }

    public Bitmap getSelectedExampleImageBitmap() {
        return selectedExampleImageBitmap;
    }

    public void setSelectedExampleImageBitmap(Bitmap selectedExampleImageBitmap) {
        this.selectedExampleImageBitmap = selectedExampleImageBitmap;
    }
}
