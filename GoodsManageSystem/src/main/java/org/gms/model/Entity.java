package org.gms.model;

@SuppressWarnings("unused")
public abstract class Entity {
	
	private int id;
	private int version=0;
	private int activity=1;
	private String update_time;
}