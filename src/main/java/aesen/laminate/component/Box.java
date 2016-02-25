package aesen.laminate.component;

import aesen.laminate.Rendering;

public class Box extends Component {
	private int color = 0xFFFFFFFF;
	
	@Override
	protected void doRender(float partialTicks) {
		Rendering.drawRect(0, 0, width, height, color);
	}
	
	public void setColor(int color) {
		this.color = color;
	}
	
	public int getColor() {
		return color;
	}
	
}
