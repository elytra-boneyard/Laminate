package aesen.laminate;

public final class Colors { 

	public static int toByte(float f) {
		return ((int)(f*255f))&0xFF;
	}

	public static int pack(float r, float g, float b, float a) {
		return toByte(a) << 24 |
				toByte(r) << 16 |
				toByte(g) << 8 |
				toByte(b);
	}
	
	public static float unpackR(int packed) {
		return ((packed >> 16) & 0xFF) / 255f;
	}
	
	public static float unpackG(int packed) {
		return ((packed >> 8) & 0xFF) / 255f;
	}
	
	public static float unpackB(int packed) {
		return (packed & 0xFF) / 255f;
	}
	
	public static float unpackA(int packed) {
		return (packed >> 24) / 255f;
	}
	
	public static boolean isTranslucent(int color) {
		return (color >> 24) < 255;
	}
	
	public static int shadow(int color) {
		return (color & 0xFCFCFC) >> 2 | color & 0x1000000;
	}
	
	private Colors() {}
}
