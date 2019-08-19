package com.core.echarts.charts.component.part;
/**
 * Echarts 设置十字准星指示器
 *
 *
 * @author LIBOYI
 * @date 2015-5-26
 * @version 1.0
 *
 */
public class CrossStyle {
   private String color;    //颜色
   private int width;    //线宽
   private String type="solid";  //线条样式，可选为：'solid' | 'dotted' | 'dashed' ，默认solid
   private String shadowColor="rgba(0,0,0,0)"; //折线主线(IE8+)有效，阴影色彩，支持rgba,默认rgba(0,0,0,0) 
   private int shadowBlur=5;                   //折线主线(IE8+)有效，阴影模糊度，大于0有效 ，默认5
   private int shadowOffsetX=3;                //折线主线(IE8+)有效，阴影横向偏移，正值往右，负值往左 ，默认3
   private int shadowOffsetY=3;                //折线主线(IE8+)有效，阴影纵向偏移，正值往下，负值往上 ,默认3
   public CrossStyle(){
	   
   }
   
public CrossStyle(String color, int width, String type, String shadowColor,
		int shadowBlur, int shadowOffsetX, int shadowOffsetY) {
	this.color = color;
	this.width = width;
	this.type = type;
	this.shadowColor = shadowColor;
	this.shadowBlur = shadowBlur;
	this.shadowOffsetX = shadowOffsetX;
	this.shadowOffsetY = shadowOffsetY;
}

public CrossStyle(String color, int width, String type) {
	this.color = color;
	this.width = width;
	this.type = type;
}

public String getColor() {
	return color;
}
public void setColor(String color) {
	this.color = color;
}
public int getWidth() {
	return width;
}
public void setWidth(int width) {
	this.width = width;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}

public String getShadowColor() {
	return shadowColor;
}

public int getShadowBlur() {
	return shadowBlur;
}

public int getShadowOffsetX() {
	return shadowOffsetX;
}

public int getShadowOffsetY() {
	return shadowOffsetY;
}
   
}
