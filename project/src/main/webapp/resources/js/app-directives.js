var bookstoreApp = angular.module('bookstoreApp.directives', []); 


bookstoreApp.directive('booksTable',function(){
	return{
		restrict:'E',
		templateUrl: 'resources/html/booksTable.html',
		controller: 'BooksController'
	};
});

