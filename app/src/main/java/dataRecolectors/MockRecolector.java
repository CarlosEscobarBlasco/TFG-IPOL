package dataRecolectors;

import java.util.ArrayList;

import model.RowData;

/**
 * Created by Carlos on 13/02/2016.
 */
public class MockRecolector  {

    private static MockRecolector instance;
    private ArrayList<RowData> data = new ArrayList<>();

    protected MockRecolector (){
        data.add(new RowData("Lorem ipsum"));
        data.add(new RowData("dolor sit amet"));
        data.add(new RowData("consectetur adipiscing elit"));
        data.add(new RowData("sed do eiusmod"));
        data.add(new RowData("tempor incididunt"));
        data.add(new RowData("ut labore"));
        data.add(new RowData("et dolore"));
        data.add(new RowData("magna aliqua"));
        data.add(new RowData("Ut enim"));
        data.add(new RowData("ad minim"));
        data.add(new RowData("veniam quis"));
        data.add(new RowData("nostrud exercitation"));
    }

    public static MockRecolector getInstance(){
        if(instance==null)instance= new MockRecolector();
        return instance;
    }

    public ArrayList<RowData> getData() {return data;}

    public RowData getObjectByName(String name){
        for (RowData object:data) {
            if (object.getText().equals(name))return object;
        }
        return null;
    }

}
