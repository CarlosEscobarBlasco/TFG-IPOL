package model;

/**
 * Created by Carlos on 28/04/2016.
 */
public class AppController {

    private static AppController instance;
    private String key;
    private String demoName;
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
}
