package com.example.aybunews;

import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class fragment_duyurular extends Fragment {

    private WebView webView = null;
    private ListView listView;
    public ArrayList listeDuyuru = new ArrayList();
    private ArrayAdapter<String> adapter;
    String URL = "https://aybu.edu.tr/muhendislik/bilgisayar/";

    public fragment_duyurular() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    /*public static fragment_duyurular newInstance(String param1, String param2) {
        fragment_duyurular fragment = new fragment_duyurular();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //new Duyurular().execute();

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_duyurular,container,false);

        /*webView = v.findViewById(R.id.webView_duyuru);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://aybu.edu.tr/muhendislik/bilgisayar/");*/

        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listeDuyuru);
        listView = v.findViewById(R.id.listview_duyuru);

        new veriGetirDuyuru().execute();

        return v;
    }

    private class veriGetirDuyuru extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            listeDuyuru.clear();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Document doc = Jsoup.connect(URL).timeout(30*1000).get();

                /*Element element = doc.getElementById("ContentPlaceHolder1_ctl02_rpData_hplink_0");
                Elements yemekler = element.getElementsByTag("a");*/
                Element element;
                Elements yemekler = null;

                for(int i = 0; i < 6; i++){
                     element = doc.getElementById("ContentPlaceHolder1_ctl02_rpData_hplink_" + i);
                     yemekler = element.getElementsByTag("a");
                     listeDuyuru.add(yemekler.text());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            listView.setAdapter(adapter);
        }

    }
}
