(function() {
	
	var app = angular.module('groovymusic', []);
	
	app.controller('musica', function($scope, $http) {
		$http.get('rest/musica').then(result => $scope.musicas = result.data);
	});
	
	
})();