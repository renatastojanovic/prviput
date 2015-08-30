var bookstoreApp = angular.module('bookstoreApp.controllers', []); 

bookstoreApp.controller('BooksController', function($scope,$rootScope, $http, $location,$routeParams,$interval,bookRestService,userRestService) {
	$scope.onlyNumbers = /^[0-9]+$/;
	$scope.page=0;
	$scope.name="Renata";
	
	
	if(!$rootScope.userLogMessage){
		$location.path('/');
	}
	$(document).ready(function () {
	    $('[data-toggle="tooltip"]').tooltip();
	    
	});
	
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
	
	$scope.messageUsername=false;
	
	$rootScope.userL={};
	$rootScope.userLogMessage=false;
	var userUlog= localStorage.getItem('userLog');
	if(userUlog){
		parameters={username:userUlog};
		 userRestService.getUsers(parameters)
		    .success(function(data){
		    	$scope.users=data;
		    	$rootScope.userL=$scope.users[0];
		    	$rootScope.userLogMessage=true;
		    })
		     .error(function(){
		    	 $scope.errorMessage='Ooops, something went wrong!';
		     });
		
	}
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
			    var user=$scope.user;
			    $rootScope.userL = user;
			    localStorage.setItem('userLog', $rootScope.userL.username);
			    $location.path('books/');
		    
		 });
		    
	};

	
	
	
	$scope.initUserLog=function(){	
		$scope.userLog={};	
	};
	
	$scope.login=function(){
		  
			var parameters={password:$scope.userLog.password, username:$scope.userLog.username};		

			userRestService.getUsers(parameters)
			    .success(function(data){
				    $scope.userRec=data;
				    if($scope.userRec.length>0){
				    	 $rootScope.userLogMessage=true;
				    	 var userLog=$scope.userLog;
						  $rootScope.userL =userLog;
						  localStorage.setItem('userLog',$rootScope.userL.username);
						  $location.path('books/');
						  
				    }else{
				    	$scope.userLog={};
				    }
			    })
			     .error(function(){
			    	 $scope.errorMessage='Ooops, something went wrong!';
			     });
		  
	};
	
	$scope.signOut=function(){
		
		 $location.path('/');
		 $rootScope.userLogMessage=false;
		 $rootScope.userL={};
		 $scope.userLog={};
		localStorage.removeItem('userLog');
	};
	
	
});

bookstoreApp.controller('TranslateController',function($scope,$translate){
	$scope.changeLang=function(code){
		$translate.use(code);
		localStorage.setItem('lang',code);//ubacimo koji jezik koristimo u browser storage
	};
	
});

