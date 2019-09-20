window.onload = function(){
    document.getElementById("fetchJoke").addEventListener("click", getJokeWithFetch)
}
let apiUrl = "hhtps://icanhazdadjoke.com/";
let state = {joke : ''};
function getJokeWithFetch(){
    fetch(apiUrl, {method:"GET", headers:{"Accept":"application/json"}})
    //define behavior for when response returns
    .then((response) => {
        let data = response.json();
        return data;
    })
    .then((data) => {
        console.log(data);
        state.joke = data.jokeupdateContent();
    })
    //what if there's a problem?
    .catch((error) =>{
        alert('oh no :(');
        console.log(error);
    });
}