package com.example.recyclick;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.recyclick.API.APIRequestData;
import com.example.recyclick.API.serverRetrofit;
import com.example.recyclick.Model.Laporan.LineChartDataItem;
import com.example.recyclick.Model.Laporan.LineChartGetInfo;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class grafikgaris extends Fragment {

    public LineChart lChart;
    public List<LineChartDataItem> item = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_grafikgaris,container, false );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        lChart = view.findViewById(R.id.chartline);
        getChartData();
    }


    private void getChartData(){
        APIRequestData API = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
        Call<LineChartGetInfo> call = API.postGetChartData(setDateOnMonday());
        call.enqueue(new Callback<LineChartGetInfo>() {
            @Override
            public void onResponse(Call<LineChartGetInfo> call, Response<LineChartGetInfo> response) {
                if(response.isSuccessful() && response.body() != null){
                    item = response.body().getData();
                    setChartdata(item);
                }else{
                    Log.d("error", "onResponse: tidak ada data");
                }
            }

            @Override
            public void onFailure(Call<LineChartGetInfo> call, Throwable t) {
                View view = getLayoutInflater().inflate(R.layout.toast_no_internet, null);
                view.findViewById(R.id.toast_noConnection);
                Toast toast = new Toast(getContext());
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(view);
                toast.show();
                toast.setGravity(Gravity.TOP | Gravity.CENTER,0,0);
                Log.d("server error", "onFailure: "+t.getMessage());
            }
        });
    }

    private void setChartdata(List<LineChartDataItem> item){
        LineDataSet lineDataSet = new LineDataSet(datavalue(item), "Data Penghasilan Seminggu");
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        int color1 = ContextCompat.getColor(getContext(), R.color.green1);
        lineDataSet.setColor(color1);
        lineDataSet.setLineWidth(2);
        lineDataSet.setCircleSize(5);
        lineDataSet.setCircleColor(color1);
        Drawable draw = ContextCompat.getDrawable(getContext(), R.drawable.chart_gradientfill);
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFillDrawable(draw);



        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);

        LineData line  = new LineData(dataSets);
        lChart.setData(line);
        lChart.invalidate();
        lChart.getAxisLeft().setDrawGridLines(false);
        lChart.getAxisRight().setDrawGridLines(false);
        lChart.getXAxis().setDrawGridLines(false);
        lChart.animateX(1000);
        lChart.getAxisRight().setEnabled(false);

        ArrayList<String> Xlabel = new ArrayList<>();
        Xlabel.add(0, "Senin");
        Xlabel.add(1, "Selasa");
        Xlabel.add(2, "Rabu");
        Xlabel.add(3, "Kamis");
        Xlabel.add(4, "Jum'at");
        Xlabel.add(5, "Sabtu");
        Xlabel.add(6, "Minggu");


        XAxis xaxis = lChart.getXAxis();
        xaxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xaxis.setGranularity(1f);
//        xaxis.setLabelRotationAngle(+30);
        xaxis.setValueFormatter(new IndexAxisValueFormatter(Xlabel));


    }

    private ArrayList<Entry> datavalue(List<LineChartDataItem> item){
        ArrayList<Entry> datax = new ArrayList<>();
        for (int i = 0; i < item.size();i++){
            LineChartDataItem chartitem = item.get(i);
            datax.add(new Entry(i, Float.parseFloat(chartitem.getNilai())));
        }
        return datax;
    }


    public String setDateOnMonday() {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_WEEK);
        SimpleDateFormat dateformat = new SimpleDateFormat("dd");
        SimpleDateFormat dateformatmonthYear = new SimpleDateFormat("yyyy-MM");
        int date = Integer.parseInt(dateformat.format(cal.getTime()));
        switch (day) {
            case Calendar.MONDAY:
                break;
            case Calendar.TUESDAY:
                date -= 1;
                break;
            case Calendar.WEDNESDAY:
                date -= 2;
                break;
            case Calendar.THURSDAY:
                date -= 3;
                break;
            case Calendar.FRIDAY:
                date -= 4;
                break;
            case Calendar.SATURDAY:
                date -= 5;
                break;
            case Calendar.SUNDAY:
                date -= 6;
                break;
        }
        return dateformatmonthYear.format(cal.getTime())+"-"+date;
    }





}
