$(document).ready(function () {

    $('#linkDepositar').click(function(){  alert("Depositar", "proyectoweb/depositar"); });
    $('#linkRetirar').click(function(){  alert("Retirar", "proyectoweb/retirar"); });
    $('#linkTransferir').click(function(){  alert("Crear Cuentas", "proyectoweb/transferir"); });
    $('#linkListarCuentas').click(function(){  alert("Listar Cuentas", "proyectoweb/viewAllCuentas"); });    
    
    function alert(mensaje, link){
        let alert = "<div class='alert alert-success alert-dismissible fade show text-center' role='alert'>"
        + "Redirigiendo a " + mensaje
        + "</div>";
        
        $("#mensaje_menu").html(alert);   
        setTimeout(() => { window.location.href = "./../"+ link; }, 2000);
    }

})