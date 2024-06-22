$(document).ready(function () {

    $('.select_transaction').on('change', function(e) {
        // retrieve the dropdown selected value
        
        var location = e.target.value;
        var ul = $('.ul_transaction');
        //console.log(location.length);

        // filtra por la cantidad de letras del value
        // ya que este valor servira para esconder o mostrar los li
        //**********  Se tuve que encerrar el li con un div ya que el hide o show no tenian efecto */
        if (location.length > 0) {
            ul.find('div[data-type!=' + location + ']').hide();
            ul.find('div[data-type=' + location + ']').show();
        } else {
            // Muestra todos los div
            ul.find('div').show();
        }

      });
})