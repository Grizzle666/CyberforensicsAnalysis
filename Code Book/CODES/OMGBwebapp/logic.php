<?php
   if (isset($_POST['submit'])) {  
    $firstvalue=$_POST['firstvalue'];

	$secondvalue= $_POST['secondvalue'];
	 
	 $totalvalue =$firstvalue - $secondvalue;
	 echo  $totalvalue;

   }  else {

    echo "Your form is not submitted yet please fill the form and visit again";
  }
?>