package dataRecolectors;

import java.util.ArrayList;

import model.rowData;

/**
 * Created by Carlos on 13/02/2016.
 */
public class MockRecolector  {

    private static MockRecolector instance;
    private ArrayList<rowData> data = new ArrayList<>();

    protected MockRecolector (){
        data.add(new rowData("Lorem ipsum"));
        data.add(new rowData("dolor sit amet"));
        data.add(new rowData("consectetur adipiscing elit"));
        data.add(new rowData("sed do eiusmod"));
        data.add(new rowData("tempor incididunt"));
        data.add(new rowData("ut labore"));
        data.add(new rowData("et dolore"));
        data.add(new rowData("magna aliqua"));
        data.add(new rowData("Ut enim"));
        data.add(new rowData("ad minim"));
        data.add(new rowData("veniam quis"));
        data.add(new rowData("nostrud exercitation"));
    }

    public static MockRecolector getInstance(){
        if(instance==null)instance= new MockRecolector();
        return instance;
    }

    public ArrayList<rowData> getData() {
        return data;
    }
}
