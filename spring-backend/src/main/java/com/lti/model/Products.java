package com.lti.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Products {
	@Id
	private int product_id;
	private String product_name;
	private int product_cost;
	private String display_size;
	private String resolution;
	private String os;
	private String processor;
	private String internal_memory;
	private String ram;
	private String no_of_camera;
	private String no_of_front_camera;
	private String usb;
	private String color;
	private String image;

	public Products() {

	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getProduct_cost() {
		return product_cost;
	}

	public void setProduct_cost(int product_cost) {
		this.product_cost = product_cost;
	}

	public String getDisplay_size() {
		return display_size;
	}

	public void setDisplay_size(String display_size) {
		this.display_size = display_size;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getProcessor() {
		return processor;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}

	public String getInternal_memory() {
		return internal_memory;
	}

	public void setInternal_memory(String internal_memory) {
		this.internal_memory = internal_memory;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getNo_of_camera() {
		return no_of_camera;
	}

	public void setNo_of_camera(String no_of_camera) {
		this.no_of_camera = no_of_camera;
	}

	public String getNo_of_front_camera() {
		return no_of_front_camera;
	}

	public void setNo_of_front_camera(String no_of_front_camera) {
		this.no_of_front_camera = no_of_front_camera;
	}

	public String getUsb() {
		return usb;
	}

	public void setUsb(String usb) {
		this.usb = usb;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Products [product_id=" + product_id + ", product_name=" + product_name + ", product_cost="
				+ product_cost + ", display_size=" + display_size + ", resolution=" + resolution + ", os=" + os
				+ ", processor=" + processor + ", internal_memory=" + internal_memory + ", ram=" + ram
				+ ", no_of_camera=" + no_of_camera + ", no_of_front_camera=" + no_of_front_camera + ", usb=" + usb
				+ ", color=" + color + ", image=" + image + "]";
	}
}
