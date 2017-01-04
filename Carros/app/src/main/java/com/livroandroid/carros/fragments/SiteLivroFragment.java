package com.livroandroid.carros.fragments;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.livroandroid.carros.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SiteLivroFragment extends BaseFragment {

    private static final String URL_SOBRE = "http://www.livroandroid.com.br/sobre.htm";
    private WebView webView;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_site_livro, container, false);
        this.webView = (WebView)view.findViewById(R.id.webview);
        this.progressBar = (ProgressBar)view.findViewById(R.id.progress);

        setWebViewClient(this.webView);

        // Carrega a página
        this.webView.loadUrl(URL_SOBRE);

        return view;
    }

    private void setWebViewClient(WebView webView) {
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }
}
