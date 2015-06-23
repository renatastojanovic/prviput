var bookstoreApp = angular.module('bookstoreApp.services', []);

bookstoreApp.service('bookRestService',function($http){
	
	this.apiBooksUrl='api/books/'; 
	
	this.getBooks=function(parameters){
		return $http.get(this.apiBooksUrl,{params : parameters}); 	
	};
	
	
	this.saveBook=function(book){
		if(book.id){
			return $http.put(this.apiBooksUrl +book.id, book);
		}else{
			return $http.post(this.apiBooksUrl, book);
		}
	};
	
	this.getBook=function(id){
		return $http.get(this.apiBooksUrl + id);
	};
	
});


bookstoreApp.service('userRestService',function($http){
	
	this.apiUsersUrl='api/users/'; 
	this.getUsers=function(parameters){
		return $http.get(this.apiUsersUrl,{params : parameters}); 	
	};
	
	this.saveUser=function(user,parameters){		
		return $http.post(this.apiUsersUrl, user,{params : parameters});
	};
	
	
});