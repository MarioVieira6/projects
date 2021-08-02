$('#confirmacaoExclusaoModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);
	
	var codigoTitulo = button.data('codigo');
	var descricaoTitulo = button.data('descricao');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	if (!action.endsWith('/')) {
		action += '/';
	}
	form.attr('action', action + codigoTitulo);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o título <strong>' + descricaoTitulo + '</strong>?');
});

$(function() {
	$('[rel=tooltip]').tooltip(); // https://getbootstrap.com/docs/4.1/components/tooltips/
	$('.js-currency').maskMoney({decimal: ',', thousands: '.', allowZero: true}); // https://github.com/plentz/jquery-maskmoney/blob/master/demo/index.html
	$('.js-atualizar-status').on('click', function(event) {
		event.preventDefault(); // Impede que a funcao encaminhe a requisicao
		
		var botaoReceber = $(event.currentTarget); // Acessa o componete que disparou o evento
		var urlReceber = botaoReceber.attr('href'); // Acessa a url definida no botao

		/**
		 * No conceito de AJAX, o JavaScript inica a requisicao, o resultado da requisicao volta para o 
		 * JavaScript e com isso e possivel mexer em partes especificas da tela sem alterar o estado 
		 * da pagina.
		 */
		var response = $.ajax({
			url: urlReceber,
			type: 'PUT'
		});
		
		/**
		 * Evento disparado caso a requisicao seja concluida com sucesso.
		 */
		response.done(function(e) {
			var codigoTitulo = botaoReceber.data('codigo');
			$('[data-role=' + codigoTitulo + ']').html('<span class="label label-success">' + e + '</span>');
			botaoReceber.hide();
		});
		
		/**
		 * Evento disparado caso a requisicao retorne um erro.
		 */
		response.fail(function(e) {
			console.log(e);
			alert('Erro recebendo cobrança');
		});
	});
});