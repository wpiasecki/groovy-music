(function() {
	
	var app = angular.module('groovymusic', ['ui.bootstrap']);
	
	var musicaService = function($http) {
		this.listar = callback => $http.get('rest/musica').then(callback);
		this.excluir = musica => $http.delete('rest/musica/' + musica.id);
		this.editar = params => $http.put('rest/musica/' + params.musica.id);
	}
	
	
	var musicaController = function($scope, $http, $timeout, $uibModal, musicaService) {
		musicaService.listar(result => $scope.musicas = result.data );
		
		var apresentarMensagem = params => response => {
			$scope.status = params;
			$timeout(() => $scope.status = {}, 7000);
		};
		
		$scope.salvarMusica = musica => {
			console.log('salvar musica');
			console.log(musica);
		}
		
		$scope.excluirMusica = musica => {
			if (confirm("Tem certeza de que deseja excluir '" + musica.nome + "'?")) {
				musicaService.excluir(musica).then( 
					apresentarMensagem({tipo: 'sucesso', mensagem: "Música excluída com sucesso!"}),
					apresentarMensagem({tipo: 'erro', mensagem: "Ocorreu um erro ao excluir a música."})
				).then(() => {
					musicaService.listar(result => $scope.musicas = result.data );
				});
			}
		}
		
		$scope.abrirModalEditarMusica = musica => {
			$scope.musicaEmEdicao = musica;
			$scope.showModal = true;
			$scope.modalEditar = $uibModal.open({
				templateUrl: 'partials/modal.html',
			    controller: 'musicaController',
			    controllerAs: '$ctrl',
			    scope: $scope,
			    resolve: { 
			    	musicaEmEdicao: () => musica,
		    	}
			});
		}
	}
	
	app.service('musicaService', ['$http', musicaService]);
	app.controller('musicaController', ['$scope', '$http', '$timeout', '$uibModal', 'musicaService', musicaController]);
	
})();