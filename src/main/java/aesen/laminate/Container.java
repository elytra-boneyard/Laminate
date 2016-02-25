package aesen.laminate;

import java.util.List;

import com.google.common.collect.Lists;

import aesen.laminate.component.Component;

/**
 * A collection of components that may or may not also be
 * a component itself.
 */
public class Container {
	protected int width;
	protected int height;
	
	protected final List<Component> children = Lists.newArrayList();
	
	protected void renderChildren(float partialTicks) {
		for (Component c : children) {
			if (c == null) continue;
			c.render(partialTicks);
		}
	}
	
	/**
	 * Add a Component to this Container, as a child.
	 * It will be rendered after (and therefore on top) of
	 * all current children.
	 */
	public void add(Component component) {
		children.add(component);
	}
	
	/**
	 * Remove a Component from this Container. If the
	 * passed Component isn't in this Container,
	 * nothing happens.
	 */
	public void remove(Component component) {
		children.remove(component);
	}
	
	/**
	 * Removes every child from this Container.
	 */
	public void clear() {
		children.clear();
	}
	
	/**
	 * Returns the amount of children in this Container.
	 */
	public int size() {
		return children.size();
	}
	
	/**
	 * Returns {@code true} if this Container has no
	 * children.
	 */
	public boolean isEmpty() {
		return children.isEmpty();
	}
	
	
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height;
	}
}
