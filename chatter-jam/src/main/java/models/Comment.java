package models;

import java.time.LocalDate;

public class Comment {

	int commentId;
	int postId;
	String content;
	String author;
	LocalDate datePosted;

	public Comment(int commentId, int postId, String content, String author, LocalDate datePosted) {
		this.commentId = commentId;
		this.postId = postId;
		this.content = content;
		this.author = author;
		this.datePosted = datePosted;
	}

	//region Setters and Getters
	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
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

	//todo: Create method for comment data to print out
}
