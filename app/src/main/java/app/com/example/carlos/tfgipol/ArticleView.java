package app.com.example.carlos.tfgipol;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import dataCollector.URLImageCollector;

public class ArticleView extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    protected boolean a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }


    public static class DemoFragment extends Fragment {
        ImageView image1;
        String imageRoute;
        View view;
        private String selectedImagePath;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
            view = inflater.inflate(R.layout.fragment_demo, container, false);
            Button cameraButton = (Button) view.findViewById(R.id.camera_bttn);
            Button galleryButton = (Button) view.findViewById(R.id.gallery_bttn);
            image1 = (ImageView) view.findViewById(R.id.imageView1);
            cameraAction(cameraButton);
            galleryAction(galleryButton);
            try {
                URLImageCollector urlImage= new URLImageCollector("http://www.trbimg.com/img-5081fde9/turbine/la-et-cm-dan-castellaneta-homer-simpson-to-sta-001/599/599x337");
                System.out.println(urlImage.execute().get());
                image1.setImageBitmap(urlImage.getBitmapFromURL());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return view;
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode == 0) {
                //image1.setImageBitmap(BitmapFactory.decodeFile(imageRoute));
            }else if (requestCode == 1){
                System.out.println(data.getData());
                Uri selectedImageUri = data.getData();
            }
        }

        private void galleryAction(Button galleryButton) {
            galleryButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 1);
                }
            });

        }

        private void cameraAction(final Button cameraButton) {
            final String route = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/IPOL/";
            new File(route).mkdirs();
            cameraButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imageRoute = route + code();
                    File image = new File(imageRoute);
                    try{
                        image.createNewFile();
                    }catch (Exception ignore){}
                    Uri uri = Uri.fromFile(image);
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    startActivityForResult(cameraIntent, 0);
                }
            });

        }

        private String code(){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
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

    public void goToParameters(View view){
        Intent intent = new Intent(this, ParametersView.class);
        startActivity(intent);
    }
}
