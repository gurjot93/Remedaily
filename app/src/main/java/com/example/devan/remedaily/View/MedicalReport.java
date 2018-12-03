//using library from github. This library is available at https://github.com/barteksc/AndroidPdfViewer
// using the sample medical report from the website https://ocfp.on.ca/docs/collaborative-mental-health-care-network/ministry-of-transport-medical-condition-report-form.pdf?sfvrsn=118b852e_2
package com.example.devan.remedaily.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.devan.remedaily.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.FitPolicy;

public class MedicalReport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_report);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        PDFView pdfView=(PDFView)findViewById(R.id.pdfView);
        pdfView.fromAsset("sample_report.pdf").pageFitPolicy(FitPolicy.WIDTH).load();
    }

    // source : https://stackoverflow.com/questions/10108774/how-to-implement-the-android-actionbar-back-button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
