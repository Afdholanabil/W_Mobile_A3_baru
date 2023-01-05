package com.example.recyclick;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.recyclick.API.APIRequestData;
import com.example.recyclick.API.serverRetrofit;
import com.example.recyclick.Model.Laporan.BarChartDataItem;
import com.example.recyclick.Model.Laporan.BarChartGetInfo;
import com.example.recyclick.Model.Laporan.LineChartDataItem;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class grafikbatang extends Fragment {

    public BarChart barchart;
    List<BarChartDataItem> item = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_grafikbatang, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        barchart = view.findViewById(R.id.barchart1);
        int color1 = ContextCompat.getColor(getContext(), R.color.green1);
        barchart.setNoDataText("Loading...");
        barchart.setNoDataTextColor(color1);
        getChartData();
    }

    private void getChartData(){
        APIRequestData api = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
        Call<BarChartGetInfo> call = api.getBarChartData();
        call.enqueue(new Callback<BarChartGetInfo>() {
            @Override
            public void onResponse(Call<BarChartGetInfo> call, Response<BarChartGetInfo> response) {
                if(response.isSuccessful() && response.body()!= null){
                    item = response.body().getData();
                    setBarchart(item);
                    Log.d("TAG", "onResponse: "+response.body().getKode());
                }else {
                    Log.d(response.body().getMessage(), "onResponseGrafikBatang: ");
                }
            }

            @Override
            public void onFailure(Call<BarChartGetInfo> call, Throwable t) {

            }
        });
    }


    private void setBarchart(List<BarChartDataItem> itembar){
        BarDataSet barDataSet = new BarDataSet(monthDataValue(itembar), "");
        barDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        int color1 = ContextCompat.getColor(getContext(), R.color.green1);
        int color2 = ContextCompat.getColor(getContext(), R.color.green2);
        int color3 = ContextCompat.getColor(getContext(), R.color.green3);
        int color4 = ContextCompat.getColor(getContext(), R.color.green4);
        int color5 = ContextCompat.getColor(getContext(), R.color.green5);
        int color6 = ContextCompat.getColor(getContext(), R.color.oren2);
        barDataSet.setColors(new int[] {color5, color1, color2, color3, color4,
                color6, color6, color4, color3, color2, color1, color5});

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(barDataSet);

        BarData bar = new BarData(dataSets);
        bar.setBarWidth(0.5f);

        barchart.setData(bar);
        barchart.invalidate();
        barchart.getAxisLeft().setDrawGridLines(false);
        barchart.getAxisRight().setDrawGridLines(false);
        barchart.getXAxis().setDrawGridLines(false);
        barchart.getAxisRight().setEnabled(false);
        barchart.animateX(1000);
        barchart.animateY(500);
        barchart.setDragEnabled(true);
        barchart.setVisibleXRange(8f, 1f);
        barchart.getDescription().setEnabled(false);
        barchart.getLegend().setEnabled(false);
        barchart.setExtraBottomOffset(12);

        String[] bulan = {"Jan", "Feb", "Mar", "Apr", "Mei", "Jun", "Jul",
                "Ags", "Sep", "Okt", "Nov", "Des"};
        XAxis xaxis = barchart.getXAxis();
        xaxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xaxis.setGranularity(1f);
        xaxis.setTextSize(8f);

        xaxis.setLabelRotationAngle(+40);

        xaxis.setValueFormatter(new IndexAxisValueFormatter(bulan));
    }



    private ArrayList<BarEntry> monthDataValue(List<BarChartDataItem> itembar){
        ArrayList<BarEntry> datax = new ArrayList<>();
        for (int i = 0; i < itembar.size();i++){
            BarChartDataItem chartitem = itembar.get(i);
            datax.add(new BarEntry(i, Float.parseFloat(chartitem.getNilai())));
        }
        return datax;
    }


}
