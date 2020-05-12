package com.example.aybunews;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class fragment_haberler extends Fragment {

    private ListView listView;
    public ArrayList liste = new ArrayList();
    private ArrayAdapter<String> adapter;

    public fragment_haberler() {
        // Required empty public constructor
    }


    WebView webView;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_haberler,container,false);

        /*webView = v.findViewById(R.id.webView_haber);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://aybu.edu.tr/muhendislik/bilgisayar/");*/

        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, liste);
        listView = v.findViewById(R.id.listview_haber);

        new veriGetirHaber().execute();

        return v;
    }

    private class veriGetirHaber extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            liste.clear();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Document doc = Jsoup.connect("https://aybu.edu.tr/muhendislik/bilgisayar/").timeout(30*1000).get();

                Elements haberler = doc.select("div.cncItem");
                for(int i = 0; i < 5; i++){
                    liste.add(haberler.get(i).text());
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
