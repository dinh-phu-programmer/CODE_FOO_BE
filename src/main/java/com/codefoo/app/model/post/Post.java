package com.codefoo.app.model.post;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "Post")
@Table(name = "post")
public class Post {
	private final String classType = "Post";
	
}
