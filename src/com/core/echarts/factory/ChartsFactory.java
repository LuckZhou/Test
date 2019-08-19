package com.core.echarts.factory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.core.echarts.charts.AreaStackOptionBean;
import com.core.echarts.charts.BarLineMashupOptionBean;
import com.core.echarts.charts.BarOptionBean;
import com.core.echarts.charts.ChinaMapOptionBean;
import com.core.echarts.charts.FunnelOptionBean;
import com.core.echarts.charts.GaugeOptionBean;
import com.core.echarts.charts.LineOptionBean;
import com.core.echarts.charts.PieOptionBean;
import com.core.echarts.charts.RoseDiagramOptionBean;
import com.core.echarts.charts.ThermometerOptionBean;
import com.core.echarts.charts.component.AreaStackChartsDataBean;
import com.core.echarts.charts.component.BarToolTipBean;
import com.core.echarts.charts.component.ChartsAreaStackSeriesBean;
import com.core.echarts.charts.component.ChartsBarDataBean;
import com.core.echarts.charts.component.ChartsBarSeriesBean;
import com.core.echarts.charts.component.ChartsChinaMapToolTipBean;
import com.core.echarts.charts.component.ChartsDataBean;
import com.core.echarts.charts.component.ChartsFunnelSeriesBean;
import com.core.echarts.charts.component.ChartsGaugeSeriesBean;
import com.core.echarts.charts.component.ChartsLineDataBean;
import com.core.echarts.charts.component.ChartsLineSeriesBean;
import com.core.echarts.charts.component.ChartsMapSeriesBean;
import com.core.echarts.charts.component.ChartsPieSeriesBean;
import com.core.echarts.charts.component.ChartsRoseSeriesBean;
import com.core.echarts.charts.component.ChartsTitleBean;
import com.core.echarts.charts.component.ChartsToolBoxBean;
import com.core.echarts.charts.component.ChartsToolTipBean;
import com.core.echarts.charts.component.DataRange;
import com.core.echarts.charts.component.LegendBean;
import com.core.echarts.charts.component.MapDataBean;
import com.core.echarts.charts.component.PieDataBean;
import com.core.echarts.charts.component.RoamController;
import com.core.echarts.charts.component.ThermometerBean;
import com.core.echarts.charts.component.ThermometerSeriesBean;
import com.core.echarts.charts.component.XAxisBean;
import com.core.echarts.charts.component.YAxisBean;
import com.core.echarts.charts.component.part.AxisLabel;
import com.core.echarts.charts.component.part.DataView;
import com.core.echarts.charts.component.part.DataZoom;
import com.core.echarts.charts.component.part.Feature;
import com.core.echarts.charts.component.part.GaugeDetail;
import com.core.echarts.charts.component.part.Label;
import com.core.echarts.charts.component.part.MagicType;
import com.core.echarts.charts.component.part.MapItemStyle;
import com.core.echarts.charts.component.part.MapItemStyleEmphasis;
import com.core.echarts.charts.component.part.MapItemStyleNormal;
import com.core.echarts.charts.component.part.MapTypeControl;
import com.core.echarts.charts.component.part.Mark;
import com.core.echarts.charts.component.part.Restore;
import com.core.echarts.charts.component.part.SaveAsImage;
import com.core.echarts.charts.component.part.TextStyle;
import com.core.echarts.charts.component.part.ThermometerBaseItemStyle;
import com.core.echarts.charts.component.part.ThermometerItemStyle;
import com.core.echarts.factory.tool.Toolkit;

import net.sf.json.JSONObject;


/**
 * 创建图形工厂
 *
 *
 * @author LIBOYI
 * @date 2015-6-17
 * @version 2.0
 *
 */
public class  ChartsFactory {
	
   /**
    * 创建仪表盘
    * @return
    * @param text 仪表盘文本名称
    * @param fontSize 字体大小
    * @param value仪表盘的值
    */
   public static JSONObject createGauge(String text,int fontSize,String value){
	   GaugeOptionBean option=new GaugeOptionBean();
	   //设置标题
	   ChartsTitleBean title=new ChartsTitleBean();
	   title.setText(text);
	   TextStyle textStyle=new TextStyle();
	   textStyle.setFontSize(fontSize);
	   title.setTextStyle(textStyle);
	   option.setTitle(title);
	   //设置工具提示
	   ChartsToolTipBean tooltip=new ChartsToolTipBean();
	   tooltip.setFormatter("{c}%");
	   option.setTooltip(tooltip);
	   //设置工具栏
	   ChartsToolBoxBean toolbox=new ChartsToolBoxBean();
	   option.setToolbox(toolbox);
	   //设值
	   List<ChartsGaugeSeriesBean> series=new ArrayList<ChartsGaugeSeriesBean>();
	   ChartsGaugeSeriesBean gaugeSeries=new ChartsGaugeSeriesBean();
	   GaugeDetail detail=new GaugeDetail();
	   detail.setFormatter("{value}%");
	   gaugeSeries.setName("");
	   gaugeSeries.setDetail(detail);
	   String data[]=new String[1];
	   data[0]=value;
	   gaugeSeries.setData(data);
	   gaugeSeries.setType("gauge");
	   series.add(gaugeSeries);
	   option.setSeries(series);
	   JSONObject  jsonObject = JSONObject.fromObject(option);
	   return jsonObject;
   }
   /**
    * 创建柱状图/柱状堆积
    * @return
    * @param text标题
    * @param fontSize字体大小
    * @param xAxisName x轴名称
    * @param yAxisName y轴名称
    * @param yAxisUnit 单位 如"%"、"℃"之类
    * @param xData x轴值
    * @param dataList数据
    */
   public static JSONObject createBar(String text,int fontSize,String xAxisName, String xData[],
		  String yAxisName,String yAxisUnit,List<ChartsBarDataBean> dataList){
	   String trigger="axis";
	   String x="center";
	   String y="top";
	   boolean isShow=true;
	   String formatter="{value}"+yAxisUnit;
	   List<ChartsBarSeriesBean> series=new ArrayList<ChartsBarSeriesBean>();
	   //拿到图例
	   String legend[]=new String[dataList.size()];
	   if(!Toolkit.isEmpty(dataList)&&dataList.size()>0){
		   for (int i = 0; i < dataList.size(); i++) {
			   legend[i]=dataList.get(i).getName();
			   ChartsBarSeriesBean temp=new ChartsBarSeriesBean();
			   temp.setName(dataList.get(i).getName());
			   temp.setStack(dataList.get(i).getStack());
			   temp.setData(dataList.get(i).getData());
			   temp.setType("bar");
			   series.add(temp);
		}
		ChartsBarDataBean bar=dataList.get(0);
		if(bar.getData().length==1){
			y="bottom";
			trigger="item";
		}
	   }else{
			   series=new ArrayList<ChartsBarSeriesBean>();
			   ChartsBarSeriesBean seriesBean=new ChartsBarSeriesBean();
			   seriesBean.setData(new String[]{});
			   series.add(seriesBean);
	   }
	   BarOptionBean option =new BarOptionBean();
	   //设置标题
	   ChartsTitleBean title=new ChartsTitleBean();
	   title.setText(text);
	   TextStyle textStyle=new TextStyle();
	   textStyle.setFontSize(fontSize);
	   option.setTitle(title);
	   //设置工具提示
	   BarToolTipBean tooltip=new BarToolTipBean();
	   tooltip.setTrigger(trigger);
	   option.setTooltip(tooltip);
	   //设置工具栏
	   ChartsToolBoxBean toolbox=new ChartsToolBoxBean();
	   String type[]={"bar","line"};
	   Feature feature=new Feature();
	   feature.setMark(new Mark(true));
	   feature.setDataView(new DataView(true));
	   feature.setDataZoom(new DataZoom(false));
	   feature.setMagicType(new MagicType(true,type));
	   feature.setRestore(new Restore(true));
	   feature.setSaveAsImage(new SaveAsImage(true));
	   toolbox.setFeature(feature);
	   toolbox.setShow(isShow);
	   option.setToolbox(toolbox);
	   //设置图例
	   LegendBean legendBean=new LegendBean(x,y,legend);
	   option.setLegend(legendBean);
	   //设置x轴
	   XAxisBean xAxis=new XAxisBean();
	   xAxis.setName(xAxisName);
	   if(xData==null){
		   xData=new String[1];
		   xData[0]="";
	   }
	   xAxis.setData(xData);
	   option.setxAxis(xAxis);
	   //设置y轴
	   YAxisBean yAxis=new YAxisBean();
	   AxisLabel axisLabel=new AxisLabel(formatter);
	   yAxis.setAxisLabel(axisLabel);
	   yAxis.setName(yAxisName);
	   option.setyAxis(yAxis);
	   //数据
	   option.setSeries(series);
	   JSONObject  jsonObject = JSONObject.fromObject(option);
	   return jsonObject;
   }
   /**
    * 柱状和折线混搭图
    * @param text标题
    * @param fontSize字体大小
    * @param xAxisName x轴名称
    * @param y1AxisName y1轴名称
    * @param y2AxisName y2轴名称
    * @param xData x轴值
    * @param series数据
    * 柱状图 设置name和data两个属性，支持堆积，若堆积设置stack
    * 折线图除设置name和data外，还需设置type为line
    * @param y1Unit,y2Unit 单位 如"%"、"℃"之类
    */
   @Deprecated  
   public static JSONObject createMashupOne(String text,int fontSize,String xAxisName, String xData[],
			  String y1AxisName,String y1Unit,String y2AxisName,String y2Unit,List<ChartsBarSeriesBean> series){
	   BarLineMashupOptionBean option=new BarLineMashupOptionBean();
	   String y1Formatter="{value}"+y1Unit;
	   String y2Formatter="{value}"+y2Unit;
	   //拿到图例
	   String legend[]=new String[series.size()];
	   if(!Toolkit.isEmpty(series)&&series.size()>0){
		   for (int i = 0; i < series.size(); i++) {
			   legend[i]=series.get(i).getName();
		}
	   }else{
		   series=new ArrayList<ChartsBarSeriesBean>();
		   ChartsBarSeriesBean seriesBean=new ChartsBarSeriesBean();
		   seriesBean.setData(new String[]{});
		   series.add(seriesBean);
	   }
	   //设置标题
	   ChartsTitleBean title=new ChartsTitleBean();
	   title.setText(text);
	   TextStyle textStyle=new TextStyle();
	   textStyle.setFontSize(fontSize);
	   option.setTitle(title);
	   //设置工具提示
	   BarToolTipBean tooltip=new BarToolTipBean();
	   tooltip.setTrigger("axis");
	   option.setTooltip(tooltip);
	   //设置工具栏
	   ChartsToolBoxBean toolbox=new ChartsToolBoxBean(true);
	   String type[]={"bar","line"};
	   Feature feature=new Feature();
	   feature.setMark(new Mark(true));
	   feature.setDataView(new DataView(true));
	   feature.setDataZoom(new DataZoom(false));
	   feature.setMagicType(new MagicType(true,type));
	   feature.setRestore(new Restore(true));
	   feature.setSaveAsImage(new SaveAsImage(true));
	   toolbox.setFeature(feature);
	   option.setToolbox(toolbox);
	   //设置图例
	   LegendBean legendBean=new LegendBean("center","top",legend);
	   option.setLegend(legendBean);
	   //设置x轴
	   XAxisBean xAxis=new XAxisBean();
	   xAxis.setName(xAxisName);
	   if(xData==null){
		   xData=new String[1];
		   xData[0]="";
	   }
	   xAxis.setData(xData);
	   option.setxAxis(xAxis);
	   //设置y轴
	   List<YAxisBean> yAxis=new ArrayList<YAxisBean>();
	   YAxisBean y1=new YAxisBean();
	   y1.setName(y1AxisName);
	   AxisLabel y1AxisLabel=new AxisLabel(y1Formatter);
	   y1.setAxisLabel(y1AxisLabel);
	   yAxis.add(y1);
	   YAxisBean y2=new YAxisBean();
	   y2.setName(y2AxisName);
	   AxisLabel y2AxisLabel=new AxisLabel(y2Formatter);
	   y2.setAxisLabel(y2AxisLabel);
	   yAxis.add(y2);
	   option.setyAxis(yAxis);
	   //数据
	   option.setSeries(series);
	   JSONObject  jsonObject = JSONObject.fromObject(option);
	   return jsonObject;
   }
   /**
    * 柱状和折线混搭图
    * @param text标题
    * @param fontSize字体大小
    * @param xAxisName x轴名称
    * @param y1AxisName y1轴名称
    * @param y2AxisName y2轴名称
    * @param xData x轴值
    * @param barList 柱状图数据
    * @param lineList 折线图数据
    * 柱状图 设置name和data两个属性，支持堆积，若堆积设置stack
    * 折线图除设置name和data外，还需设置type为line
    * @param y1Unit,y2Unit 单位 如"%"、"℃"之类
    */
   public static JSONObject createMashupOne2(String text,int fontSize,String xAxisName, String xData[],
			  String y1AxisName,String y1Unit,String y2AxisName,String y2Unit,List<ChartsBarDataBean> barList,List<ChartsLineDataBean> lineList){
	   List<ChartsBarSeriesBean> series=new ArrayList<ChartsBarSeriesBean>();
	   if(!Toolkit.isEmpty(barList)&&barList.size()>0){
		   for (ChartsBarDataBean barDataBean : barList) {
			   ChartsBarSeriesBean barSeries=new ChartsBarSeriesBean();
			   barSeries.setData(barDataBean.getData());
			   barSeries.setName(barDataBean.getName());
			   barSeries.setStack(barDataBean.getStack());
			   series.add(barSeries);
		   }
	   }
	   if(!Toolkit.isEmpty(lineList)&&lineList.size()>0){
		   for (ChartsLineDataBean lineDataBean : lineList) {
			   ChartsBarSeriesBean barSeries=new ChartsBarSeriesBean();
			   barSeries.setData(lineDataBean.getData());
			   barSeries.setName(lineDataBean.getName());
			   barSeries.setType("line");
			   barSeries.setyAxisIndex(1);
			   series.add(barSeries);
		   }
	   }
	
	   BarLineMashupOptionBean option=new BarLineMashupOptionBean();
	   String y1Formatter="{value}"+y1Unit;
	   String y2Formatter="{value}"+y2Unit;
	   //拿到图例
	   String legend[]=new String[series.size()];
	   if(!Toolkit.isEmpty(series)&&series.size()>0){
		   for (int i = 0; i < series.size(); i++) {
			   legend[i]=series.get(i).getName();
		}
	   }else{
		   series=new ArrayList<ChartsBarSeriesBean>();
		   ChartsBarSeriesBean seriesBean=new ChartsBarSeriesBean();
		   seriesBean.setData(new String[]{});
		   series.add(seriesBean);
	   }
	   //设置标题
	   ChartsTitleBean title=new ChartsTitleBean();
	   title.setText(text);
	   TextStyle textStyle=new TextStyle();
	   textStyle.setFontSize(fontSize);
	   option.setTitle(title);
	   //设置工具提示
	   BarToolTipBean tooltip=new BarToolTipBean();
	   tooltip.setTrigger("axis");
	   option.setTooltip(tooltip);
	   //设置工具栏
	   ChartsToolBoxBean toolbox=new ChartsToolBoxBean(true);
	   String type[]={"bar","line"};
	   Feature feature=new Feature();
	   feature.setMark(new Mark(true));
	   feature.setDataView(new DataView(true));
	   feature.setDataZoom(new DataZoom(false));
	   feature.setMagicType(new MagicType(true,type));
	   feature.setRestore(new Restore(true));
	   feature.setSaveAsImage(new SaveAsImage(true));
	   toolbox.setFeature(feature);
	   option.setToolbox(toolbox);
	   //设置图例
	   LegendBean legendBean=new LegendBean("center","top",legend);
	   option.setLegend(legendBean);
	   //设置x轴
	   XAxisBean xAxis=new XAxisBean();
	   xAxis.setName(xAxisName);
	   if(xData==null){
		   xData=new String[1];
		   xData[0]="";
	   }
	   xAxis.setData(xData);
	   option.setxAxis(xAxis);
	   //设置y轴
	   List<YAxisBean> yAxis=new ArrayList<YAxisBean>();
	   YAxisBean y1=new YAxisBean();
	   y1.setName(y1AxisName);
	   AxisLabel y1AxisLabel=new AxisLabel(y1Formatter);
	   y1.setAxisLabel(y1AxisLabel);
	   yAxis.add(y1);
	   YAxisBean y2=new YAxisBean();
	   y2.setName(y2AxisName);
	   AxisLabel y2AxisLabel=new AxisLabel(y2Formatter);
	   y2.setAxisLabel(y2AxisLabel);
	   yAxis.add(y2);
	   option.setyAxis(yAxis);
	   //数据
	   option.setSeries(series);
	   JSONObject  jsonObject = JSONObject.fromObject(option);
	   return jsonObject;
   }
   /**
    * 创建折线图
    * @return
    * @param text标题
    * @param fontSize字体大小
    * @param xAxisName x轴名称
    * @param yAxisName y轴名称
    * @param xData x轴值
    * @param dataList值
    * @param unit单位 如"%"、"℃"之类
    */
   public static JSONObject createLine(String text,int fontSize,String xAxisName,String xData[],
		  String yAxisName,String unit,List<ChartsLineDataBean> dataList){
	   List<ChartsLineSeriesBean> series=new ArrayList<ChartsLineSeriesBean>();
	   //拿到图例
	   String legend[]=new String[dataList.size()];
	   if(!Toolkit.isEmpty(dataList)&&dataList.size()>0){
		   for (int i = 0; i < dataList.size(); i++) {
			   legend[i]=dataList.get(i).getName();
			   ChartsLineSeriesBean temp=new ChartsLineSeriesBean();
			   temp.setName(dataList.get(i).getName());
			   temp.setData(dataList.get(i).getData());
			   temp.setType("line");
			   series.add(temp);
		}
	   }else{
		   series=new ArrayList<ChartsLineSeriesBean>();
		   ChartsLineSeriesBean seriesBean=new ChartsLineSeriesBean();
		   seriesBean.setData(new String[]{});
		   series.add(seriesBean);
	   }
	 
	   LineOptionBean option =new LineOptionBean();
	   //设置标题
	   ChartsTitleBean title=new ChartsTitleBean();
	   title.setText(text);
	   TextStyle textStyle=new TextStyle();
	   textStyle.setFontSize(fontSize);
	   option.setTitle(title);
	   //设置工具提示
	   BarToolTipBean tooltip=new BarToolTipBean("axis");
	   option.setTooltip(tooltip);
	   //设置工具栏
	   ChartsToolBoxBean toolbox=new ChartsToolBoxBean(true);
	   String type[]={"line","bar"};
	   Feature feature=new Feature();
	   feature.setMark(new Mark(true));
	   feature.setDataView(new DataView(true));
	   feature.setDataZoom(new DataZoom(false));
	   feature.setMagicType(new MagicType(true,type));
	   feature.setRestore(new Restore(true));
	   feature.setSaveAsImage(new SaveAsImage(true));
	   toolbox.setFeature(feature);
	   option.setToolbox(toolbox);
	   //设置图例
	   LegendBean legendBean=new LegendBean("center","top",legend);
	   option.setLegend(legendBean);
	   //设置x轴
	   XAxisBean xAxis=new XAxisBean();
	   xAxis.setName(xAxisName);
	   if(xData==null){
		   xData=new String[1];
		   xData[0]="";
	   }
	   xAxis.setData(xData);
	   option.setxAxis(xAxis);
	   //设置y轴
	   YAxisBean yAxis=new YAxisBean();
	   yAxis.setName(yAxisName);
	   String formatter="{value} "+unit;
	   AxisLabel axisLabel=new AxisLabel(formatter);
	   yAxis.setAxisLabel(axisLabel);
	   option.setyAxis(yAxis);
	   //数据
	   option.setSeries(series);
	   JSONObject  jsonObject = JSONObject.fromObject(option);
	   return jsonObject;
   }
   
   /**
    * 创建饼图
    * @return
    * @param text 标题
    * @param fontSize字体大小
    * @param x 图例水平安放位置，默认为全图居中，可选为：'center' | 'left' | 'right'
    * @param y 图例垂直安放位置，默认为全图顶端，可选为：'top' | 'bottom' | 'center'
    * @param radius半径 格式50%
    * @param data饼图数据
    * 
    */
   public static JSONObject createPie(String text,int fontSize,String x,String y,
		String radius,List<PieDataBean> data){
	   String radiusArray[]=new String[2];
	   radiusArray[0]="0%";
	   radiusArray[1]=radius;
	   JSONObject  jsonObject = createPieAndRingJson(text,fontSize,x,y,radiusArray,data);
	   return jsonObject;
   }
   /**
    * 创建环形图
    * @return
    * @param text 标题
    * @param fontSize字体大小
    * @param x 图例水平安放位置，默认为全图居中，可选为：'center' | 'left' | 'right'
    * @param y 图例垂直安放位置，默认为全图顶端，可选为：'top' | 'bottom' | 'center'
    * @param innerRadius内半径，格式20%
    * @param outerRadius外半径，格式30%
    * @param data环形图数据
    */
   public static JSONObject createRing(String text,int fontSize,String x,String y,
		String innerRadius,String outerRadius,List<PieDataBean> data){
	   String radiusArray[]=new String[2];
	   radiusArray[0]=innerRadius;
	   radiusArray[1]=outerRadius;
	   JSONObject  jsonObject = createPieAndRingJson(text,fontSize,x,y,radiusArray,data);
	   return jsonObject;
   }
   /**
    * 饼图和环形图生成json
    * @param text 标题
    * @param fontSize字体大小
    * @param x 图例水平安放位置，默认为全图居中，可选为：'center' | 'left' | 'right'
    * @param y 图例垂直安放位置，默认为全图顶端，可选为：'top' | 'bottom' | 'center'
    * @param radiusArray[]半径数组，[内半径，外半径] 格式{"50%", "70%"}
    */
   private static JSONObject createPieAndRingJson(String text,int fontSize,String x,String y,
			String radiusArray[],List<PieDataBean> data){
	   PieOptionBean option =new PieOptionBean();
	   //设置标题
	   ChartsTitleBean title=new ChartsTitleBean();
	   title.setText(text);
	   TextStyle textStyle=new TextStyle();
	   textStyle.setFontSize(fontSize);
	   option.setTitle(title);
	   //设置工具提示
	   ChartsToolTipBean tooltip=new ChartsToolTipBean();
	   tooltip.setFormatter("{a} <br/>{b} : {c} ({d}%)");
	   tooltip.setTrigger("item");
	   option.setTooltip(tooltip);
	   //设置工具栏
	   ChartsToolBoxBean toolbox=new ChartsToolBoxBean(true);
	   String type[]={"pie", "funnel"};
	   Feature feature=new Feature(new Mark(false),new DataView(true),
				new MagicType(true,type),new  Restore(true),new  SaveAsImage(true));
	   toolbox.setFeature(feature);
	   option.setToolbox(toolbox);
	   //设置图例
	   LegendBean legendBean=new LegendBean(x,y);
	   String[] legendData=new String[data.size()];
	   if(!Toolkit.isEmpty(data)&&data.size()>0){
		   for (int i = 0; i < data.size(); i++) {
			   legendData[i]=data.get(i).getName();
		}
	   }
	   legendBean.setData(legendData);
	   option.setLegend(legendBean);
	   List<ChartsPieSeriesBean> series=new ArrayList<ChartsPieSeriesBean>();
	   ChartsPieSeriesBean ringSeries=new ChartsPieSeriesBean();
	   
	   //数据
	   ringSeries.setName(text);
	   ringSeries.setType("pie");
	   ringSeries.setRadius(radiusArray);
	   ringSeries.setData(data);
	   series.add(ringSeries);
	   option.setSeries(series);
	   return JSONObject.fromObject(option);
   }

   /**
    * 标准面积图/堆积面积图
    * @param text 标题
    * @param fontSize
    * @param xAxisName x轴名称
    * @param yAxisName y轴名称
    * @param xData x轴值
    * @param unit单位 如"%"、"℃"之类
    * @param dataList数据
    * @return
    */
   public static JSONObject createAreaStack(String text,int fontSize,String xAxisName,String xData[],
			  String yAxisName,String unit,List<AreaStackChartsDataBean> dataList){
	   AreaStackOptionBean option=new AreaStackOptionBean();
	   List<ChartsAreaStackSeriesBean> series=new ArrayList<ChartsAreaStackSeriesBean>();
	   //设置标题
	   ChartsTitleBean title=new ChartsTitleBean();
	   title.setText(text);
	   TextStyle textStyle=new TextStyle();
	   textStyle.setFontSize(fontSize);
	   option.setTitle(title);
	   //设置图例
	   LegendBean legendBean=new LegendBean();
	   String[] legendData=new String[dataList.size()];
	   if(!Toolkit.isEmpty(dataList)&&dataList.size()>0){
		   for (int i = 0; i < dataList.size(); i++) {
			   legendData[i]=dataList.get(i).getName();
			   ChartsAreaStackSeriesBean temp=new ChartsAreaStackSeriesBean();
			   temp.setData(dataList.get(i).getData());
			   temp.setName(dataList.get(i).getName());
			   temp.setStack(dataList.get(i).getStack());
			   temp.setType("line");
			   series.add(temp);
			   
		}
	   }else{
		   ChartsAreaStackSeriesBean seriesBean=new ChartsAreaStackSeriesBean();
		   seriesBean.setData(new String[]{});
		   series.add(seriesBean);
	   }
	   legendBean.setData(legendData);
	   option.setLegend(legendBean);
	   //设置x轴
	   List<XAxisBean> xAxis=new ArrayList<XAxisBean>();
	   XAxisBean x1=new XAxisBean(true);
	   x1.setName(xAxisName);
	   if(xData==null){
		   xData=new String[1];
		   xData[0]="";
	   }
	   x1.setData(xData);
	   xAxis.add(x1);
	   option.setxAxis(xAxis);
	   //设置y轴
	   List<YAxisBean> yAxis=new ArrayList<YAxisBean>();
	   YAxisBean y1=new YAxisBean();
	   y1.setName(yAxisName);
	   String formatter="{value} "+unit;
	   AxisLabel axisLabel=new AxisLabel(formatter);
	   y1.setAxisLabel(axisLabel);
	   yAxis.add(y1);
	   option.setyAxis(yAxis);
	   //设置工具栏
	   ChartsToolBoxBean toolbox=new ChartsToolBoxBean();
	   Feature feature=new Feature(new Mark(false),new DataZoom(false),new DataView(true),
				new MagicType(true,new String[]{"line","bar","stack","tiled"}),new  Restore(true),new SaveAsImage(true));
	   toolbox.setFeature(feature);
	   option.setToolbox(toolbox);
	   //数据
	   option.setSeries(series);
	   JSONObject  jsonObject = JSONObject.fromObject(option);
	   return jsonObject;
   }
   
   /**
    * 温度计式图表
    * @param text 标题
    * @param fontSize
    * @param xAxisName x轴名称
    * @param yAxisName y轴名称
    * @param xData x轴值
    * @param unit单位 如"%"、"℃"之类
    * @param thermometerList数据
    * @return
    */
   public static JSONObject createTermometer(String text,int fontSize,String xAxisName,String xData[],
			  String yAxisName,String unit,List<ThermometerBean> thermometerList){
	   ThermometerOptionBean option=new ThermometerOptionBean();
	   //设置标题
	   ChartsTitleBean title=new ChartsTitleBean();
	   title.setText(text);
	   TextStyle textStyle=new TextStyle();
	   textStyle.setFontSize(fontSize);
	   option.setTitle(title);
	   //设置工具栏
	   ChartsToolBoxBean toolbox=new ChartsToolBoxBean();
	   Feature feature=new Feature(new Mark(true),new DataZoom(false),new DataView(true),
				new MagicType(false),new Restore(true),new SaveAsImage(true));
	   toolbox.setFeature(feature);
	   option.setToolbox(toolbox);
	   //设置图例
	   LegendBean legendBean=new LegendBean();
	   String[] legendData=new String[thermometerList.size()];
	   if(!Toolkit.isEmpty(thermometerList)&&thermometerList.size()>0){
		   for (int i = 0; i < thermometerList.size(); i++) {
			   legendData[i]=thermometerList.get(i).getName();
		}
	   }
	   legendBean.setSelectedMode(true);
	   legendBean.setData(legendData);
	   option.setLegend(legendBean);
	   //设置x轴
	   List<XAxisBean> xAxis=new ArrayList<XAxisBean>();
	   XAxisBean x1=new XAxisBean(true);
	   x1.setName(xAxisName);
	   if(xData==null){
		   xData=new String[1];
		   xData[0]="";
	   }
	   x1.setData(xData);
	   xAxis.add(x1);
	   option.setxAxis(xAxis);
	   //设置y轴
	   List<YAxisBean> yAxis=new ArrayList<YAxisBean>();
	   YAxisBean y1=new YAxisBean();
	   y1.setName(yAxisName);
	   String formatter="{value} "+unit;
	   AxisLabel axisLabel=new AxisLabel(formatter);
	   y1.setAxisLabel(axisLabel);
	   yAxis.add(y1);
	   option.setyAxis(yAxis);
	   List<ThermometerSeriesBean> series=new ArrayList<ThermometerSeriesBean>();
	   if(!Toolkit.isEmpty(thermometerList)&&thermometerList.size()>1){
		   ThermometerBean thermometer= thermometerList.get(0);
		   ThermometerSeriesBean temp=new ThermometerSeriesBean();
		   temp.setName(thermometer.getName());
		   temp.setData(thermometer.getData());
		   temp.setStack("stack");
		   ThermometerItemStyle itemStyle=new ThermometerItemStyle(new ThermometerBaseItemStyle("tomato",
				   new Label("insideTop",new TextStyle("#fff"))));
		   temp.setItemStyle(itemStyle);
		   temp.setType("bar");
		   series.add(temp);
		   
		   ThermometerBean thermometerBean= thermometerList.get(1);
		   ThermometerSeriesBean tempBean=new ThermometerSeriesBean();
		   tempBean.setName(thermometerBean.getName());
		   tempBean.setData(thermometerBean.getData());
		   tempBean.setStack("stack");
		   tempBean.setType("bar");
		   ThermometerItemStyle itemStyleBean=new ThermometerItemStyle(new ThermometerBaseItemStyle("#fff",
				   new Label("top",new TextStyle("tomato"))));
		   tempBean.setItemStyle(itemStyleBean);
		   series.add(tempBean);
	   }
	   //数据
	   option.setSeries(series);
	   JSONObject  jsonObject = JSONObject.fromObject(option);
	   return jsonObject;
   }
   /**
    * 创建漏斗图
    * @return
    * @param text 标题
    * @param fontSize 字体大小
    * @param dataList漏斗图的值
    */
   public static JSONObject createFunnel(String text,int fontSize,List<ChartsDataBean> dataList){
	   FunnelOptionBean option=new FunnelOptionBean();
	   //设置标题
	   ChartsTitleBean title=new ChartsTitleBean();
	   title.setText(text);
	   TextStyle textStyle=new TextStyle();
	   textStyle.setFontSize(fontSize);
	   title.setTextStyle(textStyle);
	   option.setTitle(title);
	   //设置图例
	   LegendBean legendBean=new LegendBean();
	   String[] legendData=new String[dataList.size()];
	   if(!Toolkit.isEmpty(dataList)&&dataList.size()>0){
		   for (int i = 0; i < dataList.size(); i++) {
			   legendData[i]=dataList.get(i).getName();
		}
	   }
	 
	   legendBean.setX("center");
	   legendBean.setY("bottom"); 
	   legendBean.setSelectedMode(true);
	   legendBean.setData(legendData);
	   option.setLegend(legendBean);
	   //设置工具提示
	   ChartsToolTipBean tooltip=new ChartsToolTipBean("item","{a} <br/>{b} : {c}");
	   option.setTooltip(tooltip);
	   //设置工具栏
	   ChartsToolBoxBean toolbox=new ChartsToolBoxBean(true,new Feature(new Mark(false),
			   new DataView(true),new MagicType(false),new Restore(true),new SaveAsImage(true)));
	   toolbox.setShow(true);
	   option.setToolbox(toolbox);
	   //数据
	   List<ChartsFunnelSeriesBean> series=new ArrayList<ChartsFunnelSeriesBean>();
	   ChartsFunnelSeriesBean funnelSeries=new ChartsFunnelSeriesBean();
	   funnelSeries.setName(text);
	   funnelSeries.setData(dataList);
	   funnelSeries.setType("funnel");
	   series.add(funnelSeries);
	   option.setSeries(series);
	   JSONObject  jsonObject =JSONObject.fromObject(option);
	   return jsonObject;
   }
   /**
    * 创建玫瑰图
    * @return
    * @param text标题
    * @param fontSize字体大小
    * 
    */
   public static JSONObject createRose(String text,int fontSize,
		List<PieDataBean> data){
	   RoseDiagramOptionBean option =new RoseDiagramOptionBean();
	   //设置标题
	   ChartsTitleBean title=new ChartsTitleBean();
	   title.setText(text);
	   TextStyle textStyle=new TextStyle();
	   textStyle.setFontSize(fontSize);
	   option.setTitle(title);
	   //设置工具提示
	   ChartsToolTipBean tooltip=new ChartsToolTipBean();
	   tooltip.setFormatter("{a} <br/>{b} : {c} ({d}%)");
	   tooltip.setTrigger("item");
	   option.setTooltip(tooltip);
	   //设置工具栏
	   ChartsToolBoxBean toolbox=new ChartsToolBoxBean(true);
	   String type[]={"pie", "funnel"};
	   Feature feature=new Feature(new Mark(false),new DataView(true),
				new MagicType(true,type),new  Restore(true),new  SaveAsImage(true));
	   toolbox.setFeature(feature);
	   option.setToolbox(toolbox);
	   //设置图例
	   LegendBean legendBean=new LegendBean("center","bottom");
	   String[] legendData=new String[data.size()];
	   if(!Toolkit.isEmpty(data)&&data.size()>0){
		   for (int i = 0; i < data.size(); i++) {
			   legendData[i]=data.get(i).getName();
		}
	   }
	   legendBean.setData(legendData);
	   option.setLegend(legendBean);
	   List<ChartsRoseSeriesBean> series=new ArrayList<ChartsRoseSeriesBean>();
	   ChartsRoseSeriesBean roseSeries=new ChartsRoseSeriesBean();
	   //数据
	   String radius[]={"20","110"};
	   roseSeries.setName(text);
	   roseSeries.setType("pie");
	   roseSeries.setRadius(radius);
	   roseSeries.setRoseType("area");
	   roseSeries.setData(data);
	   series.add(roseSeries);
	   option.setSeries(series);
	   JSONObject  jsonObject = JSONObject.fromObject(option);
	   return jsonObject;
   }
   /**
    * 创建中国地图
    * @return
    * @param text柱状图标题
    * @param fontSize字体大小
    * 
    */
   public static JSONObject createChinaMap(String text,int fontSize,List<MapDataBean> data){
	   ChinaMapOptionBean option =new ChinaMapOptionBean();
	   List<ChartsMapSeriesBean> series=new ArrayList<ChartsMapSeriesBean>();
	   String[] legendData=new String[data.size()];
	   List<ChartsDataBean> valueList=new ArrayList<ChartsDataBean>(); 
	   int dataRangeMax=0;
	   if(!Toolkit.isEmpty(data)&&data.size()>0){
		   for (int i = 0; i < data.size(); i++) {
			   legendData[i]=data.get(i).getName();
			   ChartsMapSeriesBean seriesBean=new ChartsMapSeriesBean();
			   seriesBean.setMapType("china");
			   seriesBean.setType("map");
			   seriesBean.setName(data.get(i).getName());
			   seriesBean.setData(data.get(i).getData());
			   valueList.addAll(data.get(i).getData());
			   MapItemStyle itemStyle=new MapItemStyle();
			   MapItemStyleNormal normal=new MapItemStyleNormal();
			   MapItemStyleEmphasis emphasis=new MapItemStyleEmphasis();
			   itemStyle.setNormal(normal);
			   itemStyle.setEmphasis(emphasis);
			   seriesBean.setItemStyle(itemStyle);
			   series.add(seriesBean);
		   }
		   int valueArray[]=new int[valueList.size()];
		   for (int i = 0; i < valueList.size(); i++) {
			   valueArray[i]=Integer.parseInt(valueList.get(i).getValue());
		   }
		   Arrays.sort(valueArray);
		   dataRangeMax=valueArray[valueArray.length-1];
	   }
	   //设置标题
	   ChartsTitleBean title=new ChartsTitleBean();
	   title.setText(text);
	   title.setX("center");
	   TextStyle textStyle=new TextStyle();
	   textStyle.setFontSize(fontSize);
	   option.setTitle(title);
	   //设置工具提示
	   ChartsChinaMapToolTipBean tooltip=new ChartsChinaMapToolTipBean();
	   tooltip.setTrigger("item");
	   option.setTooltip(tooltip);
	   //设置工具栏
	   ChartsToolBoxBean toolbox=new ChartsToolBoxBean(true);
	   String type[]={""};
	   Feature feature=new Feature(new Mark(true),new DataView(true),
				new MagicType(false,type),new  Restore(true),new  SaveAsImage(true));
	   toolbox.setFeature(feature);
	   toolbox.setX("right");
	   toolbox.setY("center");
	   toolbox.setOrient("vertical");
	   option.setToolbox(toolbox);
	   //值域漫游
	   DataRange dataRange=new DataRange();
	   dataRange.setX("left");
	   dataRange.setY("bottom");
	   dataRange.setCalculable(true);
	   dataRange.setMin(0);
	   dataRange.setMax(dataRangeMax);
	   option.setDataRange(dataRange);
	   //指定漫游组件可控地图类型
	   RoamController roamController=new RoamController();
	   roamController.setShow(true);
	   roamController.setX("right");
	   MapTypeControl mapTypeControl=new MapTypeControl();
	   mapTypeControl.setChina(true);
	   roamController.setMapTypeControl(mapTypeControl);
	   option.setRoamController(roamController);
	   //设置图例
	   LegendBean legend=new LegendBean();
	   legend.setOrient("vertical");
	   legend.setX("left");
	   legend.setData(legendData);
	   option.setLegend(legend);
	   //设置数据
	   option.setSeries(series);
	   JSONObject  jsonObject = JSONObject.fromObject(option);
	   return jsonObject;
   }
}
