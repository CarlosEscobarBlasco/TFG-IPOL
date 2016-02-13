package dataRecolectors;

import java.util.ArrayList;

/**
 * Created by Carlos on 13/02/2016.
 */
public class MockRecolector extends DataRecolector {

    @Override
    public ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        data.add("Lorem ipsum");
        data.add("dolor sit amet");
        data.add("consectetur adipiscing elit");
        data.add("sed do eiusmod");
        data.add("tempor incididunt");
        data.add("ut labore");
        data.add("et dolore");
        data.add("magna aliqua");
        data.add("Ut enim");
        return data;
    }
}
