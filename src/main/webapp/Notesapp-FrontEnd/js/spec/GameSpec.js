describe("category", function() {
/*	  var request, response;
	  var success, error, complete;
	  var client, onreadystatechange;
	  var sharedContext = {};
	  var fakeGlobal, mockAjax;
	
	describe("Testing and the response is Success, but with JSON", function () {
	      beforeEach(function() {
	    	  
	    	  var fakeXMLHttpRequest = jasmine.createSpy('realFakeXMLHttpRequest');
	    	    fakeGlobal = {
	    	      XMLHttpRequest: fakeXMLHttpRequest,
	    	      DOMParser: window.DOMParser,
	    	      ActiveXObject: window.ActiveXObject
	    	    };
	    	    mockAjax = new window.MockAjax(fakeGlobal);
	    	    mockAjax.install();
	    	    
	    	    success = jasmine.createSpy("onSuccess");
	    	    error = jasmine.createSpy("onFailure");
	    	    complete = jasmine.createSpy("onComplete");

	    	    onreadystatechange = function() {
	    	      if (this.readyState === (this.DONE || 4)) { 
	    	        if (this.status === 200) {
	    	          success(this.responseText, this.textStatus, this);
	    	        } else {
	    	          error(this, this.textStatus, '');
	    	        }

	    	        complete(this, this.textStatus);
	    	      }
	    	    };
	    	    
	        client = new fakeGlobal.XMLHttpRequest();
	        client.onreadystatechange = onreadystatechange;
	        client.open("GET", "example.com/someApi");
	        client.setRequestHeader("Content-Type", "application/json");
	        client.send();

	        request = mockAjax.requests.mostRecent();
	        var responseObject = {status: 200, statusText: "OK", contentType: "application/json", responseText: '{"foo":"bar"}', responseType: "json"};

	        request.respondWith(responseObject);

	        sharedContext.responseCallback = success;
	        sharedContext.status = responseObject.status;
	        sharedContext.statusText = responseObject.statusText;
	        sharedContext.contentType = responseObject.contentType;
	        sharedContext.responseText = responseObject.responseText;
	        sharedContext.responseType = responseObject.responseType;
	        sharedContext.responseURL = responseObject.responseURL;

	        response = success.calls.mostRecent().args[2];
	      });

	      it("should call the success handler", function() {
	        expect(success).toHaveBeenCalled();
	      });

	      it("should not call the failure handler", function() {
	        expect(error).not.toHaveBeenCalled();
	      });

	      it("should call the complete handler", function() {
	        expect(complete).toHaveBeenCalled();
	      });

	      it("should return a JavaScript object for XHR2 response", function() {
	        var responseText = sharedContext.responseText;
	        expect(success.calls.mostRecent().args[0]).toEqual(responseText);
	        expect(response.responseText).toEqual(responseText);
	        expect(response.response).toEqual({foo: "bar"});
	      });

	     // sharedAjaxResponseBehaviorForZepto_Success(sharedContext);
	    });
	*/
	describe("mocking ajax", function() {
		
		describe("Add category", function() {
			
			beforeEach(function() {
			      jasmine.Ajax.install();
			    });
			
			afterEach(function() {
			      jasmine.Ajax.uninstall();
			    });
			
			 it("response", function() {
			     
				 ajaxcall("POST","/category/add","application/x-www-form-urlencoded","categoryName=sports&createdBy=Admin");
				// var responseObject = {status: 200, statusText: "OK", contentType: "application/json", responseText: '{"foo":"bar"}', responseType: "json"};
			      //except(this.readyState).toBe();
			 	});
		
			});	
	});
	
});