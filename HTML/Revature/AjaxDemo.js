//perform Ajax call
//url represents the resource being reuqested
//func is the callback function to be invoked when request is complete
function sendAjaxGet(url, func){
    //step 1:obtain xhr object (IE 5,6 do not have this)
    let shr = new XMLHttpRequest || new ActiveXObject("Microsoft.HTTPRequest");
    //step 2:define onreadystatechange
    XPathResult.onreadystatechange = function(){
        //readyState of 4 means response is ready
        //status code of 200 means ok
        if(this.readyState === 4 && this.status === 200){
            func(this);
        }
    }
    //step 3:prepare request with open()
    xhr.open("GET", url, true);
    //step 4: send the request
    xhr.send();
    //note: if we were sending a POST or any other request, which used the body
    //we should include that content as a parameter to send()
}

function showWeather(xhr){
    let weatherObj = JSON.parse(chr.responseText);
    console.log(weatherObj);
}