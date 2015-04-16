<?php
/**
* Created by JetBrains PhpStorm.
* User: Cristiaan
* Date: 11-3-13
* Time: 15:58
* To change this template use File | Settings | File Templates.
*/
require_once("config.php");
$mysqli = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_DATABASE);

// Allow from any origin
    if (isset($_SERVER['HTTP_ORIGIN'])) {
        header("Access-Control-Allow-Origin: {$_SERVER['HTTP_ORIGIN']}");
        header('Access-Control-Allow-Credentials: true');
        header('Access-Control-Max-Age: 86400');    // cache for 1 day
    }
    // Access-Control headers are received during OPTIONS requests
    if ($_SERVER['REQUEST_METHOD'] == 'OPTIONS') {

        if (isset($_SERVER['HTTP_ACCESS_CONTROL_REQUEST_METHOD']))
            header("Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS");         

        if (isset($_SERVER['HTTP_ACCESS_CONTROL_REQUEST_HEADERS']))
            header("Access-Control-Allow-Headers: {$_SERVER['HTTP_ACCESS_CONTROL_REQUEST_HEADERS']}");

    }

if ($mysqli->connect_errno) {
    printf("DB connect failed: %s\n", $mysqli->connect_error);
    exit;
}
$query = "SELECT * FROM highscore";

$result = $mysqli->query($query);
$Weetje = array();

//loop the results from the query and add them to the players array
while ($row = $result->fetch_array(MYSQL_ASSOC)) {
    array_push($Weetje, $row);
}

//free result set
$result->close();
//close connection
$mysqli->close();

//set the header to tell the client some json is coming its way
header("Content-Type: application/json");
echo json_encode($Weetje);
exit;

?>