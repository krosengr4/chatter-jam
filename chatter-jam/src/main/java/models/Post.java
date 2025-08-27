package models;

import java.time.LocalDate;

public class Post {

	int postId;
	String title;
	String content;
	String author;
	LocalDate datePosted;

	public Post(int postId, String title, String content, String author, LocalDate datePosted) {
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.author = author;
		this.datePosted = datePosted;
	}

	//region Setters and Getters
	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public LocalDate getDatePosted() {
		return datePosted;
	}

	public void setDatePosted(LocalDate datePosted) {
		this.datePosted = datePosted;
	}
	//endregion

	//todo: Create method for posts to be printed
	public void printData() {
		System.out.printf("""
										-----POST ID: %d-----
										  By: %s
									   ------------------------
				
										  Title: %s
							--------------------------------------------------------
				%s
		
				""", this.postId, this.author, this.title, this.content);
		System.out.println("Date Posted: " + this.datePosted);
		System.out.println("\n\n");
	}
}
