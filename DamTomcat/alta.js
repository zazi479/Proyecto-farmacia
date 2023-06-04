// Función getMedicines() para la página Alta
function getMedicines() {
    var mail = sessionStorage.getItem("email");
    var session = sessionStorage.getItem("session");

    var http = new XMLHttpRequest();
    var url = "http://localhost:3006/Dam_tomcat/ServeMedicines";
    var params = "mail=" + mail + "&session=" + session;
    http.open("GET", url + "?" + params, true);


    http.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            var medicines = JSON.parse(http.responseText);
    
            console.log(http.responseText);
            

            añadirOpcionesSelect("medicamento", medicines);
        }
    };

    http.send();
}



function hacer_gets() {
    getPatients();
    getMedicines();
}


function añadirOpcionesSelect(selectA, json) {
    var select = document.getElementsByName(selectA)[0];

    if (select.length > 0){
        for (let i=select.option.length; 0 >= select.length; i--){
            select.remove(i);
        }
    }

    for(let i=0; i < json.length; i++){ 
        let option = document.createElement("option"); 
        if (selectA == "paciente") {
            option.innerHTML = json[i].mail;
        } else {
            option.value = json[i].id;
            option.textContent = json[i].name;
        } 
        select.appendChild(option);
    }
}


function getPatients() {

    var mail = sessionStorage.getItem("email");
    var session = sessionStorage.getItem("session");

    var http = new XMLHttpRequest();
    var url = "http://localhost:3006/Dam_tomcat/ServePatients";
    var params = "mail=" + mail + "&session=" + session;
    http.open("GET", url + "?" + params, true);
    http.send();

    http.onreadystatechange=function(){
        if(this.readyState==4 && this.status==200){
            var json = JSON.parse(http.responseText);
            console.log(http.responseText);
            añadirOpcionesSelect("paciente",json);
        }
    }

}



function enviar(){

    var email = sessionStorage.getItem('email');
    var session = sessionStorage.getItem('session');
    let mailP = document.getElementById("paciente").value;
    let idXip = document.getElementById("idXip").value;
    let idMed = document.getElementById("medicamento").value;
    let dateLimit = document.getElementById("dateInput").value;

    // Crear un objeto de fecha actual
    var fechaActual = new Date();

    // Obtener los componentes de la fecha
    var anio = fechaActual.getFullYear();
    var mes = ('0' + (fechaActual.getMonth() + 1)).slice(-2); // Los meses en JavaScript son indexados desde 0, se agrega '0' y se utiliza slice para obtener los dos dígitos
    var dia = ('0' + fechaActual.getDate()).slice(-2); // Se agrega '0' y se utiliza slice para obtener los dos dígitos

    // Construir la fecha en el formato deseado (por ejemplo, AAAA-MM-DD)
    var fechaFormateada = anio + '-' + mes + '-' + dia;

    if (dateLimit < fechaFormateada){
        document.getElementById('error').innerHTML = 'La fecha introducida no es correcta';
        console.log("prueba")
    }
    else{
        var http = new XMLHttpRequest();
        http.open("POST", "http://localhost:3006/Dam_tomcat/Release", true);
        http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    
    
        http.onreadystatechange = function () {
            if (http.readyState === XMLHttpRequest.DONE) {
                if (http.status === 200) {
                    
                } else {
                    console.error('Error en la petició al backend:', http.status);
                }
            }
        };
    
        http.send("email=" + email + "&session=" + session + "&mailP=" + mailP + "&idXip=" + idXip + "&idMed=" + idMed + "&dateLimit=" + dateLimit);
    
        window.location.href = 'alta.html';
        
    } 
}