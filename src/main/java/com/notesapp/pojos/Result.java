package com.notesapp.pojos;

import java.util.List;

public class Result<K> {

	 public String cursor;
	  public List<K> result;

	  public Result(List<K> result, String cursor) {
	    this.result = result;
	    this.cursor = cursor;
	  }

	  public Result(List<K> result) {
	    this.result = result;
	    this.cursor = null;
	  }
}
