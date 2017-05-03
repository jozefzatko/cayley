package sk.zatko.cayley.models;

import java.awt.Color;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Edge data model
 * 
 * @author Jozef Zaťko
 */
public class EdgeData {

	private String id;
	private Color color;
	private Vertex source;
	private Vertex target;
	private String label;
	
	public EdgeData(String id, Color color, Vertex source, Vertex target, String label) {

		this.id = id;
		this.color = color;
		this.source = source;
		this.target = target;
		this.label = label;
	}

	@JsonProperty("color")
	public String serializeColor() {
		return String.format("#%02x%02x%02x", this.color.getRed(), this.color.getGreen(), this.getColor().getBlue());
	}
	
	@JsonProperty("source")
	public String serializeSource() {
		return this.source.getData().getId();
	}
	
	@JsonProperty("target")
	public String serializeTarget() {
		return this.target.getData().getId();
	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Vertex getSource() {
		return source;
	}

	public void setSource(Vertex source) {
		this.source = source;
	}

	public Vertex getTarget() {
		return target;
	}

	public void setTarget(Vertex target) {
		this.target = target;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
