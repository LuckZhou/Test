package com.core.echarts.charts.component.part;

public class AreaStackNormalItemStyle {
	
	public AreaStackNormalItemStyle(){
		
	}
	
	
    public AreaStackNormalItemStyle(AreaStyle areaStyle) {
		this.areaStyle = areaStyle;
	}


	private AreaStyle areaStyle=new AreaStyle();

	public AreaStyle getAreaStyle() {
		return areaStyle;
	}

	public void setAreaStyle(AreaStyle areaStyle) {
		this.areaStyle = areaStyle;
	}
    
}
