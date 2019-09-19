window.onload = function() {
    document.getElementById("myButton").addEventListener("click", function() {
        let num3 = document.getElementById("inNumber").value;
        let word3 = document.getElementById("inWord").value;
        catDogBird(num3,word3)
    });
 }
 function catDogBird(num3, word3){
    var numArray= num3.split(",");
    var strArray = word3.split(",");
    console.log (numArray);
    console.log(strArray);
    console.log("num1 is " + numArray[0] +", word1 is " + strArray[0]);
    console.log("num2 is " + numArray[1] +", word2 is " + strArray[1]);
    console.log("num3 is " + numArray[2] +", word3 is " + strArray[2]);
    var i;
    var j = false;
    var k = "";
    for(i=1;i<106;i++){
        if (j === false){
            k = i + " ";
            //console.log(i);
        }
        if(i%numArray[0]===0){
            k += strArray[0] + " ";
            //console.log(strArray[0]);
            j = true;
        }
        if(i%numArray[1]===0){
            k += strArray[1] + " ";
            //console.log(strArray[1]);
            j = true;
        }
        if(i%numArray[2]===0){
            k += strArray[2] + " ";
            //console.log(strArray[2]);
            j = true;
        }
        console.log(k);
        k = "";
        j = false;
    }
 }