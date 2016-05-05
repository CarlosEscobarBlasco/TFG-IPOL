package app.com.example.carlos.tfgipol;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import internetConexion.URLImageCollector;
import model.AppController;

public class ArticleView extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    static protected ContentResolver resolver;
    static protected ArticleView a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        resolver = this.getContentResolver();
        a=this;
    }


    public static class DemoFragment extends Fragment {
        private static final int CAMERA_REQUEST = 1888;
        private final int GALLERY_REQUEST_CODE = 2222;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_demo, container, false);
            Button cameraButton = (Button) view.findViewById(R.id.camera_bttn);
            Button galleryButton = (Button) view.findViewById(R.id.gallery_bttn);
            cameraAction(cameraButton);
            galleryAction(galleryButton);
            setExampleImage((ImageView) view.findViewById(R.id.imageView1), 1);
            setExampleImage((ImageView) view.findViewById(R.id.imageView2),2);
            setExampleImage((ImageView) view.findViewById(R.id.imageView3),3);
            return view;
        }

        private void setExampleImage(final ImageView image,int number)  {
            URLImageCollector urlImage = new URLImageCollector("http://dev.ipol.im/~asalgado/ipol_demo_interpreter/input/"+ AppController.getInstance().getDemoName()+"/"+number+".png","demo","demo");
            try {
                urlImage.execute().get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            image.setImageBitmap(urlImage.getBitmapFromURL());
            image.buildDrawingCache();
        }


        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                //AppController.getInstance().setSelectedExampleImageBitmap(photo);
                a.goToParametersWithOwnImage(photo);
            }
            else if (data != null && requestCode == GALLERY_REQUEST_CODE) {
                try {
                    Uri imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(resolver,imageUri);
                    a.goToParametersWithOwnImage(bitmap);
                    //AppController.getInstance().setSelectedExampleImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void galleryAction(Button galleryButton) {
            galleryButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Intent galleryIntent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent,GALLERY_REQUEST_CODE);
                }
            });
        }

        private void cameraAction(final Button cameraButton) {
            cameraButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            });
        }

        private String code(){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss", Locale.getDefault());
            String date = dateFormat.format(new Date());
            return "pic_" + date+".jpg";
        }
    }

    public static class ArticleFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_article, container, false);
            TextView textView = (TextView) view.findViewById(R.id.abstract_text);
            textView.setMovementMethod(new ScrollingMovementMethod());
            return view;
        }
    }

    public static class FragmentSelector {
        public static Fragment loadFragment(int sectionNumber) {
            Fragment fragment = sectionNumber==0?new DemoFragment():new ArticleFragment();
            Bundle args = new Bundle();
            fragment.setArguments(args);
            return fragment;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        private String[] tabs = {"DEMO","ARTICLE"};

        @Override
        public Fragment getItem(int position) {
            return FragmentSelector.loadFragment(position);
        }

        @Override
        public int getCount() {
            return tabs.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }
    }

    public void goToMenu(View v){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void goToParametersWithExample(View view){
        AppController.getInstance().setSelectedExampleImage(view.getTag().toString());
        AppController.getInstance().setSelectedExampleImageBitmap(view.getDrawingCache());
        System.out.println(view.getDrawingCache());
        Intent intent = new Intent(this, ParametersView.class);
        startActivity(intent);
    }

    public void goToParametersWithOwnImage(Bitmap bitmap){
        AppController.getInstance().setSelectedExampleImage("1");
        AppController.getInstance().setSelectedExampleImageBitmap(bitmap);
        Intent intent = new Intent(this, ParametersView.class);
        startActivity(intent);
    }
}
