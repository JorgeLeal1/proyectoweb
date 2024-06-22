var balance = Number(localStorage.balances);

function Descontar_saldo(Nombre, cuenta) {
    flag = 0; mensaje = "";
    //console.log(localStorage.balances);

    if (Number(localStorage.balances) > 0) {
        flag = 1;
        localStorage.balances = 0;
        $('#balance').text(localStorage.balances);
        mensaje = "<h4 class='alert-heading'>Envio realizado con exito!!</h4>: "
            + "<p> Nombre: " + Nombre + "</p>"
            + "<p> Cuenta: " + cuenta + "</p>"
            + "<p> Dinero Enviado: " + balance + "</p>";
    } else {
        mensaje = "No se a realizado el envio ya que no existe saldo sufuciente en la cuenta!!!";
    }

    if (flag == 1) { class_alert = "alert-success"; } else { class_alert = "alert-danger"; }

    div_alert = "<div class='alert " + class_alert + " alert-dismissible fade show text-center' role='alert'>"
        + mensaje
        + "<button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>"
        + "</div>";

    $("#mensaje_sendmoney").html(div_alert);

}

$(document).ready(function () {

    //pruebas para recorrer tabla y crear filtro 
    $('#balance').text(balance);
    //variable global
    let tabla = document.getElementById("TablaSendMoney");

    $('#Form_sendmoney').submit(function (e) {
        e.preventDefault();

        // limpia input de espacios
        let inputBuscarContacto = $("#inputBuscarContacto").val().trim();

        //se asigna el valor del input sin espacios en pantalla
        $("#inputBuscarContacto").val(inputBuscarContacto);

        ValidarContacto(inputBuscarContacto);


    });


    //al hacer click en el tr se puede obtener su id
    $(".tr_click").click(function (e) {
        e.preventDefault();
        let fila = $(this).attr("data-valor");

        //Removemos la clase de la fila seleccionada anteriormente
        $("tr").removeClass("fw-bold");

        //Agregamos a la fila selccionada las siguientes clases
        $("#" + fila).addClass('fw-bold');

        //Al seleccionar la fila vamos a agregar un boton que deposite el total del valor de la cuenta al contacto.
        // nomesclatura ==> tr, seleccionado + columna del boton ==> 1_td_columna6

        let Alias = tabla.rows[fila].cells[3].innerHTML;
        let cuenta = tabla.rows[fila].cells[2].innerHTML;
        //let mensaje =

        let btn_enviar = "<button type='submit' class='btn btn-primary mb-2' style='float: right;' id='btnEnviar_money' onclick=Descontar_saldo('" + Alias + "'," + cuenta + ")>"
            + "Enviar dinero";
        +"</button>";
        $("#" + fila + "_td_columna6").html(btn_enviar);
        limpiar_button(fila);

    });


    //console.log(tabla);
    //console.log(tabla.rows[1].cells[1].innerHTML);
    //console.log(tabla.rows.length);



    /* se recorre tabla como matrix[][]
    for (let i = 1; i < tabla.rows.length; i++) {
        for (let j = 0; j < 5; j++) {
           // console.log(tabla.rows[i].cells[j].innerHTML);
        }
    }
    */

    /* 
    Realizar búsqueda en la agenda de transferencias:

    Podemos agregar una funcionalidad de búsqueda que permita al usuario buscar en la agenda de transferencias por nombre o alias. 
    Al enviar el formulario de búsqueda, podemos filtrar la lista de contactos y mostrar solo aquellos que coincidan con el término 
    de búsqueda.
    */

    function mensaje_alert(mensaje, flag) {
        let alert = "", class_alert = "";

        if (flag == 1) { class_alert = "alert-success"; } else { class_alert = "alert-danger"; }

        alert = "<div class='alert " + class_alert + " alert-dismissible fade show text-center' role='alert'>"
            + mensaje
            + "<button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>"
            + "</div>";

        $("#mensaje_sendmoney").html(alert);
    }

    // Verifica si existe contanto  en la tabla, retornando 0 == NO y >0 == Si existe
    function ValidarContacto_respaldo(nombre) {

        let fila = 0, flag = 0;
        // flag almacenara si existe coincidencia en el total de la tabla

        for (let i = 1; i < tabla.rows.length; i++) {
            for (let j = 0; j < 5; j++) {

                // j==1 se obtiene la columna 1, Nombre, j==3 se obtiene la columna 3, Alias
                if ((j == 1 && tabla.rows[i].cells[j].innerHTML === nombre) ||
                    (j == 3 && tabla.rows[i].cells[j].innerHTML === nombre)
                ) {
                    fila = 1; flag++;
                }
            }

            if (fila == 0) { $("#" + i).hide(); } else { $("#" + i).show(); }
            fila = 0;
        }

        return flag;

    }

    // Verifica si existe contanto  en la tabla, retornando 0 == NO y >0 == Si existe
    function ValidarContacto(nombre) {

        //var value = $(this).val().toLowerCase();
        var value = nombre;
        var $table = $("#TablaSendMoney");

        var $tr = $table.find('tr:gt(0)');

        let flag=0;

        $tr.hide();
        $tr.filter(function () {
            var text = $(this).text().toLowerCase();
            if(text.indexOf(value) >= 0){
            flag++;
            return text.indexOf(value);
            }
        }).show();

        if (flag == 0) {
            mensaje = "<p>Sin coincidencias!!</p>";
            mensaje_alert(mensaje, 0);
        } 

    }

    //limpiar botones al no estar seleccionado
    function limpiar_button(fila) {

        //Obteniendo solo la columna 1(Nombre)
        for (let i = 1; i < tabla.rows.length; i++) {
            if (fila != i) {
                // console.log("#"+i+"_td_columna6");
                $("#" + i + "_td_columna6").empty();
            }
        }

    }




})