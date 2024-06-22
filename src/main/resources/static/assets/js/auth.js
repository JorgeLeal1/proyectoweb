/** 
 * Guarda true si el susuario esta autentificado, de lo cotrario redirige a la pantalla login
 * 
 * 
 */

const logged = sessionStorage.key('auth');

if(!logged)
    location.href = '../../../login.html';
