package aesen.laminate.shadowbox;

/**
 * A background for a Screen. Can range from extremely simple,
 * like a solid color, to something complex like an end portal
 * background or main-menu-esque panorama.
 *
 * @see PanoramaShadowbox
 * @see SolidShadowbox
 * @see EndShadowbox
 * @see TextureShadowbox
 */
public abstract class Shadowbox {
	public abstract void render(float partialTicks);
	public void tick() {
		// not all subclasses will need this method
	}
}
