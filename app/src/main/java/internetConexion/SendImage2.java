package internetConexion;

import android.content.Context;
import android.graphics.Bitmap;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import model.AppController;

/**
 * Created by Carlos on 15/06/2016.
 */
public class SendImage2 {

    private String urlDirection;
    private Context context;

    public SendImage2(String demoID, Context context) {
        this.context = context;
        createURL(demoID);
    }

    private void createURL(String demoID) {
        this.urlDirection ="http://dev.ipol.im/~asalgado/ipol_demo_interpreter/"+demoID+"/mobile_upload_kk";
    }

    public void execute(){
            String charset = "UTF-8";
            File file = new File(context.getCacheDir(), "imageToSend");
            //File uploadFile2 = new File("e:/Test/PIC2.JPG");
            String requestURL = urlDirection;

            try {
                OutputStream os = new BufferedOutputStream(new FileOutputStream(file));
                AppController.getInstance().getSelectedImage().compress(Bitmap.CompressFormat.PNG, 100, os);
                os.close();
                MultipartUtility multipart = new MultipartUtility(requestURL, charset);

                //multipart.addHeaderField("User-Agent", "CodeJava");
               // multipart.addHeaderField("Test-Header", "Header-Value");

                multipart.addFormField("sigma", AppController.getInstance().getParam());
                //multipart.addFormField("keywords", "Java,upload,Spring");

                multipart.addFilePart("file_0",file);
                //multipart.addFilePart("fileUpload", uploadFile2);

                List<String> response = multipart.finish();

                System.out.println("SERVER REPLIED:");

                for (String line : response) {
                    AppController.getInstance().setKey(line);
                    System.out.println(line);
                }
            } catch (IOException ex) {
                System.err.println(ex);
            }

    }

}
