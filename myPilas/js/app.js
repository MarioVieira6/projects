var buttonSwitch = document.querySelector('.js-button-switch');

/* JavaScript Hook é um nome de classe específica para selecionar o elemento através de uma classe CSS. Essa classe não tem nenhum estilo configurado,
é usado apenas para selecionar o elemento. Na convenção, o nome da classe segue com o prefixo js-[class-name]. */
buttonSwitch.onclick = function() {
  var menu = document.querySelector('.js-menu');
  menu.classList.toggle('menu--is-showing');
}
