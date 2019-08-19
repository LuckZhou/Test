package com.core.echarts.charts;

import java.util.List;

import com.core.echarts.charts.component.ChartsPieSeriesBean;
import com.core.echarts.charts.component.ChartsTitleBean;
import com.core.echarts.charts.component.ChartsToolBoxBean;
import com.core.echarts.charts.component.ChartsToolTipBean;
import com.core.echarts.charts.component.LegendBean;


/**
 * 标准环形图
 *
 *
 * @author LIBOYI
 * @date 2015-5-27
 * @version 1.0
 *
 */
public class PieOptionBean {
	/**
	 * 是否启用拖拽重计算特性，默认启用
	 */
     private boolean calculable=true;
     
     private List<ChartsPieSeriesBean> series;
     
     private ChartsToolBoxBean toolbox;
     
     /**
      * 标题
      */
     private ChartsTitleBean title=new ChartsTitleBean();
     
     /**
      * 工具提示
      */
     private ChartsToolTipBean tooltip=new ChartsToolTipBean();
     
     /**
      * 图例
      */
     private LegendBean legend=new LegendBean("center", "bottom", "horizontal");
     
	public boolean isCalculable() {
		return calculable;
	}

	public void setCalculable(boolean calculable) {
		this.calculable = calculable;
	}



	public LegendBean getLegend() {
		return legend;
	}

	public void setLegend(LegendBean legend) {
		this.legend = legend;
	}

   


	public List<ChartsPieSeriesBean> getSeries() {
		return series;
	}

	public ChartsToolBoxBean getToolbox() {
		return toolbox;
	}

	public void setToolbox(ChartsToolBoxBean toolbox) {
		this.toolbox = toolbox;
	}

	public void setSeries(List<ChartsPieSeriesBean> series) {
		this.series = series;
	}

	public ChartsTitleBean getTitle() {
		return title;
	}

	public void setTitle(ChartsTitleBean title) {
		this.title = title;
	}

	public ChartsToolTipBean getTooltip() {
		return tooltip;
	}

	public void setTooltip(ChartsToolTipBean tooltip) {
		this.tooltip = tooltip;
	}

     

}
