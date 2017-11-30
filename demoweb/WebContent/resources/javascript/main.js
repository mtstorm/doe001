function main() {
$('.knoppen').hide();
 $('.knoppen').fadeIn(1000);
 $('#projects').hide();
 $('.projects-button').on('click', function(){

  $(this).next().slideToggle(400); 

 $(this).toggleClass('active')
   $(this).text('geklikt');
                          });
}
$(document).ready(main);