package objects;

import java.util.Date;

public class Category {
	
	private long id;
	private String categoryName;
	private Date createdDateTime;
	private String createdBy;
	private Date modifiedDateTime;
	private String modifiedBy;
	
	public static final String ID = "id";
	public static final String CATEGORY_NAME = "category_name";
	public static final String CREATED_DATETIME = "created_datetime";
	public static final String CREATED_BY = "created_by";
	public static final String MODIFIED_DATETIME = "modified_datetime";
	public static final String MODIFIED_BY = "modified_by";
	
	public Category(Builder builder) {
		this.id = builder.id;
		this.categoryName = builder.categoryName;
		this.createdDateTime = builder.createdDateTime;
		this.createdBy = builder.createdBy;
		this.modifiedDateTime = builder.modifiedDateTime;
		this.modifiedBy = builder.modifiedBy;
	}
	
	public static class Builder{
		
		private long id;
		private String categoryName;
		private Date createdDateTime;
		private String createdBy;
		private Date modifiedDateTime;
		private String modifiedBy;
		
		public Builder id(long id) {
			 this.id = id;
			 return this;
		}

		public Builder categoryName(String categoryName) {
			 this.categoryName = categoryName;
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
		
		public Category build() {
		     return new Category(this);
		 }
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
