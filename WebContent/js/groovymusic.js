(function() {
	
	var app = angular.module('groovymusic', []);
	
	var musicaService = function($http) {
		this.listar = callback => $http.get('rest/musica').then(callback);
		this.excluir = musica => $http.delete('rest/musica/' + musica.id);
		this.editar = params => $http.put('rest/musica/' + params.musica.id);
	}
	
	app.service('musicaService', ['$http', musicaService]);
	
	
	var musicaController = function($scope, $http, $timeout, musicaService) {
		musicaService.listar(result => $scope.musicas = result.data );
		
		$scope.editar = musica => {
			alert('editar ' + musica.nome);
			console.log(musicaService);
		}
		
		$scope.excluir = musica => {
			if (confirm("Tem certeza de que deseja excluir '" + musica.nome + "'?")) {
				musicaService.excluir(musica).then( 
					(response) => { 
						$scope.status = {
							sucesso: true,
							mensagem: "Música excluída com sucesso!"
						}
					},
					(response) => {
						$scope.status = {
							erro: true,
							mensagem: "Ocorreu um erro ao excluir a música."
						}
					}
				).then(() => {
					$timeout(() => { 
						$scope.status.erro = false; 
						$scope.status.sucesso = false; 
					}, 7000);
					musicaService.listar(result => $scope.musicas = result.data );
				});
			}
		}
	}
	
	app.controller('musica', ['$scope', '$http', '$timeout', 'musicaService', musicaController]);
	
})();