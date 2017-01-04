package com.livroandroid.carros.fragments.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.livroandroid.carros.R;

import org.w3c.dom.Text;

import livroandroid.lib.utils.AndroidUtils;

/**
 * Created by Antonio on 04/01/2017.
 */

public class AboutDialog extends DialogFragment {

    public static void showAbout(FragmentManager fragmentManager) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        Fragment prev = fragmentManager.findFragmentByTag("dialog_about");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        new AboutDialog().show(ft, "dialog_about");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Cria o html com o texto de sobre
        SpannableStringBuilder aboutBody = new SpannableStringBuilder();

        // Versão do app
        String versionName = AndroidUtils.getVersionName(getActivity());

        // Converte texto em HTML
        aboutBody.append(Html.fromHtml(getString(R.string.about_dialog_text, versionName)));

        // Infla o layout
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        TextView textView = (TextView)inflater.inflate(R.layout.dialog_about, null);
        textView.setText(aboutBody);
        textView.setMovementMethod(new LinkMovementMethod());

        // Cria o dialog customizado
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.about_dialog_title)
                .setView(textView)
                .setPositiveButton(R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                .create();
    }
}
