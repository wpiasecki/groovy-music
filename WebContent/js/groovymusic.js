(function() {
	'use strict';
	
	var app = angular.module('groovymusic', ['ui.bootstrap']);
	
	var musicaService = function($http) {
		this.listar    = callback => $http.get('rest/musica').then(callback);
		this.filtrar   = (callback, params) => $http.get('rest/musica', { params: params }).then(callback);
		this.excluir   = musica => $http.delete('rest/musica/' + musica.id);
		this.atualizar = musica => $http.put('rest/musica/' + musica.id, musica);
	}
	
	var albumService = function($http) {
		this.listar = callback => $http.get('rest/album').then(callback);
	}
	
	var musicaController = function($scope, $http, $timeout, $uibModal, musicaService, albumService) {
		
		$scope.listarMusicas = () => musicaService.listar(result => $scope.musicas = result.data );
		$scope.listarAlbums  = () => albumService.listar(result => $scope.albums = result.data);
		
		
		$scope.musicaFiltro = { nome: "", albumId: "" };
		$scope.filtrarMusicas = filtro => {
			if (!filtro.albumId) delete filtro.albumId;
			musicaService.filtrar(result => $scope.musicas = result.data, filtro);
		};
				
		
		var apresentarMensagem = params => response => {
			$scope.status = params;
			console.log('apresentar mensagem', params)
			$timeout(() => $scope.status = {}, 7000);
		};
		
		$scope.salvarMusica = musica => {
			musicaService.atualizar(musica).then(
					apresentarMensagem({ tipo: 'sucesso', mensagem:"Música atualizada com sucesso" }),
					apresentarMensagem({ tipo: 'erro', mensagem:"Erro ao atualizar música" })
			).then($scope.modalEditar.close)
			 .then($scope.atualizarLista);
		}
		
		$scope.excluirMusica = musica => {
			if (confirm("Tem certeza de que deseja excluir '" + musica.nome + "'?")) {
				musicaService.excluir(musica).then( 
					apresentarMensagem({ tipo: 'sucesso', mensagem: "Música excluída com sucesso!" }),
					apresentarMensagem({ tipo: 'erro', mensagem: "Ocorreu um erro ao excluir a música." })
				).then($scope.listarMusicas);
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
		
		$scope.listarMusicas();
		$scope.listarAlbums();
	}
	
	app.service('musicaService', ['$http', musicaService]);
	app.service('albumService', ['$http', albumService]);
	app.controller('musicaController', ['$scope', '$http', '$timeout', '$uibModal', 'musicaService', 'albumService', musicaController]);
	
})();