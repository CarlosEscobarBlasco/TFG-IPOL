package internetConexion;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Carlos on 04/03/2016.
 */
public class MockJSONCollector {

    public JSONObject getJSON(){
        return getExample2();
    }

    private JSONObject getExample1(){
        try {
            return new JSONObject("{ \n" +
                    "  'general': { \n" +
                    "     'demo_title':             'Non-Local Means Denoising',\n" +
                    "     'demo_email':              'asalgado@dis.ulpgc.es',\n" +
                    "     'demo_input_description': 'To use correctly this demo, it is advised to upload good quality noiseless images. The algorithm will add to the image a white noise with the standard deviation you will select. The denoising algorithm uses only the knowledge of the standard deviation of the noise.',\n" +
                    "     'demo_params_description': 'The algorithm is run in 2 steps:   <ol>  <li> a Gaussian noise is added to the input image; </li> <li> the NLmeans algorithm is used to denoise the image. </li> </ol>'\n" +
                    "  }, \n" +
                    "  'test': [ 'valldemossa', 'book', 'alley', 'trees', 'gardens' ], \n" +
                    "  'preprocess': [\n" +
                    "     {\n" +
                    "       'action':      'crop'\n" +
                    "     }\n" +
                    "  ],\n" +
                    "  'params': [\n" +
                    "    {\n" +
                    "      'id'            : 'sigma',\n" +
                    "      'type'          : 'float', \n" +
                    "      'label'         : 'Select additive Gaussian noise:',\n" +
                    "      'default_value' : '2:2, 5:5, 10:10, 15:15, 20:20, 25:25, 30:30, 35:35, 40:40',\n" +
                    "      'type_format'   : 'selection_collapsed'\n" +
                    "    },\n" +
                    "    {\n" +
                    "      'id'            : 'alpha',\n" +
                    "      'type'          : 'float', \n" +
                    "      'label'         : 'Alpha: ',\n" +
                    "      'tooltip'       : '(weight of the regularization term, e.g. &alpha;=1 discontinuous flow, &alpha;=40 smooth flow)',\n" +
                    "      'default_value' : '15'\n" +
                    "    },\n" +
                    "    {\n" +
                    "      'id'            : 'sigma',\n" +
                    "      'type'          : 'float', \n" +
                    "      'label'         : 'Beta',\n" +
                    "      'default_value' : '2:2, 5:5, 10:10, 15:15, 20:20, 25:25, 30:30, 35:35, 40:40',\n" +
                    "      'type_format'   : 'text_slider'\n" +
                    "    }\n" +
                    "  ]}");
        } catch (JSONException e) {}
        return null;
    }

    private JSONObject getExample2(){
        try {
            return new JSONObject("{ \n" +
                    "  'general': { \n" +
                    "     'demo_title':             'Non-Local Means Denoising',\n" +
                    "     'demo_email':              'asalgado@dis.ulpgc.es',\n" +
                    "     'demo_input_description': 'To use correctly this demo, it is advised to upload good quality noiseless images. The algorithm will add to the image a white noise with the standard deviation you will select. The denoising algorithm uses only the knowledge of the standard deviation of the noise.',\n" +
                    "     'demo_params_description': 'The algorithm is run in 2 steps:   <ol>  <li> a Gaussian noise is added to the input image; </li> <li> the NLmeans algorithm is used to denoise the image. </li> </ol>'\n" +
                    "  }, \n" +
                    "  'test': [ 'valldemossa', 'book', 'alley', 'trees', 'gardens' ], \n" +
                    "  'preprocess': [\n" +
                    "     {\n" +
                    "       'action':      'crop'\n" +
                    "     }\n" +
                    "  ],\n" +
                    "  'params': [\n" +
                    "    {\n" +
                    "      'id'            : 'sigma',\n" +
                    "      'type'          : 'float', \n" +
                    "      'label'         : 'Select additive Gaussian noise:',\n" +
                    "      'default_value' : '2:2, 5:5, 10:10, 15:15, 20:20, 25:25, 30:30, 35:35, 40:40',\n" +
                    "      'type_format'   : 'selection_collapsed'\n" +
                    "    },\n" +
                    "    {\n" +
                    "      'id'            : 'alpha',\n" +
                    "      'type'          : 'float', \n" +
                    "      'label'         : 'Alpha: ',\n" +
                    "      'tooltip'       : '(weight of the regularization term, e.g. &alpha;=1 discontinuous flow, &alpha;=40 smooth flow)',\n" +
                    "      'default_value' : '15'\n" +
                    "    },\n" +
                    "    {\n" +
                    "      'id'            : 'sigma',\n" +
                    "      'type'          : 'float', \n" +
                    "      'label'         : 'Beta',\n" +
                    "      'default_value' : '2:2, 5:5, 10:10, 15:15, 20:20, 25:25, 30:30, 35:35, 40:40',\n" +
                    "      'type_format'   : 'text_slider'\n" +
                    "    },\n" +
                    "    {\n" +
                    "      'id'            : 'alpha',\n" +
                    "      'type'          : 'float', \n" +
                    "      'label'         : 'Standard deviation: ',\n" +
                    "      'tooltip'       : '(weight of the regularization term, e.g. &alpha;=1 discontinuous flow, &alpha;=40 smooth flow)',\n" +
                    "      'default_value' : '15'\n" +
                    "    },\n" +
                    "    {\n" +
                    "      'id'            : 'alpha',\n" +
                    "      'type'          : 'float', \n" +
                    "      'label'         : 'Output/input size ratio: ',\n" +
                    "      'tooltip'       : '(weight of the regularization term, e.g. &alpha;=1 discontinuous flow, &alpha;=40 smooth flow)',\n" +
                    "      'default_value' : '15'\n" +
                    "    },\n" +
                    "    {\n" +
                    "      'id'            : 'sigma',\n" +
                    "      'type'          : 'float', \n" +
                    "      'label'         : 'Select additive Gaussian noise:',\n" +
                    "      'default_value' : '2:2, 5:5, 10:10, 15:15, 20:20, 25:25, 30:30, 35:35, 40:40',\n" +
                    "      'type_format'   : 'selection_collapsed'\n" +
                    "    },\n" +
                    "    {\n" +
                    "      'id'            : 'sigma',\n" +
                    "      'type'          : 'float', \n" +
                    "      'label'         : 'select a level:',\n" +
                    "      'default_value' : '2:2, 5:5, 10:10, 15:15, 20:20, 25:25, 30:30, 35:35, 40:40',\n" +
                    "      'type_format'   : 'text_slider'\n" +
                    "    },\n" +
                    "    {\n" +
                    "      'id'            : 'alpha',\n" +
                    "      'type'          : 'float', \n" +
                    "      'label'         : 'Scale: ',\n" +
                    "      'tooltip'       : '(weight of the regularization term, e.g. &alpha;=1 discontinuous flow, &alpha;=40 smooth flow)',\n" +
                    "      'default_value' : '15'\n" +
                    "    }\n" +
                    "  ]}");
        } catch (JSONException e) {}
        return null;
    }


}
