package com.core.echarts.charts.component;

import com.core.echarts.charts.component.part.AxisLine;
import com.core.echarts.charts.component.part.GaugeDetail;
import com.core.echarts.charts.component.part.LineStyle;


/**
 *  series（仪表盘） 
 *
 *
 *
 * @author LIBOYI
 * @date 2015-5-26
 * @version 1.0
 *
 */
public class ChartsGaugeSeriesBean extends ChartsSeriesBean{
   /**
    * 仪表盘详情 
    */
   private GaugeDetail detail=new GaugeDetail();
   
   private String []data; //数据  
   String color[][]={{"0.2","#ff4500"},{"0.8","#48b"},{"1","#228b22"}};
   private AxisLine axisLine=new AxisLine(true,new LineStyle(color));


public GaugeDetail getDetail() {
	return detail;
}

public void setDetail(GaugeDetail detail) {
	this.detail = detail;
}

public String[] getData() {
	return data;
}

public void setData(String[] data) {
	this.data = data;
}

public String[][] getColor() {
	return color;
}

public void setColor(String[][] color) {
	this.color = color;
}

public AxisLine getAxisLine() {
	return axisLine;
}

public void setAxisLine(AxisLine axisLine) {
	this.axisLine = axisLine;
}
   
   
}
