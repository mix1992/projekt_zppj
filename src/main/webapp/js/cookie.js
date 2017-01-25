/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function saveToCookie(login, password) {
    document.cookie = "login=" + login + ";";
    document.cookie = "password=" + password + ";";
}

function saveToCookie(token) {
    document.cookie = "token=" + token + ";";
}

function getFromCookie() {
    var cookie = document.cookie.split(';');
    return cookie;
}


function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function getToken() {
    var token = getCookie("token");
    if (token === '' || token === undefined) {
        document.location.href = "index.html";
    } else {
        return token;
    }
}
