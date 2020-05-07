package com.notesapp.pojos;

import java.util.Date;


public class Notes {

	private long id;
	private String noteName;
	private String noteDescription;
	private Category categoryId;
	private Date createdDateTime;
	private String createdBy;
	private Date modifiedDateTime;
	private String modifiedBy;
	
	public static final String ID = "id";
	public static final String Note_NAME = "note_name";
	public static final String Note_Description = "note_description";
	public static final String CATEGORY_ID = "category_Id";
	public static final String CREATED_DATETIME = "created_datetime";
	public static final String CREATED_BY = "created_by";
	public static final String MODIFIED_DATETIME = "modified_datetime";
	public static final String MODIFIED_BY = "modified_by";
	
	public Notes(Builder builder) {
		this.id = builder.id;
		this.noteName = builder.noteName;
		this.noteDescription = builder.noteDescription;
		this.categoryId = builder.categoryId;
		this.createdDateTime = builder.createdDateTime;
		this.createdBy = builder.createdBy;
		this.modifiedDateTime = builder.modifiedDateTime;
		this.modifiedBy = builder.modifiedBy;
	}
	
	public static class Builder{
		
		private long id;
		private String noteName;
		private String noteDescription;
		private Category categoryId;
		private Date createdDateTime;
		private String createdBy;
		private Date modifiedDateTime;
		private String modifiedBy;
		
		public Builder id(long id) {
			 this.id = id;
			 return this;
		}

		public Builder noteName(String noteName) {
			 this.noteName = noteName;
			 return this;
		}
		
		public Builder noteDescription(String noteDescription) {
			 this.noteDescription = noteDescription;
			 return this;
		}
		
		public Builder categoryId(Category categoryId) {
			 this.categoryId = categoryId;
			 return this;
		}
		
		public Builder createdDateTime(Date createdDateTime) {
			 this.createdDateTime = createdDateTime;
			 return this;
		}
		
		public Builder createdBy(String createdBy) {
			 this.createdBy = createdBy;
			 return this;
		}
		
		public Builder modifiedDateTime(Date modifiedDateTime) {
			 this.modifiedDateTime = modifiedDateTime;
			 return this;
		}
		
		public Builder modifiedBy(String modifiedBy) {
			 this.modifiedBy = modifiedBy;
			 return this;
		}
		
		public Notes build() {
		     return new Notes(this);
		 }
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNoteName() {
		return noteName;
	}

	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}

	public String getNoteDescription() {
		return noteDescription;
	}

	public void setNoteDescription(String noteDescription) {
		this.noteDescription = noteDescription;
	}

	public Category getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Category categoryId) {
		this.categoryId = categoryId;
	}

	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifiedDateTime() {
		return modifiedDateTime;
	}

	public void setModifiedDateTime(Date modifiedDateTime) {
		this.modifiedDateTime = modifiedDateTime;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	
}
