<?php
 
 session_start();
   
 $myusername= $_SESSION['login_user'];
 
 include ("header.php");
 
 include ("dbconnect.php");
 
?>




   
   <body>
   <div class="container">
    <div class="jumbotron" style="background-image:url('images/slide2.jpg');">
   
      <h1><i>Welcome <?php echo $myusername; ?><i></h1> 
	  <h3>Make a transaction</h3>
	
	

	  
	  <?php
$result = mysqli_query($conn,"SELECT * FROM account WHERE account_number = '".$myusername."'");

if ($result->num_rows > 0){
    // output data of each row
    while($row = $result->fetch_assoc()){
        echo "Branch: " . $row["branch_name"]. " - Balance: " . $row["balance"];
		?>
		
			  <form action="" method="post">
  <input type="text" name="firstvalue" value="<?php echo $row["balance"];?>">
  <input type="text" name="secondvalue" >
   <input type="text" name="accountnumber" >
  <input  class ="btn btn-primary"type="submit" name="submit" value="Send">
  </form>
		
		
		<?php
	
   }
}

 else{   
 echo "0 results";
 }

?>

<?php
   if (isset($_POST['submit'])) {  
    $firstvalue=$_POST['firstvalue'];

	$secondvalue= $_POST['secondvalue'];
	$account_number= $_POST['accountnumber'];	
	 
	 $totalvalue =$firstvalue - $secondvalue;
	 
	 

	 
$sql = "UPDATE account SET balance='$totalvalue' WHERE account_number='$myusername'";



if ($conn->query($sql) === TRUE) {
    echo " you have successfully sent". $totalvalue."to ".$account_number;
} else {
    echo "Error updating record: " . $conn->error;
}
 
 $res = mysqli_query($conn,"SELECT * FROM account WHERE account_number = '".$account_number."'");
 
 if ($res->num_rows > 0):
    // output data of each row
    while($row = $res->fetch_assoc()):
	 $balance =$row["balance"];

	endwhile;
	
 endif;
 
 
 $sentvalue=$balance+$secondvalue;

 
 
 
 $sqlquery = "UPDATE account SET balance='$sentvalue' WHERE account_number='$account_number'";



if ($conn->query($sqlquery) === TRUE) {
    echo $account_number." has successfully recieved the money ";
} else {
    echo "Error updating record: " . $conn->error;
}
 
 
	 
	 

   }  else {

    echo "Start a transaction!";
  }
  
  $conn->close();
?>
	  


	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
      <h2><a href = "logout.php">Sign Out</a></h2>
	  </div>
	  </div>
   </body>
   
</html>