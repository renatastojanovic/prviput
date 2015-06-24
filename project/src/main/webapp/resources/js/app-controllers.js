var bookstoreApp = angular.module('bookstoreApp.controllers', []); 


bookstoreApp.controller('BooksController', function($scope,$rootScope, $http, $location,$routeParams,$interval,bookRestService,userRestService) {
	$scope.onlyNumbers = /^[0-9]+$/;
	$scope.page=0;
	
	if(!$rootScope.userLogMessage){
		$location.path('/');
	}
	$scope.getBooks=function(){
		
		var parameters={page:$scope.page};
		if($scope.search){
			$scope.page=0;
			parameters={search: $scope.search,searchBy:$scope.searchBy,page:$scope.page, pageSize:$scope.pageSize };
			
		}
		bookRestService.getBooks(parameters) 			
		.success(function(data,status,headers) {			
			$scope.books = data;
			$scope.totalPages=headers()['total-pages'];
			$scope.totalElements=headers()['total-elements']; 
			$scope.successMessage=true;
			
			 $scope.range = function(){
				    var input = [];
				    for (var i = 0; i <$scope.totalPages; i += 1) {
				    	input.push(i);
				    }
				    return input;
				  };
			
		})
		.error(function() { 				
			$scope.errorMessage=true;
		});
	};
	
	
	$scope.initBook=function(){
		$scope.book={};
		
		if($routeParams.id){
			bookRestService.getBook($routeParams.id)
			   .success(function(data){
				   $scope.book=data;
				   $scope.editMessage=true;
			   });
		}
	};
	
	
	$scope.saveBook=function(){
	    bookRestService.saveBook( $scope.book)
		  .success(function(data){			
			 $location.path('books/');	      
		 });	
	};
	
	
	$scope.deleteBook=function(id){
		 var r = confirm("Confirm deleting!");
		    if (r == true) {
		   
		bookRestService.getBook(id)
		   .success(function(data){
			   $scope.book=data;
			   $scope.book.deleted=true;			  			  		   
			        bookRestService.saveBook($scope.book)
					  .success(function(data){
						  $scope.getBooks();							
					  });
		
		   });
		    
		    }
	};
	
	
	
	$scope.changePage=function(page){		
		$scope.page=page;
		$scope.getBooks();		
	};
	$scope.checkedIsbn=false;
    $scope.checkIsbn=function(){
    	var isbn="isbn";
    	parameters={search: $scope.book.isbn, searchBy:isbn,page:$scope.page };
    	bookRestService.getBooks(parameters) 			
		.success(function(data,status,headers) {			
			$scope.bookS = data;
			if($scope.bookS.length>0){
				$scope.checkedIsbn=true;
			}else{
				$scope.checkedIsbn=false;
			}		
		})
		.error(function() { 				// ako se dogodila greska
			$scope.errorMessage=true;
		});
    };
});
    bookstoreApp.controller('LoginController', function($scope,$rootScope, $http, $location,$routeParams,$interval,userRestService) {
	$scope.checkedUsername=false;
	$scope.checkedPassword=false;
	$rootScope.userLogMessage=false;
	
	$scope.checkUsername=function(){
		 parameters={username:$scope.user.username};
		 userRestService.getUsers(parameters)
		    .success(function(data){
			    $scope.users=data;
			    if( $scope.users.length>0){
			    	$scope.checkedUsername=true;
			    }else{
			    	$scope.checkedUsername=false;
			    }
		    })
		     .error(function(){
		    	 $scope.errorMessage='Ooops, something went wrong!';
		     });
	};
	
	$scope.checkPassword=function(){
		if($scope.user.password!=$scope.user.repeatedPassword){
			$scope.checkedPassword=true;
		}else{
			$scope.checkedPassword=false;
		}
	};

	$scope.initUser=function(){
		$scope.user={};		
	};
	
	$scope.saveUser=function(){
		
		   
	    userRestService.saveUser(  $scope.user)
		  .success(function(data){
			    $rootScope.userLogMessage=true;
			    $rootScope.userL = $scope.user;
			    $location.path('books/');
		    
		 });
		    
	};

	
	$scope.messageUsername=false;
	
	$scope.initUser=function(){	
		$scope.userL={};	
	};
	$scope.login=function(){
		  
			var parameters={password:$scope.userL.password, username:$scope.userL.username};		

			userRestService.getUsers(parameters)
			    .success(function(data){
				    $scope.user=data;
				    if($scope.user.length>0){
				    	 $rootScope.userLogMessage=true;
						  $rootScope.userL = $scope.user;
						  $location.path('books/');
						  var userL=$rootScope.userL;
						  localStorage.setItem('userL',userL);
				    }else{
				    	$scope.userL={};
				    }
			    })
			     .error(function(){
			    	 $scope.errorMessage='Ooops, something went wrong!';
			     });
		  
	};
	
	$scope.signOut=function(){
		
		 $location.path('/');
		 $rootScope.userLogMessage=false;
		 $rootScope.userL=null;
		
	};
});

bookstoreApp.controller('TranslateController',function($scope,$translate){
	$scope.changeLang=function(code){
		$translate.use(code);
		localStorage.setItem('lang',code);//ubacimo koji jezik koristimo u browser storage
	};
	
});

