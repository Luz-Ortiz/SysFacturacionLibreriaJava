<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script>
    $(document).ready(function () {
        $('.sidenav').sidenav();
        $('select').formSelect();
         M.updateTextFields();
    });
    
   (function($) {
   $(function() {

     $('.button-collapse').sideNav();
     $('select').material_select();
     $('.dropdown-button').dropdown();

   }); // end of document ready
 })(jQuery); // end of jQuery name space
</script>

