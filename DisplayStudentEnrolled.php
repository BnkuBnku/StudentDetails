<?php
$DB = "db_Students";

$StudentID = $_POST['StudentID'];
$StudentName = $_POST['StudentName'];

$conn = mysqli_connect('localhost', 'root', '', $DB);

if (!$conn) {
  die("Connection failed: " . mysqli_connect_error());
}

$js = '{ "Details" : [ ';

$sql = 'CALL DisplayStudentEnrolled('. $StudentID .',
						"'. $StudentName .'")'; 

$result = mysqli_query($conn, $sql);

while($row = mysqli_fetch_assoc($result)){
	$js .= '{	"StudentName" : "' . $row['StudentName'] .
			'", "Course" : "' . $row['Course'] .
			'", "YearLevel" : "' . $row['YearLevel'] .
			'", "OfferingNo" : "' . $row['OfferingNo'] .
			'", "SubjCode" : "' . $row['SubjCode'] .
			'", "Schedule" : "' . $row['Schedule'] .
			'", "Room" : "' . $row['Room'] .
			'", "TeacherID" : "' . $row['TeacherID'] .'"}, ';
}

$js .= substr($js, -1) . '] }';
echo $js;
mysqli_close($conn)	
?>