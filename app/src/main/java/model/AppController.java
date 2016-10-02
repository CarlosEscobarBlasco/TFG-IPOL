package model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * Created by Carlos on 28/04/2016.
 */
public class AppController {

    private static AppController instance;
    private String key;
    private String demoName;
    private String selectedExampleImageNumber;
    private String param;
    private Bitmap selectedImage;
    private String[] resultNames;

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

    public void setSelectedExampleImageNumber(String selectedExampleImageNumber) {
        this.selectedExampleImageNumber = selectedExampleImageNumber;
    }

    public String getSelectedExampleImageNumber() {
        return selectedExampleImageNumber;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getParam() {
        return param;
    }

    public Bitmap getSelectedImage() {
        return selectedImage;
    }

    public void setSelectedImage(Bitmap selectedImage) {
        this.selectedImage = selectedImage;
    }

    public String[] getResultNames() {
        return resultNames;
    }

    public void setResultNames(String[] resultNames) {
        this.resultNames = resultNames;
    }

}
