window.onload = function () {
    document.getElementById("POPULATE").addEventListener("click", getPeopleWithFetchAndAsync);
}
let apiUrl = "https://randomuser.me/api/?results=25";

async function getPeopleWithFetchAndAsync() {
    try {
        let response = await fetch(apiUrl, { method: "GET", headers : { "Accept": "application/json" } });
        let data = await response.json();
        //console.log(data);

        document.getElementById("AvgAge").innerText = 'Average Average: ' + avgage(data.results);
    
        updatePeople(data.results);
    } catch (error) {
        console.log(error);
    }
}

function avgage(results) {
    var i;
    result = 0;
    for (i = 0; i < results.length; i++) {
        result += results[i].dob.age;
    }
    result /= results.length;
    return result;
}

function updatePeople(results) {
    var i;
    var persons = [];
    for (i = 0; i < results.length; i++) {
        tname = (i + 1) + ': ' + firstLetterUpperCase(results[i].name.last) + ", " + firstLetterUpperCase(results[i].name.first);
        timage = results[i].picture.thumbnail;
        tbio = "Gender: " + firstLetterUpperCase(results[i].gender) +
            "\nAge: " + results[i].dob.age +
            "\nKnown alias: " + results[i].login.username +
            "\nLast know location: " + firstLetterUpperCase(results[i].location.city) + ", " + firstLetterUpperCase(results[i].location.state) + "; " + results[i].location.coordinates.latitude + ", " + results[i].location.coordinates.longitude;
        var person = { name: tname, image: timage, bio: tbio };
        persons[i] = person;
    }
    for (i = 0; i < persons.length; i++) {
        toHTML(persons[i], i);
    }
}

function firstLetterUpperCase(temp) {
    return temp.charAt(0).toUpperCase() + temp.substring(1);
}

function toHTML(person, i) {
    var div = document.createElement("div");
    var h3 = document.createElement("h3");
    var img = document.createElement("img");
    var p = document.createElement("p");
    h3.innerText = person.name;
    img.src = person.image;
    p.innerText = person.bio;
    
    if (i%3 === 1)
        div.style.cssFloat = "right";
    else if(i%3 === 2)
        div.style.cssFloat = "center";
    else
        div.style.cssFloat = "left";
    
    div.appendChild(h3);
    div.appendChild(img);
    div.appendChild(p);
    document.body.appendChild(div);
}