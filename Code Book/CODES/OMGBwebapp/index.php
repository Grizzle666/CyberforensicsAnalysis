<body> 
<?php include ("header.php");
require_once ("dbconnect.php");


 session_start();
   
   if($_SERVER["REQUEST_METHOD"] == "POST") {
      // username and password sent from form 
      
      $myusername = mysqli_real_escape_string($conn,$_POST['username']);
      $mypassword = mysqli_real_escape_string($conn,$_POST['password']); 
      
      $sql = "SELECT * FROM customer WHERE Username = '$myusername' and Password = '$mypassword'";
      $result = mysqli_query($conn,$sql);
      $row = mysqli_fetch_array($result,MYSQLI_ASSOC);
      $active = $row['Username'];
      
	  echo $row['Username'];
      $count = mysqli_num_rows($result);
      
      // If result matched $myusername and $mypassword, table row must be 1 row
		
      if($count == 1) {
       
         $_SESSION['login_user'] = $myusername;
         
         header("location: profile.php");
      }else {
         $error = "Your Login Name or Password is invalid";
      }
   }

 ?>

<div class="container">
  <div class="jumbotron" style="background-image:url('images/big2.jpg');">
    <h1>OMG Banking System</h1> 
    <p>We'd like to be your bank.
How can we make that happen?</p> 
  </div>
  <p>Thanks for visiting us! Now that you're here, take a tour around our site. We believe you’ll find great ideas that will make your banking not just easier, but more rewarding, more efficient, and more, 
  well personal. This could be the beginning of a beautiful friendship.</p> 

  <div align = "center">
         <div style = "width:300px; border: solid 1px #333333; " align = "left">
            <div style = "background-color:#333333; color:#FFFFFF; padding:3px;"><b>Login</b></div>
				
            <div style = "margin:30px">
               
               <form action = "" method = "post">
                  <label>UserName  :</label><input type = "text" name = "username" class = "box"/><br /><br />
                  <label>Password  :</label><input type = "password" name = "password" class = "box" /><br/><br />
                  <input type = "submit" value = " Submit "/><br />
               </form>
               
               <div style = "font-size:11px; color:#cc0000; margin-top:10px"><?php 
			   echo $error; ?></div>
					
            </div>
				
         </div>
			
      </div>
  
</div>


</body>