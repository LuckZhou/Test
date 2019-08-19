package com.core.echarts.charts.component.part;
/**
 * Echarts启用功能还原设置
 *
 *
 * @author LIBOYI
 * @date 2015-5-26
 * @version 1.0
 *
 */
public class Restore {
   private boolean show=false;
   public Restore(){
	   
   }
   
public Restore(boolean show) {
	this.show = show;
}
public boolean isShow() {
	return show;
}
public void setShow(boolean show) {
	this.show = show;
}
   
   
}
