
// Función getTable() para la página Gestio

function getTable() {

    
    
    var mail = sessionStorage.getItem("email");
    var session = sessionStorage.getItem("session");
    var http = new XMLHttpRequest();
    http.open("GET","http://localhost:3006/Dam_tomcat/ServeXips?email="+mail+ "&session=" + session,true);

    http.onreadystatechange = function () {
        if (http.readyState === XMLHttpRequest.DONE) {
            if (this.readyState === 4 && this.status === 200) {
                var response = http.responseText.trim();// Eliminar espacios en blanco al inicio y al final
                if(response.toLowerCase() === 'acces denied') {
                    document.getElementById('table').innerHTML = response;
                    document.getElementById('logout').innerText = 'Volver a login'; // Cambiar el texto del botón
                    document.getElementById('alta').style.display = 'none';
                    setTimeout(function(){
                        window.location.href = 'login.html';
                    }, 10000);

                }else {
                    document.getElementById('table').innerHTML = response;
                }
            } else {
                console.error("Error en la solicitud:", http.status);
            }
        }
    };

    http.send();
}




function logOut(){
    // Borrar el email y la sesión del sessionStorage
    sessionStorage.removeItem('email');
    sessionStorage.removeItem('session');
    
    window.location.href = 'Login.html';

}