<?php
$DB = "db_Students";

$SI = $_POST['SI'];
$ST = $_POST['ST'];

$conn = mysqli_connect('localhost', 'root', '', $DB);

if (!$conn) {
  die("Connection failed: " . mysqli_connect_error());
}

$js = '{ "Student" : [ ';

$sql = 'CALL DisplayIDName('. $SI .',
						"'. $ST .'")'; 

$result = mysqli_query($conn, $sql);

while($row = mysqli_fetch_assoc($result)){
	$js .= '{	"StudentID" : "' . $row['StudentID'] .
			'", "StudentName" : "' . $row['StudentName'] .
			'", "YearLevel" : "' . $row['YearLevel'] .'"}, ';
}

$js .= substr($js, -1) . '] }';
echo $js;
mysqli_close($conn)	
?>