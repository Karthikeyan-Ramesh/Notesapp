describe("category", function() {
	
	describe("mocking ajax", function() {
		
		describe("Category validation",function(){
			
			it("Name Check with alphabets", function() {
				expect(categoryNameCheck("dfdfds")).toBe("Success");
			});
			it("Name Check with double quotes", function() {
				expect(categoryNameCheck("")).toBe("Fail");
			});
			it("Name Check with null", function() {
				expect(categoryNameCheck(null)).toBe("Fail");
			});
			
		});

		describe("Add category", function() {

			beforeEach(function() {
				jasmine.Ajax.install();
			});

			afterEach(function() {
				jasmine.Ajax.uninstall();
			});
			

			it("response", function() {
				ajaxcall("POST", "/category","application/json","{categoryName:sports}");
			});

		});
	});

});