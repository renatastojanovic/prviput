var bookstoreApp = angular.module('bookstoreApp', ['ngRoute','pascalprecht.translate','bookstoreApp.directives','bookstoreApp.controllers','bookstoreApp.services']); // wafepaApp je AngularJS aplikacija (u index.html <html ng-app="wafepaApp">)



// konfiguracija $routeProvider - servis koji omogucuje promenu stranica, tj. rutiranje
// 	- u zavisnosti od toga koja je ruta pozvana, renderovace se odgovarajuci parcijalni view (definisan u templateUrl)
//	  ununtar elementa koji je oznacen sa ng-view direktivom
bookstoreApp.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : 'resources/html/home.html',
			controller : 'BooksController'
	    })
	    .when('/books', {
	        templateUrl : 'resources/html/books.html',
	        controller : 'BooksController'
	    })
	    .when('/books/add', {
	        templateUrl : 'resources/html/addEditBook.html',
	        controller : 'BooksController'
	    })
	    .when('/books/edit/:id',{
	        templateUrl : 'resources/html/addEditBook.html',
	        controller : 'BooksController'
	     })
	    
	    .when('/users/add',{
	    	templateUrl:'resources/html/signUp.html',
	    	controller: 'BooksController'
	    })
	    
	    .otherwise({
            redirectTo: '/'
        });
}]);

bookstoreApp.config(['$translateProvider', function($translateProvider){//za prevodjenje na druge jezike
	$translateProvider.translations('en',{
		PASSWORDSDONTMATCH:"Passwords don't match.",
		THISUSERNAMEISTAKEN:"This username is taken.",
		USERNAME:"Username",
		PASSWORD:"Password",
		MANDATORYFIELD:"Mandatory field",
		SIGNUP:"Sign up",
		REPEATPASSWORD:"Repeat password",
		YOUDONTHAVEANACCOUNT:"You don't have an account",
		SIGNOUT:"Sign out",
		HELLO:"Hello",
		ERRORMESSAGE:"Ooops, something went wrong",
		NEXT:"Next",
		PREVIUOS:"Previous",
		EDIT:"Edit ",
		DELETE:"Delete",
		THISFIELDISREQUIRED:"This field is required",
		CANCEL:"Cancel",
		BOOKS:"Books",
		ADD:"Add",
		TITLE:"Title",
		AUTHOR:"Author",
		GENRE:"Genre",
		ACTIONS:"Actions" ,
		ISBNINVALID:"ISBN invalid",
		BOOKSTORE:"bookstore",
		HOMEHEAD:"Welcome to Danulabs bookstore!",
		HOMEBODY:"Here you can browse our book database, add new books, edit or delete the old ones. Basically, you can do anything!",
		HOMEFOOTER:"To browse the contents, please sign in, or sign up for an account if you don't have one.",
		CLEAR:"Clear",
		
		SEARCHBY:"Search by",
		
		SEARCH:"Search",
		
		
		SAVE:"Save",
		
	    CONFIRM:"Confirm",
	    REVERT:"Revert",
	    
	    
		    
	});
	
	$translateProvider.translations('sr',{
		PASSWORDSDONTMATCH:"Lozinke nisu identicne.",
		THISUSERNAMEISTAKEN:"Ovo korisnicko ime je zauzeto",
		USERNAME:"Korisnicko ime",
		PASSWORD:"Lozinka",
		MANDATORYFIELD:"Obavezna polja",
		SIGNUP:"Prijavi se",
		REPEATPASSWORD:"Ponovite lozinku",
		YOUDONTHAVEANACCOUNT:"Niste registrovani",
		SIGNOUT:"Odjavi me",
		HELLO:"Zdravo",
		ERRORMESSAGE:"Ooops, nesto je krenulo lose",
		NEXT:"Sledeca",
		PREVIUOS:"Prethodna",
		EDIT:"Izmeni ",
		DELETE:"Izbrisi",
		THISFIELDISREQUIRED:"Ovo polje je obavezno",
		CANCEL:"Opozovi",
		BOOKS:"Knjige",
		ADD:"Dodaj novu knjigu",
		TITLE:"Naziv",
		AUTHOR:"Autor",
		GENRE:"Zanr",
		ACTIONS:"Akcije",
		ADDNEWBOOK:"Unos nove knjige",
		ISBNINVALID:"ISBN nije ispravna",
		BOOKSTORE:"knjizara",
        HOMEHEAD:"Dobro dosli u Danulabs-ovu knjizaru!",
        HOMEBODY:"Ovde mozete pretrazivati knjige u bazi, dodavati nove knjige, menjati ili brisati postojece. U sustini mozete raditi bilo sta!",
        HOMEFOOTER:"Da biste pregledali sadrzaj, molimo ulogujte se, ili se registrujte ukoliko ste novi korisnik.",
	    CLEAR:"Isprazni pretrage",
	    SEARCHBY:"Pretrazi po",
	    SEARCH:"Pretrazi",

	    SAVE:"Sacuvaj",
	   	CONFIRM:"Potvrdi",
	    REVERT:"Opozovi",
	   
	    
	});
	var code=localStorage.getItem('lang');
	if(code){
		$translateProvider.use(code);
	}else{
	    $translateProvider.use('en');//defaultni jezik
	}
	
}]);